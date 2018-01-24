package maximumLayersAlg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import Model.PentoContainer;
import Shapes.PentominoShape;
import Util.Coordinates;

public class TestField extends PentoContainer{
	
	@SuppressWarnings("serial")
	private ArrayList<Coordinates> containerCorners = new ArrayList<Coordinates>() {{
		add(new Coordinates(0,0,0));
		add(new Coordinates(0,0,containerHeight));
		add(new Coordinates(0,containerWidth,0));
		add(new Coordinates(0,containerWidth,containerHeight));
		add(new Coordinates(containerLength,0,0));
		add(new Coordinates(containerLength,0,containerHeight));
		add(new Coordinates(containerLength,containerWidth,0));
		add(new Coordinates(containerLength,containerWidth,containerHeight));
	}};
	
	
	 public static void main(String[] args) {
		 PentoBox testBox1 = new PentoBox(5,2);
		 testBox1.loadContainer();
	 }
	 
	 public boolean loadMaxLayerAlg() {
		 if(loadedPentominoes.size() >= (containerLength*containerWidth*containerHeight/5)) {
				printContainer();
				return true;
		}
		
		return false;
	 }
	 
	 /*
	  * returns the layer of a list which is the closest to a corner/edge/wall
	  */
	 public EML getBestEmptyLayer() {
		 EML best = new EML();
		 for(EML layer: getMaxLayers()) {
			 //!global variable
			 findClosestCorner(layer);
			 if(layer.closestDistance[0]==0&&layer.closestDistance[1]==0&&layer.closestDistance[2]==0) {
				 return layer;
			 }
			 else if(isCloser(layer.closestDistance,best.closestDistance)){
				 best = layer;
			 }
		 } 
		 return best;
	 }
	 
	 public void findClosestCorner(EML layer) {	 
		 boolean stop = false;
		 for(Coordinates contCorner : containerCorners) {
			 if(stop) break;
			 for(Coordinates layCorner : layer.corners) {
				 int[] distance = getDistance(layCorner,contCorner);
				 if(isCloser(distance,layer.closestDistance)){
					 layer.closestDistance = distance;
					 layer.closestContainerCorner = contCorner;
					 layer.closestLayerCorner = layCorner;
				 }
				 if(distance[0]==0&&distance[1]==0&&distance[2]==0) {
					 stop = true;
					 break;
				 }
			 }
		 }
	 }
	 
	 /*
	  * returns if a distance is closer in lexicographical order than another distance
	  */
	 public boolean isCloser(int[] distance1, int[] distance2) {
		 for(int i = 0; i < distance1.length; i++) {
			 if(distance1[i]<distance2[i]) {
				 return true;
			 }
			 else if(distance1[i]>distance2[i]) {
				 return false;
			 }
		 }
		 return false;
	 }
	 
	 /*
	  * returns an array which represents the distance between two points in ascending order
	  */
	 public int[] getDistance(Coordinates vertexFrom, Coordinates vertexTo) {
		 int[] distance = new int[]{Math.abs(vertexFrom.x-vertexTo.x),Math.abs(vertexFrom.y-vertexTo.y),Math.abs(vertexFrom.z-vertexTo.z)};
		 Arrays.sort(distance);
		 return distance;
	 }
	 
	 public ArrayList<EML> getMaxLayers(){
	        ArrayList<EML> allMaxLayers = new ArrayList<>();
	        
	        //YZ	
	        for(int x = 0; x < containerMatrix.length; x++){
	            allMaxLayers.addAll(findEML(containerMatrix[x],x));

	        }
	        
	        //XY
	        for(int z = 0; z < containerMatrix[0][0].length; z++) {
		        //get 2D matrix for the zAxis
		        String[][] matrix = new String[containerMatrix[0].length][containerMatrix.length];
		        for(int y = 0; y < matrix.length; y++) {
		        	for(int x = 0; x < matrix[0].length; x++) {
		        		matrix[y][x] = containerMatrix[x][y][z]; 
		        	}
		        }
		        ArrayList<EML> foundLayers = findEML(matrix,z);
		        for(EML l : foundLayers) {
		        	l.switchToY();
		        }
		        allMaxLayers.addAll(foundLayers);
	        }
	        
	        //XZ
	        for(int y = 0; y < containerMatrix[0].length; y++) {
		        //get 2D matrix for the zAxis
		        String[][] matrix = new String[containerMatrix[0].length][containerMatrix.length];
		        for(int z = 0; z < matrix.length; z++) {
		        	for(int x = 0; x < matrix[0].length; x++) {
		        		matrix[y][x] = containerMatrix[x][y][z]; 
		        	}
		        }
		        ArrayList<EML> foundLayers = findEML(matrix,y);
		        for(EML l : foundLayers) {
		        	l.switchToZ();
		        }
		        allMaxLayers.addAll(foundLayers);
	        }
	        
	        return allMaxLayers;
	    }
	 
