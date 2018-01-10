package Shapes;

import Util.Coordinates;

public class ParcelB extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int[] shape = {3,3,3};
	protected static int value = 3;
	
	public ParcelB(Facing d) {
		super(color, shape, value, d);
	}
	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelA(super.direction);
		return cloneC;
	}
}
