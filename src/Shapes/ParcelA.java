package Shapes;

import Util.Coordinates;

public class ParcelA extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int[] shape = {2,2,4};
	protected static int value = 3;
	
	public ParcelA(Coordinates coords, Facing d) {
		super(color, shape, value, coords, d);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelA(new Coordinates(currentCoords.x,currentCoords.y,currentCoords.z),super.direction);
		return cloneC;
	}
}
