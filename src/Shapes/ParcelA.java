package Shapes;

public class ParcelA {

<<<<<<< HEAD
public class ParcelA extends ParcelShape{
	
	protected static ShapeColor color = ShapeColor.BLUE;
	protected static int[] shape = {2,2,4};
	protected static int value = 3;
	
	public ParcelA(Coordinates coords) {
		super(color, shape, value, coords);
	}

	@Override
	public ParcelShape clone() {
		ParcelShape cloneC = new ParcelA(new Coordinates(currentCoords.x,currentCoords.y,currentCoords.z));
		return cloneC;
	}
	
	
=======
>>>>>>> a967f02b93d181c6c000fb742fa6f9222f2c91e4
}
