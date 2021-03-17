package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourneyScore {
    private long left, right;

    public GosuTourneyScore() {
    }

    public GosuTourneyScore(long left, long right) {
        this.left = left;
        this.right = right;
    }

    public long getLeft() {
        return this.left;
    }

    public void setLeft(long left) {
        this.left = left;
    }

    public long getRight() {
        return this.right;
    }

    public void setRight(long right) {
        this.right = right;
    }

    public GosuTourneyScore left(long left) {
        setLeft(left);
        return this;
    }

    public GosuTourneyScore right(long right) {
        setRight(right);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyScore)) {
            return false;
        }
        GosuTourneyScore gosuTourneyScore = (GosuTourneyScore) o;
        return left == gosuTourneyScore.left && right == gosuTourneyScore.right;
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
