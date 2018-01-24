package View.Options;

import Model.ContainerModel;
import Model.DivideConquerPentominoes;
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

/**
 * This class draws options for the Divide and Conquer algorithm.
 * @author Jordan, Basia
 * @version 1.2
 * @date 23.01.2018
 */
public class DivideConquerOptions extends VBox{
    private Stage stage;
    private ContainerPane container;
    private ArrayList<ParcelShape> containedShapes = new ArrayList<>();
    private ContainerModel solver;
    private ContainerView view;
    /**
     * The constructor will draw all needed options.
     * @param container instance of current running ContainerPane
     * @param options instance of OptionsPane to control currently shown options
     * @param view instance of ContainerView to control shown container 
     */
    public DivideConquerOptions(ContainerPane container, OptionsPane options, ContainerView view){
        this.container = container;
        this.view = view;
        
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
        packBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                view.hideButtons();
                if(setAmount.isSelected()){
                    ParcelSetDivideConquer set = new ParcelSetDivideConquer(getButtonPane());
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
        packPentominoes.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        packPentominoes.setFocusTraversable(false);
        packPentominoes.setMinSize(225, 50);
        packPentominoes.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                view.hideButtons();
                DivideConquerPentominoes sth = new DivideConquerPentominoes();
                container.drawPentominoes(sth.getContainedPentominoes(), sth.getContainerValue(), "D&C, Pentominoes");
            }});
        getChildren().add(packPentominoes);
        
        Button shownContainers = new Button("Generated Containers");
        shownContainers.setFocusTraversable(false);
        shownContainers.setStyle("-fx-font: 22 arial; -fx-base: #6495ED ");
        shownContainers.setFocusTraversable(false);
        shownContainers.setMinSize(225, 50);
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
    /**
     * This method generates a new stage that will ask the user to specify how he wants the divide and conquer to sort.
     * It will give options as:(by value, by ratio).
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
        
        RadioButton ratio  = new RadioButton("By value/volume ratio");
        ratio.setFocusTraversable(false);
        ratio.setFont(new Font("Arial", 15));
        ratio.setToggleGroup(packingOrderGroup);
        pane.getChildren().add(ratio);
        
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
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.show();
        pack.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                stage.close();
                if(ratio.isSelected()){
                    generateSolution(ORDER.RATIO, Integer.parseInt(Timer.getText()));
                }
                else if(value.isSelected()){
                    generateSolution(ORDER.VALUE, Integer.parseInt(Timer.getText()));
                }
        }});
    }
    /**
     * If the user chooses option set amount of boxes and submits them this method will be called.
     * It will close the other stage and generate Solutions according to the set amount of boxes,
     * give timer and Order type.
     * @param a number of boxes typeA
     * @param b number of boxes typeB
     * @param c number of boxes typeC
     * @param order Order type, used by divide and conquer
     * @param timer representing the timer of divide and conquer
     */
    public void solveSetAmountBoxes(int a, int b, int c, ORDER order, int timer){
        stage.close();
        generateSolution(a, b, c, order, timer);
    }
    /**
     * This is the actual method that generates an instance of ContainerModel. Tries to solve the container and
     * at the end draws the best found result.
     * @param order order type, used by divide and conquer
     * @param timer representing the timer of divide and conquer
     */
    public void generateSolution(ORDER order, int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        solver = new ContainerModel();
        //to turn it into milliseconds
        solver.setDelay(timer*1000);
        ContainerModel maxValueContainer = new ContainerModel();
        switch(order){
            case VALUE :    givenParcels = solver.orderParcelListByValue(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.solveDivideAndConquer(maxValueContainer);
                            containedShapes = solver.getContainedParcels();
                            container.drawBoxes(containedShapes, solver.computeTotalValue(), "D&C, By value order, infinite amount boxes, timer: " + timer);
                            break;
            case RATIO :    givenParcels = solver.orderParcelListByRatio(givenParcels);
                            solver.setParcelList(givenParcels);
                            solver.solveDivideAndConquer(maxValueContainer);
                            containedShapes = solver.getContainedParcels();
                            container.drawBoxes(containedShapes, solver.computeTotalValue(), "D&C, By ratio order, infinite amount boxes, timer: " + timer);
                            break;                              
        }
    }
    /**
     * This is the actual method that generates an instance of ContainerModel. Tries to solve the container with
     * give set of boxes and at the end draws the best found result.
     * @param a number of boxes typeA
     * @param b number of boxes typeB
     * @param c number of boxes typeC
     * @param order order type, used by divide and conquer
     * @param timer representing the timer of divide and conquer
     */
    public void generateSolution(int a, int b, int c, ORDER order, int timer){
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            if(a != 0){
                givenParcels.add(new ParcelA());
            }
            if(b != 0){
                givenParcels.add(new ParcelB());
            }
            if(c != 0){
                givenParcels.add(new ParcelC());
            }

        solver = new ContainerModel();
        solver.setDelay(timer*1000);
        ContainerModel maxValueContainer = new ContainerModel();
        switch(order){
            case VALUE :    givenParcels = solver.orderParcelListByValue(givenParcels); 
                            solver.setParcelList(givenParcels);
                            solver.setAmountOfParcels(a, b, c);
                            solver.solveDivideAndConquer(maxValueContainer);
                            containedShapes = solver.getContainedParcels();
                            container.drawBoxes(containedShapes, solver.computeTotalValue(), "D&C, By value order, TypeA: "+ a + " TypeB: " + b + " TypeC: " + c + " timer: " + timer);
                            
                            break;
            case RATIO :    givenParcels = solver.orderParcelListByRatio(givenParcels);
                            solver.setParcelList(givenParcels);
                            solver.setAmountOfParcels(a, b, c);
                            solver.solveDivideAndConquer(maxValueContainer);
                            containedShapes = solver.getContainedParcels();
                            container.drawBoxes(containedShapes, solver.computeTotalValue(), "D&C, By ratio order, TypeA: "+ a + " TypeB: " + b + " TypeC: " + c + " timer: " + timer);
                            
                            break;
        }
    }
    /**
     * A method that returns an instance of this class
     * @return instance of this class
     */
    public DivideConquerOptions getButtonPane(){
        return this;
    }
}
