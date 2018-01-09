package View;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class ContainerPane extends Pane {
	
	private Color BACKGROUND_COLOR = Color.BLACK;
	
	private Rotate xAxis = new Rotate(0, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(0, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    private double angleY = 0;
    private double angleX = 0;
    private double angleZ = 0;
    
    public ContainerPane() {
    	this.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR,CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
