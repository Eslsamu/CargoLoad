package Test;

import java.io.FileNotFoundException;

import View.AppView;
import View.ContainerPane;
import View.MouseController;
import View.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestView extends Application{
    public ContainerPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane testPane = createTestPane();
        AppView app = new AppView();
        Scene scene = new Scene(app);
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

    public BorderPane createTestPane() throws FileNotFoundException {
        BorderPane testPane = new BorderPane();
        pane = new ContainerPane();
        testPane.setCenter(pane);

        return testPane;

    }
    public static void main(String[] args) {
        launch(args);
    }
}
