package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author basia
 */
public class RulesView extends VBox{
    
    private ContainerPane pane;
    
    public RulesView(ContainerPane pane){
        this.pane = pane;
        Text controls = new Text("Camera manipulation controls: ");
        Text text1 = new Text("-> user can rotate camera by dragging the mouse \n and rotating the zoom wheel");
        Text text2 = new Text("-> using keys:");
        Text text3 = new Text("Top and down arrow -> Rotation around X-axis");
        Text text4 = new Text("Left and right arrow -> Rotation around Y-axis");
        Text text5 = new Text("Q and W -> Rotation Around Z-axis");  
        Text text6 = new Text("C, V, B, N -> manipulating the drawing sequence");
        Text text7 = new Text("R -> returns to starting camera position \n and redraws the container");
        
        setSpacing(10);
        getChildren().add(controls);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(text4);
        getChildren().add(text5);
        getChildren().add(text6);
        getChildren().add(text7);
        setHalignment(controls, HPos.CENTER);
        setHalignment(text1, HPos.CENTER);
        setHalignment(text2, HPos.CENTER);
        setHalignment(text3, HPos.CENTER);
        setHalignment(text4, HPos.CENTER);
        setHalignment(text5, HPos.CENTER);
        setHalignment(text6, HPos.CENTER);
        setHalignment(text7, HPos.CENTER);
        text1.setTextAlignment(TextAlignment.CENTER);
        text7.setTextAlignment(TextAlignment.CENTER);
        setAlignment(Pos.CENTER);
}
    
}
