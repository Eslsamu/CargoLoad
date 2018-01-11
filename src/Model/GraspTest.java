package Model;

import Shapes.ParcelA;
import Shapes.ParcelShape;

public class GraspTest {

    public static void main(String[] args){
        Grasp graspModel = new Grasp(10,10,10);
        ParcelShape parcel = new ParcelA();
        graspModel.testPlaceParcel(1,1,1,parcel);
        graspModel.testPrintContainer();
        graspModel.generateMaximalSpaces(parcel);
    }
}
