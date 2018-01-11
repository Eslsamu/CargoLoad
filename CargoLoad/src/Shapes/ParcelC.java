package Shapes;

public class ParcelC extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.RED;
	protected static int width = 3;
	protected static int height = 4;
	protected static int length = 5;
	
	protected static int value = 5;
	
	public ParcelC() {
		super(color, width, height, length, value);
	}
	
}