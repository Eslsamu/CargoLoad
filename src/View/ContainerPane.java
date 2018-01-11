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
    //box typeA
    private double TypeA_Depth = 1.0;
    private double TypeA_Width = 1.0;
    private double TypeA_Height = 1.0;
    //box typeB
    private double TypeB_Depth = 1.0;
    private double TypeB_Width = 1.5;
    private double TypeB_Height = 2.0;
    //box typeC
    private double TypeC_Depth = 1.5;
    private double TypeC_Width = 1.5;
    private double TypeC_Height = 1.5;
    //set rotation points
    private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    
    /**
     * Constructor creates a Scene with container, boxes and a camera is being set to it.
     */
    public ContainerPane() {
        //add all containers, boxes and camera to a group
        Group root = new Group();
        
    	//creating container
    	Box container = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_DEPTH);
        //making the front of the container transparent
        container.setCullFace(CullFace.FRONT);
        //drawing the container with only lines
        container.setDrawMode(DrawMode.LINE);
        //setting the color of the container
        container.setMaterial(new PhongMaterial(Color.ORANGE));
    	root.getChildren().add(container);
        //create box typeA
        
        
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();


            givenParcels.add(new ParcelA());
            givenParcels.add(new ParcelB());
            givenParcels.add(new ParcelC());



        ContainerModel container2 = new ContainerModel();
        container2.setParcelList(givenParcels);
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        int p = 0;
        container2.solve(maxValueContainer, p);
        ArrayList<ParcelShape> containedShapes = container2.getContainedParcels();
        
        Color[][][] displayedMatrix = new Color[(int)(CONTAINER_DEPTH*2)][(int)(CONTAINER_HEIGHT*2)][(int)(CONTAINER_WIDTH*2)];
        for(int i = 0; i < containedShapes.size(); i++){
            Color ranColor = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            ParcelShape parcel = containedShapes.get(i);
            int z = parcel.getCurrentCoordinates().getZ();
            int y = parcel.getCurrentCoordinates().getY();
            int x = parcel.getCurrentCoordinates().getX();
            
            for (int zCoord = z; zCoord < z + parcel.getShape()[2]; zCoord++) {
                for (int yCoord = y; yCoord < y + parcel.getShape()[1]; yCoord++) {
                    for (int xCoord = x; xCoord < x + parcel.getShape()[0]; xCoord++) {
                        displayedMatrix[zCoord][yCoord][xCoord] = ranColor;
                    }
                }
            }
        }
        for(int z = 0; z < CONTAINER_DEPTH*2; z++){
            for(int y = 0; y < CONTAINER_HEIGHT*2; y++){
                for(int x = 0; x < CONTAINER_WIDTH*2; x++){
                    if(displayedMatrix[z][y][x] == null){
                        displayedMatrix[z][y][x] = Color.TRANSPARENT;
                    }
                }
            }
        }
        
        for(int z = 0; z < CONTAINER_DEPTH*2; z++){
            for(int y = 0; y < CONTAINER_HEIGHT*2; y++){
                for(int x = 0; x < CONTAINER_WIDTH*2; x++){
                    Box box = new Box(Box_Width, Box_Height, Box_Depth);
                    box.setDrawMode(DrawMode.FILL);
                    box.setMaterial(new PhongMaterial(displayedMatrix[z][y][x]));
                    box.setTranslateX(-CONTAINER_WIDTH/2 + Box_Width/2 + z);
                    box.setTranslateY(-CONTAINER_HEIGHT/2 + Box_Height/2 + y);
                    box.setTranslateZ(CONTAINER_DEPTH/2 - Box_Depth/2 - x);
                    root.getChildren().add(box);
                }
            }
        }    
        /* for(int i = 0; i < 2; i++){
            Box typeA = new Box(TypeA_Width, TypeA_Height, TypeA_Depth);
            typeA.setDrawMode(DrawMode.FILL);
            /* PhongMaterial sth = new PhongMaterial();  
            sth.setDiffuseColor(Color.BLANCHEDALMOND);
            sth.setSpecularColor(Color.LIGHTBLUE);
            typeA.setMaterial(sth);*/ /*
            typeA.setMaterial(new PhongMaterial(Color.LIGHTBLUE));
            //set positions
            typeA.setTranslateX(-CONTAINER_WIDTH/2 + TypeA_Width*(1.5));
            typeA.setTranslateY(CONTAINER_HEIGHT/2 - TypeA_Height/2 - TypeA_Height*i);
            typeA.setTranslateZ(CONTAINER_DEPTH/2 - TypeA_Depth/2);
            root.getChildren().add(typeA);
        } */ /*
        for(int i = 0; i < 3; i++){
            Box typeA = new Box(TypeA_Width, TypeA_Height, TypeA_Depth);
            typeA.setDrawMode(DrawMode.FILL);
            typeA.setMaterial(new PhongMaterial(Color.LIGHTBLUE));
            //set positions
            typeA.setTranslateX(-CONTAINER_WIDTH/2 + TypeA_Width*i + 0.5);
            typeA.setTranslateY(CONTAINER_HEIGHT/2 - TypeA_Height/2 - TypeA_Height*2);
            typeA.setTranslateZ(CONTAINER_DEPTH/2 - TypeA_Depth/2);
            root.getChildren().add(typeA);
        } */
        //create a camera
        Camera camera = new PerspectiveCamera(true);
        //add possible rotations and position of camera
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -35));
        
        root.getChildren().add(camera);
        
        //create a Scene from the group
        SubScene subScene = new SubScene(root, 750, 750);
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
}       
