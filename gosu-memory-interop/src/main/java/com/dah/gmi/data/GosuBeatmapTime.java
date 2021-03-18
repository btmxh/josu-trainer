package com.dah.gmi.data;

import java.util.Objects;

public class GosuBeatmapTime {
    private long firstObj;
    private long current;
    @Deprecated
    private long full;
    private long mp3;

    public GosuBeatmapTime() {
    }

    public GosuBeatmapTime(long firstObj, long current, long full, long mp3) {
        this.firstObj = firstObj;
        this.current = current;
        this.full = full;
        this.mp3 = mp3;
    }

    public long getFirstObj() {
        return this.firstObj;
    }

    public void setFirstObj(long firstObj) {
        this.firstObj = firstObj;
    }

    public long getCurrent() {
        return this.current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getFull() {
        return this.full;
    }

    public void setFull(long full) {
        this.full = full;
    }

    public long getMp3() {
        return this.mp3;
    }

    public void setMp3(long mp3) {
        this.mp3 = mp3;
    }

    public GosuBeatmapTime firstObj(long firstObj) {
        setFirstObj(firstObj);
        return this;
    }

    public GosuBeatmapTime current(long current) {
        setCurrent(current);
        return this;
    }

    public GosuBeatmapTime full(long full) {
        setFull(full);
        return this;
    }

    public GosuBeatmapTime mp3(long mp3) {
        setMp3(mp3);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmapTime)) {
            return false;
        }
        GosuBeatmapTime gosuBeatmapTime = (GosuBeatmapTime) o;
        return firstObj == gosuBeatmapTime.firstObj && current == gosuBeatmapTime.current && full == gosuBeatmapTime.full && mp3 == gosuBeatmapTime.mp3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstObj, current, full, mp3);
    }

    @Override
    public String toString() {
        return "{" +
            " firstObj='" + getFirstObj() + "'" +
            ", current='" + getCurrent() + "'" +
            ", full='" + getFull() + "'" +
            ", mp3='" + getMp3() + "'" +
            "}";
    }
}
