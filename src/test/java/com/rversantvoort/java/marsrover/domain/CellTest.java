package com.rversantvoort.java.marsrover.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

  @Test
  void cellHasObstacle() {
    Obstacle obstacle = new Obstacle(1, 1);
    Cell cell = new Cell(1, 1, obstacle);

    assertThat(cell.hasObstacle()).isTrue();
  }

  @Test
  void cellDoesNotHaveObstacle() {
    Cell cell = new Cell(1, 1, null);

    assertThat(cell.hasObstacle()).isFalse();
  }

}
