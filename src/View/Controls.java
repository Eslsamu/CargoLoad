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
    
    private ContainerPane pane;
    private ContainerPane container;
    
    public Controls(){
        this.pane = pane;
        container = new ContainerPane();
        ButtonPane bp = new ButtonPane(container);
        RulesView rv = new RulesView(container);
        
        setSpacing(50);
        
        getChildren().add(rv);
        getChildren().add(bp);     
        setHalignment(bp, HPos.CENTER);
        setHalignment(rv, HPos.CENTER);
        setAlignment(Pos.CENTER);
        
    }
    
}
