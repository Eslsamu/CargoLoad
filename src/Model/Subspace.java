package Model;

import Shapes.ParcelA;
import Shapes.ParcelB;
import Shapes.ParcelC;
import Shapes.ParcelShape;

import java.util.ArrayList;

public class Subspace extends ContainerModel{
    protected int[] neededParcels;
    // in 0.5 meters
    // these are need for subspaces

    public Subspace(int z, int y, int x, ArrayList<ParcelShape> subspaceParcelList){
        containerZ = z;
        containerY = y;
        containerX = x;
        neededParcels = new int[3];
        parcelList = subspaceParcelList;
        containerMatrix = new int[containerZ][containerY][containerX];
        containedParcels = new ArrayList<>();

    }

    protected void computeNeededParcels(){
        int nrOfANeeded = 0;
        int nrOfBNeeded = 0;
        int nrOfCNeeded = 0;

        for(ParcelShape parcel : containedParcels){
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
