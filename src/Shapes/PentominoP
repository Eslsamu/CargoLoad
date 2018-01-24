package Shapes;

import Util.Coordinates;

public class PentominoP extends PentominoShape{
	
	private static int value = 4;
	private static String name = "P";
	private static ShapeMaterial material = ShapeMaterial.RED;
	
	public PentominoP() {
		super(material, name, value);
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
