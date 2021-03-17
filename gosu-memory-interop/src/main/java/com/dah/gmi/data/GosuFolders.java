package com.dah.gmi.data;

import java.util.Objects;

public class GosuFolders {
    private String game;
    private String skin;
    private String songs;

    public GosuFolders() {
    }

    public GosuFolders(String game, String skin, String songs) {
        this.game = game;
        this.skin = skin;
        this.songs = songs;
    }

    public String getGame() {
        return this.game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getSkin() {
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSongs() {
        return this.songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public GosuFolders game(String game) {
        setGame(game);
        return this;
    }

    public GosuFolders skin(String skin) {
        setSkin(skin);
        return this;
    }

    public GosuFolders songs(String songs) {
        setSongs(songs);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuFolders)) {
            return false;
        }
        GosuFolders gosuFolders = (GosuFolders) o;
        return Objects.equals(game, gosuFolders.game) && Objects.equals(skin, gosuFolders.skin) && Objects.equals(songs, gosuFolders.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, skin, songs);
    }

    @Override
    public String toString() {
        return "{" +
            " game='" + getGame() + "'" +
            ", skin='" + getSkin() + "'" +
            ", songs='" + getSongs() + "'" +
            "}";
    }
}
