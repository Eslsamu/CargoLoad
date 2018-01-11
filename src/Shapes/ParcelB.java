package Shapes;

public class ParcelB extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int width = 3;
	protected static int height = 3;
	protected static int length = 3;
	
	protected static String name = "B";
	protected static int value = 3;
	
	public ParcelB() {
		super(color, width, height, length, value, name);
	}
	
	/*
	 * @see Shapes.ParcelShape#clone()
	 */
	@Override
	public ParcelB clone() {
		ParcelB clone = new ParcelB();
	
		clone.currentCoords = this.currentCoords.clone();
		clone.setOrientation(this.orientation);
		return clone;	
	}
}
