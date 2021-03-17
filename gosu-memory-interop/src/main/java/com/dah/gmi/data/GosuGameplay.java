package com.dah.gmi.data;

import java.util.Objects;

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

    public GosuGameplay() {
    }

    public GosuGameplay(int gameMode, String name, long score, double accuracy, GosuGameplayCombo combo, GosuGameplayHP hp, GosuGameplayHits hits, GosuGameplayPP pp, GosuKeyOverlay keyOverlay, GosuGameplayLeaderboard leaderboard) {
        this.gameMode = gameMode;
        this.name = name;
        this.score = score;
        this.accuracy = accuracy;
        this.combo = combo;
        this.hp = hp;
        this.hits = hits;
        this.pp = pp;
        this.keyOverlay = keyOverlay;
        this.leaderboard = leaderboard;
    }

    public int getGameMode() {
        return this.gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public GosuGameplayCombo getCombo() {
        return this.combo;
    }

    public void setCombo(GosuGameplayCombo combo) {
        this.combo = combo;
    }

    public GosuGameplayHP getHp() {
        return this.hp;
    }

    public void setHp(GosuGameplayHP hp) {
        this.hp = hp;
    }

    public GosuGameplayHits getHits() {
        return this.hits;
    }

    public void setHits(GosuGameplayHits hits) {
        this.hits = hits;
    }

    public GosuGameplayPP getPp() {
        return this.pp;
    }

    public void setPp(GosuGameplayPP pp) {
        this.pp = pp;
    }

    public GosuKeyOverlay getKeyOverlay() {
        return this.keyOverlay;
    }

    public void setKeyOverlay(GosuKeyOverlay keyOverlay) {
        this.keyOverlay = keyOverlay;
    }

    public GosuGameplayLeaderboard getLeaderboard() {
        return this.leaderboard;
    }

    public void setLeaderboard(GosuGameplayLeaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public GosuGameplay gameMode(int gameMode) {
        setGameMode(gameMode);
        return this;
    }

    public GosuGameplay name(String name) {
        setName(name);
        return this;
    }

    public GosuGameplay score(long score) {
        setScore(score);
        return this;
    }

    public GosuGameplay accuracy(double accuracy) {
        setAccuracy(accuracy);
        return this;
    }

    public GosuGameplay combo(GosuGameplayCombo combo) {
        setCombo(combo);
        return this;
    }

    public GosuGameplay hp(GosuGameplayHP hp) {
        setHp(hp);
        return this;
    }

    public GosuGameplay hits(GosuGameplayHits hits) {
        setHits(hits);
        return this;
    }

    public GosuGameplay pp(GosuGameplayPP pp) {
        setPp(pp);
        return this;
    }

    public GosuGameplay keyOverlay(GosuKeyOverlay keyOverlay) {
        setKeyOverlay(keyOverlay);
        return this;
    }

    public GosuGameplay leaderboard(GosuGameplayLeaderboard leaderboard) {
        setLeaderboard(leaderboard);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuGameplay)) {
            return false;
        }
        GosuGameplay gosuGameplay = (GosuGameplay) o;
        return gameMode == gosuGameplay.gameMode && Objects.equals(name, gosuGameplay.name) && score == gosuGameplay.score && accuracy == gosuGameplay.accuracy && Objects.equals(combo, gosuGameplay.combo) && Objects.equals(hp, gosuGameplay.hp) && Objects.equals(hits, gosuGameplay.hits) && Objects.equals(pp, gosuGameplay.pp) && Objects.equals(keyOverlay, gosuGameplay.keyOverlay) && Objects.equals(leaderboard, gosuGameplay.leaderboard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameMode, name, score, accuracy, combo, hp, hits, pp, keyOverlay, leaderboard);
    }

    @Override
    public String toString() {
        return "{" +
            " gameMode='" + getGameMode() + "'" +
            ", name='" + getName() + "'" +
            ", score='" + getScore() + "'" +
            ", accuracy='" + getAccuracy() + "'" +
            ", combo='" + getCombo() + "'" +
            ", hp='" + getHp() + "'" +
            ", hits='" + getHits() + "'" +
            ", pp='" + getPp() + "'" +
            ", keyOverlay='" + getKeyOverlay() + "'" +
            ", leaderboard='" + getLeaderboard() + "'" +
            "}";
    }
}
