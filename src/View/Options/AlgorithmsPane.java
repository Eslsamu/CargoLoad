package View.Options;

import View.Options.OptionsPane;
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
     * @param options an instance of class controls that is used to redraw the options pane
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
        
        setSpacing(20);
        setAlignment(Pos.CENTER);
    
    }
}
