package View;

import Model.ContainerModel;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AppView {

	private final ContainerModel model;
	private final ViewController controller;
	private final BorderPane mainView;
	
	public AppView(ContainerModel model, ViewController controller) {
		this.model = model;
		this.controller = controller;
		
		mainView = new BorderPane();
		
		
	}
	
	/**
	 * @return main view panel
	 */
	public Parent asParent() {
		return mainView;
	}
}
