package Shapes;

import Util.Coordinates;

public class ParcelC extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.RED;
	protected static int[] shape = {2,3,4};
	protected static int value = 3;
	
	public ParcelC(Coordinates coords, Facing d) {
		super(color, shape, value, coords, d);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelA(new Coordinates(super.currentCoords.x,super.currentCoords.y,super.currentCoords.z),super.direction);
		return cloneC;
	}
	
}
