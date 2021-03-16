package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
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
}
