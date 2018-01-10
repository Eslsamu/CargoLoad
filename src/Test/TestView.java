package Test;

import View.ContainerPane;
import View.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestView extends Application{
	public ContainerPane pane;
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane testPane = createTestPane();
		
		Scene scene = new Scene(testPane,750,750);
                ViewController controlls = new ViewController(pane);
                //MouseController controlls = new MouseController(pane);
                //scene.addEventFilter(MouseEvent.MOUSE_PRESSED, controlls);
		//scene.addEventFilter(MouseEvent.MOUSE_DRAGGED ,controlls);
                scene.addEventFilter(KeyEvent.KEY_PRESSED, controlls);
                scene.setCamera(pane.getCamera());
		// setup primary stage
		primaryStage.setTitle("CargoLoad");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);	
						
		primaryStage.show();
	}
	
	public BorderPane createTestPane() {
		BorderPane testPane = new BorderPane();
		pane = new ContainerPane();
		testPane.setCenter(pane);
		
		return testPane;
		
	}
}
