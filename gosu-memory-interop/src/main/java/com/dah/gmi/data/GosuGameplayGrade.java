package com.dah.gmi.data;

import java.util.Objects;

public class GosuGameplayGrade {
    private String current, maxThisPlay;

    public GosuGameplayGrade() {
    }

    public GosuGameplayGrade(String current, String maxThisPlay) {
        this.current = current;
        this.maxThisPlay = maxThisPlay;
    }

    public String getCurrent() {
        return this.current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMaxThisPlay() {
        return this.maxThisPlay;
    }

    public void setMaxThisPlay(String maxThisPlay) {
        this.maxThisPlay = maxThisPlay;
    }

    public GosuGameplayGrade current(String current) {
        setCurrent(current);
        return this;
    }

    public GosuGameplayGrade maxThisPlay(String maxThisPlay) {
        setMaxThisPlay(maxThisPlay);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayGrade)) {
            return false;
        }
        GosuGameplayGrade gosuGameplayGrade = (GosuGameplayGrade) o;
        return Objects.equals(current, gosuGameplayGrade.current) && Objects.equals(maxThisPlay, gosuGameplayGrade.maxThisPlay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, maxThisPlay);
    }

    @Override
    public String toString() {
        return "{" +
            " current='" + getCurrent() + "'" +
            ", maxThisPlay='" + getMaxThisPlay() + "'" +
            "}";
    }
}
