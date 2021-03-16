package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
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
}
