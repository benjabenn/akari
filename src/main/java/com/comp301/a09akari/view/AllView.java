package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AllView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;
  private final PuzzleView puzzleView;
  private final ControlView controlView;
  private final MessageView messageView;

  public AllView(
      Model model,
      ClassicMvcController controller,
      PuzzleView puzzleView,
      ControlView controlView,
      MessageView messageView) {
    this.model = model;
    this.controller = controller;
    this.puzzleView = puzzleView;
    this.controlView = controlView;
    this.messageView = messageView;
  }

  @Override
  public Parent render() {
    HBox allViews = new HBox();
    allViews.getChildren().addAll(messageView.render(), puzzleView.render(), controlView.render());
    allViews.setSpacing(50);
    allViews.setPadding(new Insets(100));
    allViews.setMaxSize(1200, 600);
    allViews.setAlignment(Pos.CENTER);
    return allViews;
  }
}
