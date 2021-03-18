package com.dah.gmi.data;

import java.util.Objects;

public class GosuMainMenu {
    private double bassDensity;

    public GosuMainMenu() {
    }

    public GosuMainMenu(double bassDensity) {
        this.bassDensity = bassDensity;
    }

    public double getBassDensity() {
        return this.bassDensity;
    }

    public void setBassDensity(double bassDensity) {
        this.bassDensity = bassDensity;
    }

    public GosuMainMenu bassDensity(double bassDensity) {
        setBassDensity(bassDensity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuMainMenu)) {
            return false;
        }
        GosuMainMenu gosuMainMenu = (GosuMainMenu) o;
        return bassDensity == gosuMainMenu.bassDensity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bassDensity);
    }

    @Override
    public String toString() {
        return "{" +
            " bassDensity='" + getBassDensity() + "'" +
            "}";
    }
}
