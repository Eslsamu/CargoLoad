package View.Options;

import View.ContainerPane;
import View.ContainerView;
import View.Options.BacktrackingOptionsPane;
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
    private DivideConquerOptions divideOptions;
    private RandomOptionsPane randomOptions;
    /**
     * Constructor creates an instance of each pane that we want to display.
     * @param container an instance of containerPane, needed for BacktrackingOptionsPane
     */
    public OptionsPane(ContainerPane container, ContainerView view){
        options = new BacktrackingOptionsPane(container, this, view);
        algorithms = new AlgorithmsPane(this);
        divideOptions = new DivideConquerOptions(container, this, view);
        randomOptions = new RandomOptionsPane(container, this, view);
         
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
    public void drawDivideConquerOptions(){
        getChildren().remove(algorithms);
        getChildren().add(divideOptions);
    }
    public void drawRandomOptions(){
        getChildren().remove(algorithms);
        getChildren().add(randomOptions);
    }
    /**
     * Remove backtracking options and open algorithm options
     */
    public void drawAlgorithmOptions(){
        getChildren().remove(options);
        getChildren().remove(divideOptions);
        getChildren().remove(randomOptions);
        getChildren().add(algorithms);
    }
}
