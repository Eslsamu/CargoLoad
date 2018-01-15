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
	final ShapeMaterial material;
	
	public static void main(String[]args) {
		PentominoP testP = new PentominoP();
		System.out.println(testP.toString());
		
		testP.rotate(90, Axis.X);
		System.out.println(testP.toString());
		testP.rotate(180, Axis.Y);
		System.out.println(testP.toString());
		testP.rotate(270, Axis.Z);
		System.out.println(testP.toString());
		
		testP.moveToOrigin();
		System.out.println(testP.toString());
	}
	
	public PentominoShape(String n, int v, ShapeMaterial m) {
		this.name = n;
		this.value = v;
		this.material = m;		
		addChildren();
	}
	
	public void rotate(double angle, Axis ax) {
		for(Monimo m : children) {
			m.positionParentshape = Algebra.rotateUV(angle,ax,m.positionParentshape);
		}
	}
	
	public void reflect(Axis ax) {
		for(Monimo m: children ) {
			m.positionParentshape = Algebra.reflect(ax, m.positionParentshape);
		}
	}
	/*
	 * this method finds the monomio O which has the lowest z, y and x coordinate of the shape and translates 
	 * every monimo so that O is at 0,0,0. 
	 * this is used after each time the pentomino is rotated, to shift it again properly into it's shape coordinate system(not container).
	 */
	public void moveToOrigin() {
		Monimo o = children.get(0);
		for(Monimo m: children ) {
			if(m.positionParentshape.z < o.positionParentshape.z){
				o = m;
			}
			else if(m.positionParentshape.z == o.positionParentshape.z && m.positionParentshape.y < o.positionParentshape.y) {
				o = m;
			}
			else if(m.positionParentshape.z == o.positionParentshape.z && m.positionParentshape.y == o.positionParentshape.y &&
					m.positionParentshape.x < o.positionParentshape.x) {
				o = m;
			}
		}
		int xDiff = o.positionParentshape.x;
		int yDiff = o.positionParentshape.y;
		int zDiff = o.positionParentshape.z;
		for(Monimo m: children) {
			m.positionParentshape.x -= xDiff;
			m.positionParentshape.y -= yDiff;
			m.positionParentshape.z -= zDiff;
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
	
	/*
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract PentominoShape clone();
	
	public abstract void addChildren();
}
