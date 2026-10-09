package com.rversantvoort.java.marsrover.service;

import static com.rversantvoort.java.marsrover.service.Command.FORWARD;

import com.rversantvoort.java.marsrover.board.Board;
import com.rversantvoort.java.marsrover.board.Cell;
import com.rversantvoort.java.marsrover.rover.Coordinates;
import com.rversantvoort.java.marsrover.rover.MarsRover;
import com.rversantvoort.java.marsrover.service.MoveResult.Blocked;
import com.rversantvoort.java.marsrover.service.MoveResult.Moved;
import java.util.List;
import java.util.Optional;

public class MarsRoverNavigator {

  private final MarsRover marsrover;
  private final Board board;

  public MarsRoverNavigator(MarsRover marsrover, Board board) {
    this.marsrover = marsrover;
    this.board = board;
  }

  public MoveResult move(List<Command> commands) {
    MoveResult moveResult = null;
    MarsRoverNavigator navigator = this;

    if (commands.isEmpty()) {
      return new MoveResult.Moved(marsrover);
    }

    for (Command command : commands) {
      moveResult = navigator.move(command);

      switch (moveResult) {
        case Moved(MarsRover rover) -> navigator = new MarsRoverNavigator(rover, board);
        case Blocked _ -> {
          return moveResult;
        }
      }
    }

    return moveResult;
  }

  public MoveResult move(Command command) {
    if (command.equals(FORWARD) && !roverCanMove()) {
      return new Blocked(marsrover);
    }

    return switch (command) {
      case FORWARD -> new Moved(marsrover.moveForward());
      case TURN_RIGHT -> new Moved(marsrover.turnRight());
      case TURN_LEFT -> new Moved(marsrover.turnLeft());
    };
  }

  private boolean roverCanMove() {
    Coordinates nextCoordinates = marsrover.nextCoordinates();
    Optional<Cell> cellToMoveTo = board.getCellForLocation(nextCoordinates.x(), nextCoordinates.y());

    return cellToMoveTo.isPresent() && !cellToMoveTo.get().hasObstacle();
  }
}
