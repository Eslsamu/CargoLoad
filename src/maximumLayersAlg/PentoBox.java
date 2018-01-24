package maximumLayersAlg;

import Model.PentoContainer;

/*
 * not to be confused with sushi
 */
class PentoBox extends PentoContainer{
	 public PentoBox(int w, int h) {
		 this.containerX = w;
		 this.containerY = h;
		 this.containerZ = 1;
		 containerMatrix = new String[w][h][1];
	 }		 

}
