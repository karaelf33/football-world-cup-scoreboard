package com.yusuf.ScoreBoard;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final Set<Game> gamesInScoreBoard = new HashSet<>();

    public void addNewGameToScoreBoard(String homeTeam, String awayTeam) {
        var newGame = new Game(homeTeam, awayTeam);
        if (gamesInScoreBoard.contains(newGame)) {
            throw new IllegalStateException("Game already exists: " + newGame.getId());
        }
        gamesInScoreBoard.add(newGame);
    }

    public void finishGame(String gameId) {
        Game gameForRemove = findGameById(gameId);
        gamesInScoreBoard.remove(gameForRemove);
    }

    public void incrementHomeTeamScore(String gameId) {
        Game gameForeUpdate = findGameById(gameId);
        gameForeUpdate.incrementHomeScore();
    }

    public void incrementAwayTeamScore(String gameId) {
        Game gameForeUpdate = findGameById(gameId);
        gameForeUpdate.incrementAwayScore();
    }


    private Game findGameById(String gameId) {
        return gamesInScoreBoard.stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found: " + gameId));
    }

    public Set<Game> getSummary() {
        return gamesInScoreBoard
                .stream()
                .sorted(Comparator.comparingInt(Game::getTotalScore)
                        .reversed()
                        .thenComparing(Game::getStartTime)

                ).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
