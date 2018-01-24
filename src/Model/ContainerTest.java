package Model;
import Shapes.*;
import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;


public class ContainerTest {


    public static void main(String[] args) {
        ArrayList<Shape> givenParcels = new ArrayList<>();

        givenParcels.add(new PentominoP());
        givenParcels.add(new PentominoL());
        givenParcels.add(new PentominoT());

       PentoContainer container = new PentoContainer();
        System.out.println(givenParcels.size());
        //givenParcels = container.orderParcelListByValue(givenParcels);
        givenParcels = container.orderParcelListByRatio(givenParcels);
        container.setParcelList(givenParcels);
        container.setAmountOfParcels(100,100,100);
        PentoContainer maxValueContainer = new PentoContainer();
        maxValueContainer.setParcelList(givenParcels);
        int delay = 5000; // in milliseconds
        container.setDelay(delay);
        container.loadContainer(maxValueContainer,true,true);


    }
}
