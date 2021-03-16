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
}
