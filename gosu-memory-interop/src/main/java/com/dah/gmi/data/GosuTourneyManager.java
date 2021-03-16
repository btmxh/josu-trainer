package com.dah.gmi.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class GosuTourneyManager {
    private int ipcState;
    @JsonAlias({"bestOF", "bestOf"})
    private int bestOf;

    private GosuTourneyTeamName teamName;
    private GosuTourneyStars stars;
    private GosuTourneyBools bools;
    private String chat;    //TODO: figure out the type of this variable
    private GosuTourneyGameplay gameplay;
}
