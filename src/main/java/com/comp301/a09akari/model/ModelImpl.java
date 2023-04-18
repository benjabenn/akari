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
  public boolean isLit(int r, int c) { // TODO
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    boolean isLit = false;
    // First while loop will go up from (r, c) checking if lit, then
    // reset rowIndex/colIndex and go down, then left, then right
    int rowIndex = r;
    int colIndex = c;
    CellType currentCellType;
    while (rowIndex > 0) {
      currentCellType = getActivePuzzle().getCellType(r, c);
      rowIndex--;
    }
    return isLit;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lampLocations.contains(new Pair<>(r, c));
  }

  @Override
  public boolean isLampIllegal(int r, int c) { // TODO
    return false;
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
    if (index >= getPuzzleLibrarySize()) {
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
    return false;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) { // TODO
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return false;
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
