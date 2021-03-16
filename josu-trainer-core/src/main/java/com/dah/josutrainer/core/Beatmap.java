package com.dah.josutrainer.core;

import com.dah.gmi.data.GosuBeatmap;
import com.dah.gmi.data.GosuBeatmapPath;
import com.dah.gmi.data.GosuFolders;
import com.dah.gmi.data.GosuMemData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A osu! beatmap, in which content are loaded to memory and can be saved by calling {@link #save()} or {@link #save(String)}
 */
public class Beatmap {
    public static final String FFMPEG = "ffmpeg";   //TODO: make this customizable
    /**
     * <code>.osu</code> file content loaded into memory
     */
    private final List<String> lines;
    /**
     * Mapset directory of this beatmap
     */
    private final Path mapsetDir;
    /**
     * The <code>.osu</code> file of this beatmap. This is the original file that this map was generated from, and will not change when calling {@link #save()}
     */
    private final Path mapFile;
    /**
     * Current speed up rate of the beatmap (>1.0 - faster, 1.0 - normal speed, <1.0 - slower)
     */
    private double speedUpRate = 1.0;

    /**
     * Load beatmap data from <code>beatmapFile</code>.
     * @param beatmapFile the <code>.osu</code> file where the beatmap is located
     * @throws IOException if an I/O exception occurred
     */
    public Beatmap(Path beatmapFile) throws IOException {
        lines = new ArrayList<>(Files.readAllLines(beatmapFile, StandardCharsets.UTF_8));
        mapFile = beatmapFile;
        mapsetDir = beatmapFile.getParent();
    }

    /**
     * Load beatmap data from gosu-memory's folders and beatmap path.
     * @param folders gosu-memory's folder paths
     * @param beatmap gosu-memory's beatmap path
     * @throws IOException if an I/O exception occurred
     */
    public Beatmap(GosuFolders folders, GosuBeatmapPath beatmap) throws IOException {
        this(Paths.get(folders.getSongs(), beatmap.getFolder(), beatmap.getFile()));
    }

    /**
     * Load beatmap data from gosu-memory's folders and beatmap data.
     * @param folders gosu-memory's folder paths
     * @param beatmap gosu-memory's beatmap data (which contains the beatmap path)
     * @throws IOException if an I/O exception occurred
     */
    public Beatmap(GosuFolders folders, GosuBeatmap beatmap) throws IOException {
        this(folders, beatmap.getPath());
    }

    /**
     * Load current playing beatmap from gosu-memory.
     * @param data gosu-memory's data
     * @throws IOException if an I/O exception occurred
     */
    public Beatmap(GosuMemData data) throws IOException {
        this(data.getSettings().getFolders(), data.getMenu().getBm());
    }

    /**
     * Save current map, will create a new <code>.osu</code> file if not exists.
     * @throws IOException if an I/O exception occurred
     */
    public void save() throws IOException {
        if(speedUpRate != 1.0) {
            String newAudioFilename = speedUpAudio();
            if(newAudioFilename != null) {
                set("General", "AudioFilename", newAudioFilename);
            }
        }
        scaleBeatmapTiming();
        Files.write(mapFile, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }

    /**
     * Change current map's difficulty name to <code>diffName</code> and save the map. If the difficulty didn't exist, this method will create a new <code>.osu</code> file. <br>
     * If <code>diffName</code> is null, this method will not change map's difficulty name.
     * @param diffName new difficulty name, nullable
     * @throws IOException if an I/O exception occurred
     */
    public void save(String diffName) throws IOException {
        if(diffName != null) {
            set("Metadata", "Version", diffName);
        }
        save();
    }

    /**
     * Test whether a line is a section header or not (example: [General]). A section header must start with '[' and ends with ']' (after trimming all trailing whitespaces)
     * @param line line content
     * @return whether <code>line</code> is a section header or not.
     */
    private static boolean isSectionHeader(String line) {
        line = line.trim();
        return line.startsWith("[") && line.endsWith("]");
    }

    /**
     * Find a line that matches the given predicate from line number <code>from</code> to whenever <code>to</code> returns true. <br>
     * This method will return -1 if no matches found.
     * @param from index of first line in range
     * @param p predicate to match
     * @param to predicate to exit the finding process
     * @return index of the first line that matches <code>p</code>
     */
    private int findLine(int from, Predicate<String> p, Predicate<String> to) {
        for(int i = from; i < lines.size(); i++) {
            if(p.test(lines.get(i)))    return i;
            if(to.test(lines.get(i)))   break;
        }
        return -1;
    }

    /**
     * Get property line's index from <code>section</code> and <code>key</code>. <br>
     * If <code>key</code> is null, return the index of the section line instead
     * @param section section of the property (for example: General)
     * @param key key of the property (for example: AudioFilename)
     * @return index of property line, or the section line if <code>key</code> is null
     */
    private int getPropertyLineIndex(String section, String key) {
        int sectionLine = findLine(0, s -> s.trim().equals("[" + section + "]"), s -> false);
        if(sectionLine < 0 || key == null)     return -1;
        return findLine(sectionLine + 1, p -> p.startsWith(key + ":"), Beatmap::isSectionHeader);
    }

    /**
     * Set beatmap's property in <code>section</code> and for <code>key</code> to <code>value.toString()</code>
     * @param section section of the property
     * @param key key of the property
     * @param value value of the property
     */
    public void set(String section, String key, Object value) {
        int idx = getPropertyLineIndex(section, key);
        if(idx < 0) {
            lines.add(idx = getPropertyLineIndex(section, null) + 1, null);
        }
        lines.set(idx, key + ":" + value);
    }

    /**
     * Get beatmap's property in <code>section</code> and for <code>key</code>
     * @param section section of the property
     * @param key key of the property
     * @return the value of the property, or null if the property doesn't exist
     */
    public String get(String section, String key) {
        int idx = getPropertyLineIndex(section, key);
        if(idx == -1)   return null;
        String line = lines.get(getPropertyLineIndex(section, key));
        return line.substring(key.length() + 1);
    }

    /**
     * Get beatmap's property in <code>section</code> and for <code>key</code> as a double
     * @param section section of the property
     * @param key key of the property
     * @return the double value of the property, or null if the property doesn't exist
     */
    public double getDouble(String section, String key) {
        return parseDoubleSafe(get(section, key));
    }

    /**
     * Get beatmap's property in <code>section</code> and for <code>key</code> as a long
     * @param section section of the property
     * @param key key of the property
     * @return the long value of the property, or null if the property doesn't exist
     */
    public long getLong(String section, String key) {
        return parseLongSafe(get(section, key));
    }

    /**
     * Set beatmap's property in <code>section</code> and for <code>key</code> to <code>value</code>, rounded to <code>decimal</code> decimal digits
     * @param section section of the property
     * @param key key of the property
     * @param value double value of the property
     * @param decimal the number of decimal digits to round the double value
     */
    public void set(String section, String key, double value, int decimal) {
        set(section, key, String.format("%.0" + decimal + "f", value));
    }

    /**
     * Create beatmap new filename depending on the modified properties
     * @return the beatmap's new filename
     */
    private String createFilename() {
        String name = get("Metadata", "Artist") + " - " +
                get("Metadata", "Title") + " (" +
                get("Metadata", "Creator") + ") [" +
                get("Metadata", "Version") + "].osu";

        String remove = "\\/:*?\"<>|";
        for(int i = 0; i < remove.length(); i++) {
            char c = remove.charAt(i);
            name = name.replace(c, '_');
        }
        return name;
    }

    /**
     * Process comma separated values in <code>section</code>.
     * <code>process</code> function will take in the splited value array for every line to operate on it. The returned array will be joined into a line and it will replace the original line. If the returned array is null, then the original line will be simply removed.
     * @param section section to process
     * @param process the process function
     */
    private void processCSVValues(String section, Function<String[], String[]> process) {
        int idx = findLine(0, s -> s.trim().equals("[" + section + "]"), s -> false);
        ListIterator<String> it = lines.listIterator(idx + 1);
        while(it.hasNext()) {
            String line = it.next();
            if(line.startsWith("//") || isBlank(line))   continue;
            if(isSectionHeader(line))   break;
            String[] separatedValues = line.split(",");
            try {
                String[] returnedValues = process.apply(separatedValues);
                if(returnedValues == null) {
                    it.remove();
                } else {
                    it.set(String.join(",", returnedValues));
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("An IndexOutOfBoundsException occurred when processing CSV line: '" + line + "'. Ignoring that for now.");
            }
        }
    }

    //Java 8 alternative for Java 11's String.isBlank() method
    private boolean isBlank(String line) {
        return line.chars().anyMatch(c -> !Character.isWhitespace(c));
    }

    //Java 8 alternative for Java 9's InputStream.transferTo(OutputStream) method (with some modifications)
    private void transferTo(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = inputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();    //Logging ffmpeg output is not that important, we can suppress the error.
        }
    }

    /**
     * Set approach rate (AR) value
     * @param ar AR value
     */
    public void setAR(double ar) {
        set("Difficulty", "ApproachRate", ar, 1);
    }

    /**
     * Set approach rate (AR) value
     * @param cs AR value
     */
    public void setCS(double cs) {
        set("Difficulty", "CircleSize", cs, 1);
    }

    /**
     * Set overall difficulty (OD) value
     * @param od OD value
     */
    public void setOD(double od) {
        set("Difficulty", "OverallDifficulty", od, 1);
    }

    /**
     * Set HP drain rate (HP) value
     * @param hp HP value
     */
    public void setHP(double hp) {
        set("Difficulty", "HPDrainRate", hp, 1);
    }

    /**
     * Create new audio file that is the speed up/slow down version of current audio by {@link #speedUpRate} times.
     * @return the output file name (in mapset directory)
     */
    private String speedUpAudio() throws IOException {
        String oldAudio = get("General", "AudioFilename");
        if(oldAudio == null || isBlank(oldAudio))   return null;
        String newFilename = "josutrainer-audio-" + speedUpRate + ".mp3";
        Path newFile = mapsetDir.resolve(newFilename);
        double rate = speedUpRate;
        if(!Files.exists(newFile)) {
            StringBuilder atempo = new StringBuilder();
            while(rate > 2.0) {
                atempo.append("atempo=2.0,");
                rate /= 2.0;
            }
            while(rate < 0.5) {
                atempo.append("atempo=0.5,");
                rate /= 0.5;
            }
            atempo.append("atempo=").append(rate);
            String cmd =  "\"" + FFMPEG + "\" -i \"" + oldAudio + "\" -filter:a \"" + atempo + "\" -vn \"" + newFile.toAbsolutePath().toString() + "\" -y";
            System.out.println(cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            new Thread(() -> transferTo(process.getInputStream(), System.out)).start();
            new Thread(() -> transferTo(process.getErrorStream(), System.err)).start();
            new Thread(() -> {
                try {
                    int err = process.waitFor();
                    if(err != 0) {
                        System.err.println("ffmpeg error code " + err);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        return newFilename;
    }

    /**
     * Speed up this map by <code>rate</code> times. <br>
     * WARNING: This will remove all storyboard/video entries of the map
     * @param rate speed up rate of the new map (>1.0 - faster, 1.0 - same speed, <1.0 - slower)
     * @see #slowDown(double)
     */
    public void speedUp(double rate) {
        speedUpRate = Math.min(Math.max(speedUpRate * rate, 0.01), 100.0);
    }

    /**
     * Slow down this map by <code>rate</code> times. Same behavior as calling <code>speedUp(1.0 / rate)</code> <br>
     * WARNING: This will remove all storyboard/video entries of the map
     * @param rate speed up rate of the new map (>1.0 - slower, 1.0 - same speed, <1.0 - faster)
     * @see #speedUp(double)
     */
    public void slowDown(double rate) {
        speedUp(1.0 / rate);
    }

    //Helper function, should be self explanatory
    private String divideLong(String str) {
        return Long.toString((long) (parseLongSafe(str) / speedUpRate));
    }

    private String divideDouble(String str) {
        return Double.toString(parseDoubleSafe(str) / speedUpRate);
    }

    private void scaleBeatmapTiming() {
        String bookmarks = get("Editor", "Bookmarks");
        if(bookmarks != null) {
            //scale all bookmarks
            bookmarks = Arrays.stream(bookmarks.split(","))
                    .mapToLong(this::parseLongSafe)
                    .map(l -> (long) (l / speedUpRate))
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
            set("Editor", "Bookmarks", bookmarks);
        }

        processCSVValues("Events", tokens -> {
            switch(tokens[0].trim()) {
                case "Video":
                case "1": //Video (disabled)
                    return null;
                case "Break":
                case "2": //Break
                    tokens[1] = divideLong(tokens[1]);  //start time
                    tokens[2] = divideLong(tokens[2]);  //end time
            }
            return tokens;
        });

        processCSVValues("TimingPoints", tokens -> {
            tokens[0] = divideLong(tokens[0]);
            double beatLength = parseDoubleSafe(tokens[1]);
            if(beatLength > 0) {
                //beatLength can be negative when it's indicating slider velocity, we don't need to change that
                tokens[1] = divideDouble(tokens[1]);
            }

            return tokens;
        });

        processCSVValues("HitObjects", tokens -> {
            long type = parseLongSafe(tokens[3]);   //type of hit object
            tokens[2] = divideLong(tokens[2]);      //hit object's time
            if((type & 0b1000) != 0) {
                //this is a spinner
                tokens[5] = divideLong(tokens[5]);  //divide the spinner length
            }
            return tokens;
        });
    }

    private long parseLongSafe(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("WARNING: Invalid integer value '" + s + "'");
            return 0L;
        }
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("WARNING: Invalid decimal value '" + s + "'");
            return 0.0;
        }
    }
}
