package Shapes;

import Util.Coordinates;

public class PentominoT extends PentominoShape{
	private static int value = 3;
	private static String name = "T";
	private static ShapeMaterial material = ShapeMaterial.GREEN;
	
	public PentominoT() {
		super(material, name, value);
	}
	@Override
	public void addChildren() {
		super.children.add(new Monimo(new Coordinates(0,0,0), material));
		super.children.add(new Monimo(new Coordinates(0,1,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,0), material));
		super.children.add(new Monimo(new Coordinates(0,2,1), material));
		super.children.add(new Monimo(new Coordinates(0,2,-1), material));
	}

	@Override
	public PentominoShape clone() {
		return new PentominoT();
	}
	public PentominoT cloneWithCoords(){
		PentominoT p = new PentominoT();
		p.setContainerPosition(positionParcelContainer);
		System.out.println("Position parcel z:"+positionParcelContainer.z+" "+positionParcelContainer.y+" "+positionParcelContainer.x);
		for(int i=0;i<p.children.size();i++){
			p.children.set(i,this.children.get(i).clone());
		}
		return p;
	}
}
