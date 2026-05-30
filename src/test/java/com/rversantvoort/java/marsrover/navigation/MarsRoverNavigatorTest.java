package com.rversantvoort.java.marsrover.navigation;

import static com.rversantvoort.java.marsrover.domain.Direction.EAST;
import static com.rversantvoort.java.marsrover.domain.Direction.NORTH;
import static com.rversantvoort.java.marsrover.domain.Direction.WEST;
import static com.rversantvoort.java.marsrover.navigation.Command.FORWARD;
import static com.rversantvoort.java.marsrover.navigation.Command.TURN_LEFT;
import static com.rversantvoort.java.marsrover.navigation.Command.TURN_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import com.rversantvoort.java.marsrover.domain.Board;
import com.rversantvoort.java.marsrover.domain.MarsRover;
import com.rversantvoort.java.marsrover.domain.Obstacle;
import java.util.List;
import org.junit.jupiter.api.Test;

class MarsRoverNavigatorTest {

  @Test
  void marsRoverMovesForward() {
    MarsRoverNavigator navigator = navigator();

    MarsRover movedRover = navigator.move(FORWARD);

    assertThat(movedRover.y()).isEqualTo(2);
  }

  @Test
  void marsRoverTurnsRight() {
    MarsRoverNavigator navigator = navigator();

    MarsRover movedRover = navigator.move(TURN_RIGHT);

    assertThat(movedRover.direction()).isEqualTo(EAST);
  }

  @Test
  void marsRoverTurnsLeft() {
    MarsRoverNavigator navigator = navigator();

    MarsRover movedRover = navigator.move(TURN_LEFT);

    assertThat(movedRover.direction()).isEqualTo(WEST);
  }

  @Test
  void marsRoverDoesNotMoveWhenEncounteringObstacle() {
    MarsRover marsRover = new MarsRover(NORTH, 2, 2);
    MarsRoverNavigator navigator = navigatorWithObstacleAhead(marsRover);

    MarsRover movedRover = navigator.move(FORWARD);

    assertThat(movedRover).isEqualTo(marsRover);
  }

  @Test
  void marsRoverTurnsLeftWhenEncounteringObstacle() {
    MarsRover marsRover = new MarsRover(NORTH, 2, 2);
    MarsRoverNavigator navigator = navigatorWithObstacleAhead(marsRover);

    MarsRover movedRover = navigator.move(TURN_LEFT);

    assertThat(movedRover.direction()).isEqualTo(WEST);
  }

  @Test
  void marsRoverDoesNotMoveWhenNoCellIsAhead() {
    MarsRover marsRover = new MarsRover(NORTH, 5, 5);
    MarsRoverNavigator navigator = navigatorWithObstacleAhead(marsRover);

    MarsRover movedRover = navigator.move(FORWARD);

    assertThat(movedRover).isEqualTo(marsRover);
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
