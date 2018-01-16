package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;

/**
 *
 * @author basia
 */
public class Controls extends VBox{
    
    private ButtonPane bp;
    private RulesView rv;
    private AlgorithmsPane ap;
    
    public Controls(ContainerPane container){
        bp = new ButtonPane(container, this);
        rv = new RulesView();
        ap = new AlgorithmsPane(container, this);
        
        setSpacing(50);
        
        getChildren().add(rv);
        getChildren().add(ap);   
        setHalignment(rv, HPos.CENTER);
        setHalignment(ap, HPos.CENTER);
        setAlignment(Pos.TOP_CENTER);
        
    }
    
    public void redrawPane(){
        getChildren().remove(ap);
        getChildren().add(bp);
        setHalignment(bp, HPos.CENTER);
    }
    
    public void goBack(){
        getChildren().remove(bp);
        getChildren().add(ap);
        setHalignment(ap, HPos.CENTER);
    }
}
