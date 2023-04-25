package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MessageView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;
  private static final int MESSAGE_WIDTH = 300;
  private static final int MESSAGE_HEIGHT = 600;
  private static final Font MESSAGE_FONT = new Font("Verdana", 20);

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
    puzzleTracker.setFont(MESSAGE_FONT);

    String isSolvedMsg = model.isSolved() ? "Puzzle solved!" : "Not solved yet...";
    Label solvedTracker = new Label(isSolvedMsg);
    solvedTracker.setFont(MESSAGE_FONT);

    messageStack.getChildren().addAll(puzzleTracker, solvedTracker);
    messageStack.setAlignment(Pos.CENTER);
    messageStack.setSpacing(50);
    messageStack.setMinSize(MESSAGE_WIDTH, MESSAGE_HEIGHT);
    messageStack.setPrefSize(MESSAGE_WIDTH, MESSAGE_HEIGHT);
    messageStack.setMaxSize(MESSAGE_WIDTH, MESSAGE_HEIGHT);
    return messageStack;
  }
}
