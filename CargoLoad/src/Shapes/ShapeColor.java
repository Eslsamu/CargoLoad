package Shapes;

import javafx.scene.paint.Color;

public enum ShapeColor {
	
	EMPTY,
	BLUE, //A & L
	GREEN, //B & P
	RED; //C & T
	
	public Color toColor() {
        switch (this) {
            case EMPTY	: return Color.BLACK;
            case BLUE	: return Color.BLUE;
            case GREEN	: return Color.GREEN;
            case RED	: return Color.RED;
        }
		return null;
    }
}
