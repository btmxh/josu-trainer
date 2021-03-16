package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuGameplay {
    private int gameMode;
    private String name;
    private long score;
    private double accuracy;
    private GosuGameplayCombo combo;
    private GosuGameplayHP hp;
    private GosuGameplayHits hits;
    private GosuGameplayPP pp;
    private GosuKeyOverlay keyOverlay;
    private GosuGameplayLeaderboard leaderboard;
}
