package Util;

import Shapes.ShapeMaterial;
import javafx.scene.paint.Color;

/*
 * A 2d shape with four vertices that is used to construct 3d object (not used right now)
 */
public class Quadrilateral {
	
	private Coordinates vertex1;
	private Coordinates vertex2;
	private Coordinates vertex3;
	private Coordinates vertex4;
	private ShapeMaterial material;
	
	public Quadrilateral(Coordinates v1, Coordinates v2, Coordinates v3, Coordinates v4, ShapeMaterial m) {
		vertex1 = v1;
		vertex2 = v2;
		vertex3 = v3;
		vertex4 = v4;
		
		material = m;
	}
	
	/**
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Quadrilateral clone() {
		return new Quadrilateral(
				new Coordinates(vertex1.x,vertex1.y,vertex1.z),
				new Coordinates(vertex2.x,vertex2.y,vertex2.z),
				new Coordinates(vertex3.x,vertex3.y,vertex3.z),
				new Coordinates(vertex4.x,vertex4.y,vertex4.z), material);
	}
}
