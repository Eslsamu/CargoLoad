package Shapes;

import Util.Coordinates;

public abstract class Shape{
    protected int value;
    protected String name;
    protected ShapeMaterial material;
    protected Coordinates positionParcelContainer;

    public Shape(ShapeMaterial m, String n, int v){
        this.material = m;
        this.name = n;
        this.value = v;
        this.positionParcelContainer = new Coordinates(0,0,0);

    }

    public int getValue(){
        return value;
    }
    public String getName(){ return name; }

    public abstract double getRatio();

}
