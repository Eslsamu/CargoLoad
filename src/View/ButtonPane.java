package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ButtonPane extends VBox{
    
    public ButtonPane()
    {
    
    //VBox vBox = new VBox();
    setSpacing(15);
    
    Label start = new Label("Start packing:");
    start.setFont(new Font("Arial", 20));
    getChildren().add(start);
    
    Button startbox = new Button("Boxes");
    startbox.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    startbox.setMinSize(100, 50);
    startbox.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            ParcelsSet set = new ParcelsSet();
            Scene scene = new Scene(set);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(250);
            stage.setHeight(300);
            stage.show();
            
        }});
    getChildren().add(startbox);
    ToggleGroup group = new ToggleGroup();
    Button startpentominoes = new Button("Pentominoes");
    startpentominoes.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
    startpentominoes.setMinSize(100, 50);
    RadioButton button1 = new RadioButton("Fill cargo-space");
    button1.setFont(new Font("Arial", 15));
    button1.setToggleGroup(group);
    button1.setSelected(true);
    RadioButton button2 = new RadioButton("Maximum value");
    button2.setFont(new Font("Arial", 15));
    button2.setToggleGroup(group);
    GridPane pane = new GridPane();
    pane.add(button1, 0, 0);
    pane.add(button2, 0, 1);
    getChildren().add(startpentominoes);
    getChildren().add(pane);
    
    setAlignment(Pos.CENTER);
    
    
    }
}
