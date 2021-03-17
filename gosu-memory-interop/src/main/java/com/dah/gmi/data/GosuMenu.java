package com.dah.gmi.data;

import java.util.Objects;

public class GosuMenu {
    private GosuMainMenu mainMenu;
    private int state;
    private int gameMode;
    private int isChatEnabled;
    private GosuBeatmap bm;
    private GosuMods mods;
    private GosuBeatmapPP pp;

    public GosuMenu() {
    }

    public GosuMenu(GosuMainMenu mainMenu, int state, int gameMode, int isChatEnabled, GosuBeatmap bm, GosuMods mods, GosuBeatmapPP pp) {
        this.mainMenu = mainMenu;
        this.state = state;
        this.gameMode = gameMode;
        this.isChatEnabled = isChatEnabled;
        this.bm = bm;
        this.mods = mods;
        this.pp = pp;
    }

    public GosuMainMenu getMainMenu() {
        return this.mainMenu;
    }

    public void setMainMenu(GosuMainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGameMode() {
        return this.gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getIsChatEnabled() {
        return this.isChatEnabled;
    }

    public void setIsChatEnabled(int isChatEnabled) {
        this.isChatEnabled = isChatEnabled;
    }

    public GosuBeatmap getBm() {
        return this.bm;
    }

    public void setBm(GosuBeatmap bm) {
        this.bm = bm;
    }

    public GosuMods getMods() {
        return this.mods;
    }

    public void setMods(GosuMods mods) {
        this.mods = mods;
    }

    public GosuBeatmapPP getPp() {
        return this.pp;
    }

    public void setPp(GosuBeatmapPP pp) {
        this.pp = pp;
    }

    public GosuMenu mainMenu(GosuMainMenu mainMenu) {
        setMainMenu(mainMenu);
        return this;
    }

    public GosuMenu state(int state) {
        setState(state);
        return this;
    }

    public GosuMenu gameMode(int gameMode) {
        setGameMode(gameMode);
        return this;
    }

    public GosuMenu isChatEnabled(int isChatEnabled) {
        setIsChatEnabled(isChatEnabled);
        return this;
    }

    public GosuMenu bm(GosuBeatmap bm) {
        setBm(bm);
        return this;
    }

    public GosuMenu mods(GosuMods mods) {
        setMods(mods);
        return this;
    }

    public GosuMenu pp(GosuBeatmapPP pp) {
        setPp(pp);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuMenu)) {
            return false;
        }
        GosuMenu gosuMenu = (GosuMenu) o;
        return Objects.equals(mainMenu, gosuMenu.mainMenu) && state == gosuMenu.state && gameMode == gosuMenu.gameMode && isChatEnabled == gosuMenu.isChatEnabled && Objects.equals(bm, gosuMenu.bm) && Objects.equals(mods, gosuMenu.mods) && Objects.equals(pp, gosuMenu.pp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainMenu, state, gameMode, isChatEnabled, bm, mods, pp);
    }

    @Override
    public String toString() {
        return "{" +
            " mainMenu='" + getMainMenu() + "'" +
            ", state='" + getState() + "'" +
            ", gameMode='" + getGameMode() + "'" +
            ", isChatEnabled='" + getIsChatEnabled() + "'" +
            ", bm='" + getBm() + "'" +
            ", mods='" + getMods() + "'" +
            ", pp='" + getPp() + "'" +
            "}";
    }

}
