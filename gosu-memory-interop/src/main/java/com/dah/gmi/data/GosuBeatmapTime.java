package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuBeatmapTime {
    private long firstObj;
    private long current;
    @Deprecated
    private long full;
    private long mp3;
}
