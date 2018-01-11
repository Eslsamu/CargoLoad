
import Model.ContainerModel;
import View.AppView;
import View.ViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * This class initializes and starts the JavaFX application. 
 * 
 * @author Jordan, Basia, Blazej, Yvar, Stijn, Samuel
 */
public class Main extends Application{
	
	/*
	 * Version
	 */
	public static final String VERSION = "1.0"; 
	
	/*
	 * The primary stage
	 */
	private static Stage primaryStage; 
	
	public static void main(String[] args) {
        launch(args);
    }
	
	//@Override
	public void start(Stage primaryStage) throws Exception {
		ContainerModel model = new ContainerModel();
		//ViewController controller = new ViewController(model);
		//AppView view = new AppView(model,controller);
				
		//Scene scene = new Scene(view.asParent());
		
		// setup primary stage
		primaryStage.setTitle("CargoLoad");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
	//	primaryStage.setScene(scene);
				
		primaryStage.show();
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
 }
