package Shapes;

public class ParcelA extends ParcelShape{
	
	protected static ShapeMaterial material = ShapeMaterial.GREEN;
	protected static int width = 2;
	protected static int height = 2;
	protected static int length = 4;
	protected static String name = "A";
	
	protected static int value = 3;
	
	public ParcelA() {
		super(material, width, height, length, value, name);
	}
	/*
	 * @see Shapes.ParcelShape#clone()
	 */
	@Override
	public ParcelA clone() {
		ParcelA clone = new ParcelA();
		clone.setOrientation(this.orientation);
		return clone;	
	}
}
