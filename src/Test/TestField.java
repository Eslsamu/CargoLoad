

package Test;

import java.util.ArrayList;
import java.util.Stack;

public class TestField {
	 boolean[][] layer = new boolean[6][6];
	 boolean[][][] container = new boolean[3][3][3];
	 
	 public static void main(String[] args) {
		 TestField testF = new TestField();
		 testF.layer[0][0]=true;
		 
		 testF.test();
	 }
	 
	 public void test() {
		 XZ[] hist = new XZ[]{new XZ(2,1),new XZ(true), new XZ(3,2), new XZ(true), new XZ(2,3), new XZ(1,3), new XZ(2,2)};
		 XZ[] histPre = new XZ[]{new XZ(true), new XZ(true), new XZ(3,2), new XZ(2,3), new XZ(1,1),new XZ(1,3), new XZ(2,3)};
		 
		 for(EMS s : maxInHistogram(hist, histPre, 4, 2)) {
			 System.out.println(s.toString());
		 }
	 }
	 
	 /*
	  * an z vector for the height and an x vector for the depth used in the histogram
	  */
	 class XZ{
		 int xDep;
		 int zHei;
		 boolean isBlocked;
		 public XZ(int z, int x) {
			 this.xDep = x;
			 this.zHei = z;
			 this.isBlocked = false;
		 }	
		 public XZ(boolean isB) {
			 this.xDep = 0;
			 this.zHei = 0;
			 this.isBlocked = isB;
		 }	
	 }
	 
	 class EMS {
		 int vectorX;
		 int vectorY;
		 int vectorZ;
		 int startX;
		 int startY;
		 int startZ;
		 
		 public EMS(int xS, int yS, int zS, int xV, int yV, int zV) {
			 vectorX = xV;
			 vectorY = yV;
			 vectorZ = zV;
			 startX = xS;
			 startY = yS;
			 startZ = zS;
		 }
		 
		 public String toString() {
			 return new String("vectorX:" +vectorX+" vectorY:"+vectorY+" vectorZ: "+vectorZ+" startX:"+startX+" startY:"+startY+" startZ"+startZ);
		 }
	 }
	 
	 public ArrayList<EMS> findEMS(){
		 ArrayList<EMS> closedSpaces = new ArrayList<EMS>();
		 
		 ArrayList<EMS> openedSpaces = new ArrayList<EMS>();	 
		 for(int layer = 0; layer < container.length; layer++) {
			 
		 }
		 
		 return null;
	 }

