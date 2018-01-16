package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.ContainerModel;
import Model.PentoContainer;
import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;
import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 * Class ButtonPane will create a Pane that displays Backtracking options
 * @author Basia, Jordan
 * @version 1.5
 */
public class BacktrackingOptionsPane extends VBox{
    private Stage stage;
    private ContainerPane container;
    private ArrayList<ParcelShape> containedShapes;
    private ContainerModel solver;
    
    /**
     * Constructor creates a pane with options
     */
    public BacktrackingOptionsPane(ContainerPane container, OptionsPane options){
        this.container = container;
        
        Label title = new Label("Choose packing:");
        title.setFont(new Font("Arial", 20));
        title.setFocusTraversable(false);
        getChildren().add(title);
        
        Button packBox = new Button("Pack boxes");
        packBox.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        packBox.setMinSize(225, 50);
        RadioButton infiniteAmount = new RadioButton("Infinite amount of boxes");
        RadioButton setAmount = new RadioButton("Custom amount of boxes");
        /* when button is pressed it checks, which radiobutton is selected and then acts accordingly
        */
        packBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(setAmount.isSelected()){
                    ParcelsSet set = new ParcelsSet(getButtonPane());
                    Scene scene = new Scene(set);
                    
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.setWidth(450);
                    stage.setHeight(500);
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
        
        Button packPentominoes = new Button("Pack pentominoes");
        packPentominoes.setFocusTraversable(false);
        packPentominoes.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        packPentominoes.setMinSize(225, 50);
        //RadioButton fill = new RadioButton("Fill cargo-space");
        //RadioButton maximum = new RadioButton("Maximum value");
        packPentominoes.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                PentoContainer testContainer = new PentoContainer();
                testContainer.loadContainer(300);
                container.drawPentominoes(testContainer.getLoadedPentominoes());
            }});
        getChildren().add(packPentominoes);

        /* ToggleGroup packPentoGroup = new ToggleGroup();

        fill.setFont(new Font("Arial", 15));
        fill.setToggleGroup(packPentoGroup);
        fill.setSelected(true);
        fill.setFocusTraversable(false);
        
        maximum.setFont(new Font("Arial", 15));
        maximum.setToggleGroup(packPentoGroup);
        maximum.setFocusTraversable(false); */
        
        Button back = new Button("Back");
        back.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        back.setMinSize(150, 50);
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                options.drawAlgorithmOptions();
            }});
        getChildren().add(back);
        
        setSpacing(15);
        setAlignment(Pos.CENTER);
    }
    /**
     * This method generates a new stage that will ask the user to specify how he wants the backtracking to sort.
     * It will give options as:(by value, by ratio and randomly).
     */
    public void setPackingOrder(){
        VBox pane = new VBox();
        Label title = new Label("Choose packing order:");
        title.setFont(new Font("Arial", 19));
        pane.getChildren().add(title);
        
        ToggleGroup packingOrderGroup = new ToggleGroup();
        
        RadioButton value = new RadioButton("By value");
        value.setFocusTraversable(false);
        value.setFont(new Font("Arial", 15));
        value.setToggleGroup(packingOrderGroup);
        value.setSelected(true);
        pane.getChildren().add(value);
        
        RadioButton random = new RadioButton("Random");
        random.setFocusTraversable(false);
        random.setFont(new Font("Arial", 15));
        random.setToggleGroup(packingOrderGroup);
        pane.getChildren().add(random);
        
        RadioButton ratio  = new RadioButton("By value/volume ratio");
        ratio.setFocusTraversable(false);
        ratio.setFont(new Font("Arial", 15));
        ratio.setToggleGroup(packingOrderGroup);
        pane.getChildren().add(ratio);
        
        Label timer = new Label("Set timer for backtracking:");
        timer.setFont(new Font("Arial", 19));
        pane.getChildren().add(timer);
        
        TextField Timer = new TextField();
        Timer.setMaxWidth(175);
        pane.getChildren().add(Timer);
        
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
             
        Button pack = new Button("Pack");
        pack.setStyle("-fx-font: 22 arial; -fx-base: #8FBC8F;");
        pack.setMinSize(150, 50);
        
        pane.getChildren().add(pack);
        pane.setSpacing(5);
        pane.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        pack.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
                if(random.isSelected()){
                    generateSolution(ORDER.RANDOM, Integer.parseInt(Timer.getText()));
                }
                else if(ratio.isSelected()){
                    generateSolution(ORDER.RATIO, Integer.parseInt(Timer.getText()));
                }
                else{
                    generateSolution(ORDER.VALUE, Integer.parseInt(Timer.getText()));
                }
                container.drawBoxes(containedShapes, solver.computeTotalValue());
        }});
    }
    /**
     * If the user chooses option set amount of boxes and submits them this method will be called.
     * It will close the other stage and generate Solutions according to the set amount of boxes,
     * give timer and Order type.
     * @param a number of boxes typeA
     * @param b number of boxes typeB
     * @param c number of boxes typeC
     * @param order Order type, used by backtracking
     * @param timer representing the timer of backtracking
     */
    public void solveSetAmountBoxes(int a, int b, int c, ORDER order, int timer){
        stage.close();
        generateSolution(a, b, c, order, timer);
        container.drawBoxes(containedShapes, solver.computeTotalValue());
    }
    /**
     * This is the actual method that generates an instance of ContainerModel. Tries to solve the container and
     * at the end draws the best found result.
     * @param order order type, used by backtracking
     * @param timer representing the timer of backtracking
     */
    public void generateSolution(ORDER order, int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        solver = new ContainerModel();
        //to turn it into milliseconds
        solver.setDelay(timer*1000);
        switch(order){
            case RANDOM :   solver.setParcelList(givenParcels); 
                            solver.solveFirstPackedCargoRandomOrder();
                            break;
            case VALUE :    givenParcels = solver.orderParcelListByValue(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.solveBacktracking(solver, true);
                            break;
            case RATIO :    givenParcels = solver.orderParcelListByRatio(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.solveBacktracking(solver, true);
                            break;
        }
        containedShapes = solver.getContainedParcels();
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
    public void generateSolution(int a, int b, int c, ORDER order, int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        solver = new ContainerModel();
        //solver.setDelay(timer*1000);
        solver.setAmountOfParcels(a, b, c);
        switch(order){
            case RANDOM :   solver.setParcelList(givenParcels); 
                            solver.solveFirstPackedCargoRandomOrder();
                            break;
            case VALUE :    givenParcels = solver.orderParcelListByValue(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.solveBacktracking(solver, true);
                            break;
            case RATIO :    givenParcels = solver.orderParcelListByRatio(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.solveBacktracking(solver, true);
                            break;
                            
        }
        containedShapes = solver.getContainedParcels();
    }
    /**
     * A method that returns an instance of this class
     * @return instance of this class
     */
    public BacktrackingOptionsPane getButtonPane(){
        return this;
    }
}