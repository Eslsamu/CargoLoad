package View.Options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class will create a Pane that contains options for using different algorithms.
 * @author Basia, Jordan
 * @version 1.3
 * @date 23.01.2018
 */
public class AlgorithmsPane extends VBox{
    /**
     * Constructor will create a VBox with 4 buttons.
     * @param options an instance of class OptionsPane that is used to redraw the options pane
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
        
        Button random = new Button("Random");
        random.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        random.setMinSize(225, 50);
        random.setFocusTraversable(false);
        random.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                options.drawRandomOptions();   
        }});
        getChildren().add(random);
        
        Button divide = new Button("Divide & Conquer");
        divide.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        divide.setMinSize(225, 50);
        divide.setFocusTraversable(false);
        divide.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                options.drawDivideConquerOptions();
            }});
        getChildren().add(divide);
        
        Button maximalSpace = new Button("Maximal spaces");
        maximalSpace.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        maximalSpace.setMinSize(225, 50);
        maximalSpace.setFocusTraversable(false);
        maximalSpace.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                options.drawMaximalSpaceOptions();   
        }});
        getChildren().add(maximalSpace);
        
        setSpacing(20);
        setAlignment(Pos.CENTER);
    }
}
