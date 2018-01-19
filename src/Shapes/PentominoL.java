package Shapes;

import Util.Coordinates;

public class PentominoL extends PentominoShape{
	private static int value = 5;
	private static String name = "L";
	private static ShapeMaterial material = ShapeMaterial.BLUE;
	
	public PentominoL() {
		super(name,value,material);
	}
        public int getValue(){
            return value;
        }
	
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,3,0), material));
		super.children.add(new Monimo(new Coordinates(0,3,1), material));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoL();
	}
}
