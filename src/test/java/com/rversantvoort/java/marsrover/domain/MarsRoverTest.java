package com.rversantvoort.java.marsrover.domain;

import static com.rversantvoort.java.marsrover.domain.Direction.EAST;
import static com.rversantvoort.java.marsrover.domain.Direction.NORTH;
import static com.rversantvoort.java.marsrover.domain.Direction.SOUTH;
import static com.rversantvoort.java.marsrover.domain.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  static Stream<Arguments> marsRoverNextCoordinatesInput() {
    return Stream.of(Arguments.of(NORTH, 3, 4), Arguments.of(WEST, 2, 3), Arguments.of(SOUTH, 3, 2), Arguments.of(EAST, 4, 3));
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

  @ParameterizedTest(name = "Test: {index} => When the marsrover that is facing {0} would move forward, would get the new coordintes {1}, {2}")
  @MethodSource("marsRoverNextCoordinatesInput")
  void calculateNextPosition(Direction direction, int x, int y) {
    MarsRover marsRover = new MarsRover(direction, 3, 3);

    Coordinates nextCoordinates = marsRover.nextCoordinates();

    assertThat(nextCoordinates.x()).isEqualTo(x);
    assertThat(nextCoordinates.y()).isEqualTo(y);
  }
}
