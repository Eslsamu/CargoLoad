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
	
	
	public Monimo(Coordinates p, ShapeMaterial m) {
		this.positionParentshape = p;
		this.material = m;
	}
	
	private Coordinates getContainerPosition() {
		return positionContainer;
	}
	
	private void setContainerPosition(Coordinates p) {
		positionContainer = p;
	}
}
