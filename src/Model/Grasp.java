package Model;

import Shapes.*;

import java.lang.reflect.Array;
import java.util.*;
import Util.Coordinates;

public class Grasp {
    ContainerModel exampleContainer = new ContainerModel();
    private int[][][] containerMatrix = new int[exampleContainer.containerZ][exampleContainer.containerY][exampleContainer.containerX];

    private ArrayList<ParcelShape> typesLeft = new  ArrayList<ParcelShape>(); //parcel types (A,B,C) of which there are still atleast 1 parcel left (should maybe have type of chars or enums instead of ParcelShape?)
    private int A_ParcelsLeft;
    private int B_ParcelsLeft;
    private int C_ParcelsLeft;
    private ArrayList<MaximalSpace> maximalSpaces = new ArrayList<MaximalSpace>();
    private ArrayList<ParcelShape> parcelsPacked = new ArrayList<ParcelShape>();
    private int totalValue;

    private static Coordinates[] containerVertices;

    /*
    private final static Coordinates v1 = new Coordinates(0,0,0);
    private final static Coordinates v2 = new Coordinates(exampleContainer.containerX,0,0);
    private final static Coordinates v3 = new Coordinates(0,exampleContainer.containerY,0);
    private final static Coordinates v4 = new Coordinates(0,0,exampleContainer.containerZ);
    private final static Coordinates v5 = new Coordinates(exampleContainer.containerX,exampleContainer.containerY,0);
    private final static Coordinates v6 = new Coordinates(exampleContainer.containerX,0,exampleContainer.containerZ);
    private final static Coordinates v7 = new Coordinates(0,exampleContainer.containerY,exampleContainer.containerZ);
    private final static Coordinates v8 = new Coordinates(exampleContainer.containerX,exampleContainer.containerY,exampleContainer.containerZ);
    */

    public Grasp(int nrOfA, int nrOfB, int nrOfC){
        A_ParcelsLeft = nrOfA;
        B_ParcelsLeft = nrOfB;
        C_ParcelsLeft = nrOfC;

        Coordinates containerMinCoords = new Coordinates(0,0,0);
        Coordinates containerMaxCoords = new Coordinates(exampleContainer.containerX,exampleContainer.containerY,exampleContainer.containerZ);
        containerVertices = findAllVertices(containerMinCoords,containerMaxCoords);

        Coordinates initialMinCoords = new Coordinates(0,0,0);
        Coordinates initialMaxCoords = new Coordinates(exampleContainer.containerX, exampleContainer.containerY, exampleContainer.containerZ);
        MaximalSpace initialMaximalSpace = new MaximalSpace(initialMinCoords,initialMaxCoords);
        maximalSpaces.add(initialMaximalSpace);
    }

    public Coordinates[] findAllVertices(Coordinates minCoords, Coordinates maxCoords){
        int minX = minCoords.getX();
        int minY = minCoords.getY();
        int minZ = minCoords.getZ();

        int maxX = maxCoords.getX();
        int maxY = maxCoords.getY();
        int maxZ = minCoords.getZ();

        Coordinates[] vertices =  new Coordinates[8];

        vertices[0] = new Coordinates(minX, minY, minZ);
        vertices[1] = new Coordinates(maxX, minY, minZ);
        vertices[2] = new Coordinates(minX, maxY, minZ);
        vertices[3] = new Coordinates(minX, minY, maxZ);
        vertices[4] = new Coordinates(maxX, maxY, minZ);
        vertices[5] = new Coordinates(maxX, minY, maxZ);
        vertices[6] = new Coordinates(minX, maxY, maxZ);
        vertices[7] = new Coordinates(maxX, maxY, maxZ);

        return vertices;
    }

