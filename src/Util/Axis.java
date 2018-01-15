package Util;


public enum Axis {
	X,
	Y,
	Z;
	
	public Coordinates toUnitVector() {
        switch (this) {
            case X	: return new Coordinates(1,0,0);
            case Y	: return new Coordinates(0,1,0);
            case Z	: return new Coordinates(0,0,1);
        }
		return null;
    }
}
