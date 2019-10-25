# fx01_MultipleWindows

Javadoc from Main.java

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
 * 4.1. StackPane as Base Pane                                                  <- Branch: StackPane
 * 
 * Now we are using StackPane as base. All windows will be initially added to
 * the StackPane. The choice of the current window will be effected by showing
 * and hiding the respective windows (instead of adding and removing)
 * 
 * Now, the StackPane windowPane (more precise windowPane.getChiildren()) 
 * takes the role of the List<GridPane> windows.
 * 
 * 4.2. Fade in/out (setOpacity via TimeLine) the current window                <- Branch: Fade
 * 
 * Only a visual effect hence all unused windows must be invisible.
 * Otherwise one cannot use the controls on windows in the stack 
 * underneath the window added latest to the stack.
 * 
 * @author jmo3300
 * @version 0.0.1
 */
