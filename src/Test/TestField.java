package Test;

import java.util.ArrayList;

public class TestField {
	 boolean[][] container = new boolean[10][10];
	 ArrayList<SubSpace> maxSpaces = new ArrayList<SubSpace>();
	 
	 public static void main(String[] args) {
		 TestField testF = new TestField();
		 testF.container[3][3]=true;
		 testF.container[4][2]=true;
		 testF.container[0][5]=true;
		 
		 testF.container[1][4]=true;
		 
		 testF.findMaxSpaces(0, 0);
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
					 if(y < 0) break;
					 vY = y;
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