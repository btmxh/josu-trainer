package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class GosuGameplayLeaderboard {
    private boolean hasLeaderboard;
    @JsonAlias("isVisible")
    private boolean isVisible;
    private GosuLeaderboardSlot ourplayer;
    private GosuLeaderboardSlot[] slots;
}
