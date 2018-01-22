package View.Options;

import Model.ContainerModel;
import Model.PentoContainer;
import Shapes.ParcelShape;
import View.ContainerPane;
import View.ContainerView;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GraspOptionsPane extends VBox{
    private Stage stage;
    private ContainerPane container;
    private ArrayList<ParcelShape> containedShapes = new ArrayList<>();
    private ContainerModel solver;
    
    /**
     * Constructor creates a pane with options
     * @param container an instance of current shown ContainerPane
     */
    public GraspOptionsPane(ContainerPane container, OptionsPane options, ContainerView view){
        this.container = container;
        
        Label title = new Label("Choose packing:");
        title.setFont(new Font("Arial", 20));
        title.setFocusTraversable(false);
        getChildren().add(title);
        
        Button packBox = new Button("Pack boxes");
        packBox.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        packBox.setFocusTraversable(false);
        packBox.setMinSize(225, 50);
        RadioButton infiniteAmount = new RadioButton("Infinite amount of boxes");
        RadioButton setAmount = new RadioButton("Custom amount of boxes");
        /* when button is pressed it checks, which radiobutton is selected and then acts accordingly
        */
        packBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                view.hideButtons();
            }});
        getChildren().add(packBox);
        
        ToggleGroup packBoxGroup = new ToggleGroup();
        infiniteAmount.setFont(new Font("Arial", 15));
        infiniteAmount.setSelected(true);
        infiniteAmount.setFocusTraversable(false);
        infiniteAmount.setToggleGroup(packBoxGroup);
        getChildren().add(infiniteAmount);
        
        setAmount.setFont(new Font("Arial", 15));
        setAmount.setFocusTraversable(false);
        setAmount.setToggleGroup(packBoxGroup);
        getChildren().add(setAmount);
        

        /* ToggleGroup packPentoGroup = new ToggleGroup();

        fill.setFont(new Font("Arial", 15));
        fill.setToggleGroup(packPentoGroup);
        fill.setSelected(true);
        fill.setFocusTraversable(false);
        
        maximum.setFont(new Font("Arial", 15));
        maximum.setToggleGroup(packPentoGroup);
        maximum.setFocusTraversable(false); */
        Button shownContainers = new Button("Generated Containers");
        shownContainers.setFocusTraversable(false);
        shownContainers.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        shownContainers.setFocusTraversable(false);
        shownContainers.setMinSize(225, 50);
        //RadioButton fill = new RadioButton("Fill cargo-space");
        //RadioButton maximum = new RadioButton("Maximum value");
        shownContainers.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(container.getSizeShownContainers() != 0){
                    view.showButtons();
                    view.setSize(container.getSizeShownContainers());
                }
            }});
        getChildren().add(shownContainers);
        
        Button back = new Button("Back");
        back.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        back.setMinSize(150, 50);
        back.setFocusTraversable(false);
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                view.hideButtons();
                container.redrawContainer();
                options.drawAlgorithmOptions();
            }});
        getChildren().add(back);
        
        setSpacing(15);
        setAlignment(Pos.CENTER);
    }
}
