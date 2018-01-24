package Util;


/**
 * A simple class representing x,y,z coordinates in a space
 * 
 */
public class Coordinates {
	
	public int x = 0;
	public int y = 0;
	public int z = 0;
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX(){
	    return x;
    }

    public int getY(){
        return y;
    }

    public int getZ(){
        return z;
    }

    public void setX(int newX){x=newX;}
    public void setY(int newY){x=newY;}
    public void setZ(int newZ){x=newZ;}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true; 
		if (obj instanceof Coordinates 
				&& ((Coordinates) obj).x == this.x
				&& ((Coordinates) obj).y == this.y
				&& ((Coordinates) obj).z == this.z)
			return true;
		return false;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Coordinates clone() {
		return new Coordinates(x, y, z);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	
	/*
	 * returns an integer 2D array that stores the (x,y,z) values in form of a vector
	 */
	public int[][] toVector() {
		int[][] array = {{x},
						{y},
						{z}};
		return array;
	}
	
	
}
