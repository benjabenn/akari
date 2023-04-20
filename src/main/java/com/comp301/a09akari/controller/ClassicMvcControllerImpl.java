package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;

import java.util.Random;

public class ClassicMvcControllerImpl implements ClassicMvcController {
  private final Model model;

  public ClassicMvcControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
  }

  @Override
  public void clickPrevPuzzle() {
    model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
  }

  @Override
  public void clickRandPuzzle() {
//    int randomInt = model.getActivePuzzleIndex();
//    Random r = new Random();
//    while (randomInt == model.getActivePuzzleIndex() && model.getPuzzleLibrarySize() != 1) {
//      randomInt = r.nextInt(0, model.getPuzzleLibrarySize());
//    }
//    model.setActivePuzzleIndex(randomInt);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (r >= 0
        && r < model.getActivePuzzle().getHeight()
        && c >= 0
        && c < model.getActivePuzzle().getWidth()) {
      if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
        if (model.isLamp(r, c)) {
          model.removeLamp(r, c);
        } else {
          model.addLamp(r, c);
        }
      }
    }
  }
}
