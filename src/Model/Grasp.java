package Model;

import Shapes.*;
import java.util.*;
import Util.Coordinates;

public class Grasp {

    private ArrayList<ParcelShape> typesLeft = new  ArrayList<ParcelShape>(); //parcel types (A,B,C) of which there are still atleast 1 parcel left (should maybe have type of chars or enums instead of ParcelShape?)
    private int A_ParcelsLeft;
    private int B_ParcelsLeft;
    private int C_ParcelsLeft;
    private ArrayList<ParcelShape> parcelsPacked = new ArrayList<ParcelShape>();
    private ArrayList<MaximalSpace> maximalSpaces = new ArrayList<MaximalSpace>();

    private final static Coordinates[] containerVertices = new Coordinates[8];
    private final static Coordinates v1 = new Coordinates(0,0,0);
    private final static Coordinates v2 = new Coordinates(ContainerModel.containerX,0,0);
    private final static Coordinates v3 = new Coordinates(0,ContainerModel.containerY,0);
    private final static Coordinates v4 = new Coordinates(0,0,ContainerModel.containerZ);
    private final static Coordinates v5 = new Coordinates(ContainerModel.containerX,ContainerModel.containerY,0);
    private final static Coordinates v6 = new Coordinates(ContainerModel.containerX,0,ContainerModel.containerZ);
    private final static Coordinates v7 = new Coordinates(0,ContainerModel.containerY,ContainerModel.containerZ);
    private final static Coordinates v8 = new Coordinates(ContainerModel.containerX,ContainerModel.containerY,ContainerModel.containerZ);

    public void initialize(int nrOfA, int nrOfB, int nrOfC){
        A_ParcelsLeft = nrOfA;
        B_ParcelsLeft = nrOfB;
        C_ParcelsLeft = nrOfC;

        Coordinates initialMinCoords = new Coordinates(0,0,0);
        Coordinates initialMaxCoords = new Coordinates(ContainerModel.containerX, ContainerModel.containerY, ContainerModel.containerZ);
        MaximalSpace initialMaximalSpace = new MaximalSpace(initialMinCoords,initialMaxCoords);
        maximalSpaces.add(initialMaximalSpace);
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

        int minX = space.getMinCoords().getX();
        int minY = space.getMinCoords().getY();
        int minZ = space.getMinCoords().getZ();
        int maxX = space.getMaxCoords().getX();
        int maxY = space.getMaxCoords().getY();
        int maxZ = space.getMaxCoords().getZ();

        Coordinates[] maximalSpaceVertices = new Coordinates[8];
        Coordinates v1 = space.getMinCoords();
        Coordinates v2 = new Coordinates(maxX, minY, minZ);
        Coordinates v3 = new Coordinates(minX, maxY, minZ);
        Coordinates v4 = new Coordinates(minX, minY, maxZ);
        Coordinates v5 = new Coordinates(maxX, maxY, minZ);
        Coordinates v6 = new Coordinates(maxX, minY, maxZ);
        Coordinates v7 = new Coordinates(minX, maxY, maxZ);
        Coordinates v8 = space.getMaxCoords();

        //Coordinates closestCorner = v1;
        double[][] distance = new double[8][8]; //distance of each MaximalSpace vertex to each container corner
        double[] minDistancePerVertex = new double[8];   //distance of each MaximalSpace vertex to its closest container corner
        double minDistance = 0;

        for (int i = 0; i < maximalSpaceVertices.length; i++) {
            for(int j = 0; j < containerVertices.length; j++){
                distance[i][j] = Math.sqrt(
                                  Math.pow(containerVertices[j].getX() - maximalSpaceVertices[i].getX(),2)
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

        double[] closestCornerDistances = new double[8];

        for(int i = 0; i < maximalSpaces.size(); i++){
        //for(MaximalSpace space : maximalSpaces){
            //computeDistanceClosestCorner(space);
            closestCornerDistances[i] = computeDistanceClosestCorner(maximalSpaces.get(i));
        }

        double minimumClostestCornerDistance = 0;
        int chosenSpaceIndex = 0;

        for(; chosenSpaceIndex < closestCornerDistances.length; chosenSpaceIndex++){
            if(chosenSpaceIndex == 1) minimumClostestCornerDistance = closestCornerDistances[chosenSpaceIndex];
            else if(closestCornerDistances[chosenSpaceIndex] < minimumClostestCornerDistance)
                minimumClostestCornerDistance = closestCornerDistances[chosenSpaceIndex];
        }
        //index of the maximalSpace we choose, the one closest to a corner of the container
        MaximalSpace chosenMaximalSpace = maximalSpaces.get(chosenSpaceIndex);

        return chosenMaximalSpace;
    }

    public void fillChosenMaximalSpace(){
        //we place parcels in the chosen space to try to either completely fill the container or get the highest value
    }
}
