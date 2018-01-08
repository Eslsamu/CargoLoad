package Shapes;

import Util.Coordinates;

/*
 * An abstraction of the Parcel classes
 */
public abstract class ParcelShape {
	
	final ShapeColor color;
	final int[] shape;
	final int value;
	
	/*
	 * The coordinates onto which the parcel is placed in the container
	 */
	final Coordinates coords;
	
	public ParcelShape(ShapeColor color, int[] shape, int value, Coordinates coords) {
		this.color = color;
		this.shape = shape;
		this.value = value;
		this.coords = coords;
	}
	
	
}
