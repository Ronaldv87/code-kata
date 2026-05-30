package com.rversantvoort.java.marsrover.navigation;

import static com.rversantvoort.java.marsrover.navigation.Command.FORWARD;

import com.rversantvoort.java.marsrover.domain.Board;
import com.rversantvoort.java.marsrover.domain.Cell;
import com.rversantvoort.java.marsrover.domain.Coordinates;
import com.rversantvoort.java.marsrover.domain.MarsRover;
import java.util.Optional;

public class MarsRoverNavigator {

  private final MarsRover marsrover;
  private final Board board;

  public MarsRoverNavigator(MarsRover marsrover, Board board) {
    this.marsrover = marsrover;
    this.board = board;
  }

  public MarsRover move(Command command) {
    if (command.equals(FORWARD) && !checkIfRoverCanMove()) {
      return marsrover;
    }

    return switch (command) {
      case FORWARD -> marsrover.moveForward();
      case TURN_RIGHT -> marsrover.turnRight();
      case TURN_LEFT -> marsrover.turnLeft();
    };
  }

  private boolean checkIfRoverCanMove() {
    Coordinates nextCoordinates = marsrover.nextCoordinates();
    Optional<Cell> cellToMoveTo = board.getCellForLocation(nextCoordinates.x(), nextCoordinates.y());

    return cellToMoveTo.isPresent() && !cellToMoveTo.get().hasObstacle();
  }

}
