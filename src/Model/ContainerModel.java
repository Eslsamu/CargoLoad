package Model;

import Shapes.Facing;
import Shapes.ParcelShape;
import Util.Coordinates;
import java.util.ArrayList;
import java.util.Collections;

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
    private int[] remainingParcelsEachType;
    private int nonEmptyParcelType = 0;

/*

    public void setParcelList(ArrayList<ParcelShape> newParcelList){
        parcelList = newParcelList;
    }


    /**
     * This method packs the problem with a simple backtracking algorithm similar to that one from Phase 1.
     * @param maxValueContainer The container that has been already packed and reached the maximal value so far
     * @return
     */
    public boolean solveFirstPackedCargo(){
        //printContainer();

        //The end condition of the recursive loop --> checks if the container is completely filled
        if(checkIfFull()){
        	showResults();
            System.out.println("The cargo is full.");
            return true;
        }
        for(int z=0;z<containerZ;z++){
            for(int y=0;y<containerY;y++){
                for(int x=0;x<containerX;x++){
                    //check if it is empty
                    if(containerMatrix[z][y][x]==0){
                        //for each available parcel type in the parcel list
                        for(int parcelType = 0; parcelType<parcelList.size();parcelType++){
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
                                    containedParcels.add(currentParcel);
                                    if (solveFirstPackedCargo()) {
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
        showResults();
        return true;
    }
    public boolean solveFirstPackedCargoSetAmount(){
        //System.out.println("test");
        //printContainer();

        //The end condition of the recursive loop --> checks if the container is completely filled
        if(checkIfFull()){
        	showResults();
            System.out.println("The cargo is full.");
            return true;
        }
        //check if the parcel type we're currently using has run out of parcels, if it has we move onto the next type
        while(nonEmptyParcelType < parcelList.size() && remainingParcelsEachType[nonEmptyParcelType] == 0) nonEmptyParcelType++;
            //for each voxel of the space
            for (int z = 0; z < containerZ; z++) {
                for (int y = 0; y < containerY; y++) {
                    for (int x = 0; x < containerX; x++) {
                        //check if it is empty
                        if (containerMatrix[z][y][x] == 0) {
                            //for each available parcel type in the parcel list
                            //while (parcelType < parcelList.size()) {
                                for (int parcelType = nonEmptyParcelType; parcelType < parcelList.size(); parcelType++) {
                                //create a clone of the current parcel in your list
                                ParcelShape currentParcel = parcelList.get(parcelType).clone();
                                //for each possible orientation of the parcel -> set it to this orientation(changes it's shape)
                                for (Facing o : Facing.values()) {
                                    currentParcel.setOrientation(o);
                                    //check if this parcel with this orientation can be placed onto these coordinates
                                    if (doesFit(z, y, x, currentParcel)) {
                                        //place the parcel onto the container matrix
                                        placeParcel(z, y, x, currentParcel);
                                        remainingParcelsEachType[parcelType]--;
                                        //add the parcel object to the containedParcel list
                                        containedParcels.add(currentParcel);
                                        if (solveFirstPackedCargoSetAmount()) {
                                            return true;
                                        } else {
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

        showResults();
        return true;
    }

    // TODO
    // needs to be modified - this method goes through all possible configuration of packing the cargo, stores the best
    // in the maxValueContainer and at the end shows the best result
    // to use it clone the maxValueContainer and place it instead of "maxValueContainer = this"
    // and and at the end of the method set container as a clone of the current maxValueContainer
    public boolean solveWholeSearchTree(ContainerModel maxValueContainer){
//        printContainer();
//        //The end condition of the recursive loop --> checks if the container is completely filled
//        if(checkIfFull()){
//            if(computeTotalValue()>maxValueContainer.computeTotalValue()){
//                //System.out.println("Total value container: "+computeTotalValue());
//                //System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
//                maxValueContainer = this;
//            }
//            if(computeTotalValue()==maxValueContainer.computeTotalValue()){
//                System.out.println("the same");
//            }
//            showResults(maxValueContainer);
//            System.out.println("Size "+maxValueContainer.getContainedParcels().size());
//            System.out.println("The cargo is full.");
//            if(computeTotalValue()==maxValueContainer.computeTotalValue()){
//                System.out.println("the same");
//            }
//            return true;
//        }
//        //for each voxel of the space
//        for(int z=0;z<containerZ;z++){
//            for(int y=0;y<containerY;y++){
//                for(int x=0;x<containerX;x++){
//                    //check if it is empty
//                    if(containerMatrix[z][y][x]==0){
//                        //for each available parcel type in the parcel list
//                        for(int parcelType = 0;parcelType<parcelList.size();parcelType++){
//                            //create a clone of the current parcel in your list
//                            ParcelShape currentParcel = parcelList.get(parcelType).clone();
//                            //for each possible orientation of the parcel -> set it to this orientation(changes it's shape)
//                            for(Facing o: Facing.values()) {
//                                currentParcel.setOrientation(o);
//                                //check if this parcel with this orientation can be placed onto these coordinates
//                                if (doesFit(z, y, x, currentParcel)) {
//                                    //place the parcel onto the container matrix
//                                    placeParcel(z, y, x, currentParcel);
//                                    //add the parcel object to the containedParcel list
//                                    System.out.println("checkList");
//                                    containedParcels.add(currentParcel);
//                                    for(int i=0;i<containedParcels.size();i++){
//                                        System.out.println("1"+containedParcels.get(i));
//                                    }
//                                    if (solve(maxValueContainer)) {
//                                        return true;
//                                    }
//                                    else {
//                                        removeParcel(currentParcel);
//                                        containedParcels.remove(containedParcels.size() - 1);
//                                    }
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//        if(computeTotalValue()>maxValueContainer.computeTotalValue()){
//            System.out.println("Total value container: "+computeTotalValue());
//            System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
//            maxValueContainer = this;
//        }
//
//        showResults(maxValueContainer);
//        boolean methodFinished = true;
//        if(methodFinished){
//            setContainedParcels(maxValueContainer.containedParcels);
//            setContainerMatrix(maxValueContainer.containerMatrix);
//        }
//
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
                    System.out.print(containerMatrix[z][y][x]+" "); // supposing the origin is in lower left corner (instead of upper)
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



    public void showResults(){
        printContainer();
        System.out.println("The best value is :"+computeTotalValue());
    }

    /**
     * Checks if a parcel fits in a certain cell of the container with coordinates (z,y,x).
     */
    // TODO
    public boolean doesFit(int z, int y, int x, ParcelShape parcel) {
        if (	(parcel.getShapeVector().x + x > containerX) ||
                (parcel.getShapeVector().y + y > containerY) ||
                (parcel.getShapeVector().z + z > containerZ) ||
                (z + parcel.getShapeVector().x < 0) ||
                (y + parcel.getShapeVector().y < 0) ||
                (x + parcel.getShapeVector().z < 0))
            return false;
        else{
            for(int zCoord = z; zCoord < z + parcel.getShapeVector().z; zCoord++){
                for(int yCoord = y; yCoord < y + parcel.getShapeVector().y; yCoord++){
                    for(int xCoord = x; xCoord < x + parcel.getShapeVector().x; xCoord++){
                        if(containerMatrix[zCoord][yCoord][xCoord] == 1) return false;
                    }
                }
            }
        }
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
    public void placeParcel(int z, int y, int x, ParcelShape parcel){
        parcel.setCurrentCoordinates(new Coordinates(x,y,z));
        //sets a 1 in the containerMatrix for each coordinate with the vectors of the parcel shape
        for (int zCoord = z; zCoord < z + parcel.getShapeVector().z; zCoord++) {
            for (int yCoord = y; yCoord < y + parcel.getShapeVector().y; yCoord++) {
                for (int xCoord = x; xCoord < x + parcel.getShapeVector().x; xCoord++) {                 
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
        //parcel.setCurrentCoordinates(null); not sure if this is good
        for (int zCoord = parcel.getPosition().getZ(); zCoord <parcel.getPosition().getZ()  + parcel.getShapeVector().z; zCoord++) {
            for (int yCoord = parcel.getPosition().getY(); yCoord < parcel.getPosition().getY() + parcel.getShapeVector().y; yCoord++) {
                for (int xCoord = parcel.getPosition().getX(); xCoord < parcel.getPosition().getX() + parcel.getShapeVector().x; xCoord++) {
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

    public void setAmountOfParcels(int nrOfA, int nrOfB, int nrOfC) {
        remainingParcelsEachType = new int[]{nrOfA, nrOfB, nrOfC};
    }

    //when setAmountOfParcels is called without parameters we consider the amount of each parcel as "infinite"
    public void setAmountOfParcels() {
        remainingParcelsEachType = new int[]{1000,1000,1000};
    }
    
    public ArrayList<ParcelShape> getContainedParcels() {
        return containedParcels;
    }

    public ArrayList<ParcelShape> orderParcelListByValue(ArrayList<ParcelShape> givenParcels) {
        ArrayList<Integer> parcelValues = new ArrayList<>();
        ArrayList<ParcelShape> orderedParcelListbyValue = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++){
            parcelValues.add(givenParcels.get(i).getValue());
        }
        Collections.sort(parcelValues, Collections.reverseOrder());
        for(int j = 0, i = 0; i < givenParcels.size() && j < parcelValues.size(); i++){
            if(givenParcels.get(i).getValue()  == parcelValues.get(j)){
                j++;
                orderedParcelListbyValue.add(givenParcels.get(i));
                i = 0;
            }
        }
        return orderedParcelListbyValue;
    }

    public ArrayList<ParcelShape> orderParcelListByRatio(ArrayList<ParcelShape> givenParcels) {
        ArrayList<Double> parcelRatios = new ArrayList<>();
        ArrayList<ParcelShape> orderedParcelListbyRatio = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++) {
            parcelRatios.add(givenParcels.get(i).getRatio());
        }
        Collections.sort(parcelRatios, Collections.reverseOrder());
        for(int j = 0, i = 0; i < givenParcels.size() && j < parcelRatios.size(); i++){
            if(givenParcels.get(i).getRatio()  == parcelRatios.get(j)){
                j++;
                orderedParcelListbyRatio.add(givenParcels.get(i));
                i = 0;
            }
        }
        return orderedParcelListbyRatio;

    }

    public ArrayList<ParcelShape> orderParcelListRandom(ArrayList<ParcelShape> givenParcels) {
        Collections.shuffle(givenParcels);
        return givenParcels;
    }

}
