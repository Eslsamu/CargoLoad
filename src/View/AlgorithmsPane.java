package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author basia
 */
public class AlgorithmsPane extends VBox{
    
    private ContainerPane pane;
    
    public AlgorithmsPane(ContainerPane pane, Controls controls){
        this.pane = pane;
        setSpacing(20);
        
        Label label = new Label("Choose algorithm:");
        label.setFont(new Font("Arial", 25));
        Button button1 = new Button("Backtracking");
        Button button2 = new Button("GRASP");
        Button button3 = new Button("Devide & Conquer");
        
        button1.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        button1.setMinSize(225, 50);
        button1.setFocusTraversable(false);
        button2.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        button2.setMinSize(225, 50);
        button2.setFocusTraversable(false);
        button3.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        button3.setMinSize(225, 50);
        button3.setFocusTraversable(false);
        
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                controls.redrawPane();   
        }});
        
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                controls.redrawPane();  
        }});
        
        button3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                controls.redrawPane();     
        }});
        
        getChildren().add(label);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button3);
        setAlignment(Pos.CENTER);
    
    }
    
}
