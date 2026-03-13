package com.rversantvoort.java.marsrover;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
  void boardThrowsExceptionWhenHorizontalsFieldsIsNegative() {
    assertThatThrownBy(() -> new Board(-1, -1, null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Number of horizontal fields and/or vertical fields must be positive.");
  }
}
