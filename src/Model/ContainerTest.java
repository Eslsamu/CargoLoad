package Model;
import Shapes.*;
import java.util.ArrayList;



public class ContainerTest {


    public static void main(String[] args) {
        ArrayList<ParcelShape> givenParcels = new ArrayList<>();

        givenParcels.add(new ParcelA());
        givenParcels.add(new ParcelB());
        givenParcels.add(new ParcelC());
       
        ContainerModel container = new ContainerModel();
        container.setParcelList(givenParcels);
        container.setAmountOfParcels(10,10,10);
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        //container.solve(maxValueContainer);/
        
        System.out.println(maxValueContainer.getContainedParcels().size());
        container.solveFirstPackedCargo();

    }
}
