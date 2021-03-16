package com.dah.gmi.data;

import lombok.Data;

@Data
public class GosuTourney {
    private GosuTourneyManager manager;
    private String ipcClients;  //TODO: figure out the type of this variable
}
