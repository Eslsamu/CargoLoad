package Model;

import java.util.ArrayList;

import Shapes.Monimo;
import Shapes.PentominoL;
import Shapes.PentominoP;
import Shapes.PentominoShape;
import Shapes.PentominoT;
import Util.Axis;
import Util.Coordinates;

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
	protected final String[][][] containerMatrix = new String[containerLength][containerWidth][containerHeight];
	
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
		testContainer.loadContainer(300);
	}
	
	public boolean loadContainer(int iteration) {
		//to find a perfect solution, there have to be 264 pentominoes in the loaded list. 1320(containervolume)/5(pento volume)
		//the other stopping condition is the given amount of time or iterations
		if(loadedPentominoes.size() >= containerLength*containerWidth*containerHeight/5 || iteration <= 0) {
			printContainer();
			return true;
		}
		
		//for each index in the space
		for(int z = 0; z < containerHeight; z++) {
			for(int y = 0; y < containerWidth; y++) {
				for(int x = 0; x < containerLength; x++) {
					//if nothing is placed here
					if(containerMatrix[x][y][z]==null) {
						//for each pentomino in our list of given shapes
						for(PentominoShape p : givenPentominoes) {
							//get an instance of this shape by cloning it
							PentominoShape current = p.clone();
							//rotates around the each of it's axis with moving the down-left-back most monimo to the origin
							// so that it doesnt try orientations which are blocked anyway and also to 'flip' it this way around its own body
							for(int xAxis = 0; xAxis < 4; xAxis++) {
								current.rotate(90, Axis.X);
								for(int yAxis = 0; yAxis < 4; yAxis++) {
									current.rotate(90, Axis.Y);
									for(int zAxis = 0; zAxis < 4; zAxis++) {
										current.rotate(90, Axis.Z);
										current.moveToOrigin();
										//check if all of the current pentominos monimoes do fit onto these coordinates in the container
										if(doesFit(current, new Coordinates(x,y,z))){
											place(current, new Coordinates(x,y,z));
											iteration--;
											if(loadContainer(iteration)) {
												return true;
											}
											else {
												removeLast(current);
											}													
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Iterates through all the monimoes of the pentomino and checks if all of them can be placed in the container matrix
	 */
	public boolean doesFit(PentominoShape p, Coordinates c) {
		for(Monimo m : p.getChildren()) {
			int xfit = c.x+m.getPositionShape().x;
			int yfit = c.y+m.getPositionShape().y;
			int zfit = c.z+m.getPositionShape().z;
			if(xfit > containerLength - 1 || yfit > containerWidth- 1|| zfit > containerHeight - 1
					 || xfit < 0  || yfit < 0  || zfit < 0  ||
					containerMatrix[xfit][yfit][zfit]!=null) {
				return false;
			}
		}
		return true;
	}
	/*
	 * First the position variable of the instance is set, then the coordinates in the container matrix are set to true
	 */
	public void place(PentominoShape p, Coordinates c) {
		loadedPentominoes.add(p);
		p.setContainerPosition(c);
		for(Monimo m : p.getChildren()) {
			containerMatrix[m.getContainerPosition().x][m.getContainerPosition().y][m.getContainerPosition().z] = p.getName();
		}
	}
	
	/*
	 * removes the last pentomino from the list and set's its coordinates in the container matrix to false in reverse order
	 */
	public void removeLast(PentominoShape p) {
		for(Monimo m : p.getChildren()) {
			containerMatrix[m.getContainerPosition().x][m.getContainerPosition().y][m.getContainerPosition().z] = null;
		}
		loadedPentominoes.remove(loadedPentominoes.size() -1);
	}
	
	public void printContainer(){
		int holes = 0;
        for(int z=0;z<containerHeight;z++){
            System.out.println("Layer for z = "+z);
            for(int y =0;y<containerWidth;y++){
                for (int x=0;x<containerLength;x++){
                    if(containerMatrix[x][y][z]!=null) System.out.print(containerMatrix[x][y][z]+" ");
                    else {
                    	System.out.print("O ");
                    	holes++;
                    	}
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("holes: "+ holes);
        System.out.println("loaded shapes: "+ loadedPentominoes.size());
    }
}
