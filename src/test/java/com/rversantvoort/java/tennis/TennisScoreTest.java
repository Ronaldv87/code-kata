package com.rversantvoort.java.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// Red, Green, Refactor
class TennisScoreTest {

  static Stream<Arguments> tennisScores() {
    return Stream.of(Arguments.of(0, 0, "love-all"),
                     Arguments.of(1, 0, "fifteen-love"),
                     Arguments.of(0, 1, "love-fifteen"),
                     Arguments.of(1, 1, "fifteen-all"),
                     Arguments.of(2, 0, "thirty-love"),
                     Arguments.of(2, 1, "thirty-fifteen"),
                     Arguments.of(0, 2, "love-thirty"),
                     Arguments.of(1, 2, "fifteen-thirty"),
                     Arguments.of(2, 2, "thirty-all"),
                     Arguments.of(3, 0, "forty-love"),
                     Arguments.of(3, 1, "forty-fifteen"),
                     Arguments.of(3, 2, "forty-thirty"),
                     Arguments.of(0, 3, "love-forty"),
                     Arguments.of(1, 3, "fifteen-forty"),
                     Arguments.of(2, 3, "thirty-forty"),
                     Arguments.of(3, 3, "deuce"),
                     Arguments.of(4, 2, "winner player one"),
                     Arguments.of(2, 4, "winner player two"),
                     Arguments.of(4, 3, "advantage player one"),
                     Arguments.of(3, 4, "advantage player two"),
                     Arguments.of(5, 3, "winner player one"),
                     Arguments.of(3, 5, "winner player two"));
  }

  @ParameterizedTest
  @MethodSource("tennisScores")
  void shouldReturnCorrectStance(int playerOne, int playerTwo, String expected) {
    assertThat(TennisScore.score(playerOne, playerTwo)).isEqualTo(expected);
  }
}
