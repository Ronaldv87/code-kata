package com.rversantvoort.java.marsrover;

import static com.rversantvoort.java.marsrover.Direction.EAST;
import static com.rversantvoort.java.marsrover.Direction.NORTH;
import static com.rversantvoort.java.marsrover.Direction.SOUTH;
import static com.rversantvoort.java.marsrover.Direction.WEST;

public record MarsRover(Direction direction, int x, int y) {

  public MarsRover moveForward() {
    return switch (direction) {
      case EAST -> withX(x + 1);
      case NORTH -> withY(y + 1);
      case WEST -> withX(x - 1);
      case SOUTH -> withY(y - 1);
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

  private MarsRover withX(int newX) {
    return new MarsRover(this.direction, newX, this.y);
  }

  private MarsRover withY(int newY) {
    return new MarsRover(this.direction, this.x, newY);
  }

  private MarsRover withDirection(Direction newDirection) {
    return new MarsRover(newDirection, this.x, this.y);
  }
}
