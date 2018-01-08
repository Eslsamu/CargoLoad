package Model;

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

    public static boolean solve(){
        // this method works pretty similar to solve() method for phase 1
        return true;
    }
}
