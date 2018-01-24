package Shapes;
/**
 * Class representing parcel C
 * @author danyp
 */
public class ParcelC extends ParcelShape{
	
	protected static ShapeMaterial material = ShapeMaterial.RED;
	protected static int width = 3;
	protected static int height = 3;
	protected static int length = 3;
	
	protected static String name = "C";
	protected static int value = 5;
	/**
         * Construct class
         */
	public ParcelC() {
		super(material, name, value, width, height, length);;
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
        /**
         * 
         * @return cloned copy with coordinates
         */
        @Override 
        public ParcelC cloneWithCoordinates(){
            ParcelC clone = new ParcelC();
            clone.setOrientation(this.orientation);
            clone.setCurrentCoordinates(this.positionParcelContainer.clone());
            return clone;
        }
}
