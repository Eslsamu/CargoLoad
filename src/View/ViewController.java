package View;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ViewController implements EventHandler<KeyEvent>{
    private ContainerPane pane;
    double angleY = 0;
    double angleX = 0;
    double angleZ = 0;
    
    public ViewController(ContainerPane pane){
        this.pane = pane;
    }
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
