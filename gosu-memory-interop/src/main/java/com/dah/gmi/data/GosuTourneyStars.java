package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourneyStars {
    private int left, right;

    public GosuTourneyStars() {
    }

    public GosuTourneyStars(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return this.right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public GosuTourneyStars left(int left) {
        setLeft(left);
        return this;
    }

    public GosuTourneyStars right(int right) {
        setRight(right);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyStars)) {
            return false;
        }
        GosuTourneyStars gosuTourneyStars = (GosuTourneyStars) o;
        return left == gosuTourneyStars.left && right == gosuTourneyStars.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "{" +
            " left='" + getLeft() + "'" +
            ", right='" + getRight() + "'" +
            "}";
    }
}
