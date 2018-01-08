package Shapes;

public class ParcelB extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.GREEN;
	protected static int[] shape = {2,3,4};
	protected static int value = 4;
	
	public ParcelB() {
		super(color, shape, value);
	}
}
