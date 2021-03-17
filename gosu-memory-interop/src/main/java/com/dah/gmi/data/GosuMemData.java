package com.dah.gmi.data;

import java.util.Objects;

public class GosuMemData {
    private GosuSettings settings;
    private GosuMenu menu;
    private GosuGameplay gameplay;
    private GosuResultsScreen resultsScreen;
    private GosuTourney tourney;

    public GosuMemData() {
    }

    public GosuMemData(GosuSettings settings, GosuMenu menu, GosuGameplay gameplay, GosuResultsScreen resultsScreen, GosuTourney tourney) {
        this.settings = settings;
        this.menu = menu;
        this.gameplay = gameplay;
        this.resultsScreen = resultsScreen;
        this.tourney = tourney;
    }

    public GosuSettings getSettings() {
        return this.settings;
    }

    public void setSettings(GosuSettings settings) {
        this.settings = settings;
    }

    public GosuMenu getMenu() {
        return this.menu;
    }

    public void setMenu(GosuMenu menu) {
        this.menu = menu;
    }

    public GosuGameplay getGameplay() {
        return this.gameplay;
    }

    public void setGameplay(GosuGameplay gameplay) {
        this.gameplay = gameplay;
    }

    public GosuResultsScreen getResultsScreen() {
        return this.resultsScreen;
    }

    public void setResultsScreen(GosuResultsScreen resultsScreen) {
        this.resultsScreen = resultsScreen;
    }

    public GosuTourney getTourney() {
        return this.tourney;
    }

    public void setTourney(GosuTourney tourney) {
        this.tourney = tourney;
    }

    public GosuMemData settings(GosuSettings settings) {
        setSettings(settings);
        return this;
    }

    public GosuMemData menu(GosuMenu menu) {
        setMenu(menu);
        return this;
    }

    public GosuMemData gameplay(GosuGameplay gameplay) {
        setGameplay(gameplay);
        return this;
    }

    public GosuMemData resultsScreen(GosuResultsScreen resultsScreen) {
        setResultsScreen(resultsScreen);
        return this;
    }

    public GosuMemData tourney(GosuTourney tourney) {
        setTourney(tourney);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuMemData)) {
            return false;
        }
        GosuMemData gosuMemData = (GosuMemData) o;
        return Objects.equals(settings, gosuMemData.settings) && Objects.equals(menu, gosuMemData.menu) && Objects.equals(gameplay, gosuMemData.gameplay) && Objects.equals(resultsScreen, gosuMemData.resultsScreen) && Objects.equals(tourney, gosuMemData.tourney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settings, menu, gameplay, resultsScreen, tourney);
    }

    @Override
    public String toString() {
        return "{" +
            " settings='" + getSettings() + "'" +
            ", menu='" + getMenu() + "'" +
            ", gameplay='" + getGameplay() + "'" +
            ", resultsScreen='" + getResultsScreen() + "'" +
            ", tourney='" + getTourney() + "'" +
            "}";
    }
}
