package Model;

import Shapes.*;
import Util.Coordinates;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

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
    // these are need for subspaces
    protected int containerY = 8;
    protected int containerX = 5;
    protected int containerZ = 33;


    static protected int initialContainerY = 8;
    static protected int initialContainerX = 5;
    static protected int initialContainerZ = 33;

    private int[][][] containerMatrix = new int[containerZ][containerY][containerX];
    private ArrayList<ParcelShape> parcelList;

    private ArrayList<ParcelShape> containedParcels = new ArrayList<>();
    
    private int[] remainingParcelsEachType={100,100,100};
    private int AmountTypeA = 100;
    private int AmountTypeB = 100;
    private int AmountTypeC = 100;
    private int nonEmptyParcelType = 0;
    private boolean[] triedParcel = new boolean[3];
    protected boolean finish = false;
    private int delay;


    /**
     * This method packs the problem with a simple backtracking algorithm similar to that one from Phase 1.
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
    /**
     * If you want to run it without time limit, startTimer parameter should be false. Every time you are running it firstCall should be true.
     *
     */
    public boolean solveBacktracking(ContainerModel maxValueContainer, boolean startTimer, boolean firstCall) {


        if (startTimer) {
            java.util.Timer timer = new Timer();
            System.out.println("Timer");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish = true;
                    timer.cancel();
                }
            }, delay);
            startTimer = false;
        }

        //The end condition of the recursive loop --> checks if the container is completely filled
        if (checkIfFull()) {
            showResults();
            System.out.println("The cargo is full.");

            return true;
        }
        //for each voxel of the space
        while (nonEmptyParcelType < parcelList.size() && remainingParcelsEachType[nonEmptyParcelType] == 0)
            nonEmptyParcelType++;

        for (int z = 0; z < containerZ; z++) {
            for (int y = 0; y < containerY; y++) {
                for (int x = 0; x < containerX; x++) {
                    //check if it is empty
                    if (containerMatrix[z][y][x] == 0) {
                        //for each available parcel type in the parcel list
                        for (int parcelType = nonEmptyParcelType; parcelType < parcelList.size(); parcelType++) {
                            if(remainingParcelsEachType[parcelType]>0) {
                                //create a clone of the current parcel in your list
                                ParcelShape currentParcel = parcelList.get(parcelType).clone();
                                //for each possible orientation of the parcel -> set it to this orientation(changes it's shape)

                                for (Facing o : Facing.values()) {
                                    currentParcel.setOrientation(o);
                                    //check if this parcel with this orientation can be placed onto these coordinates
                                    if (doesFit(z, y, x, currentParcel)) {
                                        //place the parcel onto the container matrix
                                        placeParcel(z, y, x, currentParcel);
                                        System.out.println("Remaining" + remainingParcelsEachType[parcelType]);
                                        remainingParcelsEachType[parcelType]--;
                                        System.out.println("Remaining2" + remainingParcelsEachType[parcelType]);

                                        //add the parcel object to the containedParcel list
                                        containedParcels.add(currentParcel);
                                        if (solveBacktracking(maxValueContainer, startTimer, false)) {
                                            return true;
                                        } else {
                                            removeParcel(currentParcel);
                                            containedParcels.remove(currentParcel);
                                            remainingParcelsEachType[parcelType]++;
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        //remainingParcelsEachType = new int[]{AmountTypeA, AmountTypeB, AmountTypeC};
        if(computeTotalValue()>maxValueContainer.computeTotalValue()){
            System.out.println("Total value container: "+computeTotalValue());
            System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
            System.out.println(remainingParcelsEachType[0]+" "+remainingParcelsEachType[1]+" "+remainingParcelsEachType[2]);
            System.out.println();
            //printContainer();
            clone(maxValueContainer);
        }

        if(finish){
                System.out.println("Finish");
                cloneFinish(maxValueContainer);
                showResults();
                return true;
        }



        if(firstCall){
            System.out.println("Finish first call");
            cloneFinish(maxValueContainer);
            showResults();
            printContainedShapes();
            return true;
        }

        return false;
    }

    public void solveDivideAndConquer(ContainerModel maxValueContainer){
        int[][] subspaces = {{3,2,5},{3,4,5},{3,8,5},{11,2,5},{11,4,5},{11,8,5}};
        for(int[] subspace:subspaces){
            ContainerModel subspaceContainer = new ContainerModel();
            subspaceContainer.setDimensions(subspace[0],subspace[1],subspace[2]);
            subspaceContainer.setDelay(2000);
            subspaceContainer.setParcelList(parcelList);

            ContainerModel maxValueSubspace = new ContainerModel();
            maxValueSubspace.setDimensions(subspace[0],subspace[1],subspace[2]);
            maxValueSubspace.setParcelList(parcelList);

            subspaceContainer.solveBacktracking(maxValueSubspace,true,false);

             for (int z = 0; z < containerZ; z++) {
               for (int y = 0; y < containerY; y++) {
                    for (int x = 0; x < containerX; x++) {

                        if(enoughBlocksForSubspace()){

                            if(doesSubspaceFit(subspaceContainer, z, y, x)){ // check in a clone of the main container, by putting inside each parcel according to its coordinates from
                                                    //subspaceContainer.getContainedParcels()
                                copySubspace(subspaceContainer, z, y, x);
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            // after copying subspace fill the left space with parcels that are left
            solveFirstPackedCargoSetAmount();
        }
    }

    public boolean doesSubspaceFit(ContainerModel subspaceContainer, int z, int y, int x) {
        clone(subspaceContainer);
        int[][][] subspaceMatrix = subspaceContainer.getContainerMatrix();
        boolean doesFit = true;

        if( (z + subspaceContainer.getContainerZ() > initialContainerZ ) ||
            (y + subspaceContainer.getContainerY() > initialContainerY ) ||
            (x + subspaceContainer.getContainerX() > initialContainerX))
                return false;

        for (int zCoord = z; zCoord < z + subspaceContainer.getContainerZ(); zCoord++) {
            for (int yCoord = y; yCoord < y + subspaceContainer.getContainerY(); yCoord++) {
                for (int xCoord = x; xCoord < x + subspaceContainer.getContainerX(); xCoord++) {
                    if(containerMatrix[zCoord][yCoord][xCoord] == 1 && subspaceMatrix[zCoord - z][yCoord - y][xCoord - x] == 1)
                        doesFit = false;
                }
            }
        }
        return doesFit;
    }


    public void copySubspace(ContainerModel subspaceContainer, int z, int y, int x){
        clone(subspaceContainer);
        int[][][] subspaceMatrix = subspaceContainer.getContainerMatrix();

        for(int zCoord = z; zCoord < z + subspaceContainer.getContainerZ(); zCoord++){
            for(int yCoord = y; yCoord < y + subspaceContainer.getContainerY(); yCoord++){
                for(int xCoord = x; xCoord < x + subspaceContainer.getContainerX(); xCoord++){
                    containerMatrix[zCoord][yCoord][xCoord] = subspaceMatrix[zCoord - z][yCoord - y][xCoord - x];
                }
            }

        }
    }

/*
    public void copySubspace(ContainerModel subspaceContainer, int i){
        clone(subspaceContainer);
        int[][][] subspaceMatrix = subspaceContainer.getContainerMatrix();

        //i = nrOfPlacedSubspaces

        for(int z = i * subspaceContainer.getContainerZ(); z < (i+1) * subspaceContainer.getContainerZ(); z++){
            for(int y = i * subspaceContainer.getContainerY(); y < (i+1) * subspaceContainer.getContainerY(); y++){
                for(int x = i * subspaceContainer.getContainerX(); z < (i+1) * subspaceContainer.getContainerX(); x++){
                    containerMatrix[z][y][x] = subspaceMatrix[z - i * subspaceContainer.getContainerZ()][y - i * subspaceContainer.getContainerZ()][x - i * subspaceContainer.getContainerZ()];
                }
            }
        }

    }
   */

    public boolean enoughBlocksForSubspace(){
        //loops through the contained parcel list and counts the nr of each type,
        //then checks if this is lower than the nr of available parcels left

        int nrOfA_needed = 0;
        int nrOfB_needed = 0;
        int nrOfC_needed = 0;
        boolean enoughLeft = true;

        for(ParcelShape parcel : containedParcels){
            if(parcel instanceof ParcelA) nrOfA_needed++;
            if(parcel instanceof ParcelB) nrOfB_needed++;
            if(parcel instanceof ParcelC) nrOfC_needed++;
        }

        if(nrOfA_needed > remainingParcelsEachType[0]) enoughLeft = false;
        if(nrOfB_needed > remainingParcelsEachType[1]) enoughLeft = false;
        if(nrOfC_needed > remainingParcelsEachType[2]) enoughLeft = false;

        return enoughLeft;
    }

    public void setDelay(int newDelay){
        delay = newDelay;
    }

    public void clone(ContainerModel model){
        int[][][] newContainerMatrix = new int[containerZ][containerY][containerX];
        for(int i=0;i<containerMatrix.length;i++){
            for(int j=0;j<containerMatrix[0].length;j++){
                for(int k=0;k<containerMatrix[0][0].length;k++){
                    newContainerMatrix[i][j][k] = containerMatrix[i][j][k];
                }
            }
        }
        model.setContainerMatrix(newContainerMatrix);

        ArrayList<ParcelShape> newContainedParcels = new ArrayList<>();
        for(ParcelShape parcel: containedParcels){
            ParcelShape someParcel = parcel.clone();
            someParcel.setCurrentCoordinates(parcel.getPosition().clone());
            newContainedParcels.add(someParcel);
        }
        model.setContainedParcels(newContainedParcels);
    }

    public void cloneFinish(ContainerModel model){
        int[][][] newContainerMatrix = new int[containerZ][containerY][containerX];
        for(int i=0;i<containerMatrix.length;i++){
            for(int j=0;j<containerMatrix[0].length;j++){
                for(int k=0;k<containerMatrix[0][0].length;k++){
                    newContainerMatrix[i][j][k] = model.getContainerMatrix()[i][j][k];
                }
            }
        }
        setContainerMatrix(newContainerMatrix);

        ArrayList<ParcelShape> newContainedParcels = new ArrayList<>();
        for(ParcelShape parcel:model.getContainedParcels()){
            ParcelShape someParcel = parcel.clone();
            someParcel.setCurrentCoordinates(parcel.getPosition().clone());
            newContainedParcels.add(someParcel);
        }
        setContainedParcels(newContainedParcels);
    }


    public int[][][] getContainerMatrix(){
        return  containerMatrix;
    }

    //only for infinite amount of parcels
    public boolean solveRandom(ContainerModel maxValueContainer){
        java.util.Timer timer = new Timer();
        System.out.println("Timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish = true;
                timer.cancel();

            }
        }, delay);
        while(true){
            solveFirstPackedCargoRandomOrder();
            if(computeTotalValue()>maxValueContainer.computeTotalValue()){
                System.out.println("Total value container: "+computeTotalValue());
                System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
                System.out.println();

                clone(maxValueContainer);


            }

            if(finish){

                cloneFinish(maxValueContainer);
                showResults();
                return true;
            }
            newContainer();
        }


    }

    public void newContainer(){
        setContainedParcels(new ArrayList<>());
        setContainerMatrix(new int [initialContainerZ][initialContainerY][initialContainerX]);

        setAmountOfParcels(AmountTypeA,AmountTypeB,AmountTypeC);
        nonEmptyParcelType = 0;
        triedParcel = new boolean[3];
    }

    public boolean solveFirstPackedCargoRandomOrder(){

        //The end condition of the recursive loop --> checks if the container is completely filled
        if(checkIfFull()){
            showResults();
            System.out.println("The cargo is full.");
            return true;
        }

        //check if the parcel type we're currently using has run out of parcels, if it has we move onto the next type
        //while(nonEmptyParcelType < parcelList.size() && remainingParcelsEachType[nonEmptyParcelType] == 0) nonEmptyParcelType++;
        //for each voxel of the space
        for (int z = 0; z < containerZ; z++) {
            for (int y = 0; y < containerY; y++) {
                for (int x = 0; x < containerX; x++) {
                    //check if it is empty
                    if (containerMatrix[z][y][x] == 0) {
                        //for each available parcel type in the parcel list
                        //if(b) {
                        for(int i=0;i<triedParcel.length;i++){
                            if(remainingParcelsEachType[i]==0){
                                triedParcel[i] = true;
                            }
                            else{
                                triedParcel[i] = false;
                            }
                        }
                        while(!triedAllTypes()) {
                            int parcelType = setRandomParcelType();

                            //create a clone of the current parcel in your list
                            ParcelShape currentParcel = parcelList.get(parcelType).clone();
                            triedParcel[parcelType] = true;
                            //for each possible orientation of the parcel -> set it to this orientation(changes it's shape)
                            for (Facing o : Facing.values()) {
                                currentParcel.setOrientation(o);
                                //check if this parcel with this orientation can be placed onto these coordinates
                                if (doesFit(z, y, x, currentParcel)) {
                                    System.out.println("check2");
                                    //place the parcel onto the container matrix
                                    placeParcel(z, y, x, currentParcel);
                                    remainingParcelsEachType[parcelType]--;
                                    //add the parcel object to the containedParcel list
                                    containedParcels.add(currentParcel);
                                    if (solveFirstPackedCargoRandomOrder()) {
                                        //showResults();
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

    public boolean triedAllTypes(){
        if (triedParcel[0] && triedParcel[1] && triedParcel[2])
            return true;
        else
            return false;
    }

    /**
     * Picks one type of an available parcel at random.
     */
    public int setRandomParcelType(){
//
        ArrayList<Integer> possibleParcelTypes = new ArrayList<>();
        for(int i=0;i<remainingParcelsEachType.length;i++){
            if(remainingParcelsEachType[i]>0&&!triedParcel[i]){
                possibleParcelTypes.add(i);
            }
        }
        int randomIndex = (int) (Math.random()*possibleParcelTypes.size());
        int randomParcelType = possibleParcelTypes.get(randomIndex);

        return randomParcelType;

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
        parcel.setCurrentCoordinates(new Coordinates(x, y, z));
        if (	(parcel.getShapeVector().x + x > containerX) ||
                (parcel.getShapeVector().y + y > containerY) ||
                (parcel.getShapeVector().z + z > containerZ) ||
                (z + parcel.getShapeVector().z < 0) ||
                (y + parcel.getShapeVector().y < 0) ||
                (x + parcel.getShapeVector().x < 0))
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
                        return  full;
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
        AmountTypeA = nrOfA;
        AmountTypeB = nrOfB;
        AmountTypeC = nrOfC;
        for(int parcelTypeIndex=0;parcelTypeIndex<parcelList.size();parcelTypeIndex++){
            if(parcelList.get(parcelTypeIndex) instanceof ParcelA){
                remainingParcelsEachType[parcelTypeIndex] = nrOfA;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelB){
                remainingParcelsEachType[parcelTypeIndex] = nrOfB;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelC){
                remainingParcelsEachType[parcelTypeIndex] = nrOfC;
            }
        }

    }

    public void setDimensions(int z,int y, int x){
        containerZ = z;
        containerY = y;
        containerX = x;
    }

    //when setAmountOfParcels is called without parameters we consider the amount of each parcel as "infinite"
    public void setAmountOfParcels() {
        remainingParcelsEachType = new int[]{1000,1000,1000};
    }
    
    public ArrayList<ParcelShape> getContainedParcels() {
        return containedParcels;
    }

    public ArrayList<ParcelShape> getParcelList() {
        return parcelList;
    }

    public ArrayList<ParcelShape> orderParcelListByValue(ArrayList<ParcelShape> givenParcels) {
        ArrayList<Integer> parcelValues = new ArrayList<>();
        ArrayList<ParcelShape> orderedParcelListbyValue = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++){
            ParcelShape someShape = givenParcels.get(i).clone();
            parcelValues.add(someShape.getValue());
        }
        Collections.sort(parcelValues, Collections.reverseOrder());
        int j=0;
        int i=0;
        while(i<givenParcels.size()&&j<parcelValues.size()){
            boolean find = false;
            if(givenParcels.get(i).getValue()==parcelValues.get(j)){

                j++;
                ParcelShape otherShape = givenParcels.get(i).clone();
                orderedParcelListbyValue.add(otherShape);
                find=true;
            }
            if(find){
                i=0;
            }
            else{
                i++;
            }
        }
        for(ParcelShape parcel:orderedParcelListbyValue){
            System.out.println(parcel.getValue());
        }
        return orderedParcelListbyValue;
    }

    public ArrayList<ParcelShape> orderParcelListByRatio(ArrayList<ParcelShape> givenParcels) {
        ArrayList<Double> parcelRatios = new ArrayList<>();
        ArrayList<ParcelShape> orderedParcelListbyRatio = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++) {
            ParcelShape someShape = givenParcels.get(i).clone();
            parcelRatios.add(someShape.getRatio());
        }
        Collections.sort(parcelRatios, Collections.reverseOrder());
        for(int j = 0, i = 0; i < givenParcels.size() && j < parcelRatios.size(); i++){
            if(givenParcels.get(i).getRatio()  == parcelRatios.get(j)){
                j++;
                ParcelShape otherShape = givenParcels.get(i).clone();
                orderedParcelListbyRatio.add(otherShape);
                System.out.println(givenParcels.get(i).getRatio());
                i = 0;
            }
        }

        return orderedParcelListbyRatio;

    }

    public ArrayList<ParcelShape> orderParcelListRandom(ArrayList<ParcelShape> givenParcels) {
        Collections.shuffle(givenParcels);
        return givenParcels;
    }
    public void printContainedShapes(){
        for(int  i = 0; i < containedParcels.size(); i++){
            System.out.println("Parcel: " + i);
            ParcelShape parcel = containedParcels.get(i);
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();
            
            for(int zCoord = z; zCoord < z + parcel.getShapeVector().z; zCoord++){
                for(int yCoord = y; yCoord < y + parcel.getShapeVector().y; yCoord++){
                    for(int xCoord = x; xCoord < x + parcel.getShapeVector().x; xCoord++){
                        System.out.println("X: " + xCoord + " Y: " + yCoord + " Z: " + zCoord);
                    }
                }
            }
        }    
    }

    public int getContainerZ(){
        return containerZ;
    }


    public int getContainerY(){
        return containerY;
    }


    public int getContainerX(){
        return containerX;
    }

}
