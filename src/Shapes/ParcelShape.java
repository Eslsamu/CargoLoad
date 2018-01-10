package Shapes;

import java.util.ArrayList;

import Util.Coordinates;

/*
 * An abstraction of the Parcel classes
 */
public abstract class ParcelShape {
	
	/*
	 * The fixed dimensions of the parcel
	 */
	private final int width;
	private final int length;
	private final int height;
	
	private final ShapeColor color;
	private final int value;
	
	/*
	 * represents the way the parcel is positioned(orientation and rotation) in space with 3 vectors 
	 */
	private int[] shape;
	
	/*
	 * The coordinates onto which the of the parcel is placed from it's current shape
	 */
	protected Coordinates currentCoords;
	
	/*
	 * A Parcel can face towards 3 different directions --> up/down(Z), left/right(Y), front/back(X),
	 * with 2 different rotations for each direction.
	 */
	protected Facing orientation;
	
	
	public ParcelShape(ShapeColor color, int w, int h, int l, int value) {
		this.color = color;
		this.value = value;
		
		this.width = w;
		this.length = l;
		this.height = h;
		
		shape[0] = width;
		shape[1] = height;
		shape[2] = length;
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
	 * @param currentCoords the current position to set
	 */
	public void setCurrentCoordinates(Coordinates coords) {
		this.currentCoords = coords;
	}
	
	/*
	 * @param o set the orientation of the parcel and changes it's shape accordingly
	 *  UpA,   //Length is Z Axis, Width is Y
		UpB,   //Length is Z Axis, Width is X
		RightA, //Length is Y Axis, Width is Z
		RightB, //Length is Y Axis, Width is X
		FrontA, //Length is X Axis, Width is Z
		FrontB; //Length is X Axis, Width is Y
	 */
	public void setOrientation(Facing o) {
		this.orientation = o; 
		switch(o) {
	        case UpA: {
	        	shape[0] = height;
	        	shape[1] = width;
	        	shape[2] = length;
	        	}
	        case UpB: {
	        	shape[0] = width;
	        	shape[1] = height;
	        	shape[2] = length;
	        	}
	        case RightA: {
	        	shape[0] = height;
	        	shape[1] = length;
	        	shape[2] = width;
	        	}
	        case RightB: {
	        	shape[0] = width;
	        	shape[1] = length;
	        	shape[2] = height;
	        	}
	        case FrontA: {
	        	shape[0] = length;
	        	shape[1] = height;
	        	shape[2] = width;
	        	}
	        case FrontB: {
	        	shape[0] = length;
	        	shape[1] = width;
	        	shape[2] = height;
	        	}
		}
	}
}
