package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;

public class AllView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;
  private final PuzzleView puzzleView;
  private final ControlView controlView;
  private final MessageView messageView;
  private static final int ALL_WIDTH = 1200;
  private static final int ALL_HEIGHT = 700;

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
    allViews.setMinSize(1200, 600);
    allViews.setPrefSize(1200, 600);
    allViews.setMaxSize(1200, 600);
    allViews.setAlignment(Pos.CENTER);
    Label title = new Label("Akari");
    title.setFont(new Font("Verdana", 70));
    VBox allViewsTitle = new VBox(title, allViews);
    allViewsTitle.setAlignment(Pos.CENTER);
    allViewsTitle.setMinSize(ALL_WIDTH, ALL_HEIGHT);
    allViewsTitle.setPrefSize(ALL_WIDTH, ALL_HEIGHT);
    allViewsTitle.setMaxSize(ALL_WIDTH, ALL_HEIGHT);
    return allViewsTitle;
  }
}
