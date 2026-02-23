package com.rversantvoort.java.marsrover;

import static com.rversantvoort.java.marsrover.Direction.EAST;
import static com.rversantvoort.java.marsrover.Direction.NORTH;
import static com.rversantvoort.java.marsrover.Direction.SOUTH;
import static com.rversantvoort.java.marsrover.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayFieldTest {

  static Stream<Arguments> marsRoverMovementInput() {
    return Stream.of(Arguments.of(NORTH, 0, 5), Arguments.of(EAST, 5, 0), Arguments.of(SOUTH, 0, 0), Arguments.of(WEST, 0, 0));
  }

  @Test
  void moveMarsRoverForward() {
    MarsRover marsRover = new MarsRover(NORTH, 0, 0);
    PlayField playField = new PlayField(5, 5, marsRover);

    MarsRover movedMarsRover = playField.moveMarsRoverForward();

    assertThat(movedMarsRover.direction()).isEqualTo(NORTH);
    assertThat(movedMarsRover.x()).isZero();
    assertThat(movedMarsRover.y()).isEqualTo(1);
  }

  @ParameterizedTest(name = "Test: {index} => MarsRover cannot move off the field {0}")
  @MethodSource("marsRoverMovementInput")
  void moveMarsRoverFowardThrowsErrorWhenNewFieldIsNotInBounds(Direction direction, int x, int y) {
    MarsRover marsRover = new MarsRover(direction, x, y);
    PlayField playField = new PlayField(5, 5, marsRover);

    assertThrows(CannotMoveForwardException.class, playField::moveMarsRoverForward);
  }

  @Test
  void playFieldContainsThreeObstacles() {
    MarsRover marsRover = new MarsRover(NORTH, 0, 0);
    PlayField playField = new PlayField(5, 5, marsRover);

    assertThat(playField.getObstacles()).hasSize(3);
  }
}
