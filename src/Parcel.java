public class Parcel {

    static final int[] DIMENSIONS_A = {2,2,4}; //units of 0.5m
    static final int[] DIMENSIONS_B = {2,3,4};
    static final int[] DIMENSIONS_C = {3,3,3};


    //since we will rotate the parcels maybe width, height, length aren't useful terms
    private int width;
    private int height;
    private int length;
   // private int nrOfRotations;


  //  public int getNrOfRotations() {
  //      return nrOfRotations;
 //   }

    public Parcel(int[] dimensions){
        width = dimensions[0];
        height = dimensions[1];
        length = dimensions[2];
      //  this.nrOfRotations = nrOfRotations;

    }

    public int getLength(){
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
