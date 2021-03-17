package com.dah.gmi.data;

import java.util.Objects;

public class GosuGameplayCombo {
    private int current, max;

    public GosuGameplayCombo() {
    }

    public GosuGameplayCombo(int current, int max) {
        this.current = current;
        this.max = max;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public GosuGameplayCombo current(int current) {
        setCurrent(current);
        return this;
    }

    public GosuGameplayCombo max(int max) {
        setMax(max);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayCombo)) {
            return false;
        }
        GosuGameplayCombo gosuGameplayCombo = (GosuGameplayCombo) o;
        return current == gosuGameplayCombo.current && max == gosuGameplayCombo.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, max);
    }

    @Override
    public String toString() {
        return "{" +
            " current='" + getCurrent() + "'" +
            ", max='" + getMax() + "'" +
            "}";
    }
}
