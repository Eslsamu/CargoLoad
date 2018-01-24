package maximumLayersAlg;

import java.util.ArrayList;

import Util.Coordinates;

/*
 * empty maximal layer
 */
class EML {
	 int vectorX;
	 int vectorY;
	 int vectorZ;
	 int startX;
	 int startY;
	 int startZ;
	 Coordinates closestContainerCorner;
	 int[] closestDistance;
	 Coordinates closestLayerCorner;
	 ArrayList<Coordinates> corners = new ArrayList<Coordinates>();
	 
	 public EML(int xS, int yS, int zS, int yV, int zV) {
		 vectorY = yV;
		 vectorZ = zV;
		 startX = xS;
		 startY = yS;
		 startZ = zS;
		 generateCorners();
	 }
	 
	public EML() {
		// TODO Auto-generated constructor stub
	}
	
	private void generateCorners() {
		corners.add(new Coordinates(startX,startY,startZ));
		corners.add(new Coordinates(startX,startY,startZ+vectorZ));
		corners.add(new Coordinates(startX,startY+vectorY,startZ));
		corners.add(new Coordinates(startX,startY+vectorY,startZ+vectorZ));
		corners.add(new Coordinates(startX+vectorX,startY,startZ));
		corners.add(new Coordinates(startX+vectorX,startY,startZ+vectorZ));
		corners.add(new Coordinates(startX+vectorX,startY+vectorY,startZ));
		corners.add(new Coordinates(startX+vectorX,startY+vectorY,startZ+vectorZ));
	}
	
	public void switchToY() {
		 vectorX = vectorY;
		 vectorY = 0;
		 generateCorners();
	 }
	 
	 public void switchToZ() {
		 vectorX = vectorZ;
		 vectorZ = 0;
		 generateCorners();
	 }
	 
	 public String toString() {
		 return new String("vectorX: "+ vectorX+" vectorY:"+vectorY+" vectorZ: "+vectorZ+" startX:"+startX+" startY:"+startY+" startZ: "+startZ);
	 }
}