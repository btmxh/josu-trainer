package com.dah.gmi.data;

import java.util.Objects;

public class GosuBeatmapMetadata {
    private String artist;
    private String title;
    private String mapper;
    private String difficulty;

    public GosuBeatmapMetadata() {
    }

    public GosuBeatmapMetadata(String artist, String title, String mapper, String difficulty) {
        this.artist = artist;
        this.title = title;
        this.mapper = mapper;
        this.difficulty = difficulty;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMapper() {
        return this.mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public GosuBeatmapMetadata artist(String artist) {
        setArtist(artist);
        return this;
    }

    public GosuBeatmapMetadata title(String title) {
        setTitle(title);
        return this;
    }

    public GosuBeatmapMetadata mapper(String mapper) {
        setMapper(mapper);
        return this;
    }

    public GosuBeatmapMetadata difficulty(String difficulty) {
        setDifficulty(difficulty);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmapMetadata)) {
            return false;
        }
        GosuBeatmapMetadata gosuBeatmapMetadata = (GosuBeatmapMetadata) o;
        return Objects.equals(artist, gosuBeatmapMetadata.artist) && Objects.equals(title, gosuBeatmapMetadata.title) && Objects.equals(mapper, gosuBeatmapMetadata.mapper) && Objects.equals(difficulty, gosuBeatmapMetadata.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title, mapper, difficulty);
    }

    @Override
    public String toString() {
        return "{" +
            " artist='" + getArtist() + "'" +
            ", title='" + getTitle() + "'" +
            ", mapper='" + getMapper() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            "}";
    }
}
