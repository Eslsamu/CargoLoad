package Shapes;

public class ParcelA extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.GREEN;
	protected static int width = 2;
	protected static int height = 2;
	protected static int length = 4;
	protected static String name = "A";
	
	protected static int value = 3;
	
	public ParcelA() {
		super(color, width, height, length, value, name);
	}
	/*
	 * @see Shapes.ParcelShape#clone()
	 */
	@Override
	public ParcelA clone() {
		ParcelA clone = new ParcelA();
	
		clone.currentCoords = this.currentCoords.clone();
		clone.setOrientation(this.orientation);
		return clone;	
	}
}
