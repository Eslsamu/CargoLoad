package Shapes;

import Util.Coordinates;

public class PentominoT extends PentominoShape{
	private static int value = 3;
	private static String name = "T";
	private static ShapeMaterial material = ShapeMaterial.GREEN;
	
	public PentominoT() {
		super(name,value, material);
	}
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,1), material));
		super.children.add(new Monimo(new Coordinates(0,2,-1), material));
	}
}
