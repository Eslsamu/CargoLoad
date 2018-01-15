package Shapes;

import Util.Coordinates;

public class PentominoT extends PentominoShape{
	private static int value = 3;
	private static String name = "T";
	private static ShapeColor color = ShapeColor.GREEN;
	
	public PentominoT() {
		super(name,value, color);
	}
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), color));
		super.children.add(new Monimo(new Coordinates(0,1,0), color));
		super.children.add(new Monimo(new Coordinates(0,2,0), color));
		super.children.add(new Monimo(new Coordinates(0,2,1), color));
		super.children.add(new Monimo(new Coordinates(0,2,-1), color));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoT();
	}
}
