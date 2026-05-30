package com.rversantvoort.java.marsrover.domain;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {
  @Test
  void boardContains25Cells() {
    Board board = new Board(5, 5, null);

    assertThat(board.getCells()).hasSize(25);
  }

  @Test
  void boardContainsCellsWithObstacles() {
    Obstacle obstacle1 = new Obstacle(2, 2);
    Obstacle obstacle2 = new Obstacle(3, 3);
    List<Obstacle> obstacles = List.of(obstacle1, obstacle2);
    Board board = new Board(5, 5, obstacles);

    assertThat(board.getCells()).hasSize(25);

    List<Cell> cellsWithObstacle = board.getCells().stream()
                                        .filter(c -> c.obstacle() != null)
                                        .toList();

    assertThat(cellsWithObstacle)
      .extracting(Cell::obstacle)
      .containsExactlyInAnyOrder(obstacle1, obstacle2);
  }

  @Test
  void getCellForSpecificLocation() {
    Board board = new Board(5, 5, null);

    Optional<Cell> cell = board.getCellForLocation(2, 3);

    assertThat(cell).isPresent();
    Cell foundCell = cell.get();

    assertThat(foundCell.x()).isEqualTo(2);
    assertThat(foundCell.y()).isEqualTo(3);
  }

}
