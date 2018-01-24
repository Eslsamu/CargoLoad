package Shapes;

import Util.Coordinates;

public class PentominoL extends PentominoShape{
	private static int value = 5;
	private static String name = "L";
	private static ShapeMaterial material = ShapeMaterial.BLUE;
	
	public PentominoL() {
		super(material, name, value);
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
