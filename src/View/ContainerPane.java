package View;

import Model.ContainerModel;
import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;
import java.util.ArrayList;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * This class creates the visual representation of the container and boxes or pentominoes
 * stored in it.
 * @author Jordan. Basia, Sam
 * @version 1.0
 */
public class ContainerPane extends Parent {
    //predifined sizes of the container and boxes
    private double CONTAINER_DEPTH = 16.5;
    private double CONTAINER_WIDTH = 2.5;
    private double CONTAINER_HEIGHT = 4.0;
    //box type small
    private double Box_Depth = 0.5;
    private double Box_Width = 0.5;
    private double Box_Height = 0.5;
    //set rotation points of camera
    private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    
    private Group root;
    private ArrayList<ParcelShape> containedShapes;
    private ContainerModel container2;
    private TitlePane title;
    
    /**
     * Constructor creates a Scene with container, boxes and a camera is being set to it.
     */
    public ContainerPane(int Scene_Width, int Scene_Length, TitlePane title){
        this.title = title;
        //add all containers, boxes and camera to a group
        root = new Group();
        
    	//creating container
    	Box container = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_DEPTH);
        //making the front of the container transparent
        container.setCullFace(CullFace.FRONT);
        //drawing the container with only lines
        container.setDrawMode(DrawMode.LINE);
        //setting the color of the container
        container.setMaterial(new PhongMaterial(Color.ORANGE));
    	root.getChildren().add(container);
        
