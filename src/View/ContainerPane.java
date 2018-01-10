package View;

import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ContainerPane extends Pane {
	
    private double CONTAINER_DEPTH = 16.5;
    private double CONTAINER_WIDTH = 2.5;
    private double CONTAINER_HEIGHT = 4.0;
	
    private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    
    private PerspectiveCamera camera;
    
    public PerspectiveCamera getCamera(){
        return camera;
    }
    
    /* public void setRotationStart(double x, double y){
        angleY = x - yAxis.getAngle();
        angleX = y - xAxis.getAngle();
    }
    public void rotate(double x, double y){
        yAxis.setAngle(x - angleX);
        xAxis.setAngle(-(y - angleY));
    } */
    public void rotateX(double angle){
        xAxis.setAngle(angle);
    }
    public void rotateY(double angle){
        yAxis.setAngle(angle);
    }
    public void rotateZ(double angle){
        zAxis.setAngle(angle);
    }
    public ContainerPane() {
    	//setPrefSize(400,400);
    	//Create and position camera
    	//shifts the origin of the space 
    	//setTranslateX(200);
    	//setTranslateY(200);
    	
    	//The 3 Boxes 
    	Box ground = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_DEPTH);
    	//Box side = new Box(CONTAINER_WIDTH, CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	//Box back = new Box(CONTAINER_WIDTH,CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	
    	ground.setDrawMode(DrawMode.LINE);
    	//side.setDrawMode(DrawMode.FILL);
    	//back.setDrawMode(DrawMode.FILL);
    	
    	ground.setMaterial(new PhongMaterial(Color.LIGHTPINK));
    	//side.setMaterial(new PhongMaterial(Color.RED));
    	//back.setMaterial(new PhongMaterial(Color.ORANGE));
        
        camera = new PerspectiveCamera(true);
        
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -30));
        
        getChildren().add(camera);
    	getChildren().add(ground);
    	//getChildren().add(side);
    	//getChildren().add(back);
    }
}       
