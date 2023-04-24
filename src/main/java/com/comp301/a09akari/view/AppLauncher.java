package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ClassicMvcControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_06));

    Model model = new ModelImpl(puzzleLibrary);
    ClassicMvcController controller = new ClassicMvcControllerImpl(model);
    PuzzleView puzzleView = new PuzzleView(model, controller);
    ControlView controlView = new ControlView(model, controller);
    MessageView messageView = new MessageView(model, controller);

    AllView allView = new AllView(model, controller, puzzleView, controlView, messageView);

    stage.setTitle("Akari Game!");
    Scene scene = new Scene(allView.render());
    stage.setScene(scene);
    stage.sizeToScene();
    model.addObserver(
        (Model m) -> {
          scene.setRoot(allView.render());
          stage.sizeToScene();
        });
    stage.show();
  }
}
