package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuBeatmap {
    private GosuBeatmapTime time;
    private long id;
    private long set;
    private String md5;
    private int rankedStatus;
    private GosuBeatmapMetadata metadata;
    private GosuBeatmapStats stats;
    private GosuBeatmapPath path;
}
