package Test;

import View.ContainerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestView extends Application{
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane testPane = createTestPane();
		
		Scene scene = new Scene(testPane,500,500);
		
		// setup primary stage
		primaryStage.setTitle("CargoLoad");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);	
						
		primaryStage.show();
	}
	
	public BorderPane createTestPane() {
		BorderPane testPane = new BorderPane();
		
		testPane.setCenter(new ContainerPane());
		
		return testPane;
		
	}
}
