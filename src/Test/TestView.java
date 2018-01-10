package Test;

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
        
        Scene scene = new Scene(testPane);
        ViewController controlls = new ViewController(pane);
        MouseController controlls1 = new MouseController(pane);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, controlls1);
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED ,controlls1);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controlls);
        
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
    public static void main(String[] args) {
        launch(args);
    }
}
