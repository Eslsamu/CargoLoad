import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {

        Container cargo = new Container();

        Parcel A = new Parcel(Parcel.DIMENSIONS_A);
        Parcel B = new Parcel(Parcel.DIMENSIONS_B);
        Parcel C = new Parcel(Parcel.DIMENSIONS_C);

        //int[] location = {0,0,0};

        ArrayList<ArrayList<Parcel>> parcelList = new ArrayList<ArrayList<Parcel>>();

        ParcelGenerator startGenerator = new ParcelGenerator();

        ArrayList<ArrayList<Parcel>> allParcels = startGenerator.allParcels;

        //once no more parcels fit: end the loop and output saying no more fit

        /*
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(0));
        */
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));



        //parcelList.add(allParcels.get(1));
        //parcelList.add(allParcels.get(2));
        /*
        parcelList.add(allParcels.get(0));
        parcelList.add(allParcels.get(1));
        parcelList.add(allParcels.get(2));
        */


//test

        //parcelList.add(allParcels.get(0).get(1));
        //parcelList.add(allParcels.get(0).get(2));

       // parcelList.add(A);
       // parcelList.add(A);
       // parcelList.add(B);
       // parcelList.add(B);
       // parcelList.add(C);
      //  parcelList.add(C);

        //Container.placeParcels(location, cargo, A);

        //Container.printRepresentation(cargo);
        Container.loadCargo(cargo, parcelList);
        Container.printRepresentation(cargo);
    }
}