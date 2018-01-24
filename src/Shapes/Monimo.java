package Shapes;

import Util.Coordinates;
/*
 * A cube with the dimensions 0,5 x 0,5 x 0,5. Polynominoes constist of multiple monimos.
 * Is used in this program to represent the shape of a pentomino. 
 */
public class Monimo {
	/*
	 * the position in the container matrix after being placed
	 */
	protected Coordinates positionContainer;
	/*
	 * the position in the parent(pentomino) shape
	 */
	protected Coordinates positionParentshape;
	
	private ShapeMaterial material;
	
	/**
         * Construct monimo
         * @param p coordinates
         * @param m material
         */
	public Monimo(Coordinates p, ShapeMaterial m) {
		this.positionParentshape = p;
		this.material = m;
		positionContainer = new Coordinates(0,0,0);
	}
	/**
         * 
         * @return coordinates
         */
	public Coordinates getContainerPosition() {
		return positionContainer;
	}
	/**
         * Set coordinates
         * @param p coordinates
         */
	public void setContainerPosition(Coordinates p) {
		positionContainer.x=p.x;
		positionContainer.y=p.y;
		positionContainer.z=p.z;
	}
	/**
         * @return coordinates
         */
	public Coordinates getPositionShape() {
		return positionParentshape;
	}
        /**
         * Clone the monimo
         * @return a cloned copy of monimo
         */
	public Monimo clone(){
		Coordinates cC = positionContainer;
		Coordinates cP = positionParentshape;
		Monimo clone = new Monimo(new Coordinates(cP.x,cP.y,cP.z), material);
		clone.setContainerPosition(cC.clone());
		return clone;
	}
}
