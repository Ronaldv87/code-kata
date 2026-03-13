package com.rversantvoort.java.marsrover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

  @Test
  void cellHasObstacle() {
    Obstacle obstacle = new Obstacle(1, 1);
    Cell cell = new Cell(1, 1, obstacle);

    assertThat(cell.obstacle()).isEqualTo(obstacle);
  }

  @Test
  void cellDoesNotHaveObstacle() {
    Cell cell = new Cell(1, 1, null);

    assertThat(cell.obstacle()).isNull();
  }

}
