package Shapes;

import Util.Coordinates;
/**
 * Class representing Pentomino L
 */
public class PentominoL extends PentominoShape{
	private static int value = 3;
	private static String name = "L";
	private static ShapeMaterial material = ShapeMaterial.BLUE;
	/**
         * Construc pentomino L
         */
	public PentominoL() {
		super(material, name, value);
	}
	/**
         * Add Monimos to pentomino
         */
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,3,0), material));
		super.children.add(new Monimo(new Coordinates(0,3,1), material));
	}
        /**
         * @return copy of class
         */
	@Override
	public PentominoShape clone() {
		return new PentominoL();
	}
        /**
         * @return copy of class with coordinates
         */
	public PentominoL cloneWithCoords(){
		PentominoL p = new PentominoL();
		p.setContainerPosition(positionParcelContainer.clone());
		System.out.println("Position parcel z:"+positionParcelContainer.z+" "+positionParcelContainer.y+" "+positionParcelContainer.x);
		for(int i=0;i<p.children.size();i++){
			p.children.set(i,this.children.get(i).clone());
		}
		return p;
	}

}
