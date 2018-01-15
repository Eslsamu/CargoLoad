package Shapes;

public class ParcelC extends ParcelShape{
	
	protected static ShapeMaterial material = ShapeMaterial.RED;
	protected static int width = 3;
	protected static int height = 3;
	protected static int length = 3;
	
	protected static String name = "C";
	protected static int value = 5;
	
	public ParcelC() {
		super(material, width, height, length, value, name);
	}
	
	/*
	 * @see Shapes.ParcelShape#clone()
	 */
	@Override
	public ParcelC clone() {
		ParcelC clone = new ParcelC(); 
		clone.setOrientation(this.orientation);
		return clone;	
	}
}
