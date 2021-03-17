package com.dah.gmi.data;

import java.util.Objects;

public class GosuSettings {
    private boolean showInterface;
    private GosuFolders folders;

    public GosuSettings() {
    }

    public GosuSettings(boolean showInterface, GosuFolders folders) {
        this.showInterface = showInterface;
        this.folders = folders;
    }

    public boolean isShowInterface() {
        return this.showInterface;
    }

    public boolean getShowInterface() {
        return this.showInterface;
    }

    public void setShowInterface(boolean showInterface) {
        this.showInterface = showInterface;
    }

    public GosuFolders getFolders() {
        return this.folders;
    }

    public void setFolders(GosuFolders folders) {
        this.folders = folders;
    }

    public GosuSettings showInterface(boolean showInterface) {
        setShowInterface(showInterface);
        return this;
    }

    public GosuSettings folders(GosuFolders folders) {
        setFolders(folders);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuSettings)) {
            return false;
        }
        GosuSettings gosuSettings = (GosuSettings) o;
        return showInterface == gosuSettings.showInterface && Objects.equals(folders, gosuSettings.folders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showInterface, folders);
    }

    @Override
    public String toString() {
        return "{" +
            " showInterface='" + isShowInterface() + "'" +
            ", folders='" + getFolders() + "'" +
            "}";
    }
}
