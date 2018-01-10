package View;

import Model.ContainerModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AppView extends BorderPane{

	private final ContainerModel model;
	//private final ViewController controller;
	//private final BorderPane mainView;
	
	private Color BACKGROUND_COLOR = Color.BLACK;
	
	public AppView(ContainerModel model) {
		this.model = model;
		//this.controller = controller;
		
		//mainView = new BorderPane();
                ButtonPane bp = new ButtonPane();
                ContainerPane pane = new ContainerPane();
                Label title = new Label("Cargo Loader");
                title.setFont(new Font("Arial", 30));
                //title.setTextFill(Color.)
                setPrefSize(700,450);
                setCenter(pane);
                setTop(title);
                setAlignment(title, Pos.CENTER);
                setPadding(new Insets(15, 15, 15, 15));
                //mainView.setRight(bp);
		setRight(bp);
                setBackground(new Background(new BackgroundFill(Color.rgb(186, 216, 227), CornerRadii.EMPTY, Insets.EMPTY)));
		
		//this.controller = controller;
		
	}
	
	/**
	 * @return main view panel
	 */
	public Parent asParent() {
		return this;
	}
}
