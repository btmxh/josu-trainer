package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourneyTeamName {
    private String left, right;

    public GosuTourneyTeamName() {
    }

    public GosuTourneyTeamName(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return this.left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return this.right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public GosuTourneyTeamName left(String left) {
        setLeft(left);
        return this;
    }

    public GosuTourneyTeamName right(String right) {
        setRight(right);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyTeamName)) {
            return false;
        }
        GosuTourneyTeamName gosuTourneyTeamName = (GosuTourneyTeamName) o;
        return Objects.equals(left, gosuTourneyTeamName.left) && Objects.equals(right, gosuTourneyTeamName.right);
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
