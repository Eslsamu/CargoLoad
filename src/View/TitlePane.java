package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A class drawing a GridPane with title, box values and container value.
 * @author Jordan, Basia
 * @version 1.3
 */
public class TitlePane extends GridPane{
    Label value;
    /**
     * Constructor draws title, parcel values and container value.
     */
    public TitlePane(){
        Label title = new Label("Cargo Loader:");
        title.setFont(Font.font("Arial",FontWeight.BOLD, 33));
        add(title, 0, 0);
        
        add(drawValuePane(), 0, 1);
        add(drawInstructionsPane(), 0, 2);
                
        setAlignment(Pos.TOP_CENTER);
        setHalignment(title, HPos.CENTER);
        setVgap(20); 
    }
    /**
     * This method draws the parcel values.
     * @return GridPane with labels with parcel values
     */
    public GridPane drawValuePane(){
        GridPane gridPane = new GridPane();
        
        Label information = new Label("Information:");
        information.setFont(new Font("Arial", 25));
        gridPane.add(information, 0, 0);
        
        value = new Label("Container value: 0");
        value.setFont(new Font("Arial", 18));
        gridPane.add(value, 0, 1);
        
        Label parcelValueA = new Label("Parcel A/ Pentomino L value = 3");
        parcelValueA.setFont(new Font("Arial", 16));
        gridPane.add(parcelValueA, 0, 2);
        
        Label parcelValueB = new Label("Parcel B/ Pentomino P  value = 4");
        parcelValueB.setFont(new Font("Arial", 16));
        gridPane.add(parcelValueB, 0, 3);
        
        Label parcelValueC = new Label("Parcel C/ Pentomino T value = 5");
        parcelValueC.setFont(new Font("Arial", 16));
        gridPane.add(parcelValueC, 0, 4);
        
        
        
        gridPane.setVgap(5);
        gridPane.setHalignment(information, HPos.CENTER);
        gridPane.setHalignment(parcelValueA, HPos.CENTER);
        gridPane.setHalignment(parcelValueB, HPos.CENTER);
        gridPane.setHalignment(parcelValueC, HPos.CENTER);
        gridPane.setHalignment(value, HPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        
        return gridPane;
    }
    /**
     * This method adds some text with instructions for the user.
     * @return GridPane containing the instructions
     */
    public GridPane drawInstructionsPane(){
        GridPane gridPane = new GridPane();
        
        Label controls = new Label("Rotation controls:");
        controls.setFont(new Font("Arial", 25));
        gridPane.add(controls, 0, 0);
        
        Label keys = new Label("Keys:");
        keys.setFont(new Font("Arial", 18));
        gridPane.add(keys, 0, 1);
        
        Label up = new Label("up and down - y axis ");
        up.setFont(new Font("Arial", 16));
        gridPane.add(up, 0, 2);
        
        Label left = new Label("left and right - x axis");
        left.setFont(new Font("Arial", 16));
        gridPane.add(left, 0, 3);
        
        Label q = new Label("q and w - z axis");
        q.setFont(new Font("Arial", 16));
        gridPane.add(q, 0, 4);
        
        Label mouse = new Label("Mouse:");
        mouse.setFont(new Font("Arial", 18));
        gridPane.add(mouse, 0, 5);
        
        Label instruction = new Label("Press mouse on container \n and drag around screen");
        instruction.setFont(new Font("Arial", 16));
        gridPane.add(instruction, 0, 6);
        
        gridPane.setVgap(5);
        gridPane.setHalignment(controls, HPos.CENTER);
        gridPane.setHalignment(keys, HPos.CENTER);
        gridPane.setHalignment(up, HPos.CENTER);
        gridPane.setHalignment(left, HPos.CENTER);
        gridPane.setHalignment(q, HPos.CENTER);
        gridPane.setHalignment(mouse, HPos.CENTER);
        gridPane.setHalignment(instruction, HPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        
        return gridPane;
    }
    /**
     * this method sets the displayed container value.
     * @param Value integer with container value
     */
    public void setDisplayedValue(int Value){
        value.setText("Container value: " + Value);
    }
}
