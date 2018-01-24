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
		positionContainer = new Coordinates(0,0,0);
	}
	
	public Coordinates getContainerPosition() {
		return positionContainer;
	}
	
	public void setContainerPosition(Coordinates p) {
		positionContainer.x=p.x;
		positionContainer.y=p.y;
		positionContainer.z=p.z;
	}
	
	public Coordinates getPositionShape() {
		return positionParentshape;
	}

	// creates a copy of a certain monimo (incl. coords)
	public Monimo clone(){
		Coordinates cC = positionContainer;
		Coordinates cP = positionParentshape;
		Monimo clone = new Monimo(new Coordinates(cP.x,cP.y,cP.z), material);
		clone.setContainerPosition(cC.clone());
		return clone;
	}
}
