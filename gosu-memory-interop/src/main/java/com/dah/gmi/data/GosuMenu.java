package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuMenu {
    private GosuMainMenu mainMenu;
    private int state;
    private int gameMode;
    private int isChatEnabled;
    private GosuBeatmap bm;
    private GosuMods mods;
    private GosuBeatmapPP pp;

}
