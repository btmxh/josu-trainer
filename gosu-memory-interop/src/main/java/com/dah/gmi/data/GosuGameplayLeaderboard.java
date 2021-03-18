package com.dah.gmi.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
public class GosuGameplayLeaderboard {
    private boolean hasLeaderboard;
    @JsonAlias("isVisible")
    private boolean isVisible;
    private GosuLeaderboardSlot ourplayer;
    private GosuLeaderboardSlot[] slots;

    public GosuGameplayLeaderboard() {
    }

    public GosuGameplayLeaderboard(boolean hasLeaderboard, boolean isVisible, GosuLeaderboardSlot ourplayer, GosuLeaderboardSlot[] slots) {
        this.hasLeaderboard = hasLeaderboard;
        this.isVisible = isVisible;
        this.ourplayer = ourplayer;
        this.slots = slots;
    }

    public boolean isHasLeaderboard() {
        return this.hasLeaderboard;
    }

    public boolean getHasLeaderboard() {
        return this.hasLeaderboard;
    }

    public void setHasLeaderboard(boolean hasLeaderboard) {
        this.hasLeaderboard = hasLeaderboard;
    }

    public boolean isIsVisible() {
        return this.isVisible;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public GosuLeaderboardSlot getOurplayer() {
        return this.ourplayer;
    }

    public void setOurplayer(GosuLeaderboardSlot ourplayer) {
        this.ourplayer = ourplayer;
    }

    public GosuLeaderboardSlot[] getSlots() {
        return this.slots;
    }

    public void setSlots(GosuLeaderboardSlot[] slots) {
        this.slots = slots;
    }

    public GosuGameplayLeaderboard hasLeaderboard(boolean hasLeaderboard) {
        setHasLeaderboard(hasLeaderboard);
        return this;
    }

    public GosuGameplayLeaderboard isVisible(boolean isVisible) {
        setIsVisible(isVisible);
        return this;
    }

    public GosuGameplayLeaderboard ourplayer(GosuLeaderboardSlot ourplayer) {
        setOurplayer(ourplayer);
        return this;
    }

    public GosuGameplayLeaderboard slots(GosuLeaderboardSlot[] slots) {
        setSlots(slots);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplayLeaderboard)) {
            return false;
        }
        GosuGameplayLeaderboard gosuGameplayLeaderboard = (GosuGameplayLeaderboard) o;
        return hasLeaderboard == gosuGameplayLeaderboard.hasLeaderboard && isVisible == gosuGameplayLeaderboard.isVisible && Objects.equals(ourplayer, gosuGameplayLeaderboard.ourplayer) && Objects.equals(slots, gosuGameplayLeaderboard.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasLeaderboard, isVisible, ourplayer, slots);
    }

    @Override
    public String toString() {
        return "{" +
            " hasLeaderboard='" + isHasLeaderboard() + "'" +
            ", isVisible='" + isIsVisible() + "'" +
            ", ourplayer='" + getOurplayer() + "'" +
            ", slots='" + getSlots() + "'" +
            "}";
    }
}
