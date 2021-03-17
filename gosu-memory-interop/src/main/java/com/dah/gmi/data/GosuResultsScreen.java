package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuResultsScreen {
    private String name;
    private long score;
    private int maxCombo;
    private GosuMods mods;
    @JsonAlias("300")
    private int h300;
    @JsonAlias("100")
    private int h100;
    @JsonAlias("50")
    private int h50;
    @JsonAlias("0")
    private int h0;
    @JsonAlias("geki")
    private int hGeki;
    @JsonAlias("katu")
    private int hKatu;

    public GosuResultsScreen() {
    }

    public GosuResultsScreen(String name, long score, int maxCombo, GosuMods mods, int h300, int h100, int h50, int h0, int hGeki, int hKatu) {
        this.name = name;
        this.score = score;
        this.maxCombo = maxCombo;
        this.mods = mods;
        this.h300 = h300;
        this.h100 = h100;
        this.h50 = h50;
        this.h0 = h0;
        this.hGeki = hGeki;
        this.hKatu = hKatu;
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

    public int getMaxCombo() {
        return this.maxCombo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }

    public GosuMods getMods() {
        return this.mods;
    }

    public void setMods(GosuMods mods) {
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

    public int getHGeki() {
        return this.hGeki;
    }

    public void setHGeki(int hGeki) {
        this.hGeki = hGeki;
    }

    public int getHKatu() {
        return this.hKatu;
    }

    public void setHKatu(int hKatu) {
        this.hKatu = hKatu;
    }

    public GosuResultsScreen name(String name) {
        setName(name);
        return this;
    }

    public GosuResultsScreen score(long score) {
        setScore(score);
        return this;
    }

    public GosuResultsScreen maxCombo(int maxCombo) {
        setMaxCombo(maxCombo);
        return this;
    }

    public GosuResultsScreen mods(GosuMods mods) {
        setMods(mods);
        return this;
    }

    public GosuResultsScreen h300(int h300) {
        setH300(h300);
        return this;
    }

    public GosuResultsScreen h100(int h100) {
        setH100(h100);
        return this;
    }

    public GosuResultsScreen h50(int h50) {
        setH50(h50);
        return this;
    }

    public GosuResultsScreen h0(int h0) {
        setH0(h0);
        return this;
    }

    public GosuResultsScreen hGeki(int hGeki) {
        setHGeki(hGeki);
        return this;
    }

    public GosuResultsScreen hKatu(int hKatu) {
        setHKatu(hKatu);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuResultsScreen)) {
            return false;
        }
        GosuResultsScreen gosuResultsScreen = (GosuResultsScreen) o;
        return Objects.equals(name, gosuResultsScreen.name) && score == gosuResultsScreen.score && maxCombo == gosuResultsScreen.maxCombo && Objects.equals(mods, gosuResultsScreen.mods) && h300 == gosuResultsScreen.h300 && h100 == gosuResultsScreen.h100 && h50 == gosuResultsScreen.h50 && h0 == gosuResultsScreen.h0 && hGeki == gosuResultsScreen.hGeki && hKatu == gosuResultsScreen.hKatu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, maxCombo, mods, h300, h100, h50, h0, hGeki, hKatu);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", score='" + getScore() + "'" +
            ", maxCombo='" + getMaxCombo() + "'" +
            ", mods='" + getMods() + "'" +
            ", h300='" + getH300() + "'" +
            ", h100='" + getH100() + "'" +
            ", h50='" + getH50() + "'" +
            ", h0='" + getH0() + "'" +
            ", hGeki='" + getHGeki() + "'" +
            ", hKatu='" + getHKatu() + "'" +
            "}";
    }
}
