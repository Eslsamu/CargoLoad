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
    protected int containerY = 8;
    protected int containerX = 5;
    protected int containerZ = 33;

    protected int[][][] containerMatrix = new int[containerZ][containerY][containerX];
    protected ArrayList<ParcelShape> parcelList;

    protected ArrayList<ParcelShape> containedParcels = new ArrayList<>();
    
    protected int[] remainingParcelsEachType={100,100,100};
    protected int AmountTypeA = 100;
    protected int AmountTypeB = 100;
    protected int AmountTypeC = 100;
    protected int nonEmptyParcelType = 0;
    protected boolean[] triedParcel = new boolean[3];
    protected boolean finish = false;
    protected int delay;
    protected Subspace subspace;


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
                                        remainingParcelsEachType[parcelType]--;
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
//            printContainedShapes();
            return true;
        }

        return false;
    }

    public void solveDivideAndConquer(ContainerModel maxValueContainer){



        ArrayList<int[]> subspaces = generateSubspaces();
        for(int[] subspaceShape:subspaces){
            newContainer();
            System.out.println("Subspace "+subspaceShape[0]+" "+subspaceShape[1]+" "+subspaceShape[2]);

            subspace = new Subspace(subspaceShape[0],subspaceShape[1],subspaceShape[2],parcelList);
            subspace.setDelay(delay);

            Subspace maxValueSubspace = new Subspace(subspaceShape[0],subspaceShape[1],subspaceShape[2],parcelList);

            subspace.solveBacktracking(maxValueSubspace,true,true);
            subspace.computeNeededParcels();
             for (int z = 0; z < containerZ; z++) {
               for (int y = 0; y < containerY; y++) {
                    for (int x = 0; x < containerX; x++) {

                        if(enoughBlocksForSubspace()){

                            if(doesSubspaceFit(z, y, x)){
                                // check in a clone of the main container, by putting inside each parcel according to its coordinates from
                                //subspace.getContainedParcels()
                                copySubspace(z, y, x);
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            System.out.println("Compute container for current subspace"+computeTotalValue());
            // after copying subspace fill the left space with parcels that are left
            solveFirstPackedCargoSetAmount();
            System.out.println("Compute container for current subspace"+computeTotalValue());
            if(computeTotalValue()>maxValueContainer.computeTotalValue()){
                System.out.println("Total value container: "+computeTotalValue());
                System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
                System.out.println();
                clone(maxValueContainer);
            }
        }
        System.out.println("Finish");
        cloneFinish(maxValueContainer);
        showResults();
    }

    public ArrayList<int[]> generateSubspaces(){
        ArrayList<int[]> subspaces = new ArrayList<>();


        ArrayList<Integer> zFactors = new ArrayList<>();
        ArrayList<Integer> yFactors = new ArrayList<>();
        ArrayList<Integer> xFactors = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dimensionsFactors = new ArrayList<>();
        dimensionsFactors.add(zFactors);
        dimensionsFactors.add(yFactors);
        dimensionsFactors.add(xFactors);
        int z = containerZ;
        int y = containerY;
        int x = containerX;
        int[] tempDimensions = {z,y,x};
        for(int i=0;i<3;i++){
            int temp = tempDimensions[i];
            int sqrt = (int)Math.sqrt(tempDimensions[i]);
            for(int divisor = 2;divisor<temp;divisor++){
                if(tempDimensions[i]%divisor==0){
                    dimensionsFactors.get(i).add(divisor);
                    tempDimensions[i] /= divisor;
                }
            }
            dimensionsFactors.get(i).add(temp);
        }
        for(int zFactor=0; zFactor<dimensionsFactors.get(0).size(); zFactor++){
            for(int yFactor=0; yFactor<dimensionsFactors.get(1).size(); yFactor++){
                for(int xFactor=0; xFactor<dimensionsFactors.get(2).size(); xFactor++){
                    int[] subspace = {dimensionsFactors.get(0).get(zFactor),dimensionsFactors.get(1).get(yFactor),dimensionsFactors.get(2).get(xFactor)};
                    System.out.println(dimensionsFactors.get(0).get(zFactor)+" "+dimensionsFactors.get(1).get(yFactor)+" "+dimensionsFactors.get(2).get(xFactor));
                    subspaces.add(subspace);
                }
            }
        }
        boolean containWholeContainer = false;
        if(!containWholeContainer){
            subspaces.remove(subspaces.size()-1);
        }
        return  subspaces;
    }


    public boolean doesSubspaceFit(int z, int y, int x) {
        //clone(subspace);
        boolean doesFit = true;

        if( (z + subspace.containerZ > containerZ ) ||
            (y + subspace.containerY > containerY ) ||
            (x + subspace.containerX > containerX))
                return false;

        for (int zCoord = z; zCoord < z + subspace.containerZ; zCoord++) {
            for (int yCoord = y; yCoord < y + subspace.containerY; yCoord++) {
                for (int xCoord = x; xCoord < x + subspace.containerX; xCoord++) {
                    // if container's voxel is 1 and subspace's voxel is 0 - it's ok
                    // if container's voxel is 0 and subspace's voxel is 1 - it's ok
                    if(containerMatrix[zCoord][yCoord][xCoord] == 1 && subspace.containerMatrix[zCoord - z][yCoord - y][xCoord - x] == 1)
                        doesFit = false;
                }
            }
        }
        return doesFit;
    }


    public void copySubspace(int z, int y, int x){
        for(int zCoord = z; zCoord < z + subspace.containerZ; zCoord++){
            for(int yCoord = y; yCoord < y + subspace.containerY; yCoord++){
                for(int xCoord = x; xCoord < x + subspace.containerX; xCoord++){
                    containerMatrix[zCoord][yCoord][xCoord] = subspace.containerMatrix[zCoord - z][yCoord - y][xCoord - x];


                }
            }
        }
        containedParcels.addAll(subspace.containedParcels);
        deductNeededParcels();
    }

    private void deductNeededParcels() {

        for (int parcelTypeIndex = 0; parcelTypeIndex < parcelList.size(); parcelTypeIndex++) {
            remainingParcelsEachType[parcelTypeIndex] -= subspace.neededParcels[parcelTypeIndex];
        }
    }



    boolean enoughBlocksForSubspace(){
        //loops through the contained parcel list and counts the nr of each type,
        //then checks if this is lower than the nr of available parcels left

        boolean enoughLeft = true;
        if(subspace.neededParcels[0] > remainingParcelsEachType[0]){
            enoughLeft = false;
        }
        if(subspace.neededParcels[1] > remainingParcelsEachType[1]){
            enoughLeft = false;
        }
        if(subspace.neededParcels[2] > remainingParcelsEachType[2]){
            enoughLeft = false;
        }

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
        setContainerMatrix(new int [containerZ][containerY][containerX]);

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
        /**
         * Always run this method after calling setParcelList().This method needs a parcelList containing parcels to run correctly,
         * however before running setParcelList() the parcelList doesn't contain any value.
         *
         */
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
