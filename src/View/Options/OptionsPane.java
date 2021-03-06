package View.Options;

import View.ContainerPane;
import View.ContainerView;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * This class makes a vbox containing currently displayed options
 * @author Basia, Jordan
 * @version 1.2
 * @date 23.01.2018
 */
public class OptionsPane extends VBox{
    private BacktrackingOptionsPane options;
    private AlgorithmsPane algorithms;
    private DivideConquerOptions divideOptions;
    private RandomOptionsPane randomOptions;
    private MaximalLayersOptionsPane maximalSpaceOptions;
    /**
     * Constructor creates an instance of each pane that we want to display.
     * @param container an instance of containerPane, needed for BacktrackingOptionsPane
     * @param view an instance of ContainerView
     */
    public OptionsPane(ContainerPane container, ContainerView view){
        options = new BacktrackingOptionsPane(container, this, view);
        algorithms = new AlgorithmsPane(this);
        divideOptions = new DivideConquerOptions(container, this, view);
        randomOptions = new RandomOptionsPane(container, this, view);
        maximalSpaceOptions = new MaximalLayersOptionsPane(container, this, view);
         
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
     * Remove algorithm options and open divide and conquer options.
     */
    public void drawDivideConquerOptions(){
        getChildren().remove(algorithms);
        getChildren().add(divideOptions);
    }
    /**
     * Remove algorithm options and open random options.
     */
    public void drawRandomOptions(){
        getChildren().remove(algorithms);
        getChildren().add(randomOptions);
    }
    /**
     * Remove algorithm options and open grasp options.
     */
    public void drawMaximalSpaceOptions(){
        getChildren().remove(algorithms);
        getChildren().add(maximalSpaceOptions);
    }
    /**
     * Remove all other options and open algorithm options
     */
    public void drawAlgorithmOptions(){
        getChildren().remove(options);
        getChildren().remove(divideOptions);
        getChildren().remove(randomOptions);
        getChildren().remove(maximalSpaceOptions);
        getChildren().add(algorithms);
    }
}
