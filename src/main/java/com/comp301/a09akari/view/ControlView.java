package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;

  public ControlView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox controls = new VBox();
    Button prevPuzzleButton = new Button("Previous Puzzle");
    prevPuzzleButton.setOnAction((ActionEvent e) -> controller.clickPrevPuzzle());
    Button nextPuzzleButton = new Button("Next Puzzle");
    nextPuzzleButton.setOnAction((ActionEvent e) -> controller.clickNextPuzzle());
    Button randomPuzzleButton = new Button("Random Puzzle");
    randomPuzzleButton.setOnAction((ActionEvent e) -> controller.clickRandPuzzle());
    Button clearPuzzleButton = new Button("Clear Puzzle");
    clearPuzzleButton.setOnAction((ActionEvent e) -> controller.clickResetPuzzle());
    controls
        .getChildren()
        .addAll(prevPuzzleButton, nextPuzzleButton, randomPuzzleButton, clearPuzzleButton);
    return controls;
  }
}
