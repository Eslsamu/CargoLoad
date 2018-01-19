package Shapes;

import Util.Coordinates;

public class PentominoP extends PentominoShape{
	
	private static int value = 4;
	private static String name = "P";
	private static ShapeMaterial material = ShapeMaterial.RED;
	
	public PentominoP() {
		super(name,value, material);
	}
	public int getValue(){
            return value;
        }
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,1), material));
		super.children.add(new Monimo(new Coordinates(0,2,1), material));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoP();
	}
	
}
