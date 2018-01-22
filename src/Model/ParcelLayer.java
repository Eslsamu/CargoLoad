package Model;

import Shapes.*;

public class ParcelLayer {
    private ParcelShape parcelType;
    private int amountOfParcels;
    private Facing orientation;
    private AxisMaxSpaces a;
    private int dim1Used;
    private int dim2Used;
    private int volumeLayer;
    private int valueLayer;


    public ParcelLayer(ParcelShape parcelType, Facing orientation, int dim1Used, int dim2Used, int volumeLayer, int valueLayer, AxisMaxSpaces a){
        this.parcelType = parcelType;
        this.orientation = orientation;
        this.dim1Used = dim1Used;
        this.dim2Used = dim2Used;
        this.volumeLayer = volumeLayer;
        this.valueLayer = valueLayer;
        this.a = a;
    }

    public int getVolume(){
        return volumeLayer;
    }

    public String toString(){
        return "Nr dim1: " + dim1Used + ", Nr dim2: " + dim2Used + ", orientation " + orientation + ", axis: " + a + ", value: " + valueLayer + ", volume: " + volumeLayer;
    }

    public int getParcelsUsed(){
        return dim1Used * dim2Used;
    }

    public AxisMaxSpaces getAxis() {
        return a;
    }

    public int getDim1Used() {
        return dim1Used;
    }

    public int getDim2Used() {
        return dim2Used;
    }

    public Facing getOrientation(){
        return orientation;
    }

    public ParcelShape getParcel(){
        if (parcelType instanceof ParcelA) return new ParcelA();
        else if (parcelType instanceof ParcelB) return new ParcelB();
        else if (parcelType instanceof ParcelC) return new ParcelC();
        else return new ParcelA();
    }
}
