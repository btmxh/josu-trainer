package com.dah.gmi.data;

import java.util.Objects;

public class GosuGameplayHP {
    private double normal, smooth;

    public GosuGameplayHP() {
    }

    public GosuGameplayHP(double normal, double smooth) {
        this.normal = normal;
        this.smooth = smooth;
    }

    public double getNormal() {
        return this.normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getSmooth() {
        return this.smooth;
    }

    public void setSmooth(double smooth) {
        this.smooth = smooth;
    }

    public GosuGameplayHP normal(double normal) {
        setNormal(normal);
        return this;
    }

    public GosuGameplayHP smooth(double smooth) {
        setSmooth(smooth);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayHP)) {
            return false;
        }
        GosuGameplayHP gosuGameplayHP = (GosuGameplayHP) o;
        return normal == gosuGameplayHP.normal && smooth == gosuGameplayHP.smooth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(normal, smooth);
    }

    @Override
    public String toString() {
        return "{" +
            " normal='" + getNormal() + "'" +
            ", smooth='" + getSmooth() + "'" +
            "}";
    }
}
