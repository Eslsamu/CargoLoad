package Shapes;

import Util.Coordinates;
/**
 * This class represents pentomino p
 */
public class PentominoP extends PentominoShape{
	
	private static int value = 4;
	private static String name = "P";
	private static ShapeMaterial material = ShapeMaterial.RED;
	/**
         * Construct pentomino P
         */
	public PentominoP() {
		super(material, name, value);
	}
        /**
         * add monimos
         */
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,1), material));
		super.children.add(new Monimo(new Coordinates(0,2,1), material));
	}
        /**
         * 
         * @return copy of class
         */
	@Override
	public PentominoShape clone() {
		return new PentominoP();
	}
        
        /**
         * @return copy of class with coordinates
         */
	public PentominoP cloneWithCoords(){
		PentominoP p = new PentominoP();
		p.setContainerPosition(positionParcelContainer);

		for(int i=0;i<p.children.size();i++){
			p.children.set(i,this.children.get(i).clone());
		}
		System.out.println("Position XXparcel z:"+p.getPositionParcelContainer().z+" "+p.getPositionParcelContainer().y+" "+p.getPositionParcelContainer().x);
		return p;
	}
	
}
