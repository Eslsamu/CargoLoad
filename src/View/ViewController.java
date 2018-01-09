package View;

import Model.ContainerModel;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ViewController{
	
	private final ContainerModel model;
	
	private Stage primaryStage;
	
	public ViewController(ContainerModel model) {
		this.model = model;
		primaryStage = Main.getPrimaryStage();
		
		allowCameraHandler();
	}
	
	public void allowCameraHandler() {
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
	}
}
