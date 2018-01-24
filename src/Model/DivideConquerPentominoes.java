package Model;

import Shapes.Monimo;
import Shapes.PentominoL;
import Shapes.PentominoP;
import Shapes.PentominoShape;
import Shapes.PentominoT;
import Util.Coordinates;
import java.util.ArrayList;

/**
 * This class will try to create layers out of the container and fill them with pentominoes.
 * @author Jordan
 * @version 2.1
 * @date 23.01.2018
 */
public class DivideConquerPentominoes extends SuperContainer {
    private String[][][] CONTAINER = new String[33][8][5];
    private String[][] t = {{"T", "T", "T"},
                          {null, "T", null},
                          {null, "T", null}};
    private String[][] l = {{"L", "L", "L", "L"},
                            {null, null, null, "L"}};
    private String[][] p = {{"P", "P", "P"},
                            {"P", "P", null}};
    private ArrayList<String[][]> rotatedPentominoes = new ArrayList<>();
    private int value = 0;
    private int bestValue = 0;
    private int ContainerValue = 0;
    private String[][] layer;
    private String[][] bestLayer;
    private ArrayList<PentominoShape> loadedPentominoes = new ArrayList<>();
    private ArrayList<PentominoShape> bestLoadedPentominoes = new ArrayList<>();
    private ArrayList<PentominoShape> ContainerLoadedPentominoes = new ArrayList<>();
    private int counter = 0;
    
