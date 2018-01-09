import java.util.ArrayList;

public class Container{

    static int containerWidth = 5; //units of 0.5m
    static int containerHeight = 8;
    static int containerLength = 33;
    int[][][] containerSpace;

    public Container(){
        //this.containerWidth = containerWidth;
        //this.containerHeight = containerHeight;
        //this.containerLength = containerLength;
        containerSpace = new int[this.containerWidth][this.containerHeight][this.containerLength]; //[x][y][z]
    }

    public int[][][] getContainerSpace(){
        return containerSpace;
    }

    public void setContainerSpace(int[][][] newContainerSpace){
        containerSpace = newContainerSpace;
    }

    public static void printRepresentation(Container containerObject){
        double filledSquares = 0;
        int [][][] tempContainerSpace = containerObject.getContainerSpace();
        for(int y = 0; y < containerHeight; y++)
        {
            System.out.println();
            System.out.println("Layer " + (y + 1) + ":");
            for(int x = 0; x < containerWidth; x++)
            {
                System.out.println();
                for(int z = 0; z < containerLength; z++)
                {
                    if (tempContainerSpace[x][y][z] == 1) filledSquares++;
                    System.out.print(tempContainerSpace[x][y][z] + " ");
                }
            }
            System.out.println();
        }
        double fillPercent = (filledSquares/1330) * 100;
        String fillPercentRounded = String.format("%.5g%n", fillPercent);
        System.out.println("nr of filled squares = " + filledSquares + ", " + "\n" + fillPercentRounded + "% of the truck is filled");
    }

    public static void placeParcels(int[] placementCoords, Container container, Parcel parcel){
        int [][][] tempContainerSpace = container.getContainerSpace();
        for(int z = placementCoords[2]; z < parcel.getLength() + placementCoords[2]; z++)
        {
            for(int y = placementCoords[1]; y < parcel.getHeight() + placementCoords[1]; y++)
            {
                for(int x = placementCoords[0]; x < parcel.getWidth() + placementCoords[0]; x++)
                {
                    tempContainerSpace[x][y][z] = 1;
                }
            }
        }
        container.setContainerSpace(tempContainerSpace);
    }

    public static boolean doesFit(int[] placementCoords, Container container, Parcel parcel){
        int [][][] tempContainerSpace = container.getContainerSpace();

        if( (parcel.getWidth() + placementCoords[0] > containerWidth)
         || (parcel.getHeight() + placementCoords[1] > containerHeight)
         || (parcel.getLength() + placementCoords[2] > containerLength)
         //|| placementCoords[0] == 1 || placementCoords[1] == 1 || placementCoords[2] == 1)
         || (tempContainerSpace[placementCoords[0]][placementCoords[1]][placementCoords[2]]) == 1)
        {
           // System.out.println("doesnt fit " + placementCoords[0] + " " + placementCoords[1] + " " + placementCoords[2]);
            return false;
        }
        else {
           // System.out.println("fits " + placementCoords[0] + " " + placementCoords[1] + " " + placementCoords[2]);
            return true;
        }
    }

    //instead of 1 parcel, should use an arraylist of parcels (or use this and go parcel by parcel through an arraylist)


    public static void cargoPlacement(int[] tempPlacementCoords, Container container, ArrayList<ArrayList<Parcel>> parcelList, int i){
        int j = 0;
        for(; j < parcelList.get(i).size(); j++) {
            if (doesFit(tempPlacementCoords, container, parcelList.get(i).get(j))) {
                System.out.println("parcel " + i + " with rotation " + j + " fits in location " + tempPlacementCoords[0] + " " + tempPlacementCoords[1] + " " + tempPlacementCoords[2]);
                placeParcels(tempPlacementCoords, container, parcelList.get(i).get(j));

                System.out.println("parcellist2: " + parcelList);
                parcelList.remove(i);
                break;
                // return;
            }
        }

          // else if(!doesFit(tempPlacementCoords, container, parcelList.get(i).get(j)) && parcelList.size() == 1)
          //  {
        //        removeLastParcel(tempPlacementCoords, container, parcelList.get(i).get(j));
         //       parcelList.add(parcelList.get(i));
           // }
        //}
    }

    public static void removeLastParcel(int[] placementCoords, Container container, Parcel parcel){
        int [][][] tempContainerSpace = container.getContainerSpace();
        for(int z = placementCoords[2]; z < parcel.getLength() + placementCoords[2]; z++)
        {
            for(int y = placementCoords[1]; y < parcel.getHeight() + placementCoords[1]; y++)
            {
                for(int x = placementCoords[0]; x < parcel.getWidth() + placementCoords[0]; x++)
                {
                    tempContainerSpace[x][y][z] = 0;
                }
            }
        }
        container.setContainerSpace(tempContainerSpace);
    }

    public static boolean checkIfDone(Container container) {
        boolean done = true;
        int[][][] tempContainerSpace = container.getContainerSpace();

        for (int z = 0; z < containerHeight; z++) {
            for (int y = 0; y < containerHeight; y++) {
                for (int x = 0; x < containerWidth; x++) {
                    if (tempContainerSpace[x][y][z] == 0) done = false;
                }
            }
        }
        return done;
    }

    // @formatter:off
    public static void loadCargo(Container container, ArrayList<ArrayList<Parcel>> parcelList){
        int [][][] tempContainerSpace = container.getContainerSpace();
        int z = 0;
        int tempParcelListSize = parcelList.size();
        int currentParcelListSize = 0;

        while(!checkIfDone(container)) //|| z != containerLength //parcelList.size() != 0)
        {
            if (tempParcelListSize == currentParcelListSize)
            {
                System.out.println("No more room for parcels");
                break;
            }
            else tempParcelListSize = currentParcelListSize;

            System.out.println("parcellist1: " + parcelList);
            for(z = 0; z < containerLength; z++)
            {
                for(int y = 0; y < containerHeight; y++)
                {
                    for(int x = 0; x < containerWidth; x++)
                    {
                        if (tempContainerSpace[x][y][z] != 1) {
                            //System.out.println(x + " " + y + " " + z + " " + tempContainerSpace[x][y][z]);
                            int[] tempPlacementCoords = {x, y, z};
                            ;
                            //System.out.println(tempPlacementCoords[0] + " " + tempPlacementCoords[1] + " " + tempPlacementCoords[2]);
                            for(int i = 0; i < parcelList.size(); i++)
                            {
                                cargoPlacement(tempPlacementCoords, container, parcelList, i);
                                tempContainerSpace = container.getContainerSpace();
                                currentParcelListSize = parcelList.size();
                                /*
                                //for every rotation ( allParcels(i).size() )
                                for(int j = 0; j < ParcelGenerator.getAllParcels().get(i).size(); j++)
                                {
                                    if(doesFit(tempPlacementCoords, container, parcelList.get(i).get(j)))
                                    {
                                        cargoPlacement(tempPlacementCoords, container, parcelList, i, j);
                                        tempContainerSpace = container.getContainerSpace();

                                        System.out.println("parcel " + i + " fits in location " + tempPlacementCoords[0] + " " + tempPlacementCoords[1] + " " + tempPlacementCoords[2]);
                                        placeParcels(tempPlacementCoords, container, parcelList.get(i).get(j));
                                        tempContainerSpace = container.getContainerSpace();
                                        System.out.println("parcellist2: " + parcelList);
                                        parcelList.remove(i);
                                        break;
                                      }
                                   }
                                        */
                            }

                        }
                    }
                }
            }
        }
    }

}
// @formatter:off
