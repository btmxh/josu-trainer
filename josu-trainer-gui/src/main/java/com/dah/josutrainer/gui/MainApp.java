package com.dah.josutrainer.gui;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.dah.gmi.GosuMemoryLoader;
import com.dah.gmi.data.GosuBeatmap;
import com.dah.gmi.data.GosuMemData;
import com.dah.josutrainer.core.Beatmap;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class MainApp extends Application {
    private long updateInterval = 2000;
    private final GosuMemoryLoader loader = new GosuMemoryLoader();
    private GosuMemData data;

    private boolean generateEmptyOsz = false;   // opening an empty osz file with the same name
                                                // as the mapset directory will trigger a reload
    private Path oszDirectory = null;           // only matters when generateEmptyOsz == true

    @Override
    public void start(Stage stage) throws Exception {
        //Load user config
        System.out.println("moshi moshi");
        File file = new File("josutrainer-config.properties");
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            Properties config = new Properties();
            System.out.println(config);
            FileInputStream fis = new FileInputStream(file);
            config.load(fis);
            fis.close();
            if (config.containsKey("ffmpeg")) {
                Beatmap.FFMPEG = config.getProperty("ffmpeg");
            }
            if (config.containsKey("gosu_memory_url")) {
                try {
                    loader.jsonURL = new URL(config.getProperty("gosu_memory_url"));
                } catch (MalformedURLException e) {
                    System.err.println("Invalid gosu_memory_url value: '"
                            + config.getProperty("gosu_memory_url") + "'");
                }
            }
            if (config.containsKey("update_interval")) {
                try {
                    updateInterval = Long.parseLong(config.getProperty("updateInterval"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid update_interval value: '"
                            + config.getProperty("update_interval") + "'");
                }
            }
            if (config.containsKey("generate_empty_osz")) {
                generateEmptyOsz = Boolean.parseBoolean(config.getProperty("generate_empty_osz"));
            }
            if(config.containsKey("osz_directory")) {
                oszDirectory = Path.of(config.getProperty("osz_directory"));
            }
            System.out.println(config.getProperty("generate_empty_osz").length());
            System.out.println(generateEmptyOsz);
            System.out.println(config);
        }
        
        System.exit(1);
        AnchorPane root = new AnchorPane();

        WrappedImageView mapBackground = new WrappedImageView();
        mapBackground.fitWidthProperty().addListener(e -> mapBackground.centerImage());
        mapBackground.fitHeightProperty().addListener(e -> mapBackground.centerImage());
        mapBackground.imageProperty().addListener(e -> mapBackground.centerImage());
        GaussianBlur blur = new GaussianBlur(8.0);
        ColorAdjust adjust = new ColorAdjust();
        adjust.setBrightness(-0.5);
        adjust.setInput(blur);
        mapBackground.setEffect(adjust);

        AnchorPane.setTopAnchor(mapBackground, 0.0);
        AnchorPane.setLeftAnchor(mapBackground, 0.0);
        AnchorPane.setRightAnchor(mapBackground, 0.0);
        AnchorPane.setBottomAnchor(mapBackground, 250.0);

        Label songName = new Label("Kano - [It's not] World's end");
        songName.setTextOverrun(OverrunStyle.ELLIPSIS);
        songName.setAlignment(Pos.CENTER);
        songName.setTextFill(Color.WHITE);
        songName.setFont(Font.font(18));
        Label artistAndDiff = new Label("[Hold my hand] - BEAYJM - 7.01*");
        songName.setTextOverrun(OverrunStyle.ELLIPSIS);
        artistAndDiff.setAlignment(Pos.CENTER);
        artistAndDiff.setTextFill(Color.WHITE);
        VBox mapInfoBox = new VBox(4.0, songName, artistAndDiff);
        mapInfoBox.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(mapInfoBox, 0.0);
        AnchorPane.setLeftAnchor(mapInfoBox, 0.0);
        AnchorPane.setRightAnchor(mapInfoBox, 0.0);
        AnchorPane.setBottomAnchor(mapInfoBox, 250.0);

        GridPane mapStatPane = new GridPane();
        mapStatPane.setVgap(2.0);
        mapStatPane.setHgap(2.0);
        mapStatPane.setPadding(new Insets(4.0));
        VBox.setVgrow(mapStatPane, Priority.ALWAYS);

        Slider ar = beatmapStat(mapStatPane, "AR", 10.0, 0);
        Slider cs = beatmapStat(mapStatPane, "CS", 3.5, 1);
        Slider od = beatmapStat(mapStatPane, "OD", 7.5, 2);
        Slider hp = beatmapStat(mapStatPane, "HP", 6.5, 3);

        Spinner<Double> speed = doubleSpinner(0.1, 20.0, 1.0, 0.1);
        Label speedLabel = new Label("Speed");
        speedLabel.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setFillWidth(speedLabel, true);
        GridPane.setHalignment(speedLabel, HPos.RIGHT);
        mapStatPane.add(speedLabel, 1, 4);
        mapStatPane.add(speed, 2, 4);
        mapStatPane.add(createSpinnerLockButton(speed), 3, 4);

        Spinner<Double> bpm = doubleSpinner(0.1, Double.MAX_VALUE, 200, 10);
        Label bpmLabel = new Label("BPM");
        bpmLabel.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setFillWidth(bpmLabel, true);
        GridPane.setHalignment(bpmLabel, HPos.RIGHT);
        mapStatPane.add(bpmLabel, 1, 5);
        mapStatPane.add(bpm, 2, 5);
        mapStatPane.add(createSpinnerLockButton(bpm), 3, 5);

        ObjectProperty<Double> bpmProperty = bpm.getValueFactory().valueProperty();
        ObjectProperty<Double> speedProperty = speed.getValueFactory().valueProperty();
        new BidirectionalBinding<>(bpmProperty, speedProperty, v -> v / getCurrentMapBPM(),
                v -> v * getCurrentMapBPM());
        bpm.disableProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue)
                speed.setDisable(false);
        });
        speed.disableProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue)
                bpm.setDisable(false);
        });

        TextField diffName = new TextField("Hold my hand");

        mapStatPane.add(new Label("Difficulty Name"), 0, 6, Integer.MAX_VALUE, 1);
        mapStatPane.add(diffName, 0, 7, Integer.MAX_VALUE, 1);

        AnchorPane.setBottomAnchor(mapStatPane, 24.0);
        AnchorPane.setLeftAnchor(mapStatPane, 0.0);
        AnchorPane.setRightAnchor(mapStatPane, 0.0);

        ButtonBar buttons = new ButtonBar();

        Button generateButton = new Button("Generate");
        ButtonBar.setButtonData(generateButton, ButtonBar.ButtonData.APPLY);
        Button resetButton = new Button("Reset");
        ButtonBar.setButtonData(resetButton, ButtonBar.ButtonData.NO);
        Button closeButton = new Button("Close");
        ButtonBar.setButtonData(closeButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        buttons.setButtonOrder("CNA");
        buttons.getButtons().addAll(generateButton, resetButton, closeButton);

        AnchorPane.setBottomAnchor(buttons, 0.0);
        AnchorPane.setLeftAnchor(buttons, 0.0);
        AnchorPane.setRightAnchor(buttons, 0.0);

        root.getChildren().setAll(mapBackground, mapInfoBox, mapStatPane, buttons);
        Scene scene = new Scene(root);

        // Add css from file (reloadable in runtime, not available in builds)
        // scene.getStylesheets().add(new
        // File("src/main/resources/modena-dark.css").toURI().toURL().toExternalForm());
        // scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
        // final KeyCombination refreshCss = new KeyCodeCombination(KeyCode.R,
        // KeyCombination.CONTROL_DOWN);
        //
        // public void handle(KeyEvent e) {
        // if (refreshCss.match(e)) {
        // List<String> copy = new ArrayList<>(scene.getStylesheets());
        // scene.getStylesheets().clear();
        // scene.getStylesheets().addAll(copy);
        // e.consume();
        // }
        // }
        // });

        // Add css from resource
        scene.getStylesheets().add(getClass().getResource("/modena-dark.css").toExternalForm());

        stage.setTitle("josu-trainer");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/josutrainer.png")));

        //App logic
        resetButton.setOnAction(e -> {
            if (data == null)
                return;
            GosuBeatmap map = data.getMenu().getBm();

            try {
                Path imageFile = Paths.get(data.getSettings().getFolders().getSongs(), map.getPath().getFolder(),
                        map.getPath().getBg());
                InputStream inputStream = Files.newInputStream(imageFile);
                mapBackground.setImage(new Image(inputStream));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            songName.setText(map.getMetadata().getArtist() + " - " + map.getMetadata().getTitle());
            artistAndDiff.setText(String.format("[%s] - %s - %.02f*", map.getMetadata().getDifficulty(),
                    map.getMetadata().getMapper(), map.getStats().getFullSR()));

            if (!ar.isDisable())
                ar.setValue(map.getStats().getAr());
            if (!od.isDisable())
                od.setValue(map.getStats().getOd());
            if (!hp.isDisable())
                hp.setValue(map.getStats().getHp());
            if (!cs.isDisable())
                cs.setValue(map.getStats().getCs());

            if (speed.isDisable()) {
                bpm.getValueFactory().setValue(getCurrentMapBPM() * speed.getValue());
            } else if (bpm.isDisable()) {
                speed.getValueFactory().setValue(bpm.getValue() / getCurrentMapBPM());
            } else {
                speed.getValueFactory().setValue(1.0);
                bpm.getValueFactory().setValue(getCurrentMapBPM());
            }
            diffName.setText(map.getMetadata().getDifficulty());
        });

        generateButton.setOnAction(e -> {
            if (data == null)
                return;
            try {
                Beatmap map = new Beatmap(data);
                map.setAR(ar.getValue());
                map.setOD(od.getValue());
                map.setHP(hp.getValue());
                map.setCS(cs.getValue());
                map.speedUp(speed.getValue());
                map.addJosuTrainerTag();
                map.save(diffName.getText());
                System.out.println(generateEmptyOsz);
                if(generateEmptyOsz) {
                    // we will create a zip file with the same name as
                    // the mapset directory to force osu to reload the mapset
                    if(oszDirectory == null) {
                        oszDirectory = Path.of(data.getSettings().getFolders().getSongs())
                                .getParent().resolve("josutrainer-osz");
                    }
                    System.out.println(oszDirectory);
                    if(!Files.exists(oszDirectory)) {
                        Files.createDirectory(oszDirectory);
                    }
                    Path mapsetDir = map.getMapsetDir();
                    Path zipFile = oszDirectory.resolve(mapsetDir.getFileName() + ".osz");

                    ZipOutputStream zipos = new ZipOutputStream(
                            Files.newOutputStream(zipFile, StandardOpenOption.CREATE));
                    // we need to put something in this zip file, because
                    // osu! ignores empty files
                    String mapFilename = map.createFilename();
                    zipos.putNextEntry(new ZipEntry(mapFilename));
                    zipos.write(Files.readAllBytes(mapsetDir.resolve(mapFilename)));
                    zipos.close();
                    System.out.println(zipFile);
                    System.out.println(mapFilename);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });

        Supplier<Boolean> tryToLoadGosuMemoryData = () -> {
            try {
                String lastMapIdentifier = data == null ? null :
                        (data.getMenu() == null? null : getMapIdentifier(data.getMenu().getBm()));
                data = loader.load();
                if (data != null && data.getMenu() != null &&
                        !Objects.equals(lastMapIdentifier, getMapIdentifier(data.getMenu().getBm()))) {
                    Platform.runLater(resetButton::fire);
                }
                return true;
            } catch (IOException e) {
                System.err.println("Error loading data. Retrying...");
                return false;
            }
        };

        stage.setWidth(300);
        stage.setHeight(450);
        stage.show();

        if (!tryToLoadGosuMemoryData.get()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program will still try to load data...", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading data from gosu-memory");
            alert.showAndWait();
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tryToLoadGosuMemoryData.get();
            }
        }, 0, updateInterval);
        stage.setOnCloseRequest(e -> {
            timer.cancel();
        });
        closeButton.setOnAction(e -> {
            timer.cancel();
            stage.close();
        });
    }

    //Helper functions
    private static String getMapIdentifier(GosuBeatmap map) {
        return map.getMetadata().getArtist() + " - " + map.getMetadata().getTitle() + " ["
                + map.getMetadata().getDifficulty() + "] (mapped by " + map.getMetadata().getMapper() + ")";
    }

    private double getCurrentMapBPM() {
        // TODO: returns the bpm in parentheses instead
        return data == null ? 200.0 : data.getMenu().getBm().getStats().getBpm().getMax();
    }

    private static Slider beatmapStat(GridPane mapStatPane, String name, double initialValue, int row) {
        Label label = new Label(name);
        Slider slider = new Slider(0.0, 11.0, initialValue);
        Spinner<Double> spinner = doubleSpinner(0, 11, initialValue, 0.1);
        new BidirectionalBinding<>(spinner.getValueFactory().valueProperty(), slider.valueProperty(), v -> v,
                v -> Double.parseDouble(v.toString()));
        spinner.getValueFactory().setConverter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object == null ? "" : String.format("%.01f", object);
            }

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }
        });
        spinner.disableProperty().addListener((obs, oldValue, newValue) -> {
            slider.setDisable(newValue);
        });
        GridPane.setHgrow(slider, Priority.ALWAYS);
        mapStatPane.addRow(row, label, slider, spinner, createSpinnerLockButton(spinner));
        return slider;
    }

    private static Spinner<Double> doubleSpinner(double min, double max, double value, double step) {
        Spinner<Double> spinner = new Spinner<Double>(min, max, value, step) {
            public void increment() {
                System.out.println(getValue());
            }
        };
        spinner.setMaxWidth(64);
        spinner.setEditable(true);
        return spinner;
    }

    private static Button createSpinnerLockButton(Spinner<?> spinner) {
        Button button = new Button();
        Glyph graphic = new Glyph("FontAwesome", FontAwesome.Glyph.UNLOCK_ALT);
        button.setGraphic(graphic);
        button.setOnAction(e -> spinner.setDisable(!spinner.isDisable()));
        spinner.disableProperty().addListener((obs, oldValue, newValue) -> {
            graphic.setIcon(newValue ? FontAwesome.Glyph.LOCK : FontAwesome.Glyph.UNLOCK_ALT);
        });
        return button;
    }
}
