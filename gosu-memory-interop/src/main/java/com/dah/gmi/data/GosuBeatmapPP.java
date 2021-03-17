package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuBeatmapPP {
    @JsonAlias("100")
    private int pp100;

    @JsonAlias("99")
    private int pp99;

    @JsonAlias("98")
    private int pp98;

    @JsonAlias("97")
    private int pp97;

    @JsonAlias("96")
    private int pp96;

    @JsonAlias("95")
    private int pp95;

    private double[] strains;

    public GosuBeatmapPP() {
    }

    public GosuBeatmapPP(int pp100, int pp99, int pp98, int pp97, int pp96, int pp95, double[] strains) {
        this.pp100 = pp100;
        this.pp99 = pp99;
        this.pp98 = pp98;
        this.pp97 = pp97;
        this.pp96 = pp96;
        this.pp95 = pp95;
        this.strains = strains;
    }

    public int getPp100() {
        return this.pp100;
    }

    public void setPp100(int pp100) {
        this.pp100 = pp100;
    }

    public int getPp99() {
        return this.pp99;
    }

    public void setPp99(int pp99) {
        this.pp99 = pp99;
    }

    public int getPp98() {
        return this.pp98;
    }

    public void setPp98(int pp98) {
        this.pp98 = pp98;
    }

    public int getPp97() {
        return this.pp97;
    }

    public void setPp97(int pp97) {
        this.pp97 = pp97;
    }

    public int getPp96() {
        return this.pp96;
    }

    public void setPp96(int pp96) {
        this.pp96 = pp96;
    }

    public int getPp95() {
        return this.pp95;
    }

    public void setPp95(int pp95) {
        this.pp95 = pp95;
    }

    public double[] getStrains() {
        return this.strains;
    }

    public void setStrains(double[] strains) {
        this.strains = strains;
    }

    public GosuBeatmapPP pp100(int pp100) {
        setPp100(pp100);
        return this;
    }

    public GosuBeatmapPP pp99(int pp99) {
        setPp99(pp99);
        return this;
    }

    public GosuBeatmapPP pp98(int pp98) {
        setPp98(pp98);
        return this;
    }

    public GosuBeatmapPP pp97(int pp97) {
        setPp97(pp97);
        return this;
    }

    public GosuBeatmapPP pp96(int pp96) {
        setPp96(pp96);
        return this;
    }

    public GosuBeatmapPP pp95(int pp95) {
        setPp95(pp95);
        return this;
    }

    public GosuBeatmapPP strains(double[] strains) {
        setStrains(strains);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmapPP)) {
            return false;
        }
        GosuBeatmapPP gosuBeatmapPP = (GosuBeatmapPP) o;
        return pp100 == gosuBeatmapPP.pp100 && pp99 == gosuBeatmapPP.pp99 && pp98 == gosuBeatmapPP.pp98 && pp97 == gosuBeatmapPP.pp97 && pp96 == gosuBeatmapPP.pp96 && pp95 == gosuBeatmapPP.pp95 && Objects.equals(strains, gosuBeatmapPP.strains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pp100, pp99, pp98, pp97, pp96, pp95, strains);
    }

    @Override
    public String toString() {
        return "{" +
            " pp100='" + getPp100() + "'" +
            ", pp99='" + getPp99() + "'" +
            ", pp98='" + getPp98() + "'" +
            ", pp97='" + getPp97() + "'" +
            ", pp96='" + getPp96() + "'" +
            ", pp95='" + getPp95() + "'" +
            ", strains='" + getStrains() + "'" +
            "}";
    }
}
