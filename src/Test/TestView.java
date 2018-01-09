package Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestView extends Application{
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane testPane = createTestPane();
		
		Scene scene = new Scene(testPane);
		
		// setup primary stage
		primaryStage.setTitle("CargoLoad");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);	
						
		primaryStage.show();
	}
	
	public Pane createTestPane() {
		Pane testPane = new Pane();
		
		
		
		return testPane;
		
	}
}
