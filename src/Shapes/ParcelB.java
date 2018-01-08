package Shapes;

import Util.Coordinates;

public class ParcelB extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.GREEN;
	protected static int[] shape = {2,3,4};
	protected static int value = 4;
	
	public ParcelB(Coordinates coords) {
		super(color, shape, value, coords);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelB(new Coordinates(currentCoords.x,currentCoords.y,currentCoords.z));
		return cloneC;
	}
}
