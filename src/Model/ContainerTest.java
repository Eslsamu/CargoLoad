package Model;
import Shapes.*;
import Util.Coordinates;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class ContainerTest {


    public static void main(String[] args) {
        ArrayList<ParcelShape> givenParcels = new ArrayList<ParcelShape>();

        int y=0;
        for(Facing x: Facing.values()){
            givenParcels.set(y,new ParcelA(x));
            givenParcels.set(y+1,new ParcelB(x));
            givenParcels.set(y+2,new ParcelC(x));
            y+=3;
        }



        ContainerModel container = new ContainerModel();
        container.setParcelList(givenParcels);
        ArrayList<ParcelShape> usedParcels = new ArrayList<>();
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        container.solve(usedParcels,maxValueContainer);

    }
}
