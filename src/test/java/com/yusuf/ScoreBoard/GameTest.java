package com.yusuf.ScoreBoard;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private static final String HOME_TEAM = "Mexico";
    private static final String AWAY_TEAM = "Canada";
    private static final String VS = " VS ";


    @Test
    void constructorInitializesFieldsCorrectly() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        assertThat(game.getId()).isEqualTo(HOME_TEAM + VS + AWAY_TEAM);

        assertThat(game.getHomeTeam()).isEqualTo(HOME_TEAM);
        assertThat(game.getAwayTeam()).isEqualTo(AWAY_TEAM);

        assertThat(game.getAwayTeamScore()).isZero();
        assertThat(game.getHomeTeamScore()).isZero();
    }

    @Test
    void incrementHomeTeamScoreByOne() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        game.incrementHomeScore();
        assertThat(game.getHomeTeamScore()).isEqualTo(1);
        assertThat(game.getAwayTeamScore()).isZero();
    }

    @Test
    void incrementAwayTeamScoreByOne() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        game.incrementAwayScore();
        assertThat(game.getAwayTeamScore()).isEqualTo(1);
        assertThat(game.getHomeTeamScore()).isZero();
    }

    @Test
    void getTotalScoreReflectsWhenUpdatedScores() {
        var game = new Game(HOME_TEAM, AWAY_TEAM);

        assertThat(game.getTotalScore()).isZero();

        game.incrementHomeScore();
        assertThat(game.getTotalScore()).isEqualTo(1);

        game.incrementAwayScore();
        assertThat(game.getTotalScore()).isEqualTo(2);
    }
    @Test void equalsAndHashCodeWorkAsExpected() {
         var game1 = new Game("Spain", "Brazil");
         var game2 = new Game("Spain", "Brazil");
         var game3 = new Game("Brazil", "Spain");
         var game4 = new Game("Spain", "Germany");


        assertThat(game1).isEqualTo(game2);
        assertThat(game1.hashCode()).isEqualTo(game2.hashCode());

        assertThat(game1).isNotEqualTo(game3);
        assertThat(game1).isNotEqualTo(game4);
    }



}