package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourneyGameplay {
    private GosuTourneyScore score;

    public GosuTourneyGameplay() {
    }

    public GosuTourneyGameplay(GosuTourneyScore score) {
        this.score = score;
    }

    public GosuTourneyScore getScore() {
        return this.score;
    }

    public void setScore(GosuTourneyScore score) {
        this.score = score;
    }

    public GosuTourneyGameplay score(GosuTourneyScore score) {
        setScore(score);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyGameplay)) {
            return false;
        }
        GosuTourneyGameplay gosuTourneyGameplay = (GosuTourneyGameplay) o;
        return Objects.equals(score, gosuTourneyGameplay.score);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(score);
    }

    @Override
    public String toString() {
        return "{" +
            " score='" + getScore() + "'" +
            "}";
    }
}
