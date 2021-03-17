package com.dah.gmi.data;

import java.util.Objects;

public class GosuBPM {
    private double min, max;

    public GosuBPM() {
    }

    public GosuBPM(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return this.min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public GosuBPM min(double min) {
        setMin(min);
        return this;
    }

    public GosuBPM max(double max) {
        setMax(max);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBPM)) {
            return false;
        }
        GosuBPM gosuBPM = (GosuBPM) o;
        return min == gosuBPM.min && max == gosuBPM.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "{" +
            " min='" + getMin() + "'" +
            ", max='" + getMax() + "'" +
            "}";
    }
}
