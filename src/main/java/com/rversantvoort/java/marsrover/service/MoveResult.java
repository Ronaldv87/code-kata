package com.rversantvoort.java.marsrover.service;

import com.rversantvoort.java.marsrover.rover.MarsRover;

public sealed interface MoveResult {

  record Blocked(MarsRover rover) implements MoveResult {

  }

  record Moved(MarsRover rover) implements MoveResult {

  }
}
