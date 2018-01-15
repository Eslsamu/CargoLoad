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
		super.children.add(new Monimo(new Coordinates(1,1,1), color));
		super.children.add(new Monimo(new Coordinates(1,2,1), color));
		super.children.add(new Monimo(new Coordinates(1,3,1), color));
		super.children.add(new Monimo(new Coordinates(1,3,2), color));
		super.children.add(new Monimo(new Coordinates(1,2,2), color));
	}
}
