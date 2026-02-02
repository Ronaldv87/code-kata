package com.rversantvoort.java.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

// Red, Green, Refactor
class TennisScoreTest {

  @Test
  void shouldReturnLoveAllWhenBothPlayersHaveZeroPoints() {
    assertThat(TennisScore.score(0, 0)).isEqualTo("love-all");
  }

  @Test
  void shouldReturnFifteenLoveWhenPlayerOneHasOnePointAndPlayerTwoHasZeroPoints() {
    assertThat(TennisScore.score(1, 0)).isEqualTo("fifteen-love");
  }

  @Test
  void shouldReturnLoveFifteenWhenPlayerOneHasZeroPointAndPlayerTwoHasOnePoint() {
    assertThat(TennisScore.score(0, 1)).isEqualTo("love-fifteen");
  }

  @Test
  void shouldReturnFifteenAllWhenBothPlayersHaveOnePoint() {
    assertThat(TennisScore.score(1, 1)).isEqualTo("fifteen-all");
  }

  @Test
  void shouldReturnThirtyLoveWhenPlayerOneHasTwoPointsAndPlayerTwoHasNoPoints() {
    assertThat(TennisScore.score(2, 0)).isEqualTo("thirty-love");
  }

  @Test
  void shouldReturnThirtyFifteenWhenPlayerOneHasTwoPointsAndPlayerTwoHasOnePoint() {
    assertThat(TennisScore.score(2, 1)).isEqualTo("thirty-fifteen");
  }

  @Test
  void shouldReturnLoveThirtyWhenPlayerOneHasZeroPointsAndPlayerTwoHasOnePoint() {
    assertThat(TennisScore.score(0, 2)).isEqualTo("love-thirty");
  }

  @Test
  void shouldReturnFifteenThirtyWhenPlayerOneHasOnePointAndPlayerTwoHasTwoPoints() {
    assertThat(TennisScore.score(1, 2)).isEqualTo("fifteen-thirty");
  }

  @Test
  void shouldReturnThirtyAllWhenBothPlayersHaveTwoPoints() {
    assertThat(TennisScore.score(2, 2)).isEqualTo("thirty-all");
  }

  @Test
  void shouldReturnFortyLoveWhenPlayerOneHasThreePointsAndPlayerTwoNoPoints() {
    assertThat(TennisScore.score(3, 0)).isEqualTo("forty-love");
  }

  @Test
  void shouldReturnFortyFifteenWhenPlayerOneHasThreePointsAndPlayerTwoOnePoint() {
    assertThat(TennisScore.score(3, 1)).isEqualTo("forty-fifteen");
  }

  @Test
  void shouldReturnFortyThirtyWhenPlayerOneHasThreePointsAndPlayerTwoHasTwoPoints() {
    assertThat(TennisScore.score(3, 2)).isEqualTo("forty-thirty");
  }

  @Test
  void shouldReturnLoveFortyWhenPlayerOneHasZeroPointsAndPlayerTwoHasThreePoints() {
    assertThat(TennisScore.score(0, 3)).isEqualTo("love-forty");
  }

  @Test
  void shouldReturnFifteenFortyWhenPlayerOneHasOnePointAndPlayerTwoHasThreePoints() {
    assertThat(TennisScore.score(1, 3)).isEqualTo("fifteen-forty");
  }

  @Test
  void shouldReturnThirtyFortyWhenPlayerOneHasTwoPointsAndPlayerTwhoHasThreePoints() {
    assertThat(TennisScore.score(2, 3)).isEqualTo("thirty-forty");
  }

  @Test
  void shouldReturnDeuceWhenBothPlayersHaveThreePoints() {
    assertThat(TennisScore.score(3, 3)).isEqualTo("deuce");
  }

  @Test
  void shouldReturnWinnerPlayerOneWhenPlayerOneHasFourPointsAndPlayerTwoHasTwoPoints() {
    assertThat(TennisScore.score(4, 2)).isEqualTo("winner player one");
  }

  @Test
  void shouldReturnWinnerPlayerTwoWhenPlayerTwoHasFourPointsAndPlayerOneHasTwoPoints() {
    assertThat(TennisScore.score(2, 4)).isEqualTo("winner player two");
  }

  @Test
  void shouldReturnAdvantageForPlayerOneWhenPlayerOneHasAdvantage() {
    assertThat(TennisScore.score(4, 3)).isEqualTo("advantage player one");
  }

  @Test
  void shouldReturnAdvantageForPlayerTwoWhenPlayerTwoHasAdvantage() {
    assertThat(TennisScore.score(3, 4)).isEqualTo("advantage player two");
  }

  @Test
  void shouldReturnWinnerPlayerOneWhenPlayerOneHasFivePointsAndPlayerTwoHasThreePoints() {
    assertThat(TennisScore.score(5, 3)).isEqualTo("winner player one");
  }

  @Test
  void shouldReturnWinnerPlayerTwoWhenPlayerOneHasThreePointsAndPlayerTwoHasFivePoints() {
    assertThat(TennisScore.score(3, 5)).isEqualTo("winner player two");
  }
}
