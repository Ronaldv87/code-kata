package com.rversantvoort.java.marsrover;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.rversantvoort.java.marsrover.Direction.EAST;
import static com.rversantvoort.java.marsrover.Direction.NORTH;
import static com.rversantvoort.java.marsrover.Direction.SOUTH;
import static com.rversantvoort.java.marsrover.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverTest {

  static Stream<Arguments> marsRoverMovementInput() {
    return Stream.of(Arguments.of(EAST, 4, 3), Arguments.of(NORTH, 3, 4), Arguments.of(WEST, 2, 3), Arguments.of(SOUTH, 3, 2));
  }

  static Stream<Arguments> marsRoverLeftTurnInput() {
    return Stream.of(Arguments.of(NORTH, WEST), Arguments.of(WEST, SOUTH), Arguments.of(SOUTH, EAST), Arguments.of(EAST, NORTH));
  }

  static Stream<Arguments> marsRoverRightTurnInput() {
    return Stream.of(Arguments.of(NORTH, EAST), Arguments.of(EAST, SOUTH), Arguments.of(SOUTH, WEST), Arguments.of(WEST, NORTH));
  }

  @ParameterizedTest(name = "Test: {index} => MarsRover moves one position {0} and has new position {1},{2}")
  @MethodSource("marsRoverMovementInput")
  void marsRoverMovesForward(Direction direction, int x, int y) {
    MarsRover marsRover = new MarsRover(direction, 3, 3);

    MarsRover movedMarsRover = marsRover.moveForward();

    assertThat(movedMarsRover.x()).isEqualTo(x);
    assertThat(movedMarsRover.y()).isEqualTo(y);
  }

  @ParameterizedTest(name = "Test: {index} => When the marsrover that is facing {0} turns left, it faces {1}")
  @MethodSource("marsRoverLeftTurnInput")
  void marsRoverTurnsLeft(Direction currentFacingDirection, Direction newFacingDirection) {
    MarsRover marsRover = new MarsRover(currentFacingDirection, 3, 3);

    MarsRover turnedMarsRover = marsRover.turnLeft();

    assertThat(turnedMarsRover.direction()).isEqualTo(newFacingDirection);
  }

  @ParameterizedTest(name = "Test: {index} => When the marsrover that is facing {0} turns right, then faces {1}")
  @MethodSource("marsRoverRightTurnInput")
  void marsRoverTurnsRight(Direction currentFacingDirection, Direction newFacingDirection) {
    MarsRover marsRover = new MarsRover(currentFacingDirection, 3, 3);

    MarsRover turnedMarsRover = marsRover.turnRight();

    assertThat(turnedMarsRover.direction()).isEqualTo(newFacingDirection);
  }
}
