package Shapes;

import java.util.ArrayList;

import Util.Algebra;
import Util.Axis;
import Util.Coordinates;

/**
 * PentominoShape is an abstract class for pentominoes. A pentomino has five children which are monimoes.
 * When the orientation in the container of the pentominoe has to be changed, then
 * the change occurs in each of its children.
 * Once it is placed in the container, it's coordinates and shape shouldn't be changed anymore. 
 */
public abstract class PentominoShape extends Shape{
	
	protected final ArrayList<Monimo> children = new ArrayList<Monimo>();
        /**
         * @return material
         */
	public ShapeMaterial getMaterial(){
		return material;

	}
        /**
         * Constructor
         * @param m material
         * @param n name
         * @param v value
         */
	public PentominoShape(ShapeMaterial m,  String n, int v) {
		super(m, n, v);
		addChildren();
	}
        /**
         * Rotates a pentominoe
         * @param angle to rotate it
         * @param ax Axis to rotate around
         */
	public void rotate(double angle, Axis ax) {
		for(Monimo m : children) {
			m.positionParentshape = Algebra.rotateUV(angle,ax,m.positionParentshape);
		}
	}
        /**
         * Calculate ratio
         * @return ratio
         */
	public double getRatio() {
		double doubleValue = value;
		return  doubleValue/5.0;
	}
	/**
         * Mirror a pentomino
         * @param axis to mirror around
         */
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
	/**
         * @return String representation of pentomino
         */
	@Override
	public String toString() {
		String s = "Monimos:";
		for(Monimo m : children) {
			String c = m.positionParentshape.toString();
			s+=c;
		}
		s+= "Container Pos:";
		for(Monimo m : children) {
			String c = m.positionContainer.toString();
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
	

	public abstract PentominoShape cloneWithCoords();
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
        /**
         * 
         * @return coordinates of pentomino
         */
	public Coordinates getPositionParcelContainer(){
		return positionParcelContainer;
	}



}
