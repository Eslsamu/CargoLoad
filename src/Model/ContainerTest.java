package Model;
import Shapes.*;
import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;


public class ContainerTest {


    public static void main(String[] args) {
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();

        givenParcels.add(new ParcelA());
        givenParcels.add(new ParcelB());
        givenParcels.add(new ParcelC());
       
        ContainerModel container = new ContainerModel();

        givenParcels = container.orderParcelListByValue(givenParcels);
        //givenParcels = container.orderParcelListByRatio(givenParcels);

        container.setParcelList(givenParcels);
        container.setAmountOfParcels(10,30,20);
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        //container.solve(maxValueContainer);/
        
        System.out.println(maxValueContainer.getContainedParcels().size());
        container.solveFirstPackedCargo();

    }
}
