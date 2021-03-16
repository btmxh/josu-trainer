package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuMemData {
    private GosuSettings settings;
    private GosuMenu menu;
    private GosuGameplay gameplay;
    private GosuResultsScreen resultsScreen;
    private GosuTourney tourney;
}
