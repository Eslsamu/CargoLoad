package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ContainerView extends GridPane{
    private Label heading;
    private GridPane pane;
    private ContainerPane container;
    private int size;
    private int move = 0;
    public ContainerView(TitlePane title){
        heading = new Label();
        heading.setFont(new Font("Arial", 20));
        add(heading, 0, 0);
        setAlignment(Pos.CENTER);
        setHalignment(heading, HPos.CENTER);
        container = new ContainerPane(650, 650, title, this);
        add(container, 0, 1);
        setHalignment(container, HPos.CENTER);
        drawButtons();
    }
    public ContainerPane getContainer(){
        return container;
    }
    public void setHeading(String name){
        heading.setText(name);
    }
    public void showButtons(){
        try{
        add(pane, 0, 2);
        setHalignment(pane, HPos.CENTER);
        container.drawShownContainers(move);
        }
        catch(Exception e){
        
        }
    }
    public void hideButtons(){
        try{
        getChildren().remove(pane);
        heading.setText("");
        container.redrawContainer();
        }
        catch(Exception e){
        
        }
    }
    public void setSize(int size){
        this.size = size; 
    }
    public void drawButtons(){
        pane = new GridPane();
        Button left = new Button("Left");
        left.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        left.setFocusTraversable(false);
        left.setMinSize(225, 50);
        left.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            if(size != 0){
                move--;
                if(move < 0){
                    move = size - 1;
                }
                container.drawShownContainers(move);
            }    
        }});
        Button right = new Button("Right");
        right.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        right.setFocusTraversable(false);
        right.setMinSize(225, 50);
        right.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            if(size != 0){
                move++;
                if(move == size){
                    move = 0;
                }
                container.drawShownContainers(move);
            }
        }});
        pane.setHgap(10);
        pane.add(left, 0, 0);
        pane.add(right, 1, 0);
        pane.setAlignment(Pos.CENTER);
    }
}
