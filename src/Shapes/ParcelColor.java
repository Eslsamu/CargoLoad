package Shapes;

import javafx.scene.paint.Color;

public enum ParcelColor {
	
	EMPTY,
	BLUE, //A
	GREEN, //B
	RED; //C
	
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
