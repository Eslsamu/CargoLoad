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
        ContainerModel maxValueContainer = new ContainerModel();
        maxValueContainer.setParcelList(givenParcels);
        container.solve(maxValueContainer);
        
        for(int i = 0; i < maxValueContainer.getContainedParcels().size(); i++) {
        	System.out.println(maxValueContainer.getContainedParcels().get(i).getShape()[0]);
        }
    }
}
