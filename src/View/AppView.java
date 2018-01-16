package View;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
        container = new ContainerPane(750, 750, title);
        OptionsPane options = new OptionsPane(container);

        setCenter(container);
        setTop(title);   
        setRight(options);

        setPrefSize(700,450);
        setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
    }
    /**
     * Get method that will return the current instance of ContainerPane
     * @return current instance of ContainerPane
     */
    public ContainerPane getContainer(){
        return container;
    }
}
