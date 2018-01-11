package View;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    	//creating container
    	Box container = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_DEPTH);
        //making the front of the container transparent
        container.setCullFace(CullFace.FRONT);
        //drawing the container with only lines
        container.setDrawMode(DrawMode.LINE);
        //setting the color of the container
        container.setMaterial(new PhongMaterial(Color.ORANGE));
        
        //create box typeA
        Box typeA = new Box(TypeA_Width, TypeA_Height, TypeA_Depth);
    	typeA.setDrawMode(DrawMode.FILL);
    	typeA.setMaterial(new PhongMaterial(Color.LIGHTBLUE));
        //set positions
    	typeA.setTranslateX(-CONTAINER_WIDTH/2 + TypeA_Width/2);
        typeA.setTranslateY(CONTAINER_HEIGHT/2 - TypeA_Height/2);
        typeA.setTranslateZ(CONTAINER_DEPTH/2 - TypeA_Depth/2);
        
        //create a camera
        Camera camera = new PerspectiveCamera(true);
        //add possible rotations and position of camera
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -35));
        
        //add all containers, boxes and camera to a group
        Group root = new Group();
        root.getChildren().add(camera);
    	root.getChildren().add(container);
        root.getChildren().add(typeA);
        
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
