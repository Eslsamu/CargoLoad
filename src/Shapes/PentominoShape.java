package Shapes;

import java.util.ArrayList;

import Util.Algebra;
import Util.Axis;

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
