package Shapes;


import Util.Coordinates;

/*
 * An abstraction of the Parcel classes
 */
public abstract class ParcelShape {
	
	/*
	 * The fixed dimensions of the parcel
	 */
	private final int width;
	private final int length;
	private final int height;
	
	private final String name;
	private final ShapeColor color;
	private final int value;
	
	/*
	 * represents the way the parcel is positioned(orientation and rotation) in space with 3 vectors 
	 */
	private Coordinates shapeVector;
	
	/*
	 * The coordinates onto which the of the parcel is placed from it's current shape
	 */
	protected Coordinates positionContainer;
	
	/*
	 * A Parcel can face towards 3 different directions --> up/down(Z), left/right(Y), front/back(X),
	 * with 2 different rotations for each direction.
	 */
	protected Facing orientation;
	
	
	public ParcelShape(ShapeColor color, int w, int h, int l, int value, String name) {
		this.color = color;
		this.value = value;
		this.name = name;
		
		this.width = w;
		this.length = l;
		this.height = h;
		
		shapeVector = new Coordinates(w,l,h);
		
		orientation = Facing.UpA;
		positionContainer = new Coordinates(0,0,0);	
	}
	
	
	public Coordinates getShapeVector(){
	    return shapeVector;
    }

    public int getValue(){
	    return value;
    }

    public double getRatio() {
        return value / (shapeVector.x * shapeVector.y * shapeVector.z);
    }

	/**
	 * @return the color
	 */
	public ShapeColor getColor() {
		return color;
	}
	
	/**
	 * @return the position in the container
	 */
	public Coordinates getPosition() {
		return this.positionContainer;
	}
	
	/**
	 * @param set the positionContainer
	 */
	public void setCurrentCoordinates(Coordinates coords) {
		this.positionContainer = coords;
	}
	
	/*
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract ParcelShape clone();
	
	/*
	 * @param o set the orientation of the parcel and changes it's shape accordingly
	 *  UpA,   //Length is Z Axis, Width is Y
		UpB,   //Length is Z Axis, Width is X
		RightA, //Length is Y Axis, Width is Z
		RightB, //Length is Y Axis, Width is X
		FrontA, //Length is X Axis, Width is Z
		FrontB; //Length is X Axis, Width is Y
	*/
	public void setOrientation(Facing o) {
		this.orientation = o; 
		switch(o) {
	        case UpA: {
	        	shapeVector.x = height;
	        	shapeVector.y = width;
	        	shapeVector.z = length;
	        	break;
	        	}
	        case UpB: {
	        	shapeVector.x = width;
	        	shapeVector.y = height;
	        	shapeVector.z = length;
	        	break;
	        	}
	        case RightA: {
	        	shapeVector.x = height;
	        	shapeVector.y = length;
	        	shapeVector.z = width;
	        	break;
	        	}
	        case RightB: {
	        	shapeVector.x = width;
	        	shapeVector.y = length;
	        	shapeVector.z = height;
	        	break;
	        	}
	        case FrontA: {
	        	shapeVector.x = length;
	        	shapeVector.y = height;
	        	shapeVector.z = width;
	        	break;
	        	}
	        case FrontB: {
	        	shapeVector.x = length;
	        	shapeVector.y = width;
	        	shapeVector.z = height;
	        	break;
	        	}
		}
	}
	
}
