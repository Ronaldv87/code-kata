package com.rversantvoort.java.marsrover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PlayField {

  private final int numberOfHorizontalFields;
  private final int numberOfVerticalFields;
  private final MarsRover marsrover;
  private final Random random = new Random();
  private List<Obstacle> obstacles = new ArrayList<>();

  public PlayField(int numberOfHorizontalFields, int numberOfVerticalFields, MarsRover marsrover) {
    this.numberOfHorizontalFields = numberOfHorizontalFields;
    this.numberOfVerticalFields = numberOfVerticalFields;
    this.marsrover = marsrover;
    this.obstacles = generateObstacles();
  }

  public MarsRover moveMarsRoverForward() {
    MarsRover movedMarsrover = marsrover.moveForward();
    if (isInBoundsOfField(movedMarsrover)) {
      return movedMarsrover;
    } else {
      throw new CannotMoveForwardException("New position is not within the field");
    }
  }

  private boolean isInBoundsOfField(MarsRover marsRover) {
    return marsRover.x() >= 0 && marsRover.x() <= numberOfHorizontalFields && marsRover.y() >= 0 && marsRover.y() <= numberOfVerticalFields;
  }

  private List<Obstacle> generateObstacles() {
    int totalFields = numberOfHorizontalFields * numberOfVerticalFields;
    int numberOfObstacles = (int) Math.round(totalFields * 0.1);

    for (int i = 0; i < numberOfObstacles; i++) {
      int x = random.nextInt(numberOfHorizontalFields + 1);
      int y = random.nextInt(numberOfVerticalFields + 1);

      if (x != marsrover.x() && y != marsrover.y()) {
        obstacles.add(new Obstacle(x, y));
      }
    }

    return obstacles;
  }
}
