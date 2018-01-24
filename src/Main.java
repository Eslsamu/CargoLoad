
import View.AppView;
import View.ContainerPane;
import View.MouseController;
import View.ViewController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * Main class that runs the whole application
 * @author danyp
 */
public class Main extends Application{
    public ContainerPane pane;
    
    /**
     * Stage and Scene are constructed here
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        AppView app = new AppView();
        Scene scene = new Scene(app, 1000, 900);
        ViewController controlls = new ViewController(app.getContainer());
        MouseController controlls1 = new MouseController(app.getContainer());
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, controlls1);
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED ,controlls1);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controlls);
        
        primaryStage.setTitle("CargoLoad");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
