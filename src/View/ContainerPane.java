package View;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class ContainerPane extends Pane {
	
	private Color BACKGROUND_COLOR = Color.BLACK;
	private double CONTAINER_LENGTH = 16.5;
	private double CONTAINER_WIDTH = 2.5;
	private double CONTAINER_HEIGHT = 4.0;
	
	private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    private double angleY = 0;
    private double angleX = 0;
    private double angleZ = 0;
    
    public ContainerPane() {
    	this.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR,CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	//shifts the origin of the space 
    	setTranslateX(200);
    	setTranslateY(200);
    	
    	//The 3 Boxes 
    	Box ground = new Box(CONTAINER_WIDTH , 0, CONTAINER_LENGTH);
    	Box side = new Box(0, CONTAINER_HEIGHT, CONTAINER_LENGTH);
    	Box back = new Box(CONTAINER_WIDTH,CONTAINER_HEIGHT, 0);
    	
    	getChildren().add(ground);
    	getChildren().add(side);
    	getChildren().add(back);
    }
}
