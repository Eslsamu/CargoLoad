
package View;

import Shapes.ParcelShape;
import Shapes.PentominoShape;
import java.util.ArrayList;

public class ShownContainers {
    private ArrayList<ParcelShape> containedParcels;
    private ArrayList<PentominoShape> containedPentominoes;
    private int value;
    private String name;
    public ShownContainers(ArrayList<ParcelShape> containedParcels, int value, String name){
        this.containedParcels = containedParcels;
        this.value = value;
        this.name = name;
    }
    public ShownContainers(int value, ArrayList<PentominoShape> containedPentominoes, String name){
        this.containedPentominoes = containedPentominoes;
        this.value = value;
        this.name = name;
    }
    public ArrayList<ParcelShape> getContainedParcels(){
        return containedParcels;
    }
     public ArrayList<PentominoShape> getContainedPentominoes(){
        return containedPentominoes;
    }
    public int getValue(){
        return value;
    }
    public String getName(){
        return name;
    }
}
