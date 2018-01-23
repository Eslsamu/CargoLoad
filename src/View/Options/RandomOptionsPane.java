package View.Options;

import Model.ContainerModel;
import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;
import View.ContainerPane;
import View.ContainerView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RandomOptionsPane extends VBox{
    private Stage stage;
    private ContainerPane container;
    private ArrayList<ParcelShape> containedShapes = new ArrayList<>();
    private ContainerModel solver;
    
    public RandomOptionsPane(ContainerPane container, OptionsPane options, ContainerView view){
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
                if(setAmount.isSelected()){
                    ParcelSetRandom set = new ParcelSetRandom(getButtonPane());
                    Scene scene = new Scene(set);
                    
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.setWidth(350);
                    stage.setHeight(350);
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL); 
                    stage.show();
                }
                else{
                    setPackingOrder();
                }
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
    public void setPackingOrder(){
        VBox pane = new VBox();
       
        Label timer = new Label("Set timer:");
        timer.setFont(new Font("Arial", 19));
        pane.getChildren().add(timer);
        
        TextField Timer = new TextField();
        Timer.setMaxWidth(175);
        pane.getChildren().add(Timer);
        
        try {
            FileInputStream input = new FileInputStream("background.jpeg");
            pane.setBackground(new Background(new BackgroundImage(new Image(input,1000,1000,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException ex) {
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        }     
        Button pack = new Button("Pack");
        pack.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        pack.setMinSize(150, 50);
        
        pane.getChildren().add(pack);
        pane.setSpacing(5);
        pane.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(200);
        stage.setHeight(175);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        pack.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
                generateSolution(Integer.parseInt(Timer.getText()));
        }});
    }
    public void solveSetAmountBoxes(int a, int b, int c, int timer){
        stage.close();
        generateSolution(a, b, c, timer);
    }
    /**
     * This is the actual method that generates an instance of ContainerModel. Tries to solve the container and
     * at the end draws the best found result.
     * @param order order type, used by backtracking
     * @param timer representing the timer of backtracking
     */
    public void generateSolution(int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        solver = new ContainerModel();
        //to turn it into milliseconds
        solver.setDelay(timer*1000);
        ContainerModel maxValueContainer = new ContainerModel();
        solver.setParcelList(givenParcels);
        solver.solveRandom(maxValueContainer);
        containedShapes = solver.getContainedParcels();
        container.drawBoxes(containedShapes, solver.computeTotalValue(), "Random, infinite boxes, timer: " + timer);
                            
                            
    }
    /**
     * This is the actual method that generates an instance of ContainerModel. Tries to solve the container with
     * give set of boxes and at the end draws the best found result.
     * @param a number of boxes typeA
     * @param b number of boxes typeB
     * @param c number of boxes typeC
     * @param order order type, used by backtracking
     * @param timer representing the timer of backtracking
     */
    public void generateSolution(int a, int b, int c, int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        solver = new ContainerModel();
        solver.setDelay(timer*1000);
        ContainerModel maxValueContainer = new ContainerModel();
        solver.setParcelList(givenParcels);
        solver.setAmountOfParcels(a, b, c);
        solver.solveRandom(maxValueContainer);
        containedShapes = solver.getContainedParcels();
        container.drawBoxes(containedShapes, solver.computeTotalValue(), "Random, TypeA: " + a + " TypeB: " + b + " TypeC: " + c + " ,timer: " + timer);
       
    }
    /**
     * A method that returns an instance of this class
     * @return instance of this class
     */
    public RandomOptionsPane getButtonPane(){
        return this;
    }
}
