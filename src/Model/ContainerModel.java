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
        printContainer();
        //The end condition of the recursive loop --> checks if the container is completely filled
        if(checkIfFull()){
        	if(computeTotalValue()>maxValueContainer.computeTotalValue()){
                //System.out.println("Total value container: "+computeTotalValue());
                //System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
                maxValueContainer = this;
            }
            showResults(maxValueContainer);
            System.out.println("Size "+maxValueContainer.getContainedParcels().size());
            System.out.println("The cargo is full.");
            return true;
        }
        //for each voxel of the space
        for(int z=0;z<containerZ;z++){
            for(int y=0;y<containerY;y++){
                for(int x=0;x<containerX;x++){   
                	//check if it is empty
                    if(containerMatrix[z][y][x]==0){
                    	//for each available parcel type in the parcel list
                        for(int parcelType = 0;parcelType<parcelList.size();parcelType++){
                        	//create a clone of the current parcel in your list
                            ParcelShape currentParcel = parcelList.get(parcelType).clone();
                        	//for each possible orientation of the parcel -> set it to this orientation(changes it's shape)
                            for(Facing o: Facing.values()) {                            	
                            	currentParcel.setOrientation(o);
                                //check if this parcel with this orientation can be placed onto these coordinates
                                if (doesFit(z, y, x, currentParcel)) {
                                	//place the parcel onto the container matrix                               	
                                    placeParcel(z, y, x, currentParcel);
                                    //add the parcel object to the containedParcel list
                                    System.out.println("checkList");
                                    containedParcels.add(currentParcel);
                                    for(int i=0;i<containedParcels.size();i++){
                                        System.out.println("1"+containedParcels.get(i));
                                    }
                                    if (solve(maxValueContainer)) {
                                        return true;
                                    }
                                    else {
                                        removeParcel(currentParcel);
                                        containedParcels.remove(containedParcels.size() - 1);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        if(computeTotalValue()>maxValueContainer.computeTotalValue()){
            //System.out.println("Total value container: "+computeTotalValue());
            //System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
            maxValueContainer = this;
        }
        showResults(maxValueContainer);
        System.out.println("Size "+maxValueContainer.getContainedParcels().size());
        for(ParcelShape parcel:maxValueContainer.getContainedParcels()){
            System.out.println(parcel.getCurrentCoordinates().getZ()+" "+parcel.getCurrentCoordinates().getY()+" "+parcel.getCurrentCoordinates().getX());
        }
        return true;
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
    public boolean doesFit(int z, int y, int x, ParcelShape parcel) {
        if (	(parcel.getShape()[0] + x > containerX) ||
                (parcel.getShape()[1] + y > containerY) ||
                (parcel.getShape()[2] + z > containerZ) ||
                (z + parcel.getShape()[0]< 0) ||
                (y + parcel.getShape()[1]< 0) ||
                (x + parcel.getShape()[2]<0)  ||
                containerMatrix[z][y][x] == 1)
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
    public void placeParcel(int z, int y, int x, ParcelShape parcel){
    	System.out.println("checkPlace");
        parcel.setCurrentCoordinates(new Coordinates(x,y,z));
        //sets a 1 in the containerMatrix for each coordinate with the vectors of the parcel shape
        for (int zCoord = z; zCoord < z + parcel.getShape()[2]; zCoord++) {
            for (int yCoord = y; yCoord < y + parcel.getShape()[1]; yCoord++) {
                for (int xCoord = x; xCoord < x + parcel.getShape()[0]; xCoord++) {                 
                    containerMatrix[zCoord][yCoord][xCoord] = 1;               
                }
            }
        }
    }


    /**
     * Removes the parcel from the container.
     */
    // TODO
    public void removeParcel(ParcelShape parcel){
        System.out.println(parcel.getShape()[2]);
        System.out.println(parcel.getShape()[1]);
        System.out.println(parcel.getShape()[0]);
        System.out.println("checkRemove");
        //parcel.setCurrentCoordinates(null); not sure if this is good

        for (int zCoord = parcel.getCurrentCoordinates().getZ(); zCoord <parcel.getCurrentCoordinates().getZ()  + parcel.getShape()[2]; zCoord++) {
            for (int yCoord = parcel.getCurrentCoordinates().getY(); yCoord < parcel.getCurrentCoordinates().getY() + parcel.getShape()[1]; yCoord++) {
                for (int xCoord = parcel.getCurrentCoordinates().getX(); xCoord < parcel.getCurrentCoordinates().getX() + parcel.getShape()[0]; xCoord++) {
                    System.out.println("z" +zCoord+"y "+yCoord+"x "+xCoord);
                    containerMatrix[zCoord][yCoord][xCoord] = 0;
                }
            }
        }
    }
    

    public void setContainerMatrix(int[][][] newValues){
        containerMatrix = newValues;
    }

    public void setContainedParcels(ArrayList<ParcelShape> newContainedParcels) {
        containedParcels = newContainedParcels;
    }

    public void setParcelList(ArrayList<ParcelShape> parcelList) {
        this.parcelList = parcelList;
    }
    
    public ArrayList<ParcelShape> getContainedParcels() {
        return containedParcels;
    }
}
