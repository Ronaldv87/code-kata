package com.rversantvoort.java.marsrover;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Board {

  private final int numberOfHorizontalFields;
  private final int numberOfVerticalFields;
  private final List<Cell> cells;
  private final List<Obstacle> obstacles;

  public Board(int numberOfHorizontalFields, int numberOfVerticalFields, List<Obstacle> obstacles) {
    this.numberOfHorizontalFields = numberOfHorizontalFields;
    this.numberOfVerticalFields = numberOfVerticalFields;
    this.obstacles = obstacles;
    checkIfInputIsCorrect();
    this.cells = createCells();
  }

  private void checkIfInputIsCorrect() {
    if (numberOfHorizontalFields < 0 || numberOfVerticalFields < 0) {
      throw new IllegalArgumentException("Number of horizontal fields and/or vertical fields must be positive.");
    }
    // TODO controleren of de obstakels geen negatieve waarde bevatten
  }

  private List<Cell> createCells() {
    List<Cell> cells = new ArrayList<>();
    for(int x = 0; x < numberOfHorizontalFields; x++) {
      for(int y = 0; y < numberOfVerticalFields; y++) {
        cells.add(new Cell(x, y, determineCorrectObstacleToAdd(x, y)));
      }
    }

    return cells;
  }

  private Obstacle determineCorrectObstacleToAdd(int x, int y) {
    if (obstacles == null || obstacles.isEmpty()) {
      return null;
    }

    // TODO hoe gaat de logica om met meerdere obestakels op het zelfde veld?
    return obstacles.stream().filter(o -> o.x() == x && o.y() == y).findFirst().orElse(null);
  }

}
