package View;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * This class makes a vbox containing currently displayed options
 * @author Basia
 * @version 1.2
 */
public class OptionsPane extends VBox{
    private BacktrackingOptionsPane options;
    private AlgorithmsPane algorithms;
    /**
     * Constructor creates an instance of each pane that we want to display.
     * @param container an instance of containerPane, needed for BacktrackingOptionsPane
     */
    public OptionsPane(ContainerPane container, ContainerView view){
        options = new BacktrackingOptionsPane(container, this, view);
        algorithms = new AlgorithmsPane(this);
         
        drawAlgorithmOptions();
        
        setSpacing(50);
        setAlignment(Pos.CENTER);
    }
    /**
     * Remove algorithm options and open backtracking options.
     */
    public void drawBacktrackingOptions(){
        getChildren().remove(algorithms);
        getChildren().add(options);
    }
    /**
     * Remove backtracking options and open algorithm options
     */
    public void drawAlgorithmOptions(){
        getChildren().remove(options);
        getChildren().add(algorithms);
    }
}
