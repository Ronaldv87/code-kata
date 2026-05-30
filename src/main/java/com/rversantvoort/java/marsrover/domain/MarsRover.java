package com.rversantvoort.java.marsrover.domain;

import static com.rversantvoort.java.marsrover.domain.Direction.EAST;
import static com.rversantvoort.java.marsrover.domain.Direction.NORTH;
import static com.rversantvoort.java.marsrover.domain.Direction.SOUTH;
import static com.rversantvoort.java.marsrover.domain.Direction.WEST;

public record MarsRover(Direction direction, int x, int y) {

  public MarsRover moveForward() {
    Coordinates nextCoordinates = nextCoordinates();
    return new MarsRover(direction, nextCoordinates.x(), nextCoordinates.y());
  }

  public Coordinates nextCoordinates() {
    return switch (direction) {
      case NORTH -> new Coordinates(x, y + 1);
      case WEST -> new Coordinates(x - 1, y);
      case SOUTH -> new Coordinates(x, y - 1);
      case EAST -> new Coordinates(x + 1, y);
    };
  }

  public MarsRover turnLeft() {
    Direction newDirection = switch (direction) {
      case NORTH -> WEST;
      case WEST -> SOUTH;
      case SOUTH -> EAST;
      case EAST -> NORTH;
    };
    return withDirection(newDirection);
  }

  public MarsRover turnRight() {
    Direction newDirection = switch (direction) {
      case NORTH -> EAST;
      case EAST -> SOUTH;
      case SOUTH -> WEST;
      case WEST -> NORTH;
    };
    return withDirection(newDirection);
  }

  private MarsRover withDirection(Direction newDirection) {
    return new MarsRover(newDirection, this.x, this.y);
  }
}
