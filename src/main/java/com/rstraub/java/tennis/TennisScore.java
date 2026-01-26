package com.rstraub.java.tennis;

public class TennisScore {

  private TennisScore() {
  }

  public static String score(int playerOnePoints, int playerTwoPoints) {

    if (playerOnePoints == 1) {

      return "fifteen-love";
    }

    if (playerTwoPoints == 1) {
      return "love-fifteen";
    }

    return "love-all";
  }
}
