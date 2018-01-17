package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class will create a Pane that contains options for using different algorithms.
 * @author Basia
 */
public class AlgorithmsPane extends VBox{
    /**
     * Constructor will create a VBox with 3 buttons.
     * @param controls an instance of class controls that is used to redraw the options pane
     */
    public AlgorithmsPane(OptionsPane options){
        Label chooseAlgorithm = new Label("Choose algorithm:");
        chooseAlgorithm.setFont(new Font("Arial", 25));
        getChildren().add(chooseAlgorithm);
        
        Button backtracking = new Button("Backtracking");
        backtracking.setStyle("-fx-font: 22 arial; -fx-base: #6495ED");
        backtracking.setMinSize(225, 50);
        backtracking.setFocusTraversable(false);
        backtracking.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                options.drawBacktrackingOptions();   
        }});
        getChildren().add(backtracking);
        
        Button grasp = new Button("GRASP");
        grasp.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        grasp.setMinSize(225, 50);
        grasp.setFocusTraversable(false);
        getChildren().add(grasp);
        
        Button divide = new Button("Divide & Conquer");
        divide.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        divide.setMinSize(225, 50);
        divide.setFocusTraversable(false);
        getChildren().add(divide);
        
        setSpacing(20);
        setAlignment(Pos.CENTER);
    }  
}
