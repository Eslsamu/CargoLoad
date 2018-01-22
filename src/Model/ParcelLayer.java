package Model;

import Shapes.*;

public class ParcelLayer {
    private ParcelShape parcelType;
    private int amountOfParcels;
    private Facing orientation;
    private int dim1Used;
    private int dim2Used;
    private int volumeLayer;

    public ParcelLayer(ParcelShape parcelType, Facing orientation, int dim1Used, int dim2Used, int volumeLayer){
        this.parcelType = parcelType;
        this.orientation = orientation;
        this.dim1Used = dim1Used;
        this.dim2Used = dim2Used;
        this.volumeLayer = volumeLayer;
    }

    public int getVolume(){
        return volumeLayer;
    }

}
