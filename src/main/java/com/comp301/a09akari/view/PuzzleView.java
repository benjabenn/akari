package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PuzzleView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;
  private static final int NODE_WIDTH = 40;
  private static final int NODE_HEIGHT = 40;
  private static final int GRID_WIDTH = 600;
  private static final int GRID_HEIGHT = 600;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane puzzleGrid = new GridPane();
    puzzleGrid.setAlignment(Pos.CENTER);
    puzzleGrid.setPrefSize(GRID_WIDTH, GRID_HEIGHT);
    puzzleGrid.setMinSize(GRID_WIDTH, GRID_HEIGHT);
    puzzleGrid.setMaxSize(GRID_WIDTH, GRID_HEIGHT);
    CornerRadii cornerRadii = new CornerRadii(2);
    Insets insets = new Insets(2);
    for (int rowIndex = 0; rowIndex < model.getActivePuzzle().getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getActivePuzzle().getWidth(); colIndex++) {
        if (model.getActivePuzzle().getCellType(rowIndex, colIndex) == CellType.WALL) {
          Label label = new Label();
          Background background =
              new Background(new BackgroundFill(Color.BLACK, cornerRadii, insets));
          label.setBackground(background);
          label.setMinSize(NODE_WIDTH, NODE_HEIGHT);
          label.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
          label.setMaxSize(NODE_WIDTH, NODE_HEIGHT);
          puzzleGrid.add(label, colIndex, rowIndex);
        } else if (model.getActivePuzzle().getCellType(rowIndex, colIndex) == CellType.CLUE) {
          Color clueSatisfiedText = Color.rgb(101, 101, 101);
          Label label =
              new Label(String.valueOf(model.getActivePuzzle().getClue(rowIndex, colIndex)));
          label.setAlignment(Pos.CENTER);
          label.setFont(Font.font(30));
          Background background;
          if (model.isClueSatisfied(rowIndex, colIndex)) {
            label.setTextFill(clueSatisfiedText);
          } else {
            label.setTextFill(Color.WHITE);
          }
          background = new Background(new BackgroundFill(Color.BLACK, cornerRadii, insets));
          label.setBackground(background);
          label.setMinSize(NODE_WIDTH, NODE_HEIGHT);
          label.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
          label.setMaxSize(NODE_WIDTH, NODE_HEIGHT);
          puzzleGrid.add(label, colIndex, rowIndex);
        } else {
          Button button = new Button();
          Color litYellow = Color.rgb(255, 255, 153);
          button.setMinSize(NODE_WIDTH, NODE_HEIGHT);
          button.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
          button.setMaxSize(NODE_WIDTH, NODE_HEIGHT);
          int finalRowIndex = rowIndex;
          int finalColIndex = colIndex;
          button.setOnAction((ActionEvent e) -> controller.clickCell(finalRowIndex, finalColIndex));
          if (model.isLamp(rowIndex, colIndex)) {
            if (model.isLampIllegal(rowIndex, colIndex)) {
              Image illegalLamp = new Image("illegal-bulb-light-red.png");
              ImageView illegalLampView = new ImageView(illegalLamp);
              illegalLampView.setPreserveRatio(true);
              illegalLampView.setFitHeight(30);
              button.setGraphic(illegalLampView);
              Background background =
                  new Background(new BackgroundFill(litYellow, cornerRadii, insets));
              button.setBackground(background);
            } else {
              Image lamp = new Image("light-bulb.png");
              ImageView lampView = new ImageView(lamp);
              lampView.setPreserveRatio(true);
              lampView.setFitHeight(30);
              button.setGraphic(lampView);
              Background background =
                  new Background(new BackgroundFill(litYellow, cornerRadii, insets));
              button.setBackground(background);
            }
          } else if (model.isLit(rowIndex, colIndex)) {
            Background background =
                new Background(new BackgroundFill(litYellow, cornerRadii, insets));
            button.setBackground(background);
          } else {
            button.setStyle("-fx-background-fill: black, white");
            button.setStyle("-fx-background-insets: 0, 1");
          }
          puzzleGrid.add(button, colIndex, rowIndex);
        }
      }
    }
    return puzzleGrid;
  }
}
