package com.yusuf.ScoreBoard;

import java.util.Objects;

public class Game {
    public final static String VsWithSpace = " VS ";

    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final long startTime;


    public Game(String homeTeam, String awayTeam) {
        this.id = homeTeam + VsWithSpace + awayTeam;
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

    public void incrementHomeScore() {
        this.homeScore++;
    }

    public void incrementAwayScore() {
        this.awayScore++;
    }

    public int getTotalScore() {
        return getHomeTeamScore() + getAwayTeamScore();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var game = (Game) o;

        return Objects.equals(getHomeTeam(), game.getHomeTeam()) && Objects.equals(getAwayTeam(), game.getAwayTeam());    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam());
    }
}
