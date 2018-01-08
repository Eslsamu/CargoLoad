import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class initializes and starts the JavaFX application. 
 * 
 * @author Jordan, Basia, Blazej, Yvar, Stijn, Samue
 */
public class Main extends Application{
	
	// VERSION
	public static final String VERSION = "1.0"; 
	
	//@Override
	public void start(Stage primaryStage) throws Exception {
		CargoModel model = new CargoModel();
		CargoController controller = new CargoController(model);
		CargoView view = new CargoView(model,controller);
				
	}

}
