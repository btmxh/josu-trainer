package com.dah.gmi.data;

import java.util.Objects;

public class GosuBeatmap {
    private GosuBeatmapTime time;
    private long id;
    private long set;
    private String md5;
    private int rankedStatus;
    private GosuBeatmapMetadata metadata;
    private GosuBeatmapStats stats;
    private GosuBeatmapPath path;

    public GosuBeatmap() {
    }

    public GosuBeatmap(GosuBeatmapTime time, long id, long set, String md5, int rankedStatus, GosuBeatmapMetadata metadata, GosuBeatmapStats stats, GosuBeatmapPath path) {
        this.time = time;
        this.id = id;
        this.set = set;
        this.md5 = md5;
        this.rankedStatus = rankedStatus;
        this.metadata = metadata;
        this.stats = stats;
        this.path = path;
    }

    public GosuBeatmapTime getTime() {
        return this.time;
    }

    public void setTime(GosuBeatmapTime time) {
        this.time = time;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSet() {
        return this.set;
    }

    public void setSet(long set) {
        this.set = set;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getRankedStatus() {
        return this.rankedStatus;
    }

    public void setRankedStatus(int rankedStatus) {
        this.rankedStatus = rankedStatus;
    }

    public GosuBeatmapMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(GosuBeatmapMetadata metadata) {
        this.metadata = metadata;
    }

    public GosuBeatmapStats getStats() {
        return this.stats;
    }

    public void setStats(GosuBeatmapStats stats) {
        this.stats = stats;
    }

    public GosuBeatmapPath getPath() {
        return this.path;
    }

    public void setPath(GosuBeatmapPath path) {
        this.path = path;
    }

    public GosuBeatmap time(GosuBeatmapTime time) {
        setTime(time);
        return this;
    }

    public GosuBeatmap id(long id) {
        setId(id);
        return this;
    }

    public GosuBeatmap set(long set) {
        setSet(set);
        return this;
    }

    public GosuBeatmap md5(String md5) {
        setMd5(md5);
        return this;
    }

    public GosuBeatmap rankedStatus(int rankedStatus) {
        setRankedStatus(rankedStatus);
        return this;
    }

    public GosuBeatmap metadata(GosuBeatmapMetadata metadata) {
        setMetadata(metadata);
        return this;
    }

    public GosuBeatmap stats(GosuBeatmapStats stats) {
        setStats(stats);
        return this;
    }

    public GosuBeatmap path(GosuBeatmapPath path) {
        setPath(path);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuBeatmap)) {
            return false;
        }
        GosuBeatmap gosuBeatmap = (GosuBeatmap) o;
        return Objects.equals(time, gosuBeatmap.time) && id == gosuBeatmap.id && set == gosuBeatmap.set && Objects.equals(md5, gosuBeatmap.md5) && rankedStatus == gosuBeatmap.rankedStatus && Objects.equals(metadata, gosuBeatmap.metadata) && Objects.equals(stats, gosuBeatmap.stats) && Objects.equals(path, gosuBeatmap.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, id, set, md5, rankedStatus, metadata, stats, path);
    }

    @Override
    public String toString() {
        return "{" +
            " time='" + getTime() + "'" +
            ", id='" + getId() + "'" +
            ", set='" + getSet() + "'" +
            ", md5='" + getMd5() + "'" +
            ", rankedStatus='" + getRankedStatus() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", stats='" + getStats() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
