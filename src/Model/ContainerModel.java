package Model;

import Shapes.ParcelShape;

public class ContainerModel {
    /**
     * How it should work:
     * The programs tries to fill cargo with given parcels (A, B, C), starting with the most valuable. It adds more and more parcels,
     * until nothing can be added to the cargo.
     * If the cargo is not full, it saves the configuration and value and keeps backtracking search like in Phase 1. If the cargo is full,
     * it returns the score. If it goes through all possibilities and in none of them cargo is filled, the best value is returned.
     *
     */

    public static void main(String[] args) {
        Container container = new Container();
        container.printContainer();
    }

    /**
     * This method packs the problem with a simple backtracking algorithm similar to that one from Phase 1.
     * @param container A container that we want to fill with parcels
     * @param parcels An array of parcels we can use. In the first version it is A, B, C and in the second it's L, P, T.
     * @param maxValueContainer The container that has been already packed and reached the maximal value so far
     * @return
     */
    public static boolean solve(Container container, ParcelShape[] parcels, Container maxValueContainer){
        return true;
    }
}
