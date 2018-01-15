package Model;

import java.util.ArrayList;

import Shapes.PentominoShape;

public class PentoContainer {
	
	/*
	 * the dimensions of the container. can be defined by the user
	 */
	public final int containerLength = 8;
	public final int containerWidth = 5;
	public final int containerHeight = 33;
	
	/*
	 * amount of pentominoes which are given of each type. can also be defined by a user
	 */
	public final int amountL = 100;
	public final int amountP = 100;
	public final int amountT = 100;
	
	/*
	 * each index of this matrix represents a 0,5 x 0,5 x 0,5 space where a monimo is either placed or not
	 */
	protected final boolean[][][] containerMatrix = new boolean[containerLength][containerWidth][containerHeight];
	
	/*
	 * a list of pentominoes which were loaded into the container
	 */
	private ArrayList<PentominoShape> loadedPentominoes = new ArrayList<PentominoShape>();
	
	public static void main(String[]args) {
		PentoContainer testContainer = new PentoContainer();
		testContainer.loadContainer();
	}
	
	public void loadContainer() {
		
	}
}
