package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * fx01_MultipleWindows
 * 
 * Example for Multiple Windows in JavaFX
 * 
 * 1. Approach
 * 
 * The Application has 3 different windows with different controls.
 * Only window is active at the same time.
 * The Application provides feature(s) for choosing the current window and make it visible.
 *
 * 
 * 2. Configuration
 * 
 * Java 13
 * 
 * JavaFX 13
 * 
 * 
 * 3.1. BasePane, First Window and a single Controller
 * 
 * Base pane is an empty AnchorPane (defined by main.fxml). It is the base for adding multiple windows.
 * 
 * First window's is a GridPane with a Label as single visible control with text "Screen 0" (defined by window0.fxml)
 * The label control is assigned to the style group "windowLabel", which in turn is formatted by the central style sheet main.css.
 * 
 * The first window is added to the base pane.
 * 
 * The present class Main is used as controller for both panes.
 * 
 * 3.2. Additional Windows and feature for selecting the current window
 * 
 * TODO
 *
 * @author jmo3300
 * @version 0.0.1
 */

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
			fxmlLoader.setController(this);
			AnchorPane root = (AnchorPane) fxmlLoader.load();

			fxmlLoader = new FXMLLoader(getClass().getResource("/window0.fxml"));
			fxmlLoader.setController(this);
			GridPane window = fxmlLoader.load();

			root.getChildren().add(window);

			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			root.requestFocus(); // avoid focus

		} catch (Exception e) {

			e.printStackTrace();

		}
	}	
}
