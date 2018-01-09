package View;

import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ContainerPane extends Pane {
	
	private double CONTAINER_LENGTH = 16.5*10;
	private double CONTAINER_WIDTH = 2.5*10;
	private double CONTAINER_HEIGHT = 4.0*10;
	
	private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    private double angleY = 0;
    private double angleX = 0;
    private double angleZ = 0;
    
    public ContainerPane() {
    	setPrefSize(400,400);
    	
    	// Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -50));
    	
    	//shifts the origin of the space 
    	setTranslateX(200);
    	setTranslateY(200);
    	
    	//The 3 Boxes 
    	Box ground = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	//Box side = new Box(CONTAINER_WIDTH, CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	//Box back = new Box(CONTAINER_WIDTH,CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	
    	ground.setDrawMode(DrawMode.FILL);
    	//side.setDrawMode(DrawMode.FILL);
    	//back.setDrawMode(DrawMode.FILL);
    	
    	ground.setMaterial(new PhongMaterial(Color.LIGHTPINK));
    	//side.setMaterial(new PhongMaterial(Color.RED));
    	//back.setMaterial(new PhongMaterial(Color.ORANGE));
    	
    	getChildren().add(camera);
    	getChildren().add(ground);
    	//getChildren().add(side);
    	//getChildren().add(back);
    }
}
