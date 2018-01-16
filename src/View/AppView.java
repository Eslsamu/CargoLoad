package View;

import Model.ContainerModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class creates a BorderPane with the container, title and ButtonPane
 * @author Jordan, Basia
 * @version 1.2
 */
public class AppView extends BorderPane{
    private ContainerPane container;
    /**
     * Constructor will create a BorderPane with title, ContainerPane and ButtonPane
     */
    public AppView(){
            TitlePane title = new TitlePane();
            container = new ContainerPane(500, 500, title);
            ButtonPane bp = new ButtonPane(container);
            
            setCenter(container);
            setTop(title);
            setRight(bp);
            setAlignment(title, Pos.CENTER);
            setPrefSize(700,450);
            setPadding(new Insets(15, 15, 15, 15));
            setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));


    }
    /**
     * Get method that will return the current instance of ContainerPane
     * @return current instance of ContainerPane
     */
    public ContainerPane getContainer(){
        return container;
    }
}
