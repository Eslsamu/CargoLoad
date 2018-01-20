package Test;

import java.util.ArrayList;

public class TestField {
	 boolean[][] container = new boolean[6][6];
	 ArrayList<SubSpace> maxSpaces = new ArrayList<SubSpace>();
	 
	 public static void main(String[] args) {
		 TestField testF = new TestField();
		 testF.container[4][4]=true;
		 testF.container[3][5]=true;

		 
		 testF.find(0, 0);
		 testF.printList();
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
	 
	 public void findMaxSpaces(int startX,int startY) {
		 int vX = container.length;
		 int vY = container[0].length;
		 
		 //into y direction 
		 
		 
		 for(int x = startX; x < vX; x++) {
			 if(x == vX-1 && vY > 0) maxSpaces.add(new SubSpace(x+1, vY, startX,startY));
			 for(int y = startY; y < vY; y++) {
				 if(container[x][y]) {
					 if(x > 0) {
						 maxSpaces.add(new SubSpace(x,vY,startX,startY));
					 }
					 if(y <= 0) break;
					 vY = y;
				 }
				 
			 }
		 }
	 }
	 
	 public void find(int startX, int startY) {
		 int xMax = container.length;
		 int yMax = container[0].length;
		 int pivotY = yMax; 

		 maxSpaces.add(new SubSpace(xMax,yMax,startX, startY));
		 for(int x = 0; x < xMax; x++) {
			 for(int y = 0; y < pivotY; y++) {
				 if(container[x][y]) {
					if(x==0) {
						maxSpaces.remove(maxSpaces.size()-1);
						}
					else{
					maxSpaces.get(maxSpaces.size()-1).vectorX = x;
					maxSpaces.get(maxSpaces.size()-1).vectorY = pivotY;
					}
					
					pivotY = y;
					
					if(y>0) maxSpaces.add(new SubSpace(xMax, y, startX, startY));
					break;
				 }
			 }
		 }
	 }

	 
	 public void printList() {
		 for(SubSpace s : maxSpaces) {
			 System.out.println(s.toString());
		 }
	 }
}