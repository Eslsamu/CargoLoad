mport java.util.ArrayList;

public class ParcelGenerator {


    //set to private and make getters and setters maybe

    ArrayList<Parcel> rotationsA = new ArrayList<Parcel>();

    ArrayList<Parcel> rotationsB = new ArrayList<Parcel>();

    ArrayList<Parcel> rotationsC = new ArrayList<Parcel>();

    static ArrayList<ArrayList<Parcel>> allParcels = new ArrayList<ArrayList<Parcel>>();

    public static ArrayList<ArrayList<Parcel>> getAllParcels() {
        return allParcels;
    }

    public ParcelGenerator() {
        Parcel A1 = new Parcel(new int[]{2, 2, 4});
        Parcel A2 = new Parcel(new int[]{2, 4, 2});
        Parcel A3 = new Parcel(new int[]{4, 2, 2});

        rotationsA.add(A1);
        rotationsA.add(A2);
        rotationsA.add(A3);


        Parcel B1 = new Parcel(new int[]{2, 3, 4});
        Parcel B2 = new Parcel(new int[]{2, 4, 3});
        Parcel B3 = new Parcel(new int[]{3, 2, 4});
        Parcel B4 = new Parcel(new int[]{3, 4, 2});
        Parcel B5 = new Parcel(new int[]{4, 2, 3});
        Parcel B6 = new Parcel(new int[]{4, 3, 2});

        rotationsB.add(B1);
        rotationsB.add(B2);
        rotationsB.add(B3);
        rotationsB.add(B4);
        rotationsB.add(B5);
        rotationsB.add(B6);

        Parcel C1 = new Parcel(new int[]{3, 3, 3});

        rotationsC.add(C1);

        allParcels.add(rotationsA);
        allParcels.add(rotationsB);
        allParcels.add(rotationsC);
    }





}