    /**
     * The constructor will generate all possible rotations and will then find a solution.
     */
    public DivideConquerPentominoes(){
        ArrayList<String[][]> givenPentominoes = new ArrayList<>();
        givenPentominoes.add(t);
        givenPentominoes.add(p);
        givenPentominoes.add(l);
        for(String[][] pento: givenPentominoes){
            rotatedPentominoes.add(rotateLeft(pento));
            rotatedPentominoes.add(rotateUpsideDown(pento));
            rotatedPentominoes.add(rotateUpsideDown(rotateLeft(pento)));
            rotatedPentominoes.add(pento);
            rotatedPentominoes.add(mirror(rotateLeft(pento)));
            rotatedPentominoes.add(mirror(rotateUpsideDown(pento)));
            rotatedPentominoes.add(mirror(rotateUpsideDown(rotateLeft(pento))));
            rotatedPentominoes.add(mirror(pento));
        }
        int[] layerDimensions = checkMatrix(CONTAINER);
        int[] dimensions = generateLayers(layerDimensions);
        findLayers(dimensions[0], dimensions[1]);
        ContainerValue+=bestValue*dimensions[2];
        System.out.println(ContainerValue);
        spreadLayer(dimensions[2], dimensions[3]);
        System.out.println(ContainerLoadedPentominoes.size());
        printContainedShapes(ContainerLoadedPentominoes);
        
    }
    /**
     * Gets the value of the container
     * @return value of Container
     */
    public int getContainerValue(){
        return ContainerValue;
    }
    /**
     * Get the pentominoes used for the solution.
     * @return ArrayList with pentominoes that are used for a solution
     */
    public ArrayList<PentominoShape> getContainedPentominoes(){
        return ContainerLoadedPentominoes;
    }
    /**
     * This method is used when a layer is found to multiply it several times in the container
     * @param multiply the number of times it can be multiplied in the container
     * @param dimension the coordinate(width, length, depth) in which it can be multiplied
     */
    public void spreadLayer(int multiply, int dimension){
        if(dimension == 0){
            for(int z = 0; z < multiply; z++){
                for(int y = 0; y < bestLayer.length; y++){
                    for(int x = 0; x < bestLayer[0].length; x++){
                        CONTAINER[z][y][x] = bestLayer[y][x];
                    }
                }
            }
            for(int z = 0; z < multiply; z++){
                for(PentominoShape p: bestLoadedPentominoes){
                    PentominoShape shape = p.clone();
                    shape.getChildren().clear();
                    for(int i = 0; i < p.getChildren().size(); i++){
                         shape.getChildren().add(new Monimo(new Coordinates(p.getChildren().get(i).getPositionShape().x, p.getChildren().get(i).getPositionShape().y, z), p.getMaterial()));
                    }
                    ContainerLoadedPentominoes.add(shape);
                }
            }
        }
        else if(dimension == 1){
            for(int z = 0; z < bestLayer.length; z++){
                for(int y = 0; y < multiply; y++){
                    for(int x = 0; x < bestLayer[0].length; x++){
                        CONTAINER[z][y][x] = bestLayer[z][x];
                    }
                }
            }
            for(int y = 0; y < multiply; y++){
                for(PentominoShape p: bestLoadedPentominoes){
                    PentominoShape shape = p.clone();
                    shape.getChildren().clear();
                    for(int i = 0; i < p.getChildren().size(); i++){
                        shape.getChildren().add(new Monimo(new Coordinates(p.getChildren().get(i).getPositionShape().x, y, p.getChildren().get(i).getPositionShape().y), p.getMaterial())); 
                    }
                    ContainerLoadedPentominoes.add(shape);
                }
            }
        }
        else if(dimension == 2){
            for(int z = 0; z < bestLayer.length; z++){
                for(int y = 0; y < bestLayer[0].length; y++){
                    for(int x = 0; x < multiply; x++){
                        CONTAINER[z][y][x] = bestLayer[z][y];
                    }
                }
            }
            for(int x = 0; x < multiply; x++){
                for(PentominoShape p: bestLoadedPentominoes){
                    PentominoShape shape = p.clone();
                    shape.getChildren().clear();
                    for(int i = 0; i < p.getChildren().size(); i++){
                        shape.getChildren().add(new Monimo(new Coordinates(x, p.getChildren().get(i).getPositionShape().x, p.getChildren().get(i).getPositionShape().y), p.getMaterial()));
                    }
                    ContainerLoadedPentominoes.add(shape);
                }
            }
        }
    }
    /**
     * This method tries to find the biggest empty cuboid in the container
     * @param container a String representation of the container
     * @return an array with dimensions of the cuboid
     */
    public int[] checkMatrix(String[][][] container){
        int z = 0;
        int y = 0;
        int x = 0;
        while(container[z][y][x] == null && z < container.length - 1){
           while(container[z][y][x] == null && y < container[0].length - 1){
               while(container[z][y][x] == null && x < container[0][0].length - 1){
                   x++;
               }
               y++;
           }
           z++;
        }
        return new int[]{z + 1, y + 1, x + 1};
    }
    /**
     * Finds the best possible layer to be filled in the cuboid
     * @param containerDimensions the dimensions of the cuboid
     * @return array with data, first two are dimensions of the
     * layer that should be filled, third is the times the layer can 
     * be spread and forth is the orientation of multiplaying it
     */
    public int[] generateLayers(int[] containerDimensions){
        int[] dimensions = new int[4];
        for(int i = 0; i < containerDimensions.length; i++){
            for(int p = 0; p < containerDimensions.length; p++){
                if(p > i){
                    if((containerDimensions[i]*containerDimensions[p])%5 == 0 && (dimensions[0] == 0 || dimensions[0]*dimensions[1] > containerDimensions[i]*containerDimensions[p])){
                        dimensions = new int[]{containerDimensions[i], containerDimensions[p], containerDimensions[3 - i - p], (3 - i - p)};
                    }
                }
            }
        }
        if(dimensions[0] == 0){
            for(int i = 0; i < containerDimensions.length; i++){
                for(int p = 0; p < containerDimensions.length; p++){
                    if(p > i){
                        while((containerDimensions[i]*containerDimensions[p])%5 != 0){
                            containerDimensions[i]--;
                        }
                        if((containerDimensions[i]*containerDimensions[p])%5 == 0 && (dimensions[0] == 0 || dimensions[0]*dimensions[1] > containerDimensions[i]*containerDimensions[p])){
                            dimensions = new int[]{containerDimensions[i], containerDimensions[p], containerDimensions[3 - i - p], (3 - i - p)};
                        }
                    }
                }
            }    
        }    
        return dimensions;
    }
    /**
     * Find a solution for a layer
     * @param y y dimension of the layer
     * @param x x dimension of the layer
     */
    public void findLayers(int y, int x){
       layer = new String[y][x];
        if(find()){
            System.out.println("new Layer");
        }
        else{
            System.out.println("not found");
        }
        printLayer(bestLayer);
        System.out.println(bestValue);
        System.out.println(bestLoadedPentominoes.size());
    }
    /**
     * The actual backtracking method used to find a solution of a layer.
     * @return boolean when it finds/doesnt find solution
     */
    public boolean find(){
        if(loadedPentominoes.size() >= (layer.length*layer[0].length)/5) {
            counter++;
            if(value >= bestValue){
                bestLoadedPentominoes.clear();
                for(PentominoShape p: loadedPentominoes){
                   PentominoShape sth = p.clone();
                   sth.getChildren().clear();
                   for(int i = 0; i < p.getChildren().size(); i++){
                       sth.getChildren().add(new Monimo(new Coordinates(p.getChildren().get(i).getPositionShape().x, p.getChildren().get(i).getPositionShape().y, p.getChildren().get(i).getPositionShape().z), p.getMaterial()));
                   }
                   bestLoadedPentominoes.add(sth);
                }
                bestValue = value;
                bestLayer = layer.clone();
            }
            if(counter >= 1){
                return true;
            }
            return false;
	}
        for(int y = 0; y < layer.length; y++){
            for(int x = 0; x < layer[0].length; x++){
                if(layer[y][x] == null){
                    for(String[][] pentominoe: rotatedPentominoes){
                        int xx = x;
                        while(pentominoe[0][xx-x] == null && x > 0){
                            x--;}
                        if(doesFit(pentominoe, y, x)){
                            place(pentominoe, y, x);
                            if(name(pentominoe) == "T"){
                                value+=5;
                                loadedPentominoes.add(load(pentominoe, new PentominoT(), y, x));
                            }
                            else if(name(pentominoe) == "P"){
                                value+=4;
                                loadedPentominoes.add(load(pentominoe, new PentominoP(), y, x));
                            }
                            else if(name(pentominoe) == "L"){
                                value+=3;
                                loadedPentominoes.add(load(pentominoe, new PentominoL(), y, x));
                            }
                            if(find()) {
                                return true;
                            }
                            else {
                                removeLast(pentominoe, y, x);
                                value -= loadedPentominoes.get(loadedPentominoes.size() - 1).getValue();
                                loadedPentominoes.remove(loadedPentominoes.size()- 1);
                            }													
                        }
                        x = xx;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Convert a String pentominoe to a PentominoShape, to be added in an arrayList with pentominoes needed for the solution
     * @param pentominoe a String representation of a pentominoes
     * @param shape a PentominoShape pentomino  
     * @param yy the starting height position
     * @param xx the starting width position
     * @return a converted PentominoShape
     */
    public PentominoShape load(String[][] pentominoe, PentominoShape shape, int yy, int xx){
        int[] coordinates = new int[10];
        int count = 0;
        for(int y = 0; y < pentominoe.length; y++){
            for(int x = 0; x < pentominoe[0].length; x++){
                if(pentominoe[y][x] != null){
                    coordinates[0 + count] = y + yy;
                    coordinates[1 + count] = x + xx;
                    count+=2;
                }
            }
        }
        shape.getChildren().clear();
        for(int i = 0; i < coordinates.length; i+=2){
            //System.out.println(coordinates[i + 1] + " " + coordinates[i]);
            shape.getChildren().add(new Monimo(new Coordinates(coordinates[i + 1],coordinates[i],0), shape.getMaterial()));
            //shape.getChildren().get(i/2).setContainerPosition(new Coordinates(coordinates[i + 1],coordinates[i],0));
            //System.out.println(shape.getChildren().get(i/2).getPositionShape().x + " " + shape.getChildren().get(i/2).getPositionShape().y + " " + shape.getChildren().get(i/2).getPositionShape().z);
        }
        return shape;
    }
    /**
     * Used to rotate the String pentominoe
     * @param pentominoe take a String as a parameter and rotate it left
     * @return a String with left rotated pentominoe
     */
    public String[][] rotateLeft(String[][] pentominoe){
        String[][] rotated = new String[pentominoe[0].length][pentominoe.length];
        for(int x = 0; x < pentominoe[0].length; x++){
            for(int y = 0; y < pentominoe.length; y++){
                rotated[x][y] = pentominoe[y][x];
            }
        }
        return rotated;
    }
    /**
     * Used to rotate the String pentominoe
     * @param pentominoe take a String as a parameter and rotate it upside down
     * @return a String with upside down rotated pentominoe
     */
    public String[][] rotateUpsideDown(String[][] pentominoe){
        String[][] rotated = new String[pentominoe.length][pentominoe[0].length];
        for(int y = pentominoe.length - 1; y > -1; y--){
            for(int x = 0; x < pentominoe[0].length; x++){
                rotated[(pentominoe.length - 1 - y)][x] = pentominoe[y][x];
            }
        }
        return rotated;
    }
    /**
     * Used to rotate the String pentominoe
     * @param pentominoe take a String as a parameter and mirror it
     * @return a String with mirrored pentominoe
     */
    public String[][] mirror(String[][] pentominoe){
        String[][] rotated = new String[pentominoe.length][pentominoe[0].length];
        for(int y = 0; y < pentominoe.length; y++){
            for(int x = pentominoe[0].length - 1; x > - 1; x--){
                rotated[y][pentominoe[0].length - 1 - x] = pentominoe[y][x];
            }
        }
        return rotated;
    }
    /**
     * Finds the name of the String pentominoe
     * @param pentominoe String pentominoe
     * @return A String with the name of the pentominoe
     */
    public String name(String[][] pentominoe){
        int x = 0;
        while(pentominoe[0][x] == null){
            x++;
        }
        return pentominoe[0][x];
    }
    /**
     * Checks if a pentominoe will fit at a certain position in a layer.
     * @param pentominoe the String representation of the pentominoe
     * @param yy the height coordinate
     * @param xx the width coordinate
     * @return boolean true/false depending if it fits
     */
    public boolean doesFit(String[][] pentominoe, int yy, int xx) {
        for(int y = 0; y < pentominoe.length; y++){
            for(int x = 0; x < pentominoe[0].length; x++){
                if(yy + pentominoe.length > layer.length || xx + pentominoe[0].length > layer[0].length){
                    return false;
                }
                else if(pentominoe[y][x] != null && layer[y + yy][x + xx] != null){
                    return false;
                }
            }
        }
	return true;
    }
    /**
     * If a pentominoe fits it will be placed at these positions.
     * @param pentominoe String representation of pentominoe
     * @param yy the height coordinate
     * @param xx the width coordinate
     */
    public void place(String[][] pentominoe, int yy, int xx) {
        for(int y = 0; y < pentominoe.length; y++){
            for(int x = 0; x < pentominoe[0].length; x++){
                if(pentominoe[y][x] != null){
                layer[y + yy][x + xx] = pentominoe[y][x]; 
                }
            }
        }    
    }

    /**
     * If a pentominoe is to be removed this method is called.
     * @param pentominoe the pentominoe that should be removed
     * @param yy the height coordinate of the pentominoe
     * @param xx the width coordinate of the pentominoe
     */
    public void removeLast(String[][] pentominoe, int yy, int xx) {
        for(int y = 0; y < pentominoe.length; y++){
            for(int x = 0; x < pentominoe[0].length; x++){
                if(pentominoe[y][x] != null){
                layer[y + yy][x + xx] = null; 
                }
            }
        } 
    }
    /**
     * Prints a String array
     * @param layer a String
     */
    public void printLayer(String[][] layer){
        for(int i = 0; i < layer.length; i++){
            for(int x = 0; x < layer[0].length; x++){
                System.out.print(layer[i][x] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Prints first five coordinates of pentominoes added to a list.
     * @param loadedPentominoes arrayList with pentominoes to be printed
     */
    public void printContainedShapes(ArrayList<PentominoShape> loadedPentominoes){
        for(int  i = 0; i < 5; i++){
            System.out.println("Parcel: " + i);
            PentominoShape parcel = loadedPentominoes.get(i);
            for(Monimo m: parcel.getChildren()){
                int z = m.getPositionShape().getZ();
                int y = m.getPositionShape().getY();
                int x = m.getPositionShape().getX();
                System.out.println("X: " + x + " Y: " + y + " Z: " + z);  
            }
        }
    }
}
