package Shapes;

import java.util.ArrayList;

import Util.Algebra;
import Util.Axis;

/*
 * PentominoShape is an abstract class for pentominoes. A pentomino has five children which are monimoes.
 * When the orientation in the container of the pentominoe has to be changed, then
 * the change occurs in each of its children.
 * Once it is placed in the container, it's coordinates and shape shouldn't be changed anymore. 
 */
public abstract class PentominoShape {
	
	final ArrayList<Monimo> children = new ArrayList<Monimo>();
	final String name;
	final int value;
	final ShapeColor color;
	
	public static void main(String[]args) {
		PentominoP testP = new PentominoP();
		System.out.println(testP.toString());
		
		testP.rotate(90, Axis.X);
		System.out.println(testP.toString());
		testP.rotate(180, Axis.Y);
		System.out.println(testP.toString());
		testP.rotate(270, Axis.Z);
		System.out.println(testP.toString());
	}
	
	public PentominoShape(String n, int v, ShapeColor c) {
		this.name = n;
		this.value = v;
		this.color = c;
		
		addChildren();
	}
	
	public void rotate(double angle, Axis ax) {
		for(Monimo m : children) {
			m.positionParentshape = Algebra.rotateUV(angle,ax,m.positionParentshape);
		}
	}
	
	public void reflect(Axis ax) {
		for(Monimo m: children ) {
			m.positionParentshape = 
		}
	}
	
	@Override
	public String toString() {
		String s = "Monimos:";
		for(Monimo m : children) {
			String c = m.positionParentshape.toString();
			s+=c;
		}
		return s;
	}
	
	public abstract void addChildren();
}
