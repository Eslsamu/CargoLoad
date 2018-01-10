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


    public MaximalSpace chooseMaximalSpace() {

        /*
        "For each
        new maximal-space, we compute the distance from every corner of the
        space to the corner of the container nearest to it and keep the minimum
        distance in the lexicographical order"
         */

        //so first we need to store the coordinates of the 8 corners of the container: containerVertices

        //then we loop through the containerVertices and compute for both the min and max coords the closest corner

        for (int i = 0; i < containerVertices.length; i++) {

        }

        int a = 0; //index of the maximalSpace we choose, the one closest to a corner of the container
        MaximalSpace chosenMaximalSpace = maximalSpaces.get(a);

        return chosenMaximalSpace;
    }

    public void fillChosenMaximalSpace(){
        //we place parcels in the chosen space to try to either completely fill the container or get the highest value
    }
}
