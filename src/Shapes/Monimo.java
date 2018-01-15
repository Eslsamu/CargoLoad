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
	
	private ShapeColor color;
	
	
	public Monimo(Coordinates p, ShapeColor cr) {
		this.positionParentshape = p;
		this.color = cr;
	}
	
	private Coordinates getContainerPosition() {
		return positionContainer;
	}
	
	private void setContainerPosition(Coordinates p) {
		positionContainer = p;
	}
}
