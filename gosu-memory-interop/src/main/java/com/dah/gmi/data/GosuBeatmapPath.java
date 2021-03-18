package com.dah.gmi.data;

import java.util.Objects;

public class GosuBeatmapPath {
    private String full, folder, file, bg, audio;

    public GosuBeatmapPath() {
    }

    public GosuBeatmapPath(String full, String folder, String file, String bg, String audio) {
        this.full = full;
        this.folder = folder;
        this.file = file;
        this.bg = bg;
        this.audio = audio;
    }

    public String getFull() {
        return this.full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getBg() {
        return this.bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public GosuBeatmapPath full(String full) {
        setFull(full);
        return this;
    }

    public GosuBeatmapPath folder(String folder) {
        setFolder(folder);
        return this;
    }

    public GosuBeatmapPath file(String file) {
        setFile(file);
        return this;
    }

    public GosuBeatmapPath bg(String bg) {
        setBg(bg);
        return this;
    }

    public GosuBeatmapPath audio(String audio) {
        setAudio(audio);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmapPath)) {
            return false;
        }
        GosuBeatmapPath gosuBeatmapPath = (GosuBeatmapPath) o;
        return Objects.equals(full, gosuBeatmapPath.full) && Objects.equals(folder, gosuBeatmapPath.folder) && Objects.equals(file, gosuBeatmapPath.file) && Objects.equals(bg, gosuBeatmapPath.bg) && Objects.equals(audio, gosuBeatmapPath.audio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(full, folder, file, bg, audio);
    }

    @Override
    public String toString() {
        return "{" +
            " full='" + getFull() + "'" +
            ", folder='" + getFolder() + "'" +
            ", file='" + getFile() + "'" +
            ", bg='" + getBg() + "'" +
            ", audio='" + getAudio() + "'" +
            "}";
    }
}
