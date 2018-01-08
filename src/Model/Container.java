package Model;
import Shapes.*;

public class Container {
    // in 0.5 meters
    private final int containerY = 8;
    private final int containerX = 5;
    private final int containerZ = 33;
    private final int[][][] values = new int[containerZ][containerY][containerX];

    public void printContainer(){
        values[0][0][0] = 1;
        for(int z=0;z<containerZ;z++){
            System.out.println("Layer for z = "+z);
            for(int y =0;y<containerY;y++){
                for (int x=0;x<containerX;x++){
                    System.out.print(values[z][containerY-1-y][x]+" "); // supposing the origin is in lower left corner (instead of upper)
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Checks if a parcel fits in a certain cell of the container with coordinates (z,y,x).
     */
    // TODO
    public boolean doesFit(int z, int y, int x, ParcelShape parcel){
        return true;
    }

    /**
     * Checks if the container is full
     */
    // TODO
    public boolean checkIfFull(){
        return true;
    }

    /**
     * Places the parcel in a certain cell of the container with coordinates (z,y,x).
     */
    // TODO
    public void placeParcel(int z, int y, int x, ParcelShape parcel){

    }


}
