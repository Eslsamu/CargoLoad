package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Shapes.*;
import Util.Axis;
import Util.Coordinates;
/**
 * Class used for solving the container with pentominoes
 * @author Samuel, Blazej, Yvar, Stijn
 */
public class PentoContainer extends SuperContainer{
        /**
	 * A matrix representing the container. Each index of this matrix represents a 0,5 x 0,5 x 0,5 space.
	 *
	 */
	protected String[][][] containerMatrix = new String[containerX][containerY][containerZ];


	/**
	 * Solves a container with backtracking.
	 * @param maxValueContainer
	 * @param startTimer
	 * @param firstCall
	 * @return
	 */
	public boolean loadContainer(PentoContainer maxValueContainer, boolean startTimer, boolean firstCall) {
		if (startTimer) {
			java.util.Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("Timer");
					finish = true;
					timer.cancel();
				}
			}, delay);
			startTimer = false;
		}

		//The end condition of the recursive loop --> checks if the container is completely filled
		if (checkIfFull()) {
			System.out.println("The cargo is full.");

			return true;
		}
		while (nonEmptyParcelType < parcelList.size()-1 && remainingParcelsEachType[nonEmptyParcelType] == 0){
			nonEmptyParcelType++;
		}

		//for each index in the space
		for(int z = 0; z < containerZ; z++) {
			for(int y = 0; y < containerY; y++) {
				for(int x = 0; x < containerX; x++) {
					//if nothing is placed here
					if(containerMatrix[x][y][z]==null) {
						//for each pentomino in our list of given shapes
						for (int parcelType = nonEmptyParcelType; parcelType < parcelList.size(); parcelType++) {
							if(remainingParcelsEachType[parcelType]>0) {
								System.out.println(remainingParcelsEachType[parcelType]);
								//get an instance of this shape by cloning it
								PentominoShape helpP = (PentominoShape) parcelList.get(parcelType);
								PentominoShape current = helpP.clone();

								//rotates around the each of it's axis with moving the down-left-back most monimo to the origin
								// so that it doesnt try orientations which are blocked anyway and also to 'flip' it this way around its own body
								for (int xAxis = 0; xAxis < 4; xAxis++) {
									current.rotate(90, Axis.X);
									for (int yAxis = 0; yAxis < 4; yAxis++) {
										current.rotate(90, Axis.Y);
										for (int zAxis = 0; zAxis < 4; zAxis++) {
											current.rotate(90, Axis.Z);
											current.moveToOrigin();
											//check if all of the current pentominos monimoes do fit onto these coordinates in the container
											if (doesFit(current, new Coordinates(x, y, z))) {
												place(current, new Coordinates(x, y, z));
												remainingParcelsEachType[parcelType]--;
												containedParcels.add(current);
												for(Monimo m:((PentominoShape)containedParcels.get(0)).getChildren()){
													System.out.println("Coordaintes"+m.getPositionShape().x+" "+m.getPositionShape().y+" "+m.getPositionShape().z);
												}
												if (loadContainer(maxValueContainer, startTimer, false)) {
													return true;
												} else {
													removeLast(current);
													containedParcels.remove(current);
													remainingParcelsEachType[parcelType]++;
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
		}

		System.out.println("Total value container: "+computeTotalValue());
		System.out.println("Total value maxContainer: "+maxValueContainer.computeTotalValue());
		if(computeTotalValue()>maxValueContainer.computeTotalValue()){
			System.out.println("In ifa total value container: "+computeTotalValue());
			System.out.println("In ifb total value maxContainer: "+maxValueContainer.computeTotalValue());
			System.out.println();
			//printContainer();
			clone(maxValueContainer);
		}

		if(finish){
			System.out.println("Finish");
			cloneFinish(maxValueContainer);
			for(Shape parcel:containedParcels){
				System.out.println(((PentominoShape) parcel).getPositionParcelContainer());
			}
			return true;
		}



		if(firstCall){
			System.out.println("Finish first call");
			cloneFinish(maxValueContainer);
//			showResults();
//            printContainedShapes();
			return true;
		}
		return false;
	}
        /**
         * Set matrix representation of container
         * @param newValues matrix
         */
	public void setContainerMatrix(String[][][] newValues){
		containerMatrix = newValues;
	}
        /**
        * When timer has finished or a solution has been found we copy the best solution we have from another instance of ContainerModel
        * @param model instance of ContainerModel that stores best solution so far
        */
	public void cloneFinish(PentoContainer model){
		String[][][] newContainerMatrix = new String[containerX][containerY][containerZ];
		for(int i=0;i<containerMatrix.length;i++){
			for(int j=0;j<containerMatrix[0].length;j++){
				for(int k=0;k<containerMatrix[0][0].length;k++){
					newContainerMatrix[i][j][k] = model.getContainerMatrix()[i][j][k];
				}
			}
		}
		setContainerMatrix(newContainerMatrix);

		ArrayList<Shape> newContainedParcels = new ArrayList<>();
		 for(Shape aParcel:model.getContainedParcels()){
			PentominoShape parcel = (PentominoShape) aParcel;
			PentominoShape someParcel = parcel.cloneWithCoords();
			 newContainedParcels.add(someParcel);
		}
		setContainedParcels(newContainedParcels);
	}
        /**
         * @return current matrix representation of container
         */
	public String[][][] getContainerMatrix(){
		return  containerMatrix;
	}
        
        /**
         * Saves a container packing that is the best (most valuable) solution so far.
         * @param model
         */
	public void clone(PentoContainer model){
		String[][][] newContainerMatrix = new String[containerX][containerY][containerZ];
		for(int i=0;i<containerMatrix.length;i++){
			for(int j=0;j<containerMatrix[0].length;j++){
				for(int k=0;k<containerMatrix[0][0].length;k++){
					newContainerMatrix[i][j][k] = containerMatrix[i][j][k];
				}
			}
		}
		model.setContainerMatrix(newContainerMatrix);

		ArrayList<Shape> newContainedParcels = new ArrayList<>();
		for(Shape aParcel: containedParcels){
			PentominoShape parcel = (PentominoShape) aParcel;
			PentominoShape someParcel = parcel.cloneWithCoords();
			newContainedParcels.add(someParcel);
			System.out.println(someParcel.toString());
		}
		model.setContainedParcels(newContainedParcels);
	}

	/**
	 * Compute total value of a container.
	 * @return
	 */
	public int computeTotalValue(){
		int totalValue=0;
		for(Shape parcel:containedParcels){
			totalValue+=parcel.getValue();
		}
		return totalValue;
	}
	/**
	 * Iterates through all the monimoes of the pentomino and checks if all of them can be placed in the container matrix
	 */
	public boolean doesFit(PentominoShape p, Coordinates c) {
		for(Monimo m : p.getChildren()) {
			int xfit = c.x+m.getPositionShape().x;
			int yfit = c.y+m.getPositionShape().y;
			int zfit = c.z+m.getPositionShape().z;	if(xfit > containerX - 1 || yfit > containerY- 1|| zfit > containerZ - 1
					 || xfit < 0  || yfit < 0  || zfit < 0  ||
					containerMatrix[xfit][yfit][zfit]!=null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets amount of parcels that can be used in packing the container.
	 * @param nrOfL
	 * @param nrOfP
	 * @param nrOfT
	 */
	public void setAmountOfParcels(int nrOfL, int nrOfP, int nrOfT) {
		AmountTypeA = nrOfL;
		AmountTypeB = nrOfP;
		AmountTypeC = nrOfT;
		totalGivenParcels = nrOfL + nrOfP + nrOfT;
		/**
		 * Always run this method after calling setParcelList().This method needs a parcelList containing parcels to run correctly,
		 * however before running setParcelList() the parcelList doesn't contain any value.
		 *
		 */
		for(int parcelTypeIndex=0;parcelTypeIndex<parcelList.size();parcelTypeIndex++){
			if(parcelList.get(parcelTypeIndex) instanceof PentominoL){
				remainingParcelsEachType[parcelTypeIndex] = nrOfL;
			}
			if(parcelList.get(parcelTypeIndex) instanceof PentominoP){
				remainingParcelsEachType[parcelTypeIndex] = nrOfP;
			}
			if(parcelList.get(parcelTypeIndex) instanceof PentominoT){
				remainingParcelsEachType[parcelTypeIndex] = nrOfT;
			}
		}

	}
	/**
	 * First the position variable of the instance is set, then the coordinates in the container matrix are set to true
	 */
	public void place(Shape pe, Coordinates c) {
		PentominoShape p = (PentominoShape)pe;

		p.setContainerPosition(c);

		for(Monimo m : p.getChildren()) {
			containerMatrix[m.getContainerPosition().x][m.getContainerPosition().y][m.getContainerPosition().z] = p.getName();
		}

	}

	/**
	 * removes the last pentomino from the list and set's its coordinates in the container matrix to false in reverse order
	 */
	public void removeLast(Shape p) {
		for(Monimo m : (((PentominoShape)(p)).getChildren())) {
			containerMatrix[m.getContainerPosition().x][m.getContainerPosition().y][m.getContainerPosition().z] = null;
		}
        }        


	/**
	 * Checks if the container is fully packed.
	 * @return
	 */
	public boolean checkIfFull() {
		boolean full = true;
		for (int z = 0; z < containerZ; z++) {
			for (int y = 0; y < containerY; y++) {
				for (int x = 0; x < containerX; x++) {
					if (containerMatrix[z][y][x] == null) {
						full = false;
						return  full;
					}
                                }
                        }
                }       
			
		return full;
        }        
	
}
