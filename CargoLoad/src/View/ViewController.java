package View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * This class controls the camera with keys
 * @author Jordan, Basia
 * @version 1.0
 */
public class ViewController implements EventHandler<KeyEvent>{
    //instance of ContainerPane
    private ContainerPane pane;
    //doubles representing angles of rotation
    private double angleY = 0;
    private double angleX = 0;
    private double angleZ = 0;
    /**
     * Constructor takes as a parameter the ContainerPane so it can call methods in it
     * @param pane an instance of ContainerPane
     */
    public ViewController(ContainerPane pane){
        this.pane = pane;
    }
    /**
     * Method handle handles the events. If a button is pressed, angle is changed.
     * @param event a KeyEvent
     */
    @Override
    public void handle(KeyEvent event){
        if(event.getCode() == KeyCode.RIGHT){
            angleY+= 10;
            pane.rotateY(angleY);
        }
        else if(event.getCode() == KeyCode.LEFT){
            angleY-= 10;
            pane.rotateY(angleY);
        }
        else if(event.getCode() == KeyCode.UP){
            angleX+= 10;
            pane.rotateX(angleX);
        }
        else if(event.getCode() == KeyCode.DOWN){
            angleX-= 10;
            pane.rotateX(angleX);
        }
        else if(event.getCode() == KeyCode.Q){
            angleZ+= 10;
            pane.rotateZ(angleZ);
        }
        else if(event.getCode() == KeyCode.W){
            angleZ-= 10;
            pane.rotateZ(angleZ);
        }
    }
}
