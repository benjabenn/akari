package com.comp301.a09akari.model;

import javafx.scene.control.Cell;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary puzzleLibrary;
  private int puzzleIndex;
  private List<Pair<Integer, Integer>> lampLocations;
  private List<ModelObserver> modelObservers;

  public ModelImpl(PuzzleLibrary library) {
    this.puzzleLibrary = library;
    puzzleIndex = 0;
    lampLocations = new ArrayList<>();
    modelObservers = new ArrayList<>();
  }
  @Override
  public void addLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    else if (!lampLocations.contains(new Pair<>(r, c))) {
      lampLocations.add(new Pair<>(r, c));
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampLocations.remove(new Pair<>(r, c));
  }

  @Override
  public boolean isLit(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    // First while loop will go up from (r, c) checking if lit, then
    // reset rowIndex/colIndex and go down, then left, then right
    int rowIndex = r;
    int colIndex = c;
    CellType currentCellType;
    while (rowIndex >= 0) {
      currentCellType = getActivePuzzle().getCellType(rowIndex, colIndex);
      if (currentCellType == CellType.CORRIDOR) {
        if (isLamp(rowIndex, colIndex)) {
          return true;
        }
      }
      else if (currentCellType == CellType.CLUE || currentCellType == CellType.WALL) {
        break;
      }
      rowIndex--;
    }
    // Now we set rowIndex to r+1 and go up
    rowIndex = r + 1;
    while (rowIndex < getActivePuzzle().getHeight()) {
      currentCellType = getActivePuzzle().getCellType(rowIndex, colIndex);
      if (currentCellType == CellType.CORRIDOR) {
        if (isLamp(rowIndex, colIndex)) {
          return true;
        }
      }
      else if (currentCellType == CellType.CLUE || currentCellType == CellType.WALL) {
        break;
      }
      rowIndex++;
    }
    rowIndex = r;
    colIndex = c - 1;
    while (colIndex >= 0) {
      currentCellType = getActivePuzzle().getCellType(rowIndex, colIndex);
      if (currentCellType == CellType.CORRIDOR) {
        if (isLamp(rowIndex, colIndex)) {
          return true;
        }
      }
      else if (currentCellType == CellType.CLUE || currentCellType == CellType.WALL) {
        break;
      }
      colIndex--;
    }
    colIndex = c + 1;
    while (colIndex < getActivePuzzle().getWidth()) {
      currentCellType = getActivePuzzle().getCellType(rowIndex, colIndex);
      if (currentCellType == CellType.CORRIDOR) {
        if (isLamp(rowIndex, colIndex)) {
          return true;
        }
      }
      else if (currentCellType == CellType.CLUE || currentCellType == CellType.WALL) {
        break;
      }
      colIndex++;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lampLocations.contains(new Pair<>(r, c));
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }
    removeLamp(r, c);
    boolean result = isLit(r, c);
    addLamp(r, c);
    return result;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzleLibrary.getPuzzle(puzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return puzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= getPuzzleLibrarySize() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    puzzleIndex = index;
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    lampLocations.clear();
  }

  @Override
  public boolean isSolved() { // TODO
    for (int rowIndex = 0; rowIndex < getActivePuzzle().getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < getActivePuzzle().getWidth(); colIndex++) {
        CellType currentCellType = getActivePuzzle().getCellType(rowIndex, colIndex);
        if (currentCellType == CellType.CORRIDOR) {
          if (!isLit(rowIndex, colIndex)) {
            return false;
          }
          if (isLamp(rowIndex, colIndex)) {
            if (isLampIllegal(rowIndex, colIndex)) {
              return false;
            }
          }
        }
        else if (currentCellType == CellType.CLUE) {
          if (!isClueSatisfied(rowIndex, colIndex)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int target_num = getActivePuzzle().getClue(r, c);
    int num_lamps = 0;
    int rowIndex = r - 1;
    int colIndex = c;
    if (rowIndex >= 0) {
      if (isLamp(rowIndex, colIndex)) {
        num_lamps++;
      }
    }
    rowIndex = r + 1;
    if (rowIndex < getActivePuzzle().getHeight()) {
      if (isLamp(rowIndex, colIndex)) {
        num_lamps++;
      }
    }
    rowIndex = r;
    colIndex = c - 1;
    if (colIndex >= 0) {
      if (isLamp(rowIndex, colIndex)) {
        num_lamps++;
      }
    }
    colIndex = c + 1;
    if (colIndex < getActivePuzzle().getWidth()) {
      if (isLamp(rowIndex, colIndex)) {
        num_lamps++;
      }
    }
    return num_lamps == target_num;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    modelObservers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    modelObservers.remove(observer);
  }
}
