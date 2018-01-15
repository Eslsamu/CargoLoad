package Shapes;

import Util.Coordinates;

public class PentominoP extends PentominoShape{
	
	private static int value = 4;
	private static String name = "P";
	private static ShapeColor color = ShapeColor.RED;
	
	public PentominoP() {
		super(name,value, color);
	}
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), color));
		super.children.add(new Monimo(new Coordinates(0,1,0), color));
		super.children.add(new Monimo(new Coordinates(0,2,0), color));
		super.children.add(new Monimo(new Coordinates(0,1,1), color));
		super.children.add(new Monimo(new Coordinates(0,2,1), color));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoP();
	}
	
	public Pento
}
