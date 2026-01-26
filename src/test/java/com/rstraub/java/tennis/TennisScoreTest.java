package com.rstraub.java.tennis;

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
}
