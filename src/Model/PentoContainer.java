package Model;

import java.util.ArrayList;

import Shapes.PentominoL;
import Shapes.PentominoP;
import Shapes.PentominoShape;
import Shapes.PentominoT;

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
	
	/*
	 * a list of pentominoes shapes which are given to be placed
	 */
	private ArrayList<PentominoShape> givenPentominoes = new ArrayList<PentominoShape>() {{
		add(new PentominoP());
		add(new PentominoL());
		add(new PentominoT());
	}};
	
	public static void main(String[]args) {
		PentoContainer testContainer = new PentoContainer();
		testContainer.loadContainer();
	}
	
	public void loadContainer() {
		//for each index in the space
		for(int z = 0; z < containerLength; z++) {
			for(int y = 0; y < containerWidth; y++) {
				for(int x = 0; x < containerHeight; x++) {
					//if nothing is placed here
					if(!containerMatrix[x][y][z]) {
						//for each pentomino in our list of given shapes
						for(PentominoShape p : givenPentominoes) {
							//get an instance of this shape by cloning it
							PentominoShape current = p.clone();
							//rotates around the 
							
						}
					}
				}
			}
		}
	}
}
