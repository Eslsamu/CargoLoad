package Shapes;

import Util.Coordinates;

public class ParcelB extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int[] shape = {3,3,3};
	protected static int value = 3;
	
	public ParcelB(Coordinates coords, Facing d) {
		super(color, shape, value, coords, d);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelA(new Coordinates(currentCoords.x,currentCoords.y,currentCoords.z),super.direction);
		return cloneC;
	}
}
