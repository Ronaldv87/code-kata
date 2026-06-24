package com.rversantvoort.java.marsrover.service;

import com.rversantvoort.java.marsrover.rover.MarsRover;
import com.rversantvoort.java.marsrover.service.MoveResult.Blocked;
import com.rversantvoort.java.marsrover.service.MoveResult.Moved;

public sealed interface MoveResult permits Blocked, Moved {

  record Blocked(MarsRover rover) implements MoveResult {

  }

  record Moved(MarsRover rover) implements MoveResult {

  }
}
