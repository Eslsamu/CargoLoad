package View;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

/**
 * This class controls the camera with mouse movement
 * @author Jordan, Basia
 * @version 1.0
 */
public class MouseController implements EventHandler<MouseEvent> {
    //instance of ContainerPane
    private ContainerPane pane;
    //doubles representing the angles of rotation
    private double angleY = 0;
    private double angleX = 0;
    /**
     * Constructor takes as a parameter the ContainerPane so it can call methods in it
     * @param pane an instance of ContainerPane
     */
    public MouseController(ContainerPane pane){
        this.pane = pane;
    }
    /**
     * Method handle handles the events. If mouse is pressed we take current coordinates, so the container doesn't
     * start moving and then we move it when the mouse moves.
     * @param event a MouseEvent
     */
    @Override
    public void handle(MouseEvent event){
        if(event.getEventType().equals(MOUSE_PRESSED)){
            angleX = event.getX();
            angleY = event.getY();
        }
        if(event.getEventType().equals(MOUSE_DRAGGED)){
            pane.rotateX(-(event.getY() - angleY));
            pane.rotateY(event.getX() - angleX);
        }
    }
}
