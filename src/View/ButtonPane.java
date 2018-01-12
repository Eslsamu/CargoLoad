package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class ButtonPane will create a Pane that displays the buttons
 * @author Basia
 * @version 1.3
 */
public class ButtonPane extends VBox{
    private Stage stage;
    private ContainerPane pane;
    /**
     * Constructor will create all the buttons and options
     */
    public ButtonPane(ContainerPane pane){
    this.pane = pane;
    setSpacing(15);
    //title label
    Label start = new Label("Choose packing:");
    start.setFont(new Font("Arial", 20));
    getChildren().add(start);
    //pack boxes button
    Button startBox = new Button("Pack boxes");
    start.setFocusTraversable(false);
    startBox.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    startBox.setMinSize(225, 50);
    
    ToggleGroup group1 = new ToggleGroup();
    RadioButton button1 = new RadioButton("Infinite amount");
    RadioButton button2 = new RadioButton("Choose amount");
    button1.setFocusTraversable(false);
    button2.setFocusTraversable(false);
    startBox.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            if(button2.isSelected() == true){
                ParcelsSet set = new ParcelsSet(getButtonPane());
                Scene scene = new Scene(set);
                stage = new Stage();
                stage.setScene(scene);
                stage.setWidth(250);
                stage.setHeight(300);
                stage.show();
            }
            else{
                pane.drawBoxes();
            }
        }});
    getChildren().add(startBox);
    
    button1.setFont(new Font("Arial", 15));
    button1.setToggleGroup(group1);
    button1.setSelected(true);
    
    button2.setFont(new Font("Arial", 15));
    button2.setToggleGroup(group1);
    
    GridPane pane1 = new GridPane();
    pane1.setVgap(5);
    pane1.add(button1, 0, 0);
    pane1.add(button2, 0, 1);
    pane1.setAlignment(Pos.CENTER);
    getChildren().add(pane1);
    setHalignment(pane1, HPos.CENTER);
    //packPentominoes button
    Button startPentominoes = new Button("Pack pentominoes");
    startPentominoes.setFocusTraversable(false);
    startPentominoes.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    startPentominoes.setMinSize(225, 50);
    startPentominoes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            System.out.println("Currently doesn't do anythig.");
        }});
    getChildren().add(startPentominoes);
    
    ToggleGroup group2 = new ToggleGroup();
    
    RadioButton button3 = new RadioButton("Fill cargo-space");
    button3.setFont(new Font("Arial", 15));
    button3.setToggleGroup(group2);
    button3.setSelected(true);
    
    RadioButton button4 = new RadioButton("Maximum value");
    button4.setFont(new Font("Arial", 15));
    button4.setToggleGroup(group2);
    
    button3.setFocusTraversable(false);
    button4.setFocusTraversable(false);
    
    GridPane pane2 = new GridPane();
    pane2.setVgap(5);
    pane2.add(button3, 0, 0);
    pane2.add(button4, 0, 1);
    pane2.setAlignment(Pos.CENTER);
    getChildren().add(pane2);
    setHalignment(pane2, HPos.CENTER);
    setAlignment(Pos.CENTER);
    }
    public void solveSetAmountBoxes(int a, int b, int c){
        stage.close();
        pane.drawBoxes(a, b, c);
    }
    public ButtonPane getButtonPane(){
        return this;
    }
}
