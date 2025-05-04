package com.yusuf.ScoreBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreBoardTest {

    private ScoreBoard underTest;

    private final String HOME_TEAM = "Brazil";
    private final String AWAY_TEAM = "Argentina";

    @BeforeEach
    void setUp() {
        underTest = new ScoreBoard();
    }

    @Test
    void shouldAddNewGameSuccessfully() {
        Game actual = underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        assertThat(underTest.getSummary())
                .hasSize(1)
                .first()
                .satisfies(game -> assertThat(actual).isEqualTo(game));

    }

    @Test
    void shouldThrowIfSameTeamsAddedAgain() {
        underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        assertThatThrownBy(
                () -> underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContainingAll(String.format("A game with teams '%s' vs '%s' already exists. " +
                        "Are you sure you want to add another one?", HOME_TEAM, AWAY_TEAM));
    }

    @Test
    void shouldRemoveGameWhenFinished() {
        Game game = underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        underTest.finishGame(game.getId());

        assertThat(underTest.getSummary()).isEmpty();
    }

    @Test
    void whenFinishNotExistingGameThenThrow() {
        assertThatThrownBy(() -> underTest.finishGame("invalid-id"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Game not found: invalid-id");
    }

    @Test
    void shouldUpdateGameScoreCorrectly() {
        Game game = underTest.addNewGameToScoreBoard(HOME_TEAM, AWAY_TEAM);

        underTest.updateScore(game.getId(), 5, 2);

        assertThat(game.getHomeTeamScore()).isEqualTo(5);
        assertThat(game.getAwayTeamScore()).isEqualTo(2);
    }

    @Test
    void shouldReturnGamesSortedByTotalScore() {
        Game game1 = underTest.addNewGameToScoreBoard("Mexico", "Canada");
        Game game2 = underTest.addNewGameToScoreBoard("Spain", "Brazil");
        Game game3 = underTest.addNewGameToScoreBoard("Germany", "France");

        underTest.updateScore(game1.getId(), 0, 5);
        underTest.updateScore(game2.getId(), 10, 2);
        underTest.updateScore(game3.getId(), 2, 2);

        List<Game> summary = underTest.getSummary();

        assertThat(summary).containsExactly(game2, game1, game3);
    }

    @Test
    void shouldOrderByRecencyWhenTotalScoresAreEqual() {
        Game game1 = underTest.addNewGameToScoreBoard("Mexico", "Canada");
        Game game2 = underTest.addNewGameToScoreBoard("Spain", "Brazil");

        underTest.updateScore(game1.getId(), 7, 5);
        underTest.updateScore(game2.getId(), 10, 2);

        List<Game> summary = underTest.getSummary();

        assertThat(summary).containsExactly(game2, game1);
    }
}