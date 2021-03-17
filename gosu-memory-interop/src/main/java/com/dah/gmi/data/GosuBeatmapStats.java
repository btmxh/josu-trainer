package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuBeatmapStats {
    @JsonAlias("AR")
    private double ar;
    @JsonAlias("CS")
    private double cs;
    @JsonAlias("OD")
    private double od;
    @JsonAlias("HP")
    private double hp;
    @JsonAlias("SR")
    private double sr;
    @JsonAlias("BPM")
    private GosuBPM bpm;
    private int maxCombo;
    private double fullSR;
    private double memoryAR, memoryCS, memoryOD, memoryHP;

    public GosuBeatmapStats() {
    }

    public GosuBeatmapStats(double ar, double cs, double od, double hp, double sr, GosuBPM bpm, int maxCombo, double fullSR, double memoryAR, double memoryCS, double memoryOD, double memoryHP) {
        this.ar = ar;
        this.cs = cs;
        this.od = od;
        this.hp = hp;
        this.sr = sr;
        this.bpm = bpm;
        this.maxCombo = maxCombo;
        this.fullSR = fullSR;
        this.memoryAR = memoryAR;
        this.memoryCS = memoryCS;
        this.memoryOD = memoryOD;
        this.memoryHP = memoryHP;
    }

    public double getAr() {
        return this.ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getCs() {
        return this.cs;
    }

    public void setCs(double cs) {
        this.cs = cs;
    }

    public double getOd() {
        return this.od;
    }

    public void setOd(double od) {
        this.od = od;
    }

    public double getHp() {
        return this.hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getSr() {
        return this.sr;
    }

    public void setSr(double sr) {
        this.sr = sr;
    }

    public GosuBPM getBpm() {
        return this.bpm;
    }

    public void setBpm(GosuBPM bpm) {
        this.bpm = bpm;
    }

    public int getMaxCombo() {
        return this.maxCombo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }

    public double getFullSR() {
        return this.fullSR;
    }

    public void setFullSR(double fullSR) {
        this.fullSR = fullSR;
    }

    public double getMemoryAR() {
        return this.memoryAR;
    }

    public void setMemoryAR(double memoryAR) {
        this.memoryAR = memoryAR;
    }

    public double getMemoryCS() {
        return this.memoryCS;
    }

    public void setMemoryCS(double memoryCS) {
        this.memoryCS = memoryCS;
    }

    public double getMemoryOD() {
        return this.memoryOD;
    }

    public void setMemoryOD(double memoryOD) {
        this.memoryOD = memoryOD;
    }

    public double getMemoryHP() {
        return this.memoryHP;
    }

    public void setMemoryHP(double memoryHP) {
        this.memoryHP = memoryHP;
    }

    public GosuBeatmapStats ar(double ar) {
        setAr(ar);
        return this;
    }

    public GosuBeatmapStats cs(double cs) {
        setCs(cs);
        return this;
    }

    public GosuBeatmapStats od(double od) {
        setOd(od);
        return this;
    }

    public GosuBeatmapStats hp(double hp) {
        setHp(hp);
        return this;
    }

    public GosuBeatmapStats sr(double sr) {
        setSr(sr);
        return this;
    }

    public GosuBeatmapStats bpm(GosuBPM bpm) {
        setBpm(bpm);
        return this;
    }

    public GosuBeatmapStats maxCombo(int maxCombo) {
        setMaxCombo(maxCombo);
        return this;
    }

    public GosuBeatmapStats fullSR(double fullSR) {
        setFullSR(fullSR);
        return this;
    }

    public GosuBeatmapStats memoryAR(double memoryAR) {
        setMemoryAR(memoryAR);
        return this;
    }

    public GosuBeatmapStats memoryCS(double memoryCS) {
        setMemoryCS(memoryCS);
        return this;
    }

    public GosuBeatmapStats memoryOD(double memoryOD) {
        setMemoryOD(memoryOD);
        return this;
    }

    public GosuBeatmapStats memoryHP(double memoryHP) {
        setMemoryHP(memoryHP);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmapStats)) {
            return false;
        }
        GosuBeatmapStats gosuBeatmapStats = (GosuBeatmapStats) o;
        return ar == gosuBeatmapStats.ar && cs == gosuBeatmapStats.cs && od == gosuBeatmapStats.od && hp == gosuBeatmapStats.hp && sr == gosuBeatmapStats.sr && Objects.equals(bpm, gosuBeatmapStats.bpm) && maxCombo == gosuBeatmapStats.maxCombo && fullSR == gosuBeatmapStats.fullSR && memoryAR == gosuBeatmapStats.memoryAR && memoryCS == gosuBeatmapStats.memoryCS && memoryOD == gosuBeatmapStats.memoryOD && memoryHP == gosuBeatmapStats.memoryHP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ar, cs, od, hp, sr, bpm, maxCombo, fullSR, memoryAR, memoryCS, memoryOD, memoryHP);
    }

    @Override
    public String toString() {
        return "{" +
            " ar='" + getAr() + "'" +
            ", cs='" + getCs() + "'" +
            ", od='" + getOd() + "'" +
            ", hp='" + getHp() + "'" +
            ", sr='" + getSr() + "'" +
            ", bpm='" + getBpm() + "'" +
            ", maxCombo='" + getMaxCombo() + "'" +
            ", fullSR='" + getFullSR() + "'" +
            ", memoryAR='" + getMemoryAR() + "'" +
            ", memoryCS='" + getMemoryCS() + "'" +
            ", memoryOD='" + getMemoryOD() + "'" +
            ", memoryHP='" + getMemoryHP() + "'" +
            "}";
    }
}