    public double computeDistanceClosestCorner(MaximalSpace space){ //returns the coordinates of the corner closest to the input MaximalSpace space
        /*
        "For each
        new maximal-space, we compute the distance from every corner of the
        space to the corner of the container nearest to it and keep the minimum
        distance in the lexicographical order"
         */

        //so first we need to store the coordinates of the 8 corners of the container: containerVertices

        //then we loop through the containerVertices and compute for both the min and max coords the closest corner

        //we save a MaximalSpace's min and max coords, and using these we can find the coordinates of all 8 vertices

        Coordinates minCoords = space.getMinCoords();
        Coordinates maxCoords = space.getMaxCoords();

        Coordinates[] maximalSpaceVertices = findAllVertices(minCoords, maxCoords);

        System.out.println("maximalSpaceVerticesLength: " + maximalSpaceVertices.length);

        //Coordinates closestCorner = v1;
        double[][] distance = new double[8][8]; //distance of each MaximalSpace vertex to each container corner
        double[] minDistancePerVertex = new double[8];   //distance of each MaximalSpace vertex to its closest container corner
        double minDistance = 0;

        for (int i = 0; i < maximalSpaceVertices.length; i++) {
            for(int j = 0; j < containerVertices.length; j++){
                distance[i][j] =  Math.sqrt(
                                   Math.pow(/*containerVertices[j].getX()*/ - maximalSpaceVertices[i].getX(),2)
                                 + Math.pow(containerVertices[j].getY() - maximalSpaceVertices[i].getY(),2)
                                 + Math.pow(containerVertices[j].getZ() - maximalSpaceVertices[i].getZ(),2));
                if(j == 0) minDistancePerVertex[i] = distance[i][0];
                else if (distance[i][j] < minDistancePerVertex[i]) minDistancePerVertex[i] = distance[i][j];
            }
            if(i == 0) minDistance = minDistancePerVertex[i];
            else if (minDistancePerVertex[i] < minDistance){
                minDistance = minDistancePerVertex[i];
                //closestCorner = maximalSpaceVertices[i];
            }
        }
        return minDistance;
    }

    public MaximalSpace chooseMaximalSpace() {

        ArrayList<Double> closestCornerDistances = new ArrayList<>();
        //double[] closestCornerDistances = new double[8];

        for(int i = 0; i < maximalSpaces.size(); i++){
        //for(MaximalSpace space : maximalSpaces){
            //computeDistanceClosestCorner(space);

            //closestCornerDistances[i] = computeDistanceClosestCorner(maximalSpaces.get(i));
            closestCornerDistances.add(computeDistanceClosestCorner(maximalSpaces.get(i)));
        }

        double minimumClosestCornerDistance = 0;
        int minimumClosestCornerDistanceIndex = 0;
        int j = 0;

        System.out.println("maxSpaces size: " + maximalSpaces.size());
        System.out.println("closestCornerDistancesList size: " + closestCornerDistances.size());

        for(; j < closestCornerDistances.size(); j++){
            if(j == 1) //minimumClosestCornerDistance = closestCornerDistances.get(j);
                minimumClosestCornerDistanceIndex = j;
            else if(closestCornerDistances.get(j) < minimumClosestCornerDistance)
                //minimumClosestCornerDistance = closestCornerDistances.get(j);
                minimumClosestCornerDistanceIndex = closestCornerDistances.indexOf(closestCornerDistances.get(j));
        }
        //index of the maximalSpace we choose, the one closest to a corner of the container
        System.out.println("index of chosen space: " + minimumClosestCornerDistanceIndex);
        MaximalSpace chosenMaximalSpace = maximalSpaces.get(minimumClosestCornerDistanceIndex);

        return chosenMaximalSpace;
    }

    public void testPlaceParcel(int z, int y, int x, ParcelShape parcel){
        parcel.setCurrentCoordinates(new Coordinates(x,y,z));
        //sets a 1 in the containerMatrix for each coordinate with the vectors of the parcel shape
        for (int zCoord = z; zCoord < z + parcel.getShapeVector().z; zCoord++) {
            for (int yCoord = y; yCoord < y + parcel.getShapeVector().y; yCoord++) {
                for (int xCoord = x; xCoord < x + parcel.getShapeVector().x; xCoord++) {
                    System.out.println(xCoord + " " + yCoord + " " + zCoord);
                    containerMatrix[zCoord][yCoord][xCoord] = 1;
                }
            }
        }
    }

