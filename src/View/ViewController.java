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
            if(angleY >= 360){
                angleY-= 360;
            }
            else if(angleY < 0){
                angleY+= 360;
            }
            if(angleY >= 90 && angleY <= 270){
                pane.drawFromBack();
            }
            else{
                pane.drawFromFront();
            }
            pane.rotateY(angleY);
        }
        else if(event.getCode() == KeyCode.LEFT){
            angleY-= 10;
            if(angleY >= 360){
                angleY-= 360;
            }
            else if(angleY < 0){
                angleY+= 360;
            }
            if(angleY >= 90 && angleY <= 270){
                pane.drawFromBack();
            }
            else{
                pane.drawFromFront();
            }
            pane.rotateY(angleY);
        }
        else if(event.getCode() == KeyCode.UP){
            angleX+= 10;
            if(angleX >= 360){
                angleX-= 360;
            }
            else if(angleX < 0){
                angleX+= 360;
            }
            /* if(angleX >= 90 && angleX <= 270){
                pane.drawFromBack();
            }
            else{
                pane.drawFromFront();
            } */
            pane.rotateX(angleX);
        }
        else if(event.getCode() == KeyCode.DOWN){
            angleX-= 10;
            if(angleX >= 360){
                angleX-= 360;
            }
            else if(angleX < 0){
                angleX+= 360;
            }
            /* if(angleX >= 90 && angleX <= 270){
                pane.drawFromBack();
            }
            else{
                pane.drawFromFront();
            } */
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
        else if(event.getCode() == KeyCode.P){
            System.out.println("Z: " + angleZ);
            System.out.println("Y: " + angleY);
            System.out.println("X: " + angleX);
        }
        else if(event.getCode() == KeyCode.R){
            angleZ = 0;
            angleY = 0;
            angleX = 0;
            pane.rotateZ(angleZ);
            pane.rotateY(angleY);
            pane.rotateX(angleX);
            pane.drawFromFront();
        }
    }
}
