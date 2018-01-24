package Shapes;

import Util.Coordinates;
/**
 * A general class for PentominoShape and ParcelShape
 * @author danyp
 */
public abstract class Shape{
    protected int value;
    protected String name;
    protected ShapeMaterial material;
    protected Coordinates positionParcelContainer;
    /**
     * @param m material
     * @param n name
     * @param v value
     */
    public Shape(ShapeMaterial m, String n, int v){
        this.material = m;
        this.name = n;
        this.value = v;
        this.positionParcelContainer = new Coordinates(0,0,0);

    }
    /**
     * @return value
     */
    public int getValue(){
        return value;
    }
    /**
     * 
     * @return name 
     */
    public String getName(){ return name; }

    public abstract double getRatio();

}
