package Model;

import Shapes.*;

import java.util.ArrayList;
/**
 * Class is used when solving by divide and Conquer, subspace is a layer in the container that we are trying to fill
 * @author Blazej, Samuel, Yvar, Stijn
 */
public class Subspace extends ContainerModel{
    protected int[] neededParcels;
    // in 0.5 meters
    // these are need for subspaces
    /**
     * Construct a 3d layer 
     * @param z depth
     * @param y height
     * @param x width
     * @param subspaceParcelList list of parcels that should be tried
     */
    public Subspace(int z, int y, int x, ArrayList<Shape> subspaceParcelList){
        containerZ = z;
        containerY = y;
        containerX = x;
        neededParcels = new int[3];
        parcelList = subspaceParcelList;

        containerMatrix = new int[containerZ][containerY][containerX];
        containedParcels = new ArrayList<>();

    }
    /**
     * Computes how many parcels we need to fill the subspace
     */
    protected void computeNeededParcels(){
        int nrOfANeeded = 0;
        int nrOfBNeeded = 0;
        int nrOfCNeeded = 0;

        for(Shape parcel : containedParcels){
            if(parcel instanceof ParcelA) nrOfANeeded++;
            if(parcel instanceof ParcelB) nrOfBNeeded++;
            if(parcel instanceof ParcelC) nrOfCNeeded++;
        }

        for(int parcelTypeIndex=0;parcelTypeIndex<parcelList.size();parcelTypeIndex++){
            if(parcelList.get(parcelTypeIndex) instanceof ParcelA){
                neededParcels[parcelTypeIndex] = nrOfANeeded;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelB){
                neededParcels[parcelTypeIndex] = nrOfBNeeded;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelC){
                neededParcels[parcelTypeIndex] = nrOfCNeeded;
            }
        }
    }

}
