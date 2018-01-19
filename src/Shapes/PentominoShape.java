package Shapes;

import java.util.ArrayList;

import Util.Algebra;
import Util.Axis;
import Util.Coordinates;

/*
 * PentominoShape is an abstract class for pentominoes. A pentomino has five children which are monimoes.
 * When the orientation in the container of the pentominoe has to be changed, then
 * the change occurs in each of its children.
 * Once it is placed in the container, it's coordinates and shape shouldn't be changed anymore. 
 */
public abstract class PentominoShape {
	
	protected final ArrayList<Monimo> children = new ArrayList<Monimo>();
	protected final String name;
	protected final int value;
	protected final ShapeMaterial material;
	
	//for debug
	private String label = ".";
	
	public static void main(String[]args) {
		PentominoP testP = new PentominoP();
		System.out.println(testP.toString());
		
		testP.rotate(90, Axis.X);
		System.out.println(testP.toString());
		testP.rotate(180, Axis.Y);
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
	public int getValue(){
            return value;
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
	
	public String getName() {
		return name;
	}
	
	/*
	 * @return children returns the list of monimoes
	 */
	public ArrayList<Monimo> getChildren(){
		return children;
	}
	/*
	 * @param c the coordinates onto which the monimo with (0,0,0) shape coordinates is to be placed
	 */
	public void setContainerPosition(Coordinates c) {
		for(Monimo m : children) {
			m.setContainerPosition(new Coordinates(m.getPositionShape().x + c.x, m.getPositionShape().y + c.y, m.getPositionShape().z + c.z));
		}
	}
	
	public void setLabel(String l) {
		label = l;
	}
	public String getLabel() {
		return label;
	}
}
