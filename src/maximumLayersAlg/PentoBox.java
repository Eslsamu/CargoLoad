package maximumLayersAlg;

import Model.PentoContainer;
import Shapes.PentominoShape;
import Util.Axis;
import Util.Coordinates;

/*
 * not to be confused with sushi
 */
class PentoBox extends PentoContainer{
	 public PentoBox(int w, int h) {
		 this.containerLength = w;
		 this.containerWidth = h;
		 this.containerHeight = 1;
		 containerMatrix = new String[w][h][1];
	 }		 

}
