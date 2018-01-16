package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author basia
 */
public class Controls extends VBox{
    
    public Controls(ContainerPane container){
        ButtonPane bp = new ButtonPane(container);
        RulesView rv = new RulesView();
        
        setSpacing(50);
        
        getChildren().add(rv);
        getChildren().add(bp);     
        setHalignment(bp, HPos.CENTER);
        setHalignment(rv, HPos.CENTER);
        setAlignment(Pos.CENTER);
        
    }
    
}
