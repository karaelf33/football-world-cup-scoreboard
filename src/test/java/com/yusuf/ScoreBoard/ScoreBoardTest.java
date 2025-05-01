package com.yusuf.ScoreBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreBoardTest {

    private ScoreBoard underTest;

    private final String HOME_TEAM = "Brazil";
    private final String AWAY_TEAM = "Argentina";
    private final String GAME_ID = "Brazil VS Argentina";

    @BeforeEach
    void setUp() {
        underTest = new ScoreBoard();
    }

    @Test
    void whenAddNewGameInBoardSuccessfully() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        Game expected = new Game(HOME_TEAM, AWAY_TEAM);

        assertThat(underTest.getSummary())
                .hasSize(1)
                .first()
                .satisfies(game -> assertThat(game).isEqualTo(expected));

    }

    @Test
    void whenAddSameGameToBoard() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        assertThatThrownBy(() -> underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Game already exists: Brazil VS Argentina");
    }

    @Test
    void whenFinishGameThenShouldRemoveExistingGame() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        underTest.finishGame(GAME_ID);

        assertThat(underTest.getSummary()).isEmpty();
    }

    @Test
    void whenFinishNotExistingGameThenThrow() {
        assertThatThrownBy(() -> underTest.finishGame("invalid-id"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Game not found: invalid-id");
    }

    @Test
    void whenIncrementHomeTeamScoreThenShouldUpdateCorrectGame() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        underTest.incrementHomeTeamScore(GAME_ID);

        assertThat(underTest.getSummary())
                .first()
                .satisfies(game ->
                        assertThat(game.getHomeTeamScore()).isEqualTo(1));
    }

    @Test
    void whenIncrementAwayScoreThenShouldUpdateCorrectGame() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        underTest.incrementAwayTeamScore(GAME_ID);

        assertThat(underTest.getSummary())
                .first()
                .satisfies(game ->
                        assertThat(game.getAwayTeamScore()).isEqualTo(1));
    }

}