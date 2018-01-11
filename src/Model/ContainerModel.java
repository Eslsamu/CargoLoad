package Model;

import Shapes.Facing;
import Shapes.ParcelShape;
import Util.Coordinates;

import java.util.ArrayList;

public class ContainerModel {
    /**
     * How it should work:
     * The programs tries to fill cargo with given parcels (A, B, C), starting with the most valuable. It adds more and more parcels,
     * until nothing can be added to the cargo.
     * If the cargo is not full, it saves the configuration and value and keeps backtracking search like in Phase 1. If the cargo is full,
     * it returns the score. If it goes through all possibilities and in none of them cargo is filled, the best value is returned.
     *
     */

    // in 0.5 meters
    static protected final int containerY = 8;
    static protected final int containerX = 5;
    static protected final int containerZ = 33;
    private int[][][] containerMatrix = new int[containerZ][containerY][containerX];
    private ArrayList<ParcelShape> parcelList;
    private ArrayList<ParcelShape> containedParcels = new ArrayList<>();

/*

    public void setParcelList(ArrayList<ParcelShape> newParcelList){
        parcelList = newParcelList;
    }

    
    /**
     * This method packs the problem with a simple backtracking algorithm similar to that one from Phase 1.
     * @param maxValueContainer The container that has been already packed and reached the maximal value so far
     * @return
     */

    public boolean solve(ContainerModel maxValueContainer){


        if(checkIfFull()){
            showResults(maxValueContainer);
            System.out.println("The cargo is full.");
            return true;
        }
        for(int z=0;z<containerZ;z++){
            for(int y=0;y<containerY;y++){
                for(int x=0;x<containerX;x++){
                    if(containerMatrix[z][y][x]==0){
                        for(int parcel = 0;parcel<parcelList.size();parcel++){
                            for(Facing o: Facing.values()) {
                                ParcelShape currentParcel = parcelList.get(parcel);
                                if (doesFit(z, y, x, currentParcel,o)) {
                                    System.out.println("Fits");
                                    printContainer();
                                    placeParcel(z, y, x, currentParcel,o);
                                    containedParcels.add(currentParcel);

                                    if (solve(maxValueContainer)) {
                                        return true;
                                    } else {
                                        removeParcel(currentParcel);
                                        containedParcels.remove(containedParcels.size() - 1);
                                    }
                                }
                            }
                        }
                        if(computeTotalValue()>maxValueContainer.computeTotalValue()){
                            System.out.println("Total value container: "+computeTotalValue());
                            System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
                            maxValueContainer = clone();
                            maxValueContainer.printContainer();
                        }
                    }
                }
            }
        }
        showResults(maxValueContainer);
        return false;
    }



    /**
     * The method prints the layers of the container one after another. It's a very crude substitution until we don't have GUI.
     */
    public void printContainer(){
        for(int z=0;z<containerZ;z++){
            System.out.println("Layer for z = "+z);
            for(int y =0;y<containerY;y++){
                for (int x=0;x<containerX;x++){
                    System.out.print(containerMatrix[z][containerY-1-y][x]+" "); // supposing the origin is in lower left corner (instead of upper)
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int computeTotalValue(){
        int totalValue=0;
        for(ParcelShape parcel:containedParcels){
            totalValue+=parcel.getValue();
        }
        return totalValue;
    }



    public void showResults(ContainerModel container){
        container.printContainer();
        System.out.println("The best value is :"+container.computeTotalValue());
    }

    /**
     * Checks if a parcel fits in a certain cell of the container with coordinates (z,y,x).
     */
    // TODO
    public boolean doesFit(int z, int y, int x, ParcelShape parcel,Facing o) {
        parcel.setOrientation(o);
        if ((parcel.getShape()[0] + x > containerX) ||
                (parcel.getShape()[1] + y > containerY) ||
                (parcel.getShape()[2] + z > containerZ) ||
                containerMatrix[z][y][x] == 1 ||  z + parcel.getShape()[0]< 0 ||
        (y + parcel.getShape()[1]< 0) ||
                (x + parcel.getShape()[2]<0))
            return false;
        else
            return true;
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
                    if (containerMatrix[z][y][x] == 0) {
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
    public void placeParcel(int z, int y, int x, ParcelShape parcel, Facing o){
        System.out.println("Print z,y,x"+parcel.getClass() + z + y +x);
        System.out.println("Length y"+parcel.getShape()[0]+parcel.getShape()[1]+parcel.getShape()[2]);

        parcel.setOrientation(o);
        parcel.setCurrentCoordinates(new Coordinates(x,y,z));
         
        for (int zCoord = z; zCoord < z + parcel.getShape()[2]; zCoord++) {
            for (int yCoord = y; yCoord < y + parcel.getShape()[1]; yCoord++) {
                for (int xCoord = x; xCoord < x + parcel.getShape()[0]; xCoord++) {
                    System.out.println("set value " + zCoord + " " + yCoord + " " + xCoord);
                    System.out.println("Get shape "+parcel.getShape()[1]);
                    containerMatrix[zCoord][yCoord][xCoord] = 1;
                    System.out.println(containerMatrix[z][y][x]);
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
                    containerMatrix[zCoord][yCoord][xCoord] = 0;
                }
            }
        }
    }

    public ContainerModel clone(){
        ContainerModel model = new ContainerModel();
        model.setParcelList(parcelList);
        model.setValues(containerMatrix);
        model.setContainedParcels(containedParcels);
        return model;
    }

    public void setValues(int[][][] newValues){
        containerMatrix = newValues;
    }

    public void setContainedParcels(ArrayList<ParcelShape> newContainedParcels) {
        containedParcels = newContainedParcels;
    }
}
