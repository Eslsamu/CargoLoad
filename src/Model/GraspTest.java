package Model;

import Shapes.ParcelA;
import Shapes.ParcelShape;

public class GraspTest {

    public static void main(String[] args){
        Grasp graspModel = new Grasp(10,10,10);
        ParcelShape parcel1 = new ParcelA();
        ParcelShape parcel2 = new ParcelA();

        graspModel.testPlaceParcel(1,1,1,parcel2);
        graspModel.generateMaximalSpaces(parcel2);
       // graspModel.testPrintContainer();

        graspModel.testPlaceParcel(20,1,1,parcel1);
        graspModel.generateMaximalSpaces(parcel1);
        //graspModel.testPrintContainer();

        MaximalSpace chosenMaximalSpace = graspModel.chooseMaximalSpace();

    }
}
