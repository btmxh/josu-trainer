package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuGameplayHits {
    @JsonAlias("300")
    private int hit300;

    @JsonAlias("geki")
    private int hitGeki;

    @JsonAlias("100")
    private int hit100;

    @JsonAlias("katu")
    private int hitKatu;

    @JsonAlias("50")
    private int hit50;

    @JsonAlias("0")
    private int hit0;

    private int sliderBreaks;
    private GosuGameplayGrade grade;

    private double unstableRate;
    private double[] hitErrorArray;

    public GosuGameplayHits() {
    }

    public GosuGameplayHits(int hit300, int hitGeki, int hit100, int hitKatu, int hit50, int hit0, int sliderBreaks, GosuGameplayGrade grade, double unstableRate, double[] hitErrorArray) {
        this.hit300 = hit300;
        this.hitGeki = hitGeki;
        this.hit100 = hit100;
        this.hitKatu = hitKatu;
        this.hit50 = hit50;
        this.hit0 = hit0;
        this.sliderBreaks = sliderBreaks;
        this.grade = grade;
        this.unstableRate = unstableRate;
        this.hitErrorArray = hitErrorArray;
    }

    public int getHit300() {
        return this.hit300;
    }

    public void setHit300(int hit300) {
        this.hit300 = hit300;
    }

    public int getHitGeki() {
        return this.hitGeki;
    }

    public void setHitGeki(int hitGeki) {
        this.hitGeki = hitGeki;
    }

    public int getHit100() {
        return this.hit100;
    }

    public void setHit100(int hit100) {
        this.hit100 = hit100;
    }

    public int getHitKatu() {
        return this.hitKatu;
    }

    public void setHitKatu(int hitKatu) {
        this.hitKatu = hitKatu;
    }

    public int getHit50() {
        return this.hit50;
    }

    public void setHit50(int hit50) {
        this.hit50 = hit50;
    }

    public int getHit0() {
        return this.hit0;
    }

    public void setHit0(int hit0) {
        this.hit0 = hit0;
    }

    public int getSliderBreaks() {
        return this.sliderBreaks;
    }

    public void setSliderBreaks(int sliderBreaks) {
        this.sliderBreaks = sliderBreaks;
    }

    public GosuGameplayGrade getGrade() {
        return this.grade;
    }

    public void setGrade(GosuGameplayGrade grade) {
        this.grade = grade;
    }

    public double getUnstableRate() {
        return this.unstableRate;
    }

    public void setUnstableRate(double unstableRate) {
        this.unstableRate = unstableRate;
    }

    public double[] getHitErrorArray() {
        return this.hitErrorArray;
    }

    public void setHitErrorArray(double[] hitErrorArray) {
        this.hitErrorArray = hitErrorArray;
    }

    public GosuGameplayHits hit300(int hit300) {
        setHit300(hit300);
        return this;
    }

    public GosuGameplayHits hitGeki(int hitGeki) {
        setHitGeki(hitGeki);
        return this;
    }

    public GosuGameplayHits hit100(int hit100) {
        setHit100(hit100);
        return this;
    }

    public GosuGameplayHits hitKatu(int hitKatu) {
        setHitKatu(hitKatu);
        return this;
    }

    public GosuGameplayHits hit50(int hit50) {
        setHit50(hit50);
        return this;
    }

    public GosuGameplayHits hit0(int hit0) {
        setHit0(hit0);
        return this;
    }

    public GosuGameplayHits sliderBreaks(int sliderBreaks) {
        setSliderBreaks(sliderBreaks);
        return this;
    }

    public GosuGameplayHits grade(GosuGameplayGrade grade) {
        setGrade(grade);
        return this;
    }

    public GosuGameplayHits unstableRate(double unstableRate) {
        setUnstableRate(unstableRate);
        return this;
    }

    public GosuGameplayHits hitErrorArray(double[] hitErrorArray) {
        setHitErrorArray(hitErrorArray);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayHits)) {
            return false;
        }
        GosuGameplayHits gosuGameplayHits = (GosuGameplayHits) o;
        return hit300 == gosuGameplayHits.hit300 && hitGeki == gosuGameplayHits.hitGeki && hit100 == gosuGameplayHits.hit100 && hitKatu == gosuGameplayHits.hitKatu && hit50 == gosuGameplayHits.hit50 && hit0 == gosuGameplayHits.hit0 && sliderBreaks == gosuGameplayHits.sliderBreaks && Objects.equals(grade, gosuGameplayHits.grade) && unstableRate == gosuGameplayHits.unstableRate && Objects.equals(hitErrorArray, gosuGameplayHits.hitErrorArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hit300, hitGeki, hit100, hitKatu, hit50, hit0, sliderBreaks, grade, unstableRate, hitErrorArray);
    }

    @Override
    public String toString() {
        return "{" +
            " hit300='" + getHit300() + "'" +
            ", hitGeki='" + getHitGeki() + "'" +
            ", hit100='" + getHit100() + "'" +
            ", hitKatu='" + getHitKatu() + "'" +
            ", hit50='" + getHit50() + "'" +
            ", hit0='" + getHit0() + "'" +
            ", sliderBreaks='" + getSliderBreaks() + "'" +
            ", grade='" + getGrade() + "'" +
            ", unstableRate='" + getUnstableRate() + "'" +
            ", hitErrorArray='" + getHitErrorArray() + "'" +
            "}";
    }
}
