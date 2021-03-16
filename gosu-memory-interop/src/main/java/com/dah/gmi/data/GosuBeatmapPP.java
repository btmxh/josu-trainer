package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
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
}
