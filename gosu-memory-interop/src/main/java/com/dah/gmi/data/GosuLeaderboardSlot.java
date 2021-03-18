package com.dah.gmi.data;

import java.util.Objects;

public class GosuLeaderboardSlot {
    private String name;
    private long score;
    private int combo, maxCombo;
    private String mods;
    private int h300, h100, h50, h0;
    private int team;
    private int position;
    private int isPassing;

    public GosuLeaderboardSlot() {
    }

    public GosuLeaderboardSlot(String name, long score, int combo, int maxCombo, String mods, int h300, int h100, int h50, int h0, int team, int position, int isPassing) {
        this.name = name;
        this.score = score;
        this.combo = combo;
        this.maxCombo = maxCombo;
        this.mods = mods;
        this.h300 = h300;
        this.h100 = h100;
        this.h50 = h50;
        this.h0 = h0;
        this.team = team;
        this.position = position;
        this.isPassing = isPassing;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getCombo() {
        return this.combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getMaxCombo() {
        return this.maxCombo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }

    public String getMods() {
        return this.mods;
    }

    public void setMods(String mods) {
        this.mods = mods;
    }

    public int getH300() {
        return this.h300;
    }

    public void setH300(int h300) {
        this.h300 = h300;
    }

    public int getH100() {
        return this.h100;
    }

    public void setH100(int h100) {
        this.h100 = h100;
    }

    public int getH50() {
        return this.h50;
    }

    public void setH50(int h50) {
        this.h50 = h50;
    }

    public int getH0() {
        return this.h0;
    }

    public void setH0(int h0) {
        this.h0 = h0;
    }

    public int getTeam() {
        return this.team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsPassing() {
        return this.isPassing;
    }

    public void setIsPassing(int isPassing) {
        this.isPassing = isPassing;
    }

    public GosuLeaderboardSlot name(String name) {
        setName(name);
        return this;
    }

    public GosuLeaderboardSlot score(long score) {
        setScore(score);
        return this;
    }

    public GosuLeaderboardSlot combo(int combo) {
        setCombo(combo);
        return this;
    }

    public GosuLeaderboardSlot maxCombo(int maxCombo) {
        setMaxCombo(maxCombo);
        return this;
    }

    public GosuLeaderboardSlot mods(String mods) {
        setMods(mods);
        return this;
    }

    public GosuLeaderboardSlot h300(int h300) {
        setH300(h300);
        return this;
    }

    public GosuLeaderboardSlot h100(int h100) {
        setH100(h100);
        return this;
    }

    public GosuLeaderboardSlot h50(int h50) {
        setH50(h50);
        return this;
    }

    public GosuLeaderboardSlot h0(int h0) {
        setH0(h0);
        return this;
    }

    public GosuLeaderboardSlot team(int team) {
        setTeam(team);
        return this;
    }

    public GosuLeaderboardSlot position(int position) {
        setPosition(position);
        return this;
    }

    public GosuLeaderboardSlot isPassing(int isPassing) {
        setIsPassing(isPassing);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuLeaderboardSlot)) {
            return false;
        }
        GosuLeaderboardSlot gosuLeaderboardSlot = (GosuLeaderboardSlot) o;
        return Objects.equals(name, gosuLeaderboardSlot.name) && score == gosuLeaderboardSlot.score && combo == gosuLeaderboardSlot.combo && maxCombo == gosuLeaderboardSlot.maxCombo && Objects.equals(mods, gosuLeaderboardSlot.mods) && h300 == gosuLeaderboardSlot.h300 && h100 == gosuLeaderboardSlot.h100 && h50 == gosuLeaderboardSlot.h50 && h0 == gosuLeaderboardSlot.h0 && team == gosuLeaderboardSlot.team && position == gosuLeaderboardSlot.position && isPassing == gosuLeaderboardSlot.isPassing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, combo, maxCombo, mods, h300, h100, h50, h0, team, position, isPassing);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", score='" + getScore() + "'" +
            ", combo='" + getCombo() + "'" +
            ", maxCombo='" + getMaxCombo() + "'" +
            ", mods='" + getMods() + "'" +
            ", h300='" + getH300() + "'" +
            ", h100='" + getH100() + "'" +
            ", h50='" + getH50() + "'" +
            ", h0='" + getH0() + "'" +
            ", team='" + getTeam() + "'" +
            ", position='" + getPosition() + "'" +
            ", isPassing='" + getIsPassing() + "'" +
            "}";
    }
}
