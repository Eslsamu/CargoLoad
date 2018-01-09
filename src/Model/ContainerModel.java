package Model;

import Shapes.ParcelShape;

public class ContainerModel {
    /**
     * How it should work:
     * The programs tries to fill cargo with given parcels (A, B, C), starting with the most valuable. It adds more and more parcels,
     * until nothing can be added to the cargo.
     * If the cargo is not full, it saves the configuration and value and keeps backtracking search like in Phase 1. If the cargo is full,
     * it returns the score. If it goes through all possibilities and in none of them cargo is filled, the best value is returned.
     *
     */



    /**
     * This method packs the problem with a simple backtracking algorithm similar to that one from Phase 1.
     * @param container A container that we want to fill with parcels
     * @param parcels An array of parcels we can use. In the first version it is A, B, C and in the second it's L, P, T.
     * @param maxValueContainer The container that has been already packed and reached the maximal value so far
     * @return
     */
    public static boolean solve(ContainerTest container, ParcelShape[] parcels, ContainerTest maxValueContainer){
        return true;
    }

    // in 0.5 meters
    private final int containerY = 8;
    private final int containerX = 5;
    private final int containerZ = 33;
    private int value = 0;
    private final int[][][] values = new int[containerZ][containerY][containerX];
    private ParcelShape lastParcel;
    private int[] lastParcelCoords = new int[3];

    /**
     * The method prints the layers of the container one after another. It's a very crude substitution until we don't have GUI.
     */
    public void printContainer(){
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
    public boolean doesFit(int z, int y, int x, ParcelShape parcel) {
        if ((parcel.getShape()[0] + z > containerZ) ||
                (parcel.getShape()[1] + y > containerY) ||
                (parcel.getShape()[2] + x > containerX) ||
                values[z][y][x] == 1)
            return true;
        else
            return false;
    }
    /**
     * Checks if the container is full
     */
    // TODO
    public boolean checkIfFull() {
        boolean full = true;
        for (int z = 0; z < containerZ; z++) {
            for (int y = 0; y < containerY; y++) {
                for (int x = 0; x < containerX; x++) {
                    if (values[z][y][x] == 0) {
                        full = false;
                        break;
                    }
                }
            }
        }
        return full;
    }

    /**
     * Places the parcel in a certain cell of the container with coordinates (z,y,x).
     */
    // TODO
    public void placeParcel(int z, int y, int x, ParcelShape parcel){
        for (int zCoord = z; zCoord < z + parcel.getShape()[0]; zCoord++) {
            for (int yCoord = y; yCoord < y + parcel.getShape()[1]; yCoord++) {
                for (int xCoord = x; xCoord < x + parcel.getShape()[2]; xCoord++) {
                    values[z][y][x] = 1;
                }
            }
        }
    }

    /**
     * Removes the parcel from the container.
     */
    // TODO
    public void removeParcel(ParcelShape parcel){
        for (int zCoord = parcel.getCurrentCoordinates().getZ(); zCoord < zCoord + parcel.getCurrentCoordinates().getZ(); zCoord++) {
            for (int yCoord = parcel.getCurrentCoordinates().getY(); yCoord < yCoord + parcel.getCurrentCoordinates().getY(); yCoord++) {
                for (int xCoord = parcel.getCurrentCoordinates().getX(); xCoord < xCoord + parcel.getCurrentCoordinates().getX(); xCoord++) {
                    values[zCoord][yCoord][xCoord] = 0;
                }
            }
        }
    }

}
