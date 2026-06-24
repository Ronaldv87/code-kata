package com.rversantvoort.java.marsrover.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    this.cells = createCells();
  }

  public Optional<Cell> getCellForLocation(int x, int y) {
    return cells.stream().filter(cell -> cell.x() == x && cell.y() == y).findFirst();
  }

  private List<Cell> createCells() {
    List<Cell> cells = new ArrayList<>();
    for (int x = 0; x < numberOfHorizontalFields; x++) {
      for (int y = 0; y < numberOfVerticalFields; y++) {
        cells.add(new Cell(x, y, determineObstacleToAdd(x, y)));
      }
    }

    return cells;
  }

  private Obstacle determineObstacleToAdd(int x, int y) {
    if (obstacles == null || obstacles.isEmpty()) {
      return null;
    }

    return obstacles.stream().filter(o -> o.x() == x && o.y() == y).findFirst().orElse(null);
  }
}
