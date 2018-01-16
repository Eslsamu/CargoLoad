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
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class opens a stage with options used by backtracking algorithm when we use set amount of parcels.
 * @author Basia, Jordan
 * @version 1.5
 */
public class ParcelsSet extends VBox{
    /**
     * Constructor generates all options.
     * @param options an instance of BacktrackingOptionsPane
     */
    public ParcelsSet(BacktrackingOptionsPane options){
        
    Label amount = new Label("Enter amount of parcels:");
    amount.setFont(new Font("Arial", 19));
    getChildren().add(amount);
    
    Label typeA = new Label("Type A -> 1X1X2");
    typeA.setFont(new Font("Arial", 15));
    getChildren().add(typeA);
    
    TextField typea = new TextField();
    typea.setMaxWidth(175);
    getChildren().add(typea);
    
    Label typeB = new Label("Type B -> 1X1.5X2");
    typeB.setFont(new Font("Arial", 15));
    getChildren().add(typeB);
    
    TextField typeb = new TextField();
    typeb.setMaxWidth(175);
    getChildren().add(typeb);
    
    Label typeC = new Label("Type C -> 1.5X1.5X1.5");
    typeC.setFont(new Font("Arial", 15));
    getChildren().add(typeC);
    
    TextField typec = new TextField();
    typec.setMaxWidth(175);
    getChildren().add(typec);
    
    Label packingOrder = new Label("Choose packing order:");
    packingOrder.setFont(new Font("Arial", 19));
    getChildren().add(packingOrder);
    
    ToggleGroup packingOrderGroup = new ToggleGroup();
    RadioButton value = new RadioButton("By value");
    value.setFocusTraversable(false);
    value.setFont(new Font("Arial", 15));
    value.setToggleGroup(packingOrderGroup);
    value.setSelected(true);
    getChildren().add(value);
    
    RadioButton ratio = new RadioButton("By value/volume ratio");
    ratio.setFocusTraversable(false);
    ratio.setFont(new Font("Arial", 15));
    ratio.setToggleGroup(packingOrderGroup);
    getChildren().add(ratio);
   
    RadioButton random = new RadioButton("Random");
    random.setFocusTraversable(false);
    random.setFont(new Font("Arial", 15));
    random.setToggleGroup(packingOrderGroup);
    getChildren().add(random);
    
    Label timer = new Label("Set timer for backtracking:");
    timer.setFont(new Font("Arial", 19));
    getChildren().add(timer);
    
    TextField Timer = new TextField();
    Timer.setMaxWidth(175);
    getChildren().add(Timer);
    
    Button submit = new Button("Submit");
    submit.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    submit.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            if(value.isSelected()){
                options.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.VALUE, Integer.parseInt(Timer.getText()));
            }
            else if(random.isSelected()){
               options.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.RANDOM, Integer.parseInt(Timer.getText()));
            }
            else{
                options.solveSetAmountBoxes(Integer.parseInt(typea.getText()), Integer.parseInt(typeb.getText()), Integer.parseInt(typec.getText()), ORDER.RATIO, Integer.parseInt(Timer.getText()));
            }
        }});
    getChildren().add(submit);
    
    setSpacing(5);
    setAlignment(Pos.CENTER);
    setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
    }
    
}
