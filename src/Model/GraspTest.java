package Model;

import Shapes.*;
import Shapes.ParcelShape;
import Util.Coordinates;

public class GraspTest {

    public static void main(String[] args){
        Grasp graspModel = new Grasp(10,10,10);
        ParcelShape parcel1 = new ParcelA();
        ParcelShape parcel2 = new ParcelA();

        ParcelShape parcel3 = new ParcelB();
        ParcelShape parcel4 = new ParcelC();



        //graspModel.testPlaceParcel(1,1,1,parcel2);
        //graspModel.generateMaximalSpaces(parcel2);
        // graspModel.testPrintContainer();

        //graspModel.testPlaceParcel(20,1,1,parcel1);
        //graspModel.generateMaximalSpaces(parcel1);
        //graspModel.testPrintContainer();

        //MaximalSpace chosenMaximalSpace = graspModel.chooseMaximalSpace();

        Coordinates coords1 = new Coordinates(0,0,0);
        Coordinates coords2 = new Coordinates(5,8,12);

        MaximalSpace space = new MaximalSpace(coords1, coords2);

        ParcelLayer bestLayer = graspModel.findBestLayer(space, parcel3);

        System.out.println("Best: " + bestLayer.toString());

    }
}
