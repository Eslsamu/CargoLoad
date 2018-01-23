package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
/**
 * This class creates a GridPane, on top of it when showing already displayed containers there will be a title with
 * information about the container, following the ContainerPane and if needed buttons to scroll between already generated solutions.
 * @author Jordan, Basia
 */
public class ContainerView extends GridPane{
    private Label heading;
    private GridPane pane;
    private ContainerPane container;
    private int size;
    private int move = 0;
    /**
     * Constructor will add a title with no text and create an instance of ContainerPane which we will run.
     * @param title is an instance of TitlePane, which is used by the ContainerPane 
     */
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
    /**
     * Get the container
     * @return instance of ContainerPane
     */
    public ContainerPane getContainer(){
        return container;
    }
    /**
     * Add a title with information
     * @param name the string with the information
     */
    public void setHeading(String name){
        heading.setText(name);
    }
    /**
     * Show buttons for scrolling between already shown solutions
     */
    public void showButtons(){
        try{
        add(pane, 0, 2);
        setHalignment(pane, HPos.CENTER);
        container.drawShownContainers(move);
        }
        catch(Exception e){
        
        }
    }
    /**
     * Hide buttons
     */
    public void hideButtons(){
        try{
        getChildren().remove(pane);
        heading.setText("");
        container.redrawContainer();
        }
        catch(Exception e){
        
        }
    }
    /**
     * This method will give a value to an int representing the size of already shown containers.
     * @param size size of ArrayList with pentominoes
     */
    public void setSize(int size){
        this.size = size; 
    }
    /**
     * Create buttons and assign actions to them.
     */
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
