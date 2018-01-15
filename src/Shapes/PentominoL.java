package Shapes;

import Util.Coordinates;

public class PentominoL extends PentominoShape{
	private static int value = 5;
	private static String name = "P";
	private static ShapeMaterial material = ShapeMaterial.BLUE;
	
	public PentominoL() {
		super(name,value,material);
	}
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,0,1), material));
		super.children.add(new Monimo(new Coordinates(0,0,2), material));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoL();
	}
}
