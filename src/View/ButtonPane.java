package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.ContainerModel;
import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;
import java.util.ArrayList;

/**
 * Class ButtonPane will create a Pane that displays the buttons
 * @author Basia
 * @version 1.3
 */
public class ButtonPane extends VBox{
    private Stage stage;
    private ContainerPane pane;
    private enum order { VALUE, RANDOM, RATIO};
    /**
     * Constructor will create all the buttons and options
     */
    public ButtonPane(ContainerPane pane, Controls controls){
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
    Button back = new Button("Back");
    back.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    back.setMinSize(150, 50);
    back.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            controls.goBack();
        }});
    
    ToggleGroup group1 = new ToggleGroup();
    RadioButton button1 = new RadioButton("Infinite amount");
    button1.setFont(new Font("Arial", 18));
    RadioButton button2 = new RadioButton("Choose amount");
    button2.setFont(new Font("Arial", 18));
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
                stage.setWidth(450);
                stage.setHeight(450);
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.show();
            }
            else{
                PackingOrder(pane);
            }
        }});
    getChildren().add(startBox);
    
    
    button1.setToggleGroup(group1);
    button1.setSelected(true);
    
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
    button3.setFont(new Font("Arial", 18));
    button3.setToggleGroup(group2);
    button3.setSelected(true);
    
    RadioButton button4 = new RadioButton("Maximum value");
    button4.setFont(new Font("Arial", 18));
    button4.setToggleGroup(group2);
    
    button3.setFocusTraversable(false);
    button4.setFocusTraversable(false);
    
    GridPane pane2 = new GridPane();
    pane2.setVgap(5);
    pane2.add(button3, 0, 0);
    pane2.add(button4, 0, 1);
    pane2.setAlignment(Pos.CENTER);
    getChildren().add(pane2);
    getChildren().add(back);
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
    public void PackingOrder(ContainerPane container){
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
        
        Button button = new Button("Pack");
        button.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        button.setMinSize(150, 50);
        GridPane pane = new GridPane();
        pane.add(label, 0, 0);
        pane.add(button1, 0, 1);
        pane.add(button2, 0, 2);
        pane.add(button3, 0, 3);
        pane.add(button, 0, 4);
        
        pane.setAlignment(Pos.CENTER);
        pane.setHalignment(button, HPos.CENTER);
        pane.setHalignment(label, HPos.CENTER);
        pane.setHalignment(button1, HPos.CENTER);
        pane.setHalignment(button2, HPos.CENTER);
        pane.setHalignment(button3, HPos.CENTER);
        pane.setVgap(7.5);
        pane.setPadding(new Insets(5, 5, 5, 5));
        
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(225);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
                container.drawBoxes();
        }});
    }
    public ArrayList<ParcelShape> generateSolution(){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        ContainerModel container = new ContainerModel();
        container.setParcelList(givenParcels);
        container.solveFirstPackedCargo();
        ArrayList<ParcelShape> containedShapes = container.getContainedParcels();
        
        return containedShapes;
    }
}
