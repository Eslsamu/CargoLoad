package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class explaining how the user can manipulate the container on the screen.
 * @author Basia
 * @version 1.1
 */
public class RulesView extends VBox{
    
    public RulesView(){
        Text controls = new Text("Camera manipulation controls: ");
        getChildren().add(controls);
        Text text1 = new Text("-> user can rotate camera by dragging the mouse \n and rotating the zoom wheel");
        getChildren().add(text1);
        Text text2 = new Text("-> using keys:");
        getChildren().add(text2);
        Text text3 = new Text("Top and down arrow -> Rotation around X-axis");
        getChildren().add(text3);
        Text text4 = new Text("Left and right arrow -> Rotation around Y-axis");
        getChildren().add(text4);
        Text text5 = new Text("Q and W -> Rotation Around Z-axis");
        getChildren().add(text5);
        /* setHalignment(controls, HPos.CENTER);
        setHalignment(text1, HPos.CENTER);
        setHalignment(text2, HPos.CENTER);
        setHalignment(text3, HPos.CENTER);
        setHalignment(text4, HPos.CENTER);
        setHalignment(text5, HPos.CENTER);
        setHalignment(text6, HPos.CENTER);
        setHalignment(text7, HPos.CENTER);
        text1.setTextAlignment(TextAlignment.CENTER);
        text7.setTextAlignment(TextAlignment.CENTER); */
        
        setSpacing(10);
        setAlignment(Pos.CENTER);
    }
}
