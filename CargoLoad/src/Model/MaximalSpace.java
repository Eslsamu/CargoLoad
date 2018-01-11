package Model;
import Util.Coordinates;

public class MaximalSpace {

    private int[] shape;
    private Coordinates currentCoords;
    private Coordinates minCoords;
    private Coordinates maxCoords;

    //just like in the ParcelShape class, MaximalSpaces have an origin coordinate and a shape
    public MaximalSpace(Coordinates coords, int[] shape){
        this.currentCoords = coords;
        this.shape = shape;
    }

    //or, as in the article: "We represent a maximal-space by the vertices with
    //minimum and maximum coordinates."
    //this is probably the better way to do it, i think they mean that the min-coordinates is the one closest
    //to the origin and the max coordinate the furthest away from it but not 100% sure.
    public MaximalSpace(Coordinates minCoords, Coordinates maxCoords) {
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
    }

    public Coordinates getMinCoords(){
        return minCoords;
    }

    public Coordinates getMaxCoords(){
        return maxCoords;
    }
}
