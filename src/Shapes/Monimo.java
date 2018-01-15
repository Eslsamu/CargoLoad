package Shapes;

import Util.Coordinates;

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
