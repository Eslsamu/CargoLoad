package Shapes;

import java.util.ArrayList;

import Util.Coordinates;

/*
 * An abstraction of the Parcel classes
 */
public abstract class ParcelShape {
	
	final ShapeColor color;
	int[] shape;
	int value;
	
	/*
	 * The coordinates onto which the head of the parcel is placed
	 */
	protected Coordinates currentCoords;
	
	/*
	 * A Parcel can face towards 3 different directions --> up/down, left/right, front/back
	 */
	protected Facing orientation;
	
	public ParcelShape(ShapeColor color, int[] shape, int value) {
		this.color = color;
		this.shape = shape;
		this.value = value;
	}

	public int[] getShape(){
	    return shape;
    }

    public int getValue(){
	    return value;
    }
    
	/**
	 * @return the color
	 */
	public ShapeColor getColor() {
		return color;
	}
	
	/**
	 * @return the current Coordinates
	 */
	public Coordinates getCurrentCoordinates() {
		return this.currentCoords;
	}
	
	/**
	 * @param _currentPosition the _currentPosition to set
	 */
	public void setCurrentCoordinates(Coordinates coords) {
		this.currentCoords = coords;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract ParcelShape clone();
}
