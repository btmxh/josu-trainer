package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuKey {
    @JsonAlias("isPressed")
    private boolean isPressed;
    private int count;

    public GosuKey() {
    }

    public GosuKey(boolean isPressed, int count) {
        this.isPressed = isPressed;
        this.count = count;
    }

    public boolean isIsPressed() {
        return this.isPressed;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GosuKey isPressed(boolean isPressed) {
        setIsPressed(isPressed);
        return this;
    }

    public GosuKey count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuKey)) {
            return false;
        }
        GosuKey gosuKey = (GosuKey) o;
        return isPressed == gosuKey.isPressed && count == gosuKey.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPressed, count);
    }

    @Override
    public String toString() {
        return "{" +
            " isPressed='" + isIsPressed() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
