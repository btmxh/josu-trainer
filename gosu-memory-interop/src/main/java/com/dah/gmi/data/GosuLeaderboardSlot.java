package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuLeaderboardSlot {
    private String name;
    private long score;
    private int combo, maxCombo;
    private String mods;
    private int h300, h100, h50, h0;
    private int team;
    private int position;
    private int isPassing;
}
