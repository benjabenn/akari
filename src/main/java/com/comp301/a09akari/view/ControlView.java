package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;
  private static final int BUTTON_WIDTH = 150;
  private static final int BUTTON_HEIGHT = 60;

  public ControlView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox controls = new VBox();

    Button prevPuzzleButton = new Button("Previous Puzzle");
    prevPuzzleButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    prevPuzzleButton.setOnAction((ActionEvent e) -> controller.clickPrevPuzzle());

    Button nextPuzzleButton = new Button("Next Puzzle");
    nextPuzzleButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    nextPuzzleButton.setOnAction((ActionEvent e) -> controller.clickNextPuzzle());

    Button randomPuzzleButton = new Button("Random Puzzle");
    randomPuzzleButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    randomPuzzleButton.setOnAction((ActionEvent e) -> controller.clickRandPuzzle());

    Button clearPuzzleButton = new Button("Clear Puzzle");
    clearPuzzleButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    clearPuzzleButton.setOnAction((ActionEvent e) -> controller.clickResetPuzzle());

    controls
        .getChildren()
        .addAll(prevPuzzleButton, nextPuzzleButton, randomPuzzleButton, clearPuzzleButton);
    controls.setSpacing(50);
    controls.setAlignment(Pos.CENTER);
    return controls;
  }
}
