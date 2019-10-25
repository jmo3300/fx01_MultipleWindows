package main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * fx01_MultipleWindows
 * 
 * Example for Multiple Windows in JavaFX
 * 
 * 1. Approach
 * 
 * The Application has 3 different windows with different controls. Only window
 * is active at the same time. The Application provides feature(s) for choosing
 * the current window and make it visible.
 *
 * 
 * 2. Configuration
 * 
 * Java 13
 * 
 * JavaFX 13
 *
 * 3. Implementation by Switching to Current Window with Add and Remove
 * 
 * 3.1. BasePane, First Window and a single Controller
 * 
 * Base pane is an empty AnchorPane (defined by main.fxml). It is the base for
 * adding multiple windows.
 * 
 * First window's is a GridPane with a Label as single visible control with text
 * "Screen 0" (defined by window0.fxml) The label control is assigned to the
 * style group "windowLabel", which in turn is formatted by the central style
 * sheet main.css.
 * 
 * The first window is added to the base pane.
 * 
 * The present class Main is used as controller for both panes.
 * 
 * 3.2. Additional Windows
 * 
 * Copy window0.fxml to window1.fxml and window2.fxml and change the text of the
 * Labels in that files to "Window 1" and "Window 2".
 * 
 * Create a List<GridPane> windows, clone the initialization of Window 0 for 1
 * and 2 and add the created GridPanes to the windows list.
 * 
 * From this list we can chose a window and set it to the current window.
 * 
 * 3.3. Features for choosing the current window
 * 
 * For choosing the current window we create three buttons. Each button makes a
 * particular window the current window.
 * 
 * The buttons are collected in a toolbar. The toolbar becomes the first child
 * of the base pane. Therefore we change the type of the base pane from
 * AnchorPane to VBox in Main class and main.fxml and add toolbar and buttons to
 * the main.fxml.
 * 
 * onAction code of the buttons will be "setWindow0", "setWindow1" and
 * "setWindow2".
 * 
 * We inject and implement these messages into the Main class.
 * 
 * 3.4. Paging Feature for Choosing the Current Window
 * 
 * Add previous and next button to the toolbar in main.fxml with the onAction
 * methods previousWindow, nextWindow. Inject and implement these two messages.
 * 
 * 3.5. Additional Features on the Windows for Choosing the Current Window
 * 
 * Window 0: Inputfield for the window no + go button Window 1: Choicebox Window
 * 2: Radiobuttongroup
 * 
 * 4. Implementation by Switching to Current Window with Add and Remove
 * 
 * 4.1. StackPane as Base Pane
 * 
 * Now we are using StackPane as base. All windows will be initially added to
 * the StackPane. The choice of the current window will be effected by showing
 * and hiding the respective windows (instead of adding and removing)
 * 
 * Now, the StackPane windowPane (more precise windowPane.getChiildren()) 
 * takes the role of the List<GridPane> windows.
 * 
 * @author jmo3300
 * @version 0.0.1
 */

public class Main extends Application {

	VBox root;

	private GridPane windowCurrent;

	@FXML
	private StackPane windowPane;

	@FXML
	private TextField windowNoTextField;

	@FXML
	private ChoiceBox windowChoiceBox;

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
			GridPane window = fxmlLoader.load();
			window.setVisible(false);
			windowPane.getChildren().add(window);

			fxmlLoader = new FXMLLoader(getClass().getResource("/window1.fxml"));
			fxmlLoader.setController(this);
			window = fxmlLoader.load();
			window.setVisible(false);
			windowPane.getChildren().add(window);

			fxmlLoader = new FXMLLoader(getClass().getResource("/window2.fxml"));
			fxmlLoader.setController(this);
			window = fxmlLoader.load();
			window.setVisible(false);
			windowPane.getChildren().add(window);

			windowChoiceBox.setItems(FXCollections.observableArrayList("Window 0", "Window 1", "Window 2"));

			windowChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue ov, Number idx, Number idx_new) {
					setWindow(null, idx_new.intValue());
				}
			});

			setWindow(null, 0);

			Scene scene = new Scene(root, 600, 500);
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

	@FXML
	public void previousWindow(Event event) {

		int idxCurrent = windowPane.getChildren().indexOf(windowCurrent);
		int idxNew = idxCurrent > 0 ? idxCurrent - 1 : idxCurrent;
		setWindow(event, idxNew);

	}

	@FXML
	public void nextWindow(Event event) {

		int idxCurrent = windowPane.getChildren().indexOf(windowCurrent);
		int idxNew = idxCurrent < windowPane.getChildren().size() - 1 ? idxCurrent + 1 : idxCurrent;
		setWindow(event, idxNew);

	}

	@FXML
	public void gotoWindow(Event event) {

		try {
			if (!windowNoTextField.getText().matches("[0-" + (windowPane.getChildren().size() - 1) + "]")) {
				windowNoTextField.setText(String.valueOf(windowPane.getChildren().indexOf(windowCurrent)));
			}
		} catch (NumberFormatException nfe) {
			windowNoTextField.setText(String.valueOf(windowPane.getChildren().indexOf(windowCurrent)));
		}

		setWindow(event, Integer.parseInt(windowNoTextField.getText()));

	}

	private void setWindow(Event event, int idx) {

		if (windowCurrent != null) {
			windowCurrent.setVisible(false);
		}
		windowCurrent = (GridPane) windowPane.getChildren().get(idx);

		windowCurrent.setVisible(true);
				
	}

}
