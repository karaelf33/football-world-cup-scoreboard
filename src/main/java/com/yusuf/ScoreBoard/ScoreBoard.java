package com.yusuf.ScoreBoard;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreBoard {
    private final Set<Game> gamesInScoreBoard = new HashSet<>();

    public Game addNewGameToScoreBoard(String homeTeam, String awayTeam) {
        if (hasGameBetween(homeTeam, awayTeam)) {
            throw new IllegalStateException(
                    String.format("A game with teams '%s' vs '%s' already exists. " +
                            "Are you sure you want to add another one?", homeTeam, awayTeam)
            );
        }
        var game = new Game(homeTeam, awayTeam);
        gamesInScoreBoard.add(game);
        return game;
    }

    public boolean hasGameBetween(String homeTeam, String awayTeam) {
        return gamesInScoreBoard.stream()
                .anyMatch(game ->
                        game.getHomeTeam().equalsIgnoreCase(homeTeam) &&
                                game.getAwayTeam().equalsIgnoreCase(awayTeam)
                );
    }


    public void finishGame(String gameId) {
        Game gameForRemove = findGameById(gameId);
        gamesInScoreBoard.remove(gameForRemove);
    }

    public void updateScore(String gameId, int homeTeamScore, int awayTeamScore) {
        Game gameForUpdate = findGameById(gameId);
        gameForUpdate.updateHomeTeamScore(homeTeamScore);
        gameForUpdate.updateAwayTeamScore(awayTeamScore);
    }

    private Game findGameById(String gameId) {
        return gamesInScoreBoard.stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found: " + gameId));
    }

    public List<Game> getSummary() {
        return gamesInScoreBoard
                .stream()
                .sorted(Comparator.comparingInt(Game::getTotalScore)
                        .reversed()
                        .thenComparing(Game::getStartTime)

                ).toList();
    }

}
