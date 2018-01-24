package Model;

import Shapes.*;

import java.util.ArrayList;

public class Subspace extends ContainerModel{
    protected int[] neededParcels;
    // in 0.5 meters
    // these are need for subspaces

    public Subspace(int z, int y, int x, ArrayList<Shape> subspaceParcelList){
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
}
