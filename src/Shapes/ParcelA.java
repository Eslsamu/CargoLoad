package Shapes;

public class ParcelA extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int[] shape = {2,2,4};
	protected static int value = 3;
	
	public ParcelA() {
		super(color, shape, value);
	}
}