    public void testPrintContainer(){
        for(int z=0;z<exampleContainer.containerZ;z++){
            System.out.println("Layer for z = "+z);
            for(int y =0;y<exampleContainer.containerY;y++){
                for (int x=0;x<exampleContainer.containerX;x++){
                    System.out.print(exampleContainer.containerMatrix[z][y][x]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void graspTest(){
        ParcelShape parcel = new ParcelB();

        Coordinates coords1 = new Coordinates(0,0,0);
        Coordinates coords2 = new Coordinates(5,8,12);

        MaximalSpace space = new MaximalSpace(coords1, coords2);

        ParcelLayer bestLayer = findBestLayer(space, parcel);

        System.out.println("Best: " + bestLayer.toString());

        placeLayer(space, bestLayer);

        testPrintContainer();
    }

    public void placeLayer(MaximalSpace space, ParcelLayer layer) {
        if(layer == null) return;

        Coordinates origin = space.getMinCoords();

        int originX = origin.getX();
        int originY = origin.getY();
        int originZ = origin.getZ();

        AxisMaxSpaces axis = layer.getAxis();

        ParcelShape usedParcel = layer.getParcel();
        Facing usedFacing = layer.getOrientation();
        usedParcel.setOrientation(usedFacing);

        int dim1Used = layer.getDim1Used();
        int dim2Used = layer.getDim2Used();

        int originDim1 = 0;
        int originDim2 = 0;
        int originDim3 = 0;

        int parcelDim1 = 0;
        int parcelDim2 = 0;
        int parcelDim3 = 0;

        if (axis == axis.XY || axis == axis.XZ) {
            originDim1 = origin.getX();
            parcelDim1 = usedParcel.getShapeVector().getX();
        }
        if (axis == axis.YX || axis == axis.YZ) {
            originDim1 = origin.getY();
            parcelDim1 = usedParcel.getShapeVector().getY();
        }
        if (axis == axis.ZX || axis == axis.ZY) {
            originDim1 = origin.getZ();
            parcelDim1 = usedParcel.getShapeVector().getZ();
        }
        if (axis == axis.YX || axis == axis.ZX) {
            originDim2 = origin.getX();
            parcelDim2 = usedParcel.getShapeVector().getX();
        }
        if (axis == axis.XY || axis == axis.ZY) {
            originDim2 = origin.getY();
            parcelDim2 = usedParcel.getShapeVector().getY();
        }
        if (axis == axis.XZ || axis == axis.YZ) {
            originDim2 = origin.getZ();
            parcelDim2 = usedParcel.getShapeVector().getZ();
        }


        if(axis == axis.XY || axis == axis.YX) {
            originDim3 = origin.getZ();
            parcelDim3 = usedParcel.getShapeVector().getZ();
        }
        if(axis == axis.XZ || axis == axis.ZX) {
            originDim3 = origin.getY();
            parcelDim3 = usedParcel.getShapeVector().getY();
        }
        if(axis == axis.YZ || axis == axis.ZY) {
            originDim3 = origin.getX();
            parcelDim3 = usedParcel.getShapeVector().getX();
        }

        for (int dim1 = originDim1; dim1 < originDim1 + parcelDim1 * dim1Used; dim1 += parcelDim1) {
            for (int dim2 = originDim2; dim2 < originDim2 + parcelDim2 * dim2Used; dim2 += parcelDim2) {

                    ParcelShape currentParcel = usedParcel.clone();

                    switch (axis) {
                        case XY:
                            currentParcel.setCurrentCoordinates(new Coordinates(dim1, dim2, originZ));
                            break;
                        case YX:
                            currentParcel.setCurrentCoordinates(new Coordinates(dim2, dim1, originZ));
                            break;
                        case XZ:
                            currentParcel.setCurrentCoordinates(new Coordinates(dim1, originY, dim2));
                            break;
                        case ZX:
                            currentParcel.setCurrentCoordinates(new Coordinates(dim2, originY, dim1));
                            break;
                        case YZ:
                            currentParcel.setCurrentCoordinates(new Coordinates(originX, dim1, dim2));
                            break;
                        case ZY:
                            currentParcel.setCurrentCoordinates(new Coordinates(originX, dim2, dim1));
                            break;
                    }

                    parcelsPacked.add(currentParcel);
                    totalValue+= currentParcel.getValue();
            }
        }

        for (int dim1 = originDim1; dim1 < originDim1 + parcelDim1 * dim1Used; dim1++) {
            for (int dim2 = originDim2; dim2 < originDim2 + parcelDim2 * dim2Used; dim2++) {
                for(int dim3 = originDim3; dim3 < originDim3+ parcelDim3; dim3++) {
                    switch (axis) {
                        case XY:
                            exampleContainer.containerMatrix[dim3][dim2][dim1] = 1;
                            break;
                        case YX:
                            exampleContainer.containerMatrix[dim3][dim1][dim2] = 1;
                            break;
                        case XZ:
                            exampleContainer.containerMatrix[dim2][dim3][dim1] = 1;
                            break;
                        case ZX:
                            exampleContainer.containerMatrix[dim1][dim3][dim2] = 1;
                            break;
                        case YZ:
                            //System.out.println("originX: " + originX + " dim1: " + dim1 + " dim2: " + dim2);
                            exampleContainer.containerMatrix[dim2][dim1][dim3] = 1;
                            break;
                        case ZY:
                            System.out.println("originDim1 = " + originDim1 + " parcelDim1 = " + parcelDim1 + " dim1Used = " + dim1Used);
                            System.out.println("originDim2 = " + originDim2 + " parcelDim2 = " + parcelDim2 + " dim2Used = " + dim1Used);
                            //System.out.println("originX: " + originX + " dim1: " + dim1 + " dim2: " + dim2);
                            exampleContainer.containerMatrix[dim1][dim2][dim3] = 1;
                            break;
                    }
                }
            }
        }
    }

    public ParcelLayer findBestLayer(MaximalSpace space, ParcelShape p){
        int currentValue = 0;
        int bestValue = 0;

        ParcelLayer currentLayer;
        ParcelLayer bestLayer = null;

        for(AxisMaxSpaces axes : AxisMaxSpaces.values()){
            currentLayer = createLayer(space, p, axes);
            //System.out.println(currentLayer.toString());
            if(currentLayer != null) currentValue = createLayer(space, p, axes).getVolume();
            //else System.exit(0);

            //System.out.println("currentVolume: " + currentValue);

            //if(currentValue == bestValue && axes == AxisMaxSpaces.ZY || axes == AxisMaxSpaces.ZX)

            if(currentValue == bestValue && currentLayer.getParcelsUsed() < bestLayer.getParcelsUsed()){
                bestValue = currentValue;
                bestLayer = currentLayer;
            }
            else if(currentValue > bestValue) {
                bestValue = currentValue;
                bestLayer = currentLayer;
                //bestLayer = createLayer(space, p, axes);
            }
        }
        return bestLayer;
    }

    public ParcelLayer createLayer(MaximalSpace space, ParcelShape p, AxisMaxSpaces axis){
        Coordinates minCoords = space.getMinCoords();
        Coordinates maxCoords = space.getMaxCoords();
        //Coordinates maxCoords2D = new Coordinates(space.getMaxCoords().getZ(), space.getMinCoords().getY(),space.getMaxCoords().getX());

        int spaceWidth  = maxCoords.getX() - minCoords.getX();
        int spaceHeight = maxCoords.getY() - minCoords.getY();
        int spaceLength = maxCoords.getZ() - minCoords.getZ();

        int spaceDim1 = 0;
        int spaceDim2 = 0;
        int spaceDim3 = 0;

        int parcelDim1 = 0;
        int parcelDim2 = 0;
        int parcelDim3 = 0;

        int nrOfFittingParcelsDim1 = 0;
        int nrOfFittingParcelsDim2 = 0;
        int valueLayer = 0;
        int volumeLayer = 0;
        int maxValueLayer = 0;
        int maxVolumeLayer = 0;

        ParcelLayer bestVolumeLayer = null;
        ParcelLayer bestValueLayer = null;

        //int filledVolumeDim1 = nrOfFittingParcelsDim1 * parcelDim1 * parcelDim2;

        //try all parcel rotations, save layer value and keep the highest
        //first: check if unused dimension has enough space for the layer


        for(Facing o : Facing.values()){
            p.setOrientation(o);

            if(axis == axis.XY || axis == axis.XZ) {
                spaceDim1 = spaceWidth;
                parcelDim1 = p.getShapeVector().getX();
                //System.out.println("parcelX: " + p.getShapeVector().getX());
            }
            if(axis == axis.YX || axis == axis.YZ) {
                spaceDim1 = spaceHeight;
                parcelDim1 = p.getShapeVector().getY();
            }
            if(axis == axis.ZX || axis == axis.ZY) {
                spaceDim1 = spaceLength;
                parcelDim1 = p.getShapeVector().getZ();
            }
            if(axis == axis.YX || axis == axis.ZX) {
                spaceDim2 = spaceWidth;
                parcelDim2 = p.getShapeVector().getX();
            }
            if(axis == axis.XY || axis == axis.ZY) {
                spaceDim2 = spaceHeight;
                parcelDim2 = p.getShapeVector().getY();
            }
            if(axis == axis.XZ || axis == axis.YZ) {
                spaceDim2 = spaceLength;
                parcelDim2 = p.getShapeVector().getZ();
            }


            if(axis == axis.XY || axis == axis.YX) {
                spaceDim3 = spaceLength;
                parcelDim3 = p.getShapeVector().getZ();
            }
            if(axis == axis.XZ || axis == axis.ZX) {
                spaceDim3 = spaceHeight;
                parcelDim3 = p.getShapeVector().getY();
            }
            if(axis == axis.YZ || axis == axis.ZY) {
                spaceDim3 = spaceWidth;
                parcelDim3 = p.getShapeVector().getX();
            }

            if(parcelDim3 <= spaceDim3){
                nrOfFittingParcelsDim1 = 0;
                for(int i = parcelDim1; i <= spaceDim1; i += parcelDim1){
                    //System.out.println("spaceDim1 = " + spaceDim1 + "parcelDim1 = " + parcelDim1);
                    nrOfFittingParcelsDim1++;
                }

                //System.out.println("nrOfFittingParcelsDim1: " + nrOfFittingParcelsDim1);

                nrOfFittingParcelsDim2 = 0;
                for(int i = parcelDim2; i <= spaceDim2; i += parcelDim2){
                    nrOfFittingParcelsDim2++;
                }

                valueLayer = nrOfFittingParcelsDim1 * nrOfFittingParcelsDim2 * p.getValue();
                volumeLayer = (nrOfFittingParcelsDim1 * parcelDim1) * (nrOfFittingParcelsDim2 * parcelDim2) * parcelDim3;

                ParcelLayer currentLayer = new ParcelLayer(p,o, nrOfFittingParcelsDim1, nrOfFittingParcelsDim2, volumeLayer, valueLayer, axis);
                System.out.println("currentLayer: " + currentLayer.toString());

                if(valueLayer > maxValueLayer){
                    maxValueLayer = valueLayer;
                    Facing usedFacing = o;
                    int dim1Used = nrOfFittingParcelsDim1;
                    int dim2Used = nrOfFittingParcelsDim2;
                    bestValueLayer = new ParcelLayer(p, o, dim1Used, dim2Used,volumeLayer,maxValueLayer, axis);
                }
                if(volumeLayer > maxVolumeLayer){
                    maxVolumeLayer = volumeLayer;
                    Facing usedFacing = o;
                    int dim1Used = nrOfFittingParcelsDim1;
                    int dim2Used = nrOfFittingParcelsDim2;
                    bestVolumeLayer = new ParcelLayer(p, o, dim1Used, dim2Used,maxVolumeLayer,valueLayer, axis);
                }
            }

        }
        if(bestValueLayer != null)System.out.println("Sub-best: " + bestValueLayer.toString());
        return bestValueLayer;
    }

    public void fillChosenMaximalSpace(){
        //we place parcels in the chosen space to try to either completely fill the container or get the highest value.


    }

    public ArrayList<ParcelShape> getParcelsPacked() {
        return parcelsPacked;
    }

    public int getTotalValue() {
        return totalValue;
    }

    /*
    public void generateMaximalSpaces(ParcelShape lastPlacedParcel){ //should later use blocks (groups of parcels put into a layer) instead of single parcels

        Coordinates coords = lastPlacedParcel.getPosition();
        Coordinates minCoordsParcel = coords;
        Coordinates maxCoordsParcel = new Coordinates
        (coords.getX() + lastPlacedParcel.getShapeVector().x,
        coords.getY() + lastPlacedParcel.getShapeVector().y,
        coords.getZ() + lastPlacedParcel.getShapeVector().z);
        System.out.println("X-coord: " + coords.getX() + " Y-coord:  " + coords.getY() + " Z-coord: " + coords.getZ());

        //Coordinates[] blockVertices = new Coordinates[8];
        Coordinates[] blockVertices = findAllVertices(minCoordsParcel, maxCoordsParcel);

        //ArrayList<Coordinates> blockVertices = new ArrayList<>();
        ArrayList<MaximalSpace> generatedSpaces = new ArrayList<>();


        /*
        After we place a block (a collection of parcels to best fill the last chosen maximal space),
        we select a vertex of this block and check in all directions how much empty space is around it,
        and save the dimensions.

        for each vertex there are certain directions on which there can be empty space and certain directions
        in which the block is

        When a new maximal space is created any old maximal space with which it overlaps should be removed from the list

         */

        //Coordinates[] currentMaxSpaceVertices = new Coordinates[8];

    /*
        MaximalSpace currentMaxSpace;

        for(int i = 0; i < blockVertices.length; i++) {
            int z = blockVertices[i].getZ();
            int y = blockVertices[i].getY();
            int x = blockVertices[i].getX();

            for (; z < exampleContainer.containerZ-1 && containerMatrix[z][y][x] == 1; z++) {
                int zDimension = z;
            }
            for (; y < exampleContainer.containerY-1 && containerMatrix[z][y][x] == 1; y++) {
                int yDimension = y;
            }
            for (; x < exampleContainer.containerX-1 && containerMatrix[z][y][x] == 1; x++) {
                int xDimension = x;
            }

            System.out.println(x + " " + y + " " + z);
            Coordinates minCoords = new Coordinates(blockVertices[i].getX(), y, blockVertices[i].getZ());
            Coordinates maxCoords = new Coordinates(x, blockVertices[i].getY(), z);
            currentMaxSpace = new MaximalSpace(minCoords, maxCoords);
            generatedSpaces.add(currentMaxSpace);
            System.out.println("generatedSpaces size: " + generatedSpaces.size());
            //System.out.println("maxSpaces size: " + maximalSpaces.size());
        }


/*
        for(int i = 0; i < blockVertices.length; i++){
            for(int z = blockVertices[i].getZ(); z < exampleContainer.containerZ || containerMatrix[z][y][x] == 1; z++){
                for(int y = blockVertices[i].getY(); y < exampleContainer.containerY || containerMatrix[z][y][x] == 1; y++){
                    for(int x = blockVertices[i].getX(); x < exampleContainer.containerX || containerMatrix[z][y][x] == 1; x++){

                        Coordinates minCoords = new Coordinates(blockVertices[i].getX(), y, blockVertices[i].getZ());
                        Coordinates maxCoords = new Coordinates(x, blockVertices[i].getY(), z);
                        currentMaxSpaceVertices = findAllVertices(minCoords, maxCoords);

                    }
                }

            }
        }


        maximalSpaces.addAll(generatedSpaces);
       // return generatedSpaces;
      }
*/

}
