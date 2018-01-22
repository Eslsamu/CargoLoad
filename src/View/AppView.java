package View;

import View.Options.OptionsPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class creates a BorderPane with the container, title and ButtonPane
 * @author Jordan, Basia
 * @version 1.2
 */
public class AppView extends BorderPane{
    private ContainerView containerView;
    /**
     * Constructor will create a BorderPane with title, ContainerView and ButtonPane
     */
    public AppView(){
        TitlePane title = new TitlePane();
        containerView = new ContainerView(title);
        OptionsPane options = new OptionsPane(containerView.getContainer(), containerView);
        
        VBox pane = new VBox();
        pane.getChildren().add(title);
        pane.getChildren().add(options);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(15);
        
        setCenter(containerView);
        setRight(pane);
        
        BorderPane.setMargin(pane, new Insets(15));
        try {
            FileInputStream input = new FileInputStream("background.jpeg");
            setBackground(new Background(new BackgroundImage(new Image(input,1000,1000,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException ex) {
            setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
        }
    }
    /**
     * Get method that will return the current instance of ContainerPane
     * @return current instance of ContainerPane
     */
    public ContainerPane getContainer(){
        return containerView.getContainer();
    }
}
