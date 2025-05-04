package com.yusuf.ScoreBoard;

import java.util.Objects;
import java.util.UUID;

public class Game {

    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final long startTime;


    public Game(String homeTeam, String awayTeam) {
        this.id = UUID.randomUUID().toString();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeScore;
    }

    public int getAwayTeamScore() {
        return awayScore;
    }

    public long getStartTime() {
        return startTime;
    }

    public void updateHomeTeamScore(int newScore) {
        this.homeScore = newScore;
    }

    public void updateAwayTeamScore(int newScore) {
        this.awayScore = newScore;
    }

    public int getTotalScore() {
        return getHomeTeamScore() + getAwayTeamScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var game = (Game) o;

        return Objects.equals(getId(), game.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getId());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", startTime=" + startTime +
                '}';
    }
}
