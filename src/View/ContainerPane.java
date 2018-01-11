package View;

import Model.ContainerModel;
import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
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
    public ContainerPane() throws FileNotFoundException {
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
        container2.solve(maxValueContainer);
        ArrayList<ParcelShape> containedShapes = container2.getContainedParcels();
        
        System.out.println("Size: " + containedShapes.size());
        System.out.println("Z0: " + containedShapes.get(0).getCurrentCoordinates().getZ());
        System.out.println("Z63: " + containedShapes.get(63).getCurrentCoordinates().getZ());
        
        for(int i = 0; i < containedShapes.size(); i++){
            Color ranColor = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
           
            ParcelShape parcel = containedShapes.get(i);
            int z = parcel.getCurrentCoordinates().getZ();
            int y = parcel.getCurrentCoordinates().getY();
            int x = parcel.getCurrentCoordinates().getX();
            
            Box box = new Box(Box_Width*parcel.getShape()[0], Box_Height*parcel.getShape()[1], Box_Depth*parcel.getShape()[2]);
            box.setDrawMode(DrawMode.FILL);
            PhongMaterial material = new PhongMaterial();
            FileInputStream inputstream = new FileInputStream("C:\\Users\\danyp\\Desktop\\CS\\Project3\\BoxA.png"); 
            Image image = new Image(inputstream);
            material.setDiffuseMap(image);
            //box.setMaterial(new PhongMaterial(ranColor));
            box.setMaterial(material);
            box.setTranslateX(-CONTAINER_WIDTH/2 + box.getWidth()/2 + 0.5*x);
            box.setTranslateY(-CONTAINER_HEIGHT/2 + box.getHeight()/2 + 0.5*y);
            box.setTranslateZ(CONTAINER_DEPTH/2 - box.getDepth()/2 - 0.5*z);
            root.getChildren().add(box);
        }
        
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
