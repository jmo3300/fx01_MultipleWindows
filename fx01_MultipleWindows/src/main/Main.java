package main;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
 * 3.2. Additional Windows
 * 
 * Copy window0.fxml to window1.fxml and window2.fxml and 
 * change the text of the Labels in that files to "Window 1" and "Window 2".
 * 
 * Create a List<GridPane> windows, clone the initialization of Window 0 for 1 and 2 and add
 * the created GridPanes to the  windows list.
 * 
 * From this list we can chose a window and set it to the current window.
 * 
 * 3.3. Features for choosing the current window
 * 
 * For choosing the current window we create three buttons. 
 * Each button makes a particular window the current window.
 * 
 * The buttons are collected in a toolbar. The toolbar becomes the first child of the base pane.
 * Therefore we change the type of the base pane from AnchorPane to VBox in Main class and main.fxml and
 * add toolbar and buttons to the main.fxml.
 * 
 * onAction code of the buttons will be "setWindow0", "setWindow1" and "setWindow2".
 * 
 * We inject and implement these messages into the Main class.
 * 
 *
 * @author jmo3300
 * @version 0.0.1
 */

public class Main extends Application {
	
	VBox root;
	
	private List<GridPane> windows = new ArrayList<GridPane>();
	
	private GridPane windowCurrent;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
			fxmlLoader.setController(this);
			root = (VBox) fxmlLoader.load();

			fxmlLoader = new FXMLLoader(getClass().getResource("/window0.fxml"));
			fxmlLoader.setController(this);
			windows.add((GridPane)fxmlLoader.load());

			fxmlLoader = new FXMLLoader(getClass().getResource("/window1.fxml"));
			fxmlLoader.setController(this);
			windows.add((GridPane)fxmlLoader.load());

			fxmlLoader = new FXMLLoader(getClass().getResource("/window2.fxml"));
			fxmlLoader.setController(this);
			windows.add((GridPane)fxmlLoader.load());			
			
			windowCurrent = windows.get(1);
			root.getChildren().add(windowCurrent);

			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			root.requestFocus(); // avoid focus

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@FXML
	public void setWindow0(Event event) {

		setWindow(event, 0);
		
	}

	@FXML
	public void setWindow1(Event event) {

		setWindow(event, 1);
		
	}
	
	@FXML
	public void setWindow2(Event event) {

		setWindow(event, 2);
		
	}
	
	
	private void setWindow(Event event, int idx_new) {

		int idx_current = windows.indexOf(windowCurrent);

		if (idx_current == idx_new) {
			return;
		}
		
		root.getChildren().remove(windowCurrent);
		windowCurrent = windows.get(idx_new);
		root.getChildren().add(windowCurrent);
		
	}
	
}
