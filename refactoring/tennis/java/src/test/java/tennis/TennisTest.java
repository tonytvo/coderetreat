package tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;


public class TennisTest {

    public static class PlayerScore {
        private int player1Score;
        private int player2Score;
        private String expectedScore;

        public PlayerScore(int player1Score, int player2Score, String expectedScore) {
            this.player1Score = player1Score;
            this.player2Score = player2Score;
            this.expectedScore = expectedScore;
        }

    }


    public static Collection<Object[]> getAllScores() {
        return Arrays.asList(new Object[][] {
                { 0, 0, "Love-All" },
                { 1, 1, "Fifteen-All" },
                { 2, 2, "Thirty-All"},
                { 3, 3, "Deuce"},
                { 4, 4, "Deuce"},
                
                { 1, 0, "Fifteen-Love"},
                { 0, 1, "Love-Fifteen"},
                { 2, 0, "Thirty-Love"},
                { 0, 2, "Love-Thirty"},
                { 3, 0, "Forty-Love"},
                { 0, 3, "Love-Forty"},
                { 4, 0, "Win for player1"},
                { 0, 4, "Win for player2"},
                
                { 2, 1, "Thirty-Fifteen"},
                { 1, 2, "Fifteen-Thirty"},
                { 3, 1, "Forty-Fifteen"},
                { 1, 3, "Fifteen-Forty"},
                { 4, 1, "Win for player1"},
                { 1, 4, "Win for player2"},

                { 3, 2, "Forty-Thirty"},
                { 2, 3, "Thirty-Forty"},
                { 4, 2, "Win for player1"},
                { 2, 4, "Win for player2"},
                
                { 4, 3, "Advantage player1"},
                { 3, 4, "Advantage player2"},
                { 5, 4, "Advantage player1"},
                { 4, 5, "Advantage player2"},
                { 15, 14, "Advantage player1"},
                { 14, 15, "Advantage player2"},

                { 6, 4, "Win for player1"},
                { 4, 6, "Win for player2"},
                { 16, 14, "Win for player1"},
                { 14, 16, "Win for player2"},
        });
    }

    public void checkAllScores(TennisGame game, PlayerScore playerScore) {
        int highestScore = Math.max(playerScore.player1Score, playerScore.player2Score);
        for (int i = 0; i < highestScore; i++) {
            if (i < playerScore.player1Score)
                game.wonPoint("player1");
            if (i < playerScore.player2Score)
                game.wonPoint("player2");
        }
        Assertions.assertEquals(playerScore.expectedScore, game.getScore());
    }

    @ParameterizedTest
    @MethodSource("getAllScores")
    public void checkAllScoresTennisGame1(int player1Score, int player2Score, String expectedScore) {
        TennisGame1 game = new TennisGame1("player1", "player2");
        checkAllScores(game, new PlayerScore(player1Score, player2Score, expectedScore));
    }

    @ParameterizedTest
    @MethodSource("getAllScores")
    public void checkAllScoresTennisGame2(int player1Score, int player2Score, String expectedScore) {
        TennisGame2 game = new TennisGame2("player1", "player2");
        checkAllScores(game, new PlayerScore(player1Score, player2Score, expectedScore));
    }

    @ParameterizedTest
    @MethodSource("getAllScores")
    public void checkAllScoresTennisGame3(int player1Score, int player2Score, String expectedScore) {
        TennisGame3 game = new TennisGame3("player1", "player2");
        checkAllScores(game, new PlayerScore(player1Score, player2Score, expectedScore));
    }

}