        //create a camera
        Camera camera = new PerspectiveCamera(true);
        //add possible rotations and position of camera
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -35));
        
        root.getChildren().add(camera);
        
        //create a Scene from the group
        SubScene subScene = new SubScene(root, Scene_Width, Scene_Length);
        //set a camera for the scene
        subScene.setCamera(camera);
        getChildren().add(subScene);
    }
    /**
    * Method rotateX sets the angle of the camera around the X_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateX(double angle){
        xAxis.setAngle(angle);
    }
    /**
    * Method rotateY sets the angle of the camera around the Y_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateY(double angle){
        yAxis.setAngle(angle);
    }
    /**
    * Method rotateZ sets the angle of the camera around the Z_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateZ(double angle){
        zAxis.setAngle(angle);
    }
    /**
     * Method will run ContainerModel and get coordinates of parcels in a possible solution. It will
     * then draw boxes representing the given solution.
     */
    public void drawBoxes(){
        root.getChildren().remove(2, root.getChildren().size());
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        container2 = new ContainerModel();
        container2.setParcelList(givenParcels);
        container2.solveFirstPackedCargo();
        
        containedShapes = container2.getContainedParcels();
        drawFromFront();
    }
    public void drawBoxes(int a, int b, int c){
        root.getChildren().remove(2, root.getChildren().size());
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();
        
            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());

        container2 = new ContainerModel();
        container2.setAmountOfParcels(a, b, c);
        container2.setParcelList(givenParcels);
        
        container2.solveFirstPackedCargoSetAmount();
        
        containedShapes = container2.getContainedParcels();
        drawFromFront();
    }
    public void drawBoxes(ArrayList<ParcelShape> containedShapes, ContainerModel container){
        this.container2 = container;
        this.containedShapes = containedShapes;
        drawFromFront();
    }
    public void drawFromFront(){
        title.setDisplayedValue(container2.computeTotalValue());
        root.getChildren().remove(2, root.getChildren().size());
        for(int i = 0; i < containedShapes.size(); i++){
            ParcelShape parcel = containedShapes.get(i);
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();
            
            Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
            box.setDrawMode(DrawMode.FILL);
            box.setMaterial(parcel.getMaterial().toMaterial());
            box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
            box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
            box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
            root.getChildren().add(box);
        }
    }
    public void drawFromBack(){
        root.getChildren().remove(2, root.getChildren().size());
        for(int i = containedShapes.size() - 1; i > -1; i--){
            ParcelShape parcel = containedShapes.get(i);
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();
            
            Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
            box.setDrawMode(DrawMode.FILL);
            box.setMaterial(parcel.getMaterial().toMaterial());
            box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
            box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
            box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
            root.getChildren().add(box);
        }
    }
    public void drawUpsideDownFromBehind(){
        root.getChildren().remove(2, root.getChildren().size());
        int check = 0;
        ArrayList<ParcelShape> someParcels = new ArrayList();
        for(int i = containedShapes.size() - 1; i > -1; i--){
            ParcelShape parcelCheck = containedShapes.get(i);
            check+= parcelCheck.getShapeVector().y;
            if(check == 8){
                someParcels.add(parcelCheck);
                for(int p = someParcels.size() - 1; p > -1; p--){
                    ParcelShape parcel = someParcels.get(p);
                    int z = parcel.getPosition().getZ();
                    int y = parcel.getPosition().getY();
                    int x = parcel.getPosition().getX();

                    Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
                    box.setDrawMode(DrawMode.FILL);
                    box.setMaterial(parcel.getMaterial().toMaterial());
                    box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
                    box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
                    box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
                    root.getChildren().add(box);
                }
                someParcels.clear();
                check = 0;
            }
            else if(check > 8){
                for(int p = someParcels.size() - 1; p > -1; p--){
                    ParcelShape parcel = someParcels.get(p);
                    int z = parcel.getPosition().getZ();
                    int y = parcel.getPosition().getY();
                    int x = parcel.getPosition().getX();

                    Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
                    box.setDrawMode(DrawMode.FILL);
                    box.setMaterial(parcel.getMaterial().toMaterial());
                    box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
                    box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
                    box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
                    root.getChildren().add(box);
                }
                someParcels.clear();
                check = 0;
                someParcels.add(parcelCheck);
            }
            else{
                someParcels.add(parcelCheck);   
            }
        }
    }
    public void drawUpsideDownFromFront(){
        root.getChildren().remove(2, root.getChildren().size());
        int check = 0;
        ArrayList<ParcelShape> someParcels = new ArrayList();
        for(int i = 0; i < containedShapes.size(); i++){
            ParcelShape parcelCheck = containedShapes.get(i);
            check+= parcelCheck.getShapeVector().y;
            if(check == 8){
                someParcels.add(parcelCheck);
                for(int p = someParcels.size() - 1; p > -1; p--){
                    ParcelShape parcel = someParcels.get(p);
                    int z = parcel.getPosition().getZ();
                    int y = parcel.getPosition().getY();
                    int x = parcel.getPosition().getX();

                    Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
                    box.setDrawMode(DrawMode.FILL);
                    box.setMaterial(parcel.getMaterial().toMaterial());
                    box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
                    box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
                    box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
                    root.getChildren().add(box);
                }
                someParcels.clear();
                check = 0;
            }
            else if(check > 8){
                for(int p = someParcels.size() - 1; p > -1; p--){
                    ParcelShape parcel = someParcels.get(p);
                    int z = parcel.getPosition().getZ();
                    int y = parcel.getPosition().getY();
                    int x = parcel.getPosition().getX();

                    Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
                    box.setDrawMode(DrawMode.FILL);
                    box.setMaterial(parcel.getMaterial().toMaterial());
                    box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
                    box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
                    box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
                    root.getChildren().add(box);
                }
                someParcels.clear();
                check = 0;
                someParcels.add(parcelCheck);
            }
            else{
                someParcels.add(parcelCheck);   
            }
        }
    }
    /* public void centerLeft(){
        root.getChildren().remove(2, root.getChildren().size());
        int i = 0;
        while(containedShapes.size()/2 - i < 0 || containedShapes.size()/2 + i >= containedShapes.size()){
            for(int m = 0; m < 2; m++){
                ParcelShape parcel = containedShapes.get(containedShapes.size()/2 - (i*((-1)^m)));
                int z = parcel.getPosition().getZ();
                int y = parcel.getPosition().getY();
                int x = parcel.getPosition().getX();

                Box box = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
                box.setDrawMode(DrawMode.FILL);
                box.setMaterial(parcel.getMaterial().toMaterial());
                box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
                box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
                box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
                root.getChildren().add(box);
                i++;
            }
        }
    }
    public void centerRight(){
        
    } */
}       
