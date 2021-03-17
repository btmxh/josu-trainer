package com.dah.gmi.data;

import java.util.Objects;

public class GosuGameplayPP {
    private double current;
    private double fc;
    private double maxThisPlay;

    public GosuGameplayPP() {
    }

    public GosuGameplayPP(double current, double fc, double maxThisPlay) {
        this.current = current;
        this.fc = fc;
        this.maxThisPlay = maxThisPlay;
    }

    public double getCurrent() {
        return this.current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getFc() {
        return this.fc;
    }

    public void setFc(double fc) {
        this.fc = fc;
    }

    public double getMaxThisPlay() {
        return this.maxThisPlay;
    }

    public void setMaxThisPlay(double maxThisPlay) {
        this.maxThisPlay = maxThisPlay;
    }

    public GosuGameplayPP current(double current) {
        setCurrent(current);
        return this;
    }

    public GosuGameplayPP fc(double fc) {
        setFc(fc);
        return this;
    }

    public GosuGameplayPP maxThisPlay(double maxThisPlay) {
        setMaxThisPlay(maxThisPlay);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayPP)) {
            return false;
        }
        GosuGameplayPP gosuGameplayPP = (GosuGameplayPP) o;
        return current == gosuGameplayPP.current && fc == gosuGameplayPP.fc && maxThisPlay == gosuGameplayPP.maxThisPlay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, fc, maxThisPlay);
    }

    @Override
    public String toString() {
        return "{" +
            " current='" + getCurrent() + "'" +
            ", fc='" + getFc() + "'" +
            ", maxThisPlay='" + getMaxThisPlay() + "'" +
            "}";
    }
}
