package View;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class MouseController implements EventHandler<MouseEvent> {
    private ContainerPane pane;
    
    public MouseController(ContainerPane pane){
        this.pane = pane;
    }
     @Override
    public void handle(MouseEvent event){
        if(event.getEventType().equals(MOUSE_PRESSED)){
            /*
            method in ContainerPane that gets the coordinates of the mouse, when pressed
            event.getX() and event.getY()
            */
        }
        if(event.getEventType().equals(MOUSE_DRAGGED)){
            /*
            method in ContainerPane that sets the rotation of the camera according to the coordinates
            of the mouse
            */
        }
    }
}
