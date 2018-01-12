package View;

import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class AppView extends BorderPane{
	private ContainerPane container;
	public AppView() throws FileNotFoundException {
            
                ButtonPane bp = new ButtonPane();
                container = new ContainerPane();
                Label title = new Label("Cargo Loader");
                title.setFont(new Font("Arial", 30));
                
                setPrefSize(700,450);
                setCenter(container);
                setTop(title);
                setAlignment(title, Pos.CENTER);
                setPadding(new Insets(15, 15, 15, 15));
                //mainView.setRight(bp);
		setRight(bp);
                //setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
		
		
	}
	
	/**
	 * @return main view panel
	 */
	public Parent asParent() {
		return this;
	}
        public ContainerPane getContainer(){
            return container;
        }
}
