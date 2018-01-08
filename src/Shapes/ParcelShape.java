package Shapes;

/*
 * 
 */
public abstract class ParcelShape {
	
	final ShapeColor color;
	final int[] shape;
	final int value;
	
	public ParcelShape(ShapeColor color, int[] shape, int value) {
		this.color = color;
		this.shape = shape;
		this.value = value;
	}
	
	
}
