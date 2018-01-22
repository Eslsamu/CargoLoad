package Test;

import java.util.ArrayList;
import java.util.Stack;

public class TestField {
	 boolean[][] container = new boolean[6][6];
	 ArrayList<SubSpace> maxSpaces = new ArrayList<SubSpace>();
	 
	 public static void main(String[] args) {
		 TestField testF = new TestField();
		 testF.container[1][1]=true;

		 for(int[] rect : testF.findRectangles2D(testF.container)) {
			 System.out.print("pos:"+rect[0]+" height"+rect[1]+" width"+rect[2]);
			 System.out.println();
		 }
		 
		
	 }
	 
	 class SubSpace {
		 int vectorX;
		 int vectorY;
		 int startX;
		 int startY;
		 
		 public SubSpace(int vx, int vy, int xS, int yS) {
			 vectorX = vx;
			 vectorY = vy;
			 startX = xS;
			 startY = yS;
		 }
		 
		 public String toString() {
			 return new String("vectorX:" +vectorX+" vectorY:"+vectorY+" startX:"+startX+" startY:"+startY);
		 }
	 }
	 
	 //TODO save row position of rectangle
	 public ArrayList<int[]> findRectangles2D(boolean[][] matrix){
		 ArrayList<int[]> foundRectangles = new ArrayList<int[]>();
		 int[] heights = new int[matrix[0].length];
		 
		 ArrayList<int[]> rectanglesRow = new ArrayList<int[]>();
		 for(int rows = 0; rows < matrix.length;rows++) {					 		 
			
			 //for each column increase the heights of the histogram if it the matrix index is empty
			 for(int col = 0; col < heights.length; col++) {
				 if(!matrix[rows][col])heights[col]++;
				 else {
					 heights[col]=0;
					 //if it is not empty, then check if 
					 for(int[] rect: rectanglesRow) {
						 if(rectangleFinished(heights,col)) foundRectangles.add(rect); 
					 }
				 }
			 }
				 
			 rectanglesRow.clear();
			 rectanglesRow.addAll(largestRectangles(heights));
			 
			 if((rows+1)==container.length) {				 
				for(int[] rect: rectanglesRow) {
					foundRectangles.add(rect); 
				}		 
			 } 
		 }
		 return foundRectangles;
	 }
	 /*
	  * if position is filled or if next block is the containerlength
	  */
	 public boolean rectangleFinished(int[] rect, int pos) {
		return ((rect[0]+rect[2] - pos)>0) ? true : false;
	 }
	 
	 //time: O(n), space:O(n) n =indexes
	 //TODO potentialRect
	 public ArrayList<int[]> largestRectangles(int[] height) {
		 	ArrayList<int[]> potentialRect = new ArrayList<int[]>();
		 	
		 	
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
					int[] lastRect = potentialRect.size()==0 ? null : potentialRect.get(potentialRect.size()-1);
					if((lastRect==null)||!(lastRect[0]==p&&lastRect[2]==w&&lastRect[1]>h)&&h!=0) {
						potentialRect.add(new int[]{p,h,w});
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
				int[] lastRect = potentialRect.size()==0 ? null : potentialRect.get(potentialRect.size()-1);
				if((lastRect==null)||!(lastRect[0]==p&&lastRect[2]==w&&lastRect[1]>h)&&h!=0) {
					potentialRect.add(new int[]{p,h,w});
				};
			}
		 
			return potentialRect;
		}
	 
	 public void printList() {
		 for(SubSpace s : maxSpaces) {
			 System.out.println(s.toString());
		 }
	 }
}