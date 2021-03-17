package com.dah.josutrainer.gui;

import com.dah.gmi.GosuMemoryLoader;
import com.dah.gmi.data.GosuBeatmap;
import com.dah.gmi.data.GosuMemData;
import com.dah.josutrainer.core.Beatmap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainApp extends Application {
    private long updateInterval = 2000;
    private final GosuMemoryLoader loader = new GosuMemoryLoader();
    private GosuMemData data;

    @Override
    public void start(Stage stage) throws Exception {
        File file = new File("josutrainer-config.properties");
        if(file.exists()) {
            Properties config = new Properties();
            FileInputStream fis = new FileInputStream(file);
            config.load(fis);
            fis.close();
            if(config.containsKey("ffmpeg")) {
                Beatmap.FFMPEG = config.getProperty("ffmpeg");
            }
            if(config.containsKey("gosu_memory_url")) {
                try {
                    loader.jsonURL = new URL(config.getProperty("gosu_memory_url"));
                } catch (MalformedURLException e) {
                    System.err.println("Invalid gosu_memory_url value: '" + config.getProperty("gosu_memory_url") + "'");
                }
            }
            if(config.containsKey("update_interval")) {
                try {
                    updateInterval = Long.parseLong(config.getProperty("updateInterval"));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid update_interval value: '" + config.getProperty("update_interval") + "'");
                }
            }
        }

        GosuMemoryLoader loader = new GosuMemoryLoader();
        BorderPane root = new BorderPane();
        StackPane mapMetaPane = new StackPane();
        root.setTop(mapMetaPane);

        GridPane mapStatPane = new GridPane();
        mapStatPane.setVgap(2.0);
        mapStatPane.setHgap(2.0);
        mapStatPane.setPadding(new Insets(4.0));
        root.setCenter(mapStatPane);

        ImageView mapBackground = new ImageView();
        mapBackground.setFitWidth(240);
        mapBackground.setFitHeight(120);
        mapBackground.fitWidthProperty().addListener(e -> changeViewport(mapBackground));
        mapBackground.fitHeightProperty().addListener(e -> changeViewport(mapBackground));
        mapBackground.imageProperty().addListener(e -> changeViewport(mapBackground));
        changeViewport(mapBackground);

        {
            GaussianBlur blur = new GaussianBlur(4.0);
            ColorAdjust adjust = new ColorAdjust();
            adjust.setBrightness(-0.5);
            adjust.setInput(blur);
            mapBackground.setEffect(adjust);
        }


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

        mapMetaPane.getChildren().addAll(mapBackground, mapInfoBox);

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

        Spinner<Double> bpm = doubleSpinner(0.1, Double.MAX_VALUE, 200, 10);
        Label bpmLabel = new Label("BPM");
        bpmLabel.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setFillWidth(bpmLabel, true);
        GridPane.setHalignment(bpmLabel, HPos.RIGHT);
        mapStatPane.add(bpmLabel, 1, 5);
        mapStatPane.add(bpm, 2, 5);

        ObjectProperty<Double> bpmProperty = bpm.getValueFactory().valueProperty();
        ObjectProperty<Double> speedProperty = speed.getValueFactory().valueProperty();
        new BidirectionalBinding<>(bpmProperty, speedProperty, v -> v / getCurrentMapBPM(), v -> v * getCurrentMapBPM());

        TextField diffName = new TextField("Hold my hand");

        mapStatPane.add(new Label("Difficulty Name"), 0, 6, Integer.MAX_VALUE, 1);
        mapStatPane.add(diffName, 0, 7, Integer.MAX_VALUE, 1);

        ButtonBar buttons = new ButtonBar();

        Button generateButton = new Button("Generate");
        ButtonBar.setButtonData(generateButton, ButtonBar.ButtonData.APPLY);
        Button resetButton = new Button("Reset");
        ButtonBar.setButtonData(resetButton, ButtonBar.ButtonData.NO);
        Button closeButton = new Button("Close");
        ButtonBar.setButtonData(closeButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        buttons.setButtonOrder("CNA");
        buttons.getButtons().addAll(generateButton, resetButton, closeButton);
        root.setBottom(buttons);

        resetButton.setOnAction(e -> {
            if(data == null)    return;
            GosuBeatmap map = data.getMenu().getBm();
            
            try {
                mapBackground.setImage(new Image(Files.newInputStream(Paths.get(
                    data.getSettings().getFolders().getSongs(),
                    map.getPath().getFolder(),
                    map.getPath().getBg()
                ))));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            songName.setText(map.getMetadata().getArtist() + " - " + map.getMetadata().getTitle());
            artistAndDiff.setText(String.format("[%s] - %s - %.02f*",
                    map.getMetadata().getDifficulty(),
                    map.getMetadata().getMapper(),
                    map.getStats().getFullSR()
            ));

            ar.setValue(map.getStats().getAr());
            od.setValue(map.getStats().getOd());
            hp.setValue(map.getStats().getHp());
            cs.setValue(map.getStats().getCs());
            speed.getValueFactory().setValue(1.0);
            bpm.getValueFactory().setValue(getCurrentMapBPM());
            diffName.setText(map.getMetadata().getDifficulty());
        });
        generateButton.setOnAction(e -> {
            if(data == null)    return;
            try {
                Beatmap map = new Beatmap(data);
                map.setAR(ar.getValue());
                map.setOD(od.getValue());
                map.setHP(hp.getValue());
                map.setCS(cs.getValue());
                map.speedUp(speed.getValue());
                map.save(diffName.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        stage.setTitle("josu-trainer");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/josutrainer.png")));
        Supplier<Boolean> tryToLoadGosuMemoryData = () -> {
            try {
                String lastMapIdentifier = data == null? null : getMapIdentifier(data.getMenu().getBm());
                data = loader.load();
                if(!Objects.equals(lastMapIdentifier, getMapIdentifier(data.getMenu().getBm()))) {
                    Platform.runLater(resetButton::fire);
                }
                return true;
            } catch (IOException e) {
                System.err.println("Error loading data. Retrying...");
                return false;
            }
        };
        stage.show();
        if(!tryToLoadGosuMemoryData.get()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program will still try to load data...", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading data from gosu-memory");
            alert.showAndWait();
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
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

    private String getMapIdentifier(GosuBeatmap map) {
        return map.getMetadata().getArtist() + " - " + map.getMetadata().getTitle() + " [" + map.getMetadata().getDifficulty() + "] (mapped by " + map.getMetadata().getMapper() + ")";
    }

    private double getCurrentMapBPM() {
        //TODO: returns the bpm in parentheses instead
        return data == null? 200.0 : data.getMenu().getBm().getStats().getBpm().getMax();
    }

    private void changeViewport(ImageView mapBackground) {
        if(mapBackground.getImage() == null)    return;

        double iw = mapBackground.getImage().getWidth();
        double ih = mapBackground.getImage().getHeight();

        mapBackground.setViewport(new Rectangle2D(0.0, 0.0, iw, ih));
    }

    private Slider beatmapStat(GridPane mapStatPane, String name, double initialValue, int row) {
        Label label = new Label(name);
        Slider slider = new Slider(0.0, 11.0, initialValue);
        Spinner<Double> spinner = doubleSpinner(0, 11, initialValue, 0.1);
        new BidirectionalBinding<>(spinner.getValueFactory().valueProperty(), slider.valueProperty(), v -> v, v -> Double.parseDouble(v.toString()));
        spinner.getValueFactory().setConverter(new StringConverter<Double>(){
            @Override
            public String toString(Double object) {
                return object == null? "" : String.format("%.01f", object);
            }

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }
        });
        GridPane.setHgrow(slider, Priority.ALWAYS);
        mapStatPane.addRow(row, label, slider, spinner);
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

    //From: https://stackoverflow.com/questions/60341778/bindbidirectional-with-function
    public static class BidirectionalBinding<T, U> {

        private final Property<T> source;
        private final Property<U> target;

        private final ChangeListener<? super T> sourceListener;
        private final ChangeListener<? super U> targetListener;
        private boolean changing = false;

        public BidirectionalBinding(Property<T> source, Property<U> target,
                                    Function<T, U> mapping, Function<U, T> inverseMapping) {
            this.source = source;
            this.target = target;

            target.setValue(mapping.apply(source.getValue()));

            sourceListener = (obs, oldSourceValue, newSourceValue) -> {
                if (!changing) {
                    changing = true;
                    target.setValue(mapping.apply(newSourceValue));
                }
                changing = false;
            };
            source.addListener(sourceListener);

            targetListener = (obs, oldTargetValue, newTargetValue) -> {
                if (!changing) {
                    changing = true;
                    source.setValue(inverseMapping.apply(newTargetValue));
                }
                changing = false;
            };
            target.addListener(targetListener);

        }

        public void unbind() {
            source.removeListener(sourceListener);
            target.removeListener(targetListener);
        }
    }
}
