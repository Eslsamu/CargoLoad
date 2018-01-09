package View;

import Model.ContainerModel;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class AppView {

	private final ContainerModel model;
	private final ViewController controller;
	private final BorderPane mainView;
	private final ContainerPane containerP;
	
	private Color BACKGROUND_COLOR = Color.BLACK;
	
	public AppView(ContainerModel model, ViewController controller) {
		this.model = model;
		this.controller = controller;
		
		mainView = new BorderPane();
		containerP = new ContainerPane();
		
		mainView.setCenter(containerP);
		mainView.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR,CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	/**
	 * @return main view panel
	 */
	public Parent asParent() {
		return mainView;
	}
}
