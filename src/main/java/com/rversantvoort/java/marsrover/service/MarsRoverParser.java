package com.rversantvoort.java.marsrover.service;

import com.rversantvoort.java.marsrover.board.Board;
import com.rversantvoort.java.marsrover.board.Obstacle;
import com.rversantvoort.java.marsrover.exception.InvalidInputException;
import java.util.List;
import java.util.stream.Stream;

public class MarsRoverParser {

  private static List<Integer> parseToIntegerList(List<String> splitInput) {
    try {
      return splitInput.stream().map(String::trim).map(Integer::parseInt).toList();
    } catch (NumberFormatException _) {
      throw new InvalidInputException("Input can only contain numbers");
    }
  }

  public Board parse(String csvInput) {
    List<String> columns = List.of(csvInput.split(";"));
    validateStructure(columns);

    List<Integer> dimensions = parseToIntegerList(columns.subList(0, 2));
    List<Obstacle> obstacles = columns.size() > 2 ? createListOfObstacles(columns.get(2)) : List.of();

    return createBoard(dimensions.get(0), dimensions.get(1), obstacles);
  }

  private void validateStructure(List<String> columns) {
    if (columns.size() < 2 || columns.size() > 3) {
      throw new InvalidInputException("Input must contain 2 or 3 columns");
    }
    if (columns.get(0).isBlank() || columns.get(1).isBlank()) {
      throw new InvalidInputException("Board dimensions must contain values");
    }
  }

  private Board createBoard(Integer rows, Integer columns, List<Obstacle> obstacles) {
    return new Board(rows, columns, obstacles);
  }

  private List<Obstacle> createListOfObstacles(String column) {
    if (column.isBlank()) {
      return List.of();
    }
    return Stream.of(column.trim().split("\\s+")).map(this::createObstacle).toList();
  }

  private Obstacle createObstacle(String raw) {
    String[] parts = raw.split(",");
    if (parts.length != 2) {
      throw new InvalidInputException("Obstacle must be in the format x,y");
    }
    List<Integer> coordinates = parseToIntegerList(List.of(parts));
    return new Obstacle(coordinates.get(0), coordinates.get(1));
  }
}
