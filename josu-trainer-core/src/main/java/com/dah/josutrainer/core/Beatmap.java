package com.dah.josutrainer.core;

import com.dah.gmi.data.GosuBeatmap;
import com.dah.gmi.data.GosuBeatmapPath;
import com.dah.gmi.data.GosuFolders;
import com.dah.gmi.data.GosuMemData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A osu! beatmap, in which content are loaded to memory and can be saved by calling {@link #save()} or {@link #save(String)}
 */
public class Beatmap {
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
     * Load beatmap data from <code>beatmapFile</code>.
     * @param beatmapFile the <code>.osu</code> file where the beatmap is located
     * @throws IOException if an I/O exception occurred
     */
    public Beatmap(Path beatmapFile) throws IOException {
        lines = Files.readAllLines(beatmapFile, StandardCharsets.UTF_8);
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
            //TODO: change diff name
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
        String value = get(section, key);
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception ex) {
            System.out.println("WARNING: Invalid double value: '" + value + "'. Using default 0 instead");
            return 0;
        }
    }

    /**
     * Get beatmap's property in <code>section</code> and for <code>key</code> as a long
     * @param section section of the property
     * @param key key of the property
     * @return the long value of the property, or null if the property doesn't exist
     */
    public long getLong(String section, String key) {
        String value = get(section, key);
        try {
            return Long.parseLong(value.trim());
        } catch (Exception ex) {
            System.out.println("WARNING: Invalid long value: '" + value + "'. Using default 0 instead");
            return 0;
        }
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
            String[] returnedValues = process.apply(separatedValues);
            if(returnedValues == null) {
                it.remove();
            } else {
                it.set(String.join(",", returnedValues));
            }
        }
    }

    //Java 8 alternative for Java 11's String.isBlank() method
    private boolean isBlank(String line) {
        return line.chars().anyMatch(c -> !Character.isWhitespace(c));
    }
}