	 //TODO save row position of rectangle
	 public ArrayList<EML> findEML(String[][] matrix, int xPos){
		 ArrayList<EML> foundRectangles = new ArrayList<EML>();
		 ArrayList<EML> rectanglesRow = new ArrayList<EML>();
		 
		 int[] heights = new int[matrix[0].length];	 
		 
		 for(int rows = 0; rows < matrix.length;rows++) {			
			 //for each column increase the heights of the histogram if it the matrix index is empty
			 for(int col = 0; col < heights.length; col++) {
				 if(matrix[rows][col]==null) { 
					 heights[col]++;
				 }
				 else {
					 heights[col]=0;
					 for(EML rect: rectanglesRow) {
						 if(rectangleFinished(rect,col)) foundRectangles.add(rect); 
					 }
				 }
			 }
				 
			 rectanglesRow.clear();
			 rectanglesRow.addAll(largestRectangles(heights,xPos,rows));
			 
			 if((rows+1)==matrix.length) {				 
				for(EML rect: rectanglesRow) {
					foundRectangles.add(rect); 
				}		 
			 } 
		 }
		 return foundRectangles;
	 }
	 
	 //time: O(n), space:O(n) n =indexes
	 //TODO potentialRect
	 public ArrayList<EML> largestRectangles(int[] height,int xPos, int zPos) {
		 	ArrayList<EML> potentialRect = new ArrayList<EML>();
		 	
			if (height == null || height.length == 0) {
				return null;
			}
		 
			Stack<Integer> hStack = new Stack<Integer>();
			Stack<Integer> pStack = new Stack<Integer>();
			
			int pos = 0;
			
			while (pos < height.length) {
				
				//if the height is bigger than the top of the stack, then add the position and an increasing height
				//to both stacks until the peek of hStack is equal to the height of the current position
				if (hStack.empty()) {
					for(int i = 0; i <= height[pos]; i++) {
					hStack.push(i);
					pStack.push(pos);
					}
				}
				else if(hStack.peek()==height[pos]) pos++;
				else if(hStack.peek()>height[pos]) {
					int p = pStack.pop();
					int h = hStack.pop();
					int w = pos-p;
					EML lastRect = potentialRect.size()==0 ? null : potentialRect.get(potentialRect.size()-1);
					if((lastRect==null)||!(lastRect.startY==p&&lastRect.vectorY==w&&lastRect.vectorZ>h)&&h!=0) {
						potentialRect.add(new EML(xPos,p,zPos, w, h));
					}
				}
				//if height(pos) is bigger than hStack
				else{
					for(int i = hStack.peek()+1; i <= height[pos];i++) {
						pStack.push(pos);
						hStack.push(i);
					}
				}
				//System.out.println(pStack.peek()+" "+hStack.peek()+" "+pos+" "+height[pos]);
			}
			
			while (!hStack.isEmpty()) {
				int p = pStack.isEmpty() ? 0 : pStack.pop();
				int h = hStack.pop();
				int w = height.length-p;
				EML lastRect = potentialRect.size()==0 ? null : potentialRect.get(potentialRect.size()-1);
				if((lastRect==null)||!(lastRect.startY==p&&lastRect.vectorY==w&&lastRect.vectorZ>h)&&h!=0) {
					potentialRect.add(new EML(xPos,p,zPos, w, h));
				}
			}
			return potentialRect;
		}
	 /*
	  *
	  */
	 public boolean rectangleFinished(EML rect, int pos) {
		return ((rect.startY+rect.vectorY - pos)>0) ? true : false;
	 }
	 
}
