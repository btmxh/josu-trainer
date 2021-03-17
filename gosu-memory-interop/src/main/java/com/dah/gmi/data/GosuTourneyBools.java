package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourneyBools {
    private boolean scoreVisible, starsVisible;

    public GosuTourneyBools() {
    }

    public GosuTourneyBools(boolean scoreVisible, boolean starsVisible) {
        this.scoreVisible = scoreVisible;
        this.starsVisible = starsVisible;
    }

    public boolean isScoreVisible() {
        return this.scoreVisible;
    }

    public boolean getScoreVisible() {
        return this.scoreVisible;
    }

    public void setScoreVisible(boolean scoreVisible) {
        this.scoreVisible = scoreVisible;
    }

    public boolean isStarsVisible() {
        return this.starsVisible;
    }

    public boolean getStarsVisible() {
        return this.starsVisible;
    }

    public void setStarsVisible(boolean starsVisible) {
        this.starsVisible = starsVisible;
    }

    public GosuTourneyBools scoreVisible(boolean scoreVisible) {
        setScoreVisible(scoreVisible);
        return this;
    }

    public GosuTourneyBools starsVisible(boolean starsVisible) {
        setStarsVisible(starsVisible);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyBools)) {
            return false;
        }
        GosuTourneyBools gosuTourneyBools = (GosuTourneyBools) o;
        return scoreVisible == gosuTourneyBools.scoreVisible && starsVisible == gosuTourneyBools.starsVisible;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreVisible, starsVisible);
    }

    @Override
    public String toString() {
        return "{" +
            " scoreVisible='" + isScoreVisible() + "'" +
            ", starsVisible='" + isStarsVisible() + "'" +
            "}";
    }
}
