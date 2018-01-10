/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    
    public ParcelsSet(){
    Label typeA = new Label("Type A -> 1X1X2");
    Label typeB = new Label("Type B -> 1X1.5X2");
    Label typeC = new Label("Type C -> 1.5X1.5X1.5");
    Label amount = new Label("Enter amount of parcels:");
    typeA.setFont(new Font("Arial", 12));
    typeB.setFont(new Font("Arial", 12));
    typeC.setFont(new Font("Arial", 12));
    amount.setFont(new Font("Arial", 17));
    
    TextField typea = new TextField();
    TextField typeb = new TextField();
    TextField typec = new TextField();
    
    Button submit = new Button("Submit");
    submit.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    
    setAlignment(Pos.CENTER);
    setHalignment(amount, HPos.CENTER);
    setHalignment(typeA, HPos.CENTER);
    setHalignment(typeB, HPos.CENTER);
    setHalignment(typeC, HPos.CENTER);
    setHalignment(submit, HPos.CENTER);
    setVgap(5);
    
    
    add(amount, 0, 0);
    add(typeA, 0, 2);
    add(typea, 0, 3);
    add(typeB, 0, 4);
    add(typeb, 0, 5);
    add(typeC, 0, 6);
    add(typec, 0, 7);
    add(submit, 0, 9);
    setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
}
