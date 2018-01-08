package Shapes;

import Util.Coordinates;

public class ParcelC extends ParcelShape{

	protected static ShapeColor color = ShapeColor.RED;
	protected static int[] shape = {3,3,3};
	protected static int value = 5;
	
	public ParcelC(Coordinates coords) {
		super(color, shape, value, coords);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelC(new Coordinates(currentCoords.x,currentCoords.y,currentCoords.z));
		return cloneC;
	}
}
