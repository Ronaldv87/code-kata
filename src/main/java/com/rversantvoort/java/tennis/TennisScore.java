package com.rversantvoort.java.tennis;

public class TennisScore {

  private static final String TIE = "-all";
  private static final String DEUCE = "deuce";
  private static final String ADVANTAGE = "advantage ";
  private static final String WINNER = "winner ";
  private static final String[] SCORE = {"love", "fifteen", "thirty", "forty"};
  private static final String PLAYER_ONE = "player one";
  private static final String PLAYER_TWO = "player two";

  private TennisScore() {
  }

  public static String score(int playerOnePoints, int playerTwoPoints) {
    if (playerOnePoints >= 3 && playerTwoPoints >= 3) {
      return handleDeuceSituations(playerOnePoints, playerTwoPoints);
    }

    if (playerOnePoints == playerTwoPoints) {
      return SCORE[playerOnePoints] + TIE;
    }

    if (playerOnePoints == 4 || playerTwoPoints == 4) {
      String player = playerOnePoints == 4 ? PLAYER_ONE : PLAYER_TWO;

      return WINNER + player;
    }

    return SCORE[playerOnePoints] + "-" + SCORE[playerTwoPoints];
  }

  private static String handleDeuceSituations(int playerOnePoints, int playerTwoPoints) {
    if (playerOnePoints == playerTwoPoints) {
      return DEUCE;
    }

    if (Math.abs(playerOnePoints - playerTwoPoints) == 1) {
      String player = playerOnePoints > playerTwoPoints ? PLAYER_ONE : PLAYER_TWO;
      return ADVANTAGE + player;
    }

    String player = playerOnePoints > playerTwoPoints ? PLAYER_ONE : PLAYER_TWO;
    return WINNER + player;
  }
}
