package com.dah.gmi.data;

import java.util.Objects;

public class GosuKeyOverlay {
    private GosuKey k1, k2, m1, m2;

    public GosuKeyOverlay() {
    }

    public GosuKeyOverlay(GosuKey k1, GosuKey k2, GosuKey m1, GosuKey m2) {
        this.k1 = k1;
        this.k2 = k2;
        this.m1 = m1;
        this.m2 = m2;
    }

    public GosuKey getK1() {
        return this.k1;
    }

    public void setK1(GosuKey k1) {
        this.k1 = k1;
    }

    public GosuKey getK2() {
        return this.k2;
    }

    public void setK2(GosuKey k2) {
        this.k2 = k2;
    }

    public GosuKey getM1() {
        return this.m1;
    }

    public void setM1(GosuKey m1) {
        this.m1 = m1;
    }

    public GosuKey getM2() {
        return this.m2;
    }

    public void setM2(GosuKey m2) {
        this.m2 = m2;
    }

    public GosuKeyOverlay k1(GosuKey k1) {
        setK1(k1);
        return this;
    }

    public GosuKeyOverlay k2(GosuKey k2) {
        setK2(k2);
        return this;
    }

    public GosuKeyOverlay m1(GosuKey m1) {
        setM1(m1);
        return this;
    }

    public GosuKeyOverlay m2(GosuKey m2) {
        setM2(m2);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuKeyOverlay)) {
            return false;
        }
        GosuKeyOverlay gosuKeyOverlay = (GosuKeyOverlay) o;
        return Objects.equals(k1, gosuKeyOverlay.k1) && Objects.equals(k2, gosuKeyOverlay.k2) && Objects.equals(m1, gosuKeyOverlay.m1) && Objects.equals(m2, gosuKeyOverlay.m2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k1, k2, m1, m2);
    }

    @Override
    public String toString() {
        return "{" +
            " k1='" + getK1() + "'" +
            ", k2='" + getK2() + "'" +
            ", m1='" + getM1() + "'" +
            ", m2='" + getM2() + "'" +
            "}";
    }
}
