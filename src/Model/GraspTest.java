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

        ParcelShape parcel = new ParcelB();

        Coordinates coords1 = new Coordinates(1,3,5);
        Coordinates coords2 = new Coordinates(4,7,25);

        MaximalSpace space = new MaximalSpace(coords1, coords2);

        System.out.println(graspModel.computeLexicographicalDestinance(space));

        //ParcelLayer bestLayer = graspModel.findBestLayer(space, parcel);

        //System.out.println("Best: " + bestLayer.toString());

        //graspModel.placeLayer(space, bestLayer);

        //graspModel.testPrintContainer();


    }
}