	 //time: O(n), space:O(n) n =indexes
	 //TODO potentialRect
	 public ArrayList<EMS> maxInHistogram(XZ[] heightDepth, XZ[] before, int xPos, int zPos) {
		 	ArrayList<EMS> potentialSpaces = new ArrayList<EMS>();
		 	 	
			if (heightDepth == null || heightDepth.length == 0) {
				return null;
			}
			//height
			Stack<Integer> hStack = new Stack<Integer>();
			//depth
			Stack<Integer> dStack = new Stack<Integer>();
			//position
			Stack<Integer> pStack = new Stack<Integer>();
			
			int pos = 0;		
			while (pos < heightDepth.length) {
				XZ hdPos = heightDepth[pos];
				XZ hdBefore = before[pos];
				
				System.out.println("Position: "+pos+" Height:"+hdPos.zHei+ " Depth:"+ hdPos.xDep);
				System.out.println("Stack:");
				for(int i = 0; i < hStack.size(); i++) {
					System.out.println(pStack.get(i)+" "+hStack.get(i)+" "+dStack.get(i));
				}
				
				if(hdPos.isBlocked) {
					while(!pStack.empty()&&!hStack.empty()&&!dStack.empty()) {
						int yPos = pStack.pop();
						int height = hStack.pop();
						int width = pos - yPos;
						int depth = dStack.pop();
						
						EMS lastAddedSpace = !potentialSpaces.isEmpty() ? potentialSpaces.get(potentialSpaces.size()-1) : null;
						if(lastAddedSpace==null||!(yPos==lastAddedSpace.startY && width <= lastAddedSpace.startY+lastAddedSpace.vectorY&&height==lastAddedSpace.vectorZ)) {
							potentialSpaces.add(new EMS(xPos, yPos, zPos, depth, width, height));
							System.out.println(potentialSpaces.get(potentialSpaces.size()-1).toString());
						}
					}
					pStack.push(pos);
					hStack.push(0);
					dStack.push(0);
					pos++;
				}
				/*else if(hdBefore.xDep != hdPos.xDep){
					//double push of layers of different depth in order of the smaller height first
					//TODO for loop
					pStack.push(pos);
					pStack.push(pos);
					pStack.push((hdPos.zHei>=hdBefore.zHei) ? hdPos.zHei : hdBefore.zHei);
					pStack.push((hdPos.zHei<=hdBefore.zHei) ? hdPos.zHei : hdBefore.zHei);
					dStack.push((hdPos.zHei>=hdBefore.zHei) ? hdPos.xDep : hdBefore.xDep);
					dStack.push((hdPos.zHei<=hdBefore.zHei) ? hdPos.xDep : hdBefore.xDep);
				}*/
				//if EMPTYALL
				else if(pStack.empty()&&dStack.empty()) {
					//TODO for loop
					pStack.push(pos);
					hStack.push(hdPos.zHei);
					dStack.push(hdPos.xDep);
				}
				//if EQUALALL
				else if(hStack.peek()==hdPos.zHei&&dStack.peek()==hdPos.xDep) pos++;
				//Y lower than peek
				else if(hdPos.zHei<hStack.peek()) {
					if(hdPos.xDep<dStack.peek()) {
						//is it gets less deep then cut the hole and add it. Then replace the stack peeks with a new depth
						int yPos = pStack.peek();
						int height = hStack.peek();
						int width = pos - yPos;
						int depth = dStack.pop();
						
						EMS lastAddedSpace = !potentialSpaces.isEmpty() ? potentialSpaces.get(potentialSpaces.size()-1) : null;
						if(lastAddedSpace==null||!(yPos==lastAddedSpace.startY && width <= lastAddedSpace.startY+lastAddedSpace.vectorY&&height==lastAddedSpace.vectorZ)) {
							potentialSpaces.add(new EMS(xPos, yPos, zPos, depth, width, height));
							System.out.println(potentialSpaces.get(potentialSpaces.size()-1).toString());
						}
							
						dStack.push(hdPos.xDep);
					}
					
					int yPos = pStack.pop();
					int height = hStack.pop();
					int width = pos - yPos;
					int depth = dStack.pop();
					
					EMS lastAddedSpace = !potentialSpaces.isEmpty() ? potentialSpaces.get(potentialSpaces.size()-1) : null;
					if(lastAddedSpace==null||!(yPos==lastAddedSpace.startY && width <= lastAddedSpace.startY+lastAddedSpace.vectorY&&height==lastAddedSpace.vectorZ)) {
						potentialSpaces.add(new EMS(xPos, yPos, zPos, depth, width, height));
						System.out.println(potentialSpaces.get(potentialSpaces.size()-1).toString());
					}
				}
				else if(hdPos.zHei>hStack.peek()) {
					for(int i = hStack.peek()+1; i <= hdPos.zHei;i++) {
						pStack.push(pos);
						hStack.push(i);
						dStack.push(hdPos.xDep);
					}
				}
				else if(hdPos.xDep>dStack.peek()&&hdPos.zHei==hStack.peek()) {
					for(int i = dStack.peek()+1; i <= hdPos.xDep; i++) {
						pStack.push(pos);
						hStack.push(hdPos.zHei);
						dStack.push(hdPos.xDep);
					}
				}	
		}
		while(!pStack.empty()&&!hStack.empty()&&!dStack.empty()) {
			int yPos = pStack.pop();
			int height = hStack.pop();
			int width = pos - yPos;
			int depth = dStack.pop();
			
			EMS lastAddedSpace = !potentialSpaces.isEmpty() ? potentialSpaces.get(potentialSpaces.size()-1) : null;
			if(lastAddedSpace==null||!(yPos==lastAddedSpace.startY && width <= lastAddedSpace.startY+lastAddedSpace.vectorY&&height==lastAddedSpace.vectorZ)) {
				potentialSpaces.add(new EMS(xPos, yPos, zPos, depth, width, height));
				System.out.println(potentialSpaces.get(potentialSpaces.size()-1).toString());
			}
		}			
		System.out.println("pSpaces size: "+potentialSpaces.size());
		return potentialSpaces;
 	}
}
