package com.yusuf.ScoreBoard;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private static final String HOME_TEAM = "Mexico";
    private static final String AWAY_TEAM = "Canada";

    @Test
    void constructorInitializesFieldsCorrectly() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        assertThat(game.getId()).isNotEmpty();

        assertThat(game.getHomeTeam()).isEqualTo(HOME_TEAM);
        assertThat(game.getAwayTeam()).isEqualTo(AWAY_TEAM);

        assertThat(game.getAwayTeamScore()).isZero();
        assertThat(game.getHomeTeamScore()).isZero();
    }

    @Test
    void updateHomeTeamScore() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        game.updateHomeTeamScore(1);

        assertThat(game.getHomeTeamScore()).isEqualTo(1);
        assertThat(game.getAwayTeamScore()).isZero();
    }

    @Test
    void updateAwayTeamScore() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        game.updateAwayTeamScore(2);

        assertThat(game.getAwayTeamScore()).isEqualTo(2);
        assertThat(game.getHomeTeamScore()).isZero();
    }

    @Test
    void getTotalScoreReflectsWhenUpdatedScores() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        assertThat(game.getTotalScore()).isZero();

        game.updateHomeTeamScore(1);

        assertThat(game.getTotalScore()).isEqualTo(1);

        game.updateAwayTeamScore(1);

        assertThat(game.getTotalScore()).isEqualTo(2);
    }

    @Test
    void gamesWithDifferentIdsShouldNotBeEqual() {
         var game1 = new Game("Spain", "Brazil");
         var game2 = new Game("Spain", "Brazil");
         var game3 = new Game("Brazil", "Spain");


        assertThat(game1).isNotEqualTo(game2);
        assertThat(game1.hashCode()).isNotEqualTo(game2.hashCode());
        assertThat(game1).isNotEqualTo(game3);
    }
}