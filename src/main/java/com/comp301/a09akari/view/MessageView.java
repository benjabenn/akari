package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;

  public MessageView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox messageStack = new VBox();
    String msg =
        "Puzzle " + (model.getActivePuzzleIndex() + 1) + " out of " + model.getPuzzleLibrarySize();
    Label puzzleTracker = new Label(msg);
    String isSolvedMsg = model.isSolved() ? "Puzzle solved!" : "Not solved yet...";
    Label solvedTracker = new Label(isSolvedMsg);

    messageStack.getChildren().addAll(puzzleTracker, solvedTracker);
    return messageStack;
  }
}
