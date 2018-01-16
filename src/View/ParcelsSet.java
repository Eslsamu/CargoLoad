/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author basia
 */
public class ParcelsSet extends GridPane{
    public ParcelsSet(ButtonPane buttonPane){
    Label typeA = new Label("Type A -> 1X1X2");
    Label typeB = new Label("Type B -> 1X1.5X2");
    Label typeC = new Label("Type C -> 1.5X1.5X1.5");
    Label amount = new Label("Enter amount of parcels:");
    
    typeA.setFont(new Font("Arial", 15));
    typeB.setFont(new Font("Arial", 15));
    typeC.setFont(new Font("Arial", 15));
    amount.setFont(new Font("Arial", 19));
    
    TextField typea = new TextField();
    TextField typeb = new TextField();
    TextField typec = new TextField();
    
    Label label = new Label("Choose packing order:");
    label.setFont(new Font("Arial", 19));
    ToggleGroup group1 = new ToggleGroup();
    RadioButton button1 = new RadioButton("Highest value first");
    RadioButton button3 = new RadioButton("Highest value/volume ratio first");
    RadioButton button2 = new RadioButton("Random parcel");
    button1.setFocusTraversable(false);
    button2.setFocusTraversable(false);
    button3.setFocusTraversable(false);
    
    button1.setFont(new Font("Arial", 15));
    button1.setToggleGroup(group1);
    button1.setSelected(true);
    button2.setFont(new Font("Arial", 15));
    button2.setToggleGroup(group1);
    button3.setFont(new Font("Arial", 15));
    button3.setToggleGroup(group1);
    
    Button submit = new Button("Submit");
    submit.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    
    Label delay = new Label("Set delay for backtracking:");
    delay.setFont(new Font("Arial", 15));
    TextField Delay = new TextField();
    submit.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            if(button1.isSelected()){
                buttonPane.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.VALUE, Integer.parseInt(Delay.getText()));
            }
            else if(button2.isSelected()){
               buttonPane.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.RANDOM, Integer.parseInt(Delay.getText()));
            }
            else{
                buttonPane.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.RATIO, Integer.parseInt(Delay.getText()));
            }
        }});
    setAlignment(Pos.CENTER);
    setHalignment(amount, HPos.CENTER);
    setHalignment(typeA, HPos.CENTER);
    setHalignment(typeB, HPos.CENTER);
    setHalignment(typeC, HPos.CENTER);
    setHalignment(submit, HPos.CENTER);
    setHalignment(label, HPos.CENTER);
    setHalignment(button1, HPos.CENTER);
    setHalignment(button2, HPos.CENTER);
    setHalignment(button3, HPos.CENTER);
    setVgap(7.5);
    
    add(amount, 0, 0);
    add(typeA, 0, 2);
    add(typea, 0, 3);
    add(typeB, 0, 4);
    add(typeb, 0, 5);
    add(typeC, 0, 6);
    add(typec, 0, 7);
    add(label, 0, 8);
    add(button1, 0, 9);
    add(button2, 0, 10);
    add(button3, 0, 11);
    add(submit, 0, 14);
    add(delay, 0, 12);
    add(Delay, 0, 13);
    setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
}
