package com.rversantvoort.java.marsrover.domain;

public record Cell(int x, int y, Obstacle obstacle) {

  public boolean hasObstacle() {
    return obstacle != null;
  }

}
