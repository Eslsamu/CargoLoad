package Model;
import Shapes.*;
import Util.Coordinates;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class ContainerTest {


    public static void main(String[] args) {
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();


        givenParcels.add(new ParcelA());
        givenParcels.add(new ParcelB());
        givenParcels.add(new ParcelC());
        int i=0;
        // parameter i is used only for test, delete after solve() will start working properly
        ContainerModel container = new ContainerModel();
        container.setParcelList(givenParcels);
        ArrayList<ParcelShape> usedParcels = new ArrayList<>();
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        container.solve(maxValueContainer,i);
    }
}
