package com.rversantvoort.java.marsrover.service;

import static com.rversantvoort.java.marsrover.rover.Direction.EAST;
import static com.rversantvoort.java.marsrover.rover.Direction.NORTH;
import static com.rversantvoort.java.marsrover.rover.Direction.WEST;
import static com.rversantvoort.java.marsrover.service.Command.FORWARD;
import static com.rversantvoort.java.marsrover.service.Command.TURN_LEFT;
import static com.rversantvoort.java.marsrover.service.Command.TURN_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.rversantvoort.java.marsrover.board.Board;
import com.rversantvoort.java.marsrover.board.Obstacle;
import com.rversantvoort.java.marsrover.rover.MarsRover;
import com.rversantvoort.java.marsrover.service.MoveResult.Blocked;
import com.rversantvoort.java.marsrover.service.MoveResult.Moved;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MarsRoverNavigatorTest {

  static Stream<Arguments> marsRoverMovedInput() {
    return Stream.of(Arguments.of(Named.of("Test: MarsRover moves forward", FORWARD), new MarsRover(NORTH, 1, 2)),
                     Arguments.of(Named.of("Test: MarsRover turns right", TURN_RIGHT), new MarsRover(EAST, 1, 1)),
                     Arguments.of(Named.of("Test: MarsRover turns left", TURN_LEFT), new MarsRover(WEST, 1, 1)));
  }

  static Stream<Arguments> marsRoverBlockedInput() {
    return Stream.of(Arguments.of(Named.of("Test: MarsRover is blocked when an obstacle is ahead", new MarsRover(NORTH, 2, 2))),
                     Arguments.of(Named.of("Test: MarsRover is blocked when there no cell a head", new MarsRover(NORTH, 5, 5))));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("marsRoverMovedInput")
  void marsRoverHasMoved(Command command, MarsRover expectedMarsRover) {
    MarsRoverNavigator navigator = navigator();

    MoveResult result = navigator.move(command);

    switch (result) {
      case Moved m -> assertThat(m.rover()).isEqualTo(expectedMarsRover);
      case Blocked _ -> fail("Should not have moved");
    }
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("marsRoverBlockedInput")
  void marsRoverIsBlocked(MarsRover marsRover) {
    MarsRoverNavigator navigator = navigatorWithObstacleAhead(marsRover);

    MoveResult result = navigator.move(FORWARD);

    switch (result) {
      case Moved _ -> fail("Should not have moved");
      case Blocked b -> assertThat(b.rover()).isEqualTo(marsRover);
    }
  }

  private MarsRoverNavigator navigator() {
    List<Obstacle> obstacles = List.of();
    Board board = new Board(5, 5, obstacles);
    MarsRover marsRover = new MarsRover(NORTH, 1, 1);

    return new MarsRoverNavigator(marsRover, board);
  }

  private MarsRoverNavigator navigatorWithObstacleAhead(MarsRover marsRover) {
    List<Obstacle> obstacles = List.of(new Obstacle(2, 3));
    Board board = new Board(5, 5, obstacles);

    return new MarsRoverNavigator(marsRover, board);
  }

}
