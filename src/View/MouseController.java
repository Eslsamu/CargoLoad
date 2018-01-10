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
           pane.setRotationStart(event.getX(), event.getY());
        }
        if(event.getEventType().equals(MOUSE_DRAGGED)){
            pane.rotate(event.getX(), event.getY());
        }
    }
}
