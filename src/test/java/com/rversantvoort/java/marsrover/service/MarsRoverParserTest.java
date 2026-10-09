package com.rversantvoort.java.marsrover.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rversantvoort.java.marsrover.board.Board;
import com.rversantvoort.java.marsrover.board.Obstacle;
import com.rversantvoort.java.marsrover.exception.InvalidInputException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MarsRoverParserTest {

  MarsRoverParser parser = new MarsRoverParser();

  static Stream<Arguments> parserInputs() {
    return Stream.of(Arguments.of(Named.of("Test for invalid inputs", "x; y"), "Input can only contain numbers"),
                     Arguments.of(Named.of("Test for empty inputs", ""), "Input must contain 2 or 3 columns"),
                     Arguments.of(Named.of("Test for too many inputs", "5; 5; 5; 5"), "Input must contain 2 or 3 columns"));
  }

  @Test
  void marsRoverParserCreatesBoardCorrectly() {
    String csvInput = "5; 5";

    Board board = parser.parse(csvInput);

    assertThat(board).isNotNull();
    assertThat(board.getNumberOfHorizontalFields()).isEqualTo(5);
    assertThat(board.getNumberOfVerticalFields()).isEqualTo(5);
  }

  @Test
  void marsRoverParserCreatesBoardWithObstaclesCorrectly() {
    String csvInput = "5; 5; 1,2 3,1 3,5";
    List<Obstacle> expectedObstacles = List.of(new Obstacle(1, 2), new Obstacle(3, 1), new Obstacle(3, 5));

    Board board = parser.parse(csvInput);

    assertThat(board).isNotNull();
    assertThat(board.getNumberOfHorizontalFields()).isEqualTo(5);
    assertThat(board.getNumberOfVerticalFields()).isEqualTo(5);

    List<Obstacle> obstacles = board.getObstacles();
    assertThat(obstacles).isEqualTo(expectedObstacles);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("parserInputs")
  void marsRoverParserExceptions(String input, String expectedMessage) {

    InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parse(input));

    assertThat(exception.getMessage()).isEqualTo(expectedMessage);
  }
}
