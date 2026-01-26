package com.rversantvoort.java.tennis;

public class TennisScore {

  private TennisScore() {
  }

  private static String zeroPoints = "love";
  private static String onePoint = "fifteen";

  public static String score(int playerOnePoints, int playerTwoPoints) {

    if (playerOnePoints == 1 && playerTwoPoints == 1) {
      return onePoint + "-all";
    }

    if (playerOnePoints == 1) {
      return onePoint + "-" + zeroPoints;
    }

    if (playerTwoPoints == 1) {
      return zeroPoints + "-" + onePoint;
    }

    return zeroPoints + "-all";
  }
}
