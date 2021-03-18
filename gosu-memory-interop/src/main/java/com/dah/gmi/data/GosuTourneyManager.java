package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuTourneyManager {
    private int ipcState;
    @JsonAlias({"bestOF", "bestOf"})
    private int bestOf;

    private GosuTourneyTeamName teamName;
    private GosuTourneyStars stars;
    private GosuTourneyBools bools;
    private String chat;    //TODO: figure out the type of this variable
    private GosuTourneyGameplay gameplay;

    public GosuTourneyManager() {
    }

    public GosuTourneyManager(int ipcState, int bestOf, GosuTourneyTeamName teamName, GosuTourneyStars stars, GosuTourneyBools bools, String chat, GosuTourneyGameplay gameplay) {
        this.ipcState = ipcState;
        this.bestOf = bestOf;
        this.teamName = teamName;
        this.stars = stars;
        this.bools = bools;
        this.chat = chat;
        this.gameplay = gameplay;
    }

    public int getIpcState() {
        return this.ipcState;
    }

    public void setIpcState(int ipcState) {
        this.ipcState = ipcState;
    }

    public int getBestOf() {
        return this.bestOf;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public GosuTourneyTeamName getTeamName() {
        return this.teamName;
    }

    public void setTeamName(GosuTourneyTeamName teamName) {
        this.teamName = teamName;
    }

    public GosuTourneyStars getStars() {
        return this.stars;
    }

    public void setStars(GosuTourneyStars stars) {
        this.stars = stars;
    }

    public GosuTourneyBools getBools() {
        return this.bools;
    }

    public void setBools(GosuTourneyBools bools) {
        this.bools = bools;
    }

    public String getChat() {
        return this.chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public GosuTourneyGameplay getGameplay() {
        return this.gameplay;
    }

    public void setGameplay(GosuTourneyGameplay gameplay) {
        this.gameplay = gameplay;
    }

    public GosuTourneyManager ipcState(int ipcState) {
        setIpcState(ipcState);
        return this;
    }

    public GosuTourneyManager bestOf(int bestOf) {
        setBestOf(bestOf);
        return this;
    }

    public GosuTourneyManager teamName(GosuTourneyTeamName teamName) {
        setTeamName(teamName);
        return this;
    }

    public GosuTourneyManager stars(GosuTourneyStars stars) {
        setStars(stars);
        return this;
    }

    public GosuTourneyManager bools(GosuTourneyBools bools) {
        setBools(bools);
        return this;
    }

    public GosuTourneyManager chat(String chat) {
        setChat(chat);
        return this;
    }

    public GosuTourneyManager gameplay(GosuTourneyGameplay gameplay) {
        setGameplay(gameplay);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuTourneyManager)) {
            return false;
        }
        GosuTourneyManager gosuTourneyManager = (GosuTourneyManager) o;
        return ipcState == gosuTourneyManager.ipcState && bestOf == gosuTourneyManager.bestOf && Objects.equals(teamName, gosuTourneyManager.teamName) && Objects.equals(stars, gosuTourneyManager.stars) && Objects.equals(bools, gosuTourneyManager.bools) && Objects.equals(chat, gosuTourneyManager.chat) && Objects.equals(gameplay, gosuTourneyManager.gameplay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipcState, bestOf, teamName, stars, bools, chat, gameplay);
    }

    @Override
    public String toString() {
        return "{" +
            " ipcState='" + getIpcState() + "'" +
            ", bestOf='" + getBestOf() + "'" +
            ", teamName='" + getTeamName() + "'" +
            ", stars='" + getStars() + "'" +
            ", bools='" + getBools() + "'" +
            ", chat='" + getChat() + "'" +
            ", gameplay='" + getGameplay() + "'" +
            "}";
    }
}
