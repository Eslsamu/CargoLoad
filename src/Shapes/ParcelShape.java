package Shapes;

import java.util.ArrayList;

import Util.Coordinates;

/*
 * An abstraction of the Parcel classes
 */
public abstract class ParcelShape {
	
	final ShapeColor color;
	final int[] shape;
	final int value;
	
	/*
	 * The coordinates onto which the fixed point 
	 */
	protected Coordinates currentCoords;
	
	/*
	 * A Parcel can face towards 3 different directions --> up/down, left/right, front/back
	 */
	protected Facing direction;
	
	public ParcelShape(ShapeColor color, int[] shape, int value, Coordinates coords, Facing d) {
		this.color = color;
		this.shape = shape;
		this.value = value;
		this.currentCoords = coords;
		this.direction = d;
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
