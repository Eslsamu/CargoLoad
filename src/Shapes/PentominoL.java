package Shapes;

import Util.Coordinates;

public class PentominoL extends PentominoShape{
	private static int value = 5;
	private static String name = "P";
	private static ShapeColor color = ShapeColor.BLUE;
	
	public PentominoL() {
		super(name,value,color);
	}
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), color));
		super.children.add(new Monimo(new Coordinates(0,1,0), color));
		super.children.add(new Monimo(new Coordinates(0,2,0), color));
		super.children.add(new Monimo(new Coordinates(0,0,1), color));
		super.children.add(new Monimo(new Coordinates(0,0,2), color));
	}
}
