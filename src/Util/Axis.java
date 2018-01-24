package Util;

/**
 * Axises around which a rotation can be made
 * @author danyp
 */
public enum Axis {
	X,
	Y,
	Z;
	/**
         * 
         * @return coordinates
         */
	public Coordinates toUnitVector() {
        switch (this) {
            case X	: return new Coordinates(1,0,0);
            case Y	: return new Coordinates(0,1,0);
            case Z	: return new Coordinates(0,0,1);
        }
		return null;
    }
}
