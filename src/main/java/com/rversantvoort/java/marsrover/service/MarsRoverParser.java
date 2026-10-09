package com.rversantvoort.java.marsrover.service;

import com.rversantvoort.java.marsrover.board.Board;
import com.rversantvoort.java.marsrover.exception.InvalidInputException;
import java.util.List;
import java.util.stream.Stream;

public class MarsRoverParser {

  public Board parse(String csvInput) {
    Board board;
    List<Integer> values = parseToIntegerList(csvInput);

    if (values.size() != 2) {
      throw new InvalidInputException("Input must contain exactly 2 values");
    }

    board = createBoard(values.get(0), values.get(1));

    return board;
  }

  private static List<Integer> parseToIntegerList(String csv) {
    if (csv.isEmpty()) {
      throw new InvalidInputException("Input must contain exactly 2 values");
    }

    try {
      return Stream.of(csv.split(";")).map(String::trim).map(Integer::parseInt).toList();
    } catch (NumberFormatException _) {
      throw new InvalidInputException("Input can only contain numbers");
    }
  }

  private Board createBoard(Integer rows, Integer columns) {
    return new Board(rows, columns, List.of());
  }
}
