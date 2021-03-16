package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
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
}
