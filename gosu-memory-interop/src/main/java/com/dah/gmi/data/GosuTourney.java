package com.dah.gmi.data;

import java.util.Objects;

public class GosuTourney {
    private GosuTourneyManager manager;
    private String ipcClients;  //TODO: figure out the type of this variable

    public GosuTourney() {
    }

    public GosuTourney(GosuTourneyManager manager, String ipcClients) {
        this.manager = manager;
        this.ipcClients = ipcClients;
    }

    public GosuTourneyManager getManager() {
        return this.manager;
    }

    public void setManager(GosuTourneyManager manager) {
        this.manager = manager;
    }

    public String getIpcClients() {
        return this.ipcClients;
    }

    public void setIpcClients(String ipcClients) {
        this.ipcClients = ipcClients;
    }

    public GosuTourney manager(GosuTourneyManager manager) {
        setManager(manager);
        return this;
    }

    public GosuTourney ipcClients(String ipcClients) {
        setIpcClients(ipcClients);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourney)) {
            return false;
        }
        GosuTourney gosuTourney = (GosuTourney) o;
        return Objects.equals(manager, gosuTourney.manager) && Objects.equals(ipcClients, gosuTourney.ipcClients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, ipcClients);
    }

    @Override
    public String toString() {
        return "{" +
            " manager='" + getManager() + "'" +
            ", ipcClients='" + getIpcClients() + "'" +
            "}";
    }
}
