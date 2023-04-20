package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || r < 0 || c >= getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int cellNum = board[r][c];
    if (cellNum >= 0 && cellNum <= 4) {
      return CellType.CLUE;
    } else if (cellNum == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    // getCellType throws the out-of-bounds exception so not necessary here
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
