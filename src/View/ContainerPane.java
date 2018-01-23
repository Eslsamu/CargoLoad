package View;

import Shapes.Monimo;
import Shapes.ParcelShape;
import Shapes.PentominoShape;
import Util.Coordinates;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * This class creates the visual representation of the container and boxes or pentominoes
 * stored in it.
 * @author Jordan. Basia, Sam
 * @version 1.0
 */
public class ContainerPane extends Parent {
    //predifined sizes of the container and boxes
    private float CONTAINER_DEPTH = 16.5f;
    private float CONTAINER_WIDTH = 2.5f;
    private float CONTAINER_HEIGHT = 4.0f;
    //box type small
    private float Box_Depth = 0.5f;
    private float Box_Width = 0.5f;
    private float Box_Height = 0.5f;
    //set rotation points of camera
    private Rotate xAxis = new Rotate(350, Rotate.X_AXIS);
    private Rotate yAxis = new Rotate(340, Rotate.Y_AXIS);
    private Rotate zAxis = new Rotate(0, Rotate.Z_AXIS);
    
    private Group root;
    private TitlePane title;
    private MeshView container;
    private ArrayList<ShownContainers> shownContainers = new ArrayList<>();
    private ContainerView view;
    
    /**
     * Constructor creates a Scene with container and a camera is being set to it.
     */
    public ContainerPane(int Scene_Width, int Scene_Length, TitlePane title, ContainerView view){
        this.view = view;
        //an instance of TitlePane, to redraw container value, when it changes
        this.title = title;
        //add all containers, boxes and camera to a group
        root = new Group();
        root.setAutoSizeChildren(false);
        
    	//creating container
    	Box containerMesh = new Box(CONTAINER_WIDTH , CONTAINER_HEIGHT, CONTAINER_DEPTH);
        container = new MeshView(containerMesh);
        //making the front of the container transparent
        container.setCullFace(CullFace.NONE);
        //drawing the container with only lines
        container.setDrawMode(DrawMode.FILL);
        //setting the color of the container
        PhongMaterial material;
        try{
           material = new PhongMaterial();
           FileInputStream inputstream = new FileInputStream("container.jpeg");
           Image image = new Image(inputstream);
           material.setDiffuseMap(image);
        }
        catch(FileNotFoundException exception){
            material = new PhongMaterial(Color.ORANGE);
        };
        container.setMaterial(material);        
    	root.getChildren().add(container);
        
        //create a camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        //add possible rotations and position of camera
        camera.getTransforms().addAll(
                xAxis, yAxis, zAxis, new Translate(0, 0, -35));
        
        root.getChildren().add(camera);
        
        //create a Scene from the group
        SubScene subScene = new SubScene(root, Scene_Width, Scene_Length, true, SceneAntialiasing.BALANCED);
        //set a camera for the scene
        subScene.setCamera(camera);
        getChildren().add(subScene);
    }
    /**
     * This method will draw boxes with given containedShapes and ContainerModel
     * @param containedShapes an array list with all boxes in the container
     * @param solver an instance of the ContainerModel that found the solution
     */
    public void drawBoxes(ArrayList<ParcelShape> containedShapes, int totalValue, String name){
        ArrayList<ParcelShape> list = new ArrayList<>();
        for(ParcelShape parcel: containedShapes){
            ParcelShape someParcel = parcel.clone();
            someParcel.setCurrentCoordinates(parcel.getPosition().clone());
            list.add(someParcel);
        }
        ShownContainers shownContainer = new ShownContainers(list, totalValue, name);
        shownContainers.add(shownContainer);
        root.getChildren().remove(2, root.getChildren().size());
        container.setCullFace(CullFace.FRONT);
        container.setDrawMode(DrawMode.LINE);
        //setDisplayedValue
        title.setDisplayedValue(totalValue);
        //clean container if there is anything in it
        for(int i = 0; i < containedShapes.size(); i++){
            ParcelShape parcel = containedShapes.get(i).clone();
            parcel.setCurrentCoordinates(containedShapes.get(i).getPosition().clone());
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();
            //System.out.println(z + " " + y + " " + x);
            
            //create a box as big as it has small boxes in it
            Box boxMesh = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
            MeshView box = new MeshView(boxMesh);
            box.setDrawMode(DrawMode.FILL);
            box.setMaterial(parcel.getMaterial().toMaterial());
            box.setTranslateX(-CONTAINER_WIDTH/2 + boxMesh.getWidth()/2 + 0.5*x);
            box.setTranslateY(-CONTAINER_HEIGHT/2 + boxMesh.getHeight()/2 + 0.5*y);
            box.setTranslateZ(CONTAINER_DEPTH/2 - boxMesh.getDepth()/2 - 0.5*z);
            //if(i != containedShapes.size() - 4){
            root.getChildren().add(box);
            //}
        }
            
    }
     public void drawBoxes(ArrayList<ParcelShape> containedShapes, int totalValue){
        root.getChildren().remove(2, root.getChildren().size());
        container.setCullFace(CullFace.FRONT);
        container.setDrawMode(DrawMode.LINE);
        //setDisplayedValue
        title.setDisplayedValue(totalValue);
        //clean container if there is anything in it
        for(int i = 0; i < containedShapes.size(); i++){
            ParcelShape parcel = containedShapes.get(i).clone();
            parcel.setCurrentCoordinates(containedShapes.get(i).getPosition().clone());
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();
            //System.out.println(z + " " + y + " " + x);
            
            //create a box as big as it has small boxes in it
            Box boxMesh = new Box(Box_Width*parcel.getShapeVector().x, Box_Height*parcel.getShapeVector().y, Box_Depth*parcel.getShapeVector().z);
            MeshView box = new MeshView(boxMesh);
            box.setDrawMode(DrawMode.FILL);
            box.setMaterial(parcel.getMaterial().toMaterial());
            box.setTranslateX(-CONTAINER_WIDTH/2 + boxMesh.getWidth()/2 + 0.5*x);
            box.setTranslateY(-CONTAINER_HEIGHT/2 + boxMesh.getHeight()/2 + 0.5*y);
            box.setTranslateZ(CONTAINER_DEPTH/2 - boxMesh.getDepth()/2 - 0.5*z);
            //if(i != containedShapes.size() - 4){
            root.getChildren().add(box);
            //}
        }
            
    }
    /**
     * This method will draw pentominoes in the container after a solution has been found.
     * @param loadedPentominoes array list with loaded pentominoes
     */
    public void drawPentominoes(ArrayList<PentominoShape> loadedPentominoes, int value, String name, String param){
        ArrayList<PentominoShape> pentominoes = new ArrayList<>(loadedPentominoes);
        ShownContainers shownContainer = new ShownContainers(value, pentominoes, name);
        shownContainers.add(shownContainer);
        container.setCullFace(CullFace.FRONT);
        container.setDrawMode(DrawMode.LINE);
        title.setDisplayedValue(value);
        root.getChildren().remove(2, root.getChildren().size());
        for(int i = 0; i < loadedPentominoes.size(); i++){
            Color ranColor = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            ArrayList<Monimo> PentominoBoxCoordinates = loadedPentominoes.get(i).getChildren();
            
            for(int m = 0; m < PentominoBoxCoordinates.size(); m++){
                //Coordinates coordinates = PentominoBoxCoordinates.get(m).getContainerPosition();
                Monimo monimo = PentominoBoxCoordinates.get(m);
                Box boxMesh = new Box(Box_Width, Box_Height, Box_Depth);
                MeshView box = new MeshView(boxMesh);
                box.setDrawMode(DrawMode.FILL);
                box.setMaterial(new PhongMaterial(ranColor));
                if(param == "X"){
                box.setTranslateX(-CONTAINER_WIDTH/2 + boxMesh.getWidth()/2 + 0.5*monimo.getPositionShape().x);
                box.setTranslateY(-CONTAINER_HEIGHT/2 + boxMesh.getHeight()/2 + 0.5*monimo.getPositionShape().y);
                box.setTranslateZ(CONTAINER_DEPTH/2 - boxMesh.getDepth()/2 - 0.5*monimo.getPositionShape().z);
                }
                else if(param == "Y"){
                box.setTranslateX(-CONTAINER_WIDTH/2 + boxMesh.getWidth()/2 + 0.5*monimo.getPositionShape().y);
                box.setTranslateY(-CONTAINER_HEIGHT/2 + boxMesh.getHeight()/2 + 0.5*monimo.getPositionShape().x);
                box.setTranslateZ(CONTAINER_DEPTH/2 - boxMesh.getDepth()/2 - 0.5*monimo.getPositionShape().z);
                }
                root.getChildren().add(box);
            }
        }
    }
    public void drawPentominoes(ArrayList<PentominoShape> loadedPentominoes, int value){
        container.setCullFace(CullFace.FRONT);
        container.setDrawMode(DrawMode.LINE);
        title.setDisplayedValue(value);
        root.getChildren().remove(2, root.getChildren().size());
        for(int i = 0; i < loadedPentominoes.size(); i++){
            Color ranColor = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            ArrayList<Monimo> PentominoBoxCoordinates = loadedPentominoes.get(i).getChildren();
            
            for(int m = 0; m < PentominoBoxCoordinates.size(); m++){
                Coordinates coordinates = PentominoBoxCoordinates.get(m).getContainerPosition();
                
                Box boxMesh = new Box(Box_Width, Box_Height, Box_Depth);
                MeshView box = new MeshView(boxMesh);
                box.setDrawMode(DrawMode.FILL);
                box.setMaterial(new PhongMaterial(ranColor));
                box.setTranslateX(-CONTAINER_WIDTH/2 + boxMesh.getWidth()/2 + 0.5*coordinates.getY());
                box.setTranslateY(-CONTAINER_HEIGHT/2 + boxMesh.getHeight()/2 + 0.5*coordinates.getX());
                box.setTranslateZ(CONTAINER_DEPTH/2 - boxMesh.getDepth()/2 - 0.5*coordinates.getZ());
                root.getChildren().add(box);
            }
        }
    }
    
    /**
    * Method rotateX sets the angle of the camera around the X_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateX(double angle){
        xAxis.setAngle(angle);
    }
    /**
    * Method rotateY sets the angle of the camera around the Y_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateY(double angle){
        yAxis.setAngle(angle);
    }
    /**
    * Method rotateZ sets the angle of the camera around the Z_Axis
    * @param angle double angle that is the new angle of the camera
    */
    public void rotateZ(double angle){
        zAxis.setAngle(angle);
    }
    /**
     * Method will make container again to appear like a real container.
     */
    public void redrawContainer(){
        root.getChildren().remove(2, root.getChildren().size());
        container.setCullFace(CullFace.BACK);
        container.setDrawMode(DrawMode.FILL);
        PhongMaterial material;
        try{
           material = new PhongMaterial();
           FileInputStream inputstream = new FileInputStream("container.jpeg");
           Image image = new Image(inputstream);
           material.setDiffuseMap(image);
        }
        catch(FileNotFoundException exception){
            material = new PhongMaterial(Color.ORANGE);
        };
        container.setMaterial(material);    
    }
    public void drawShownContainers(int n){
        view.setHeading(shownContainers.get(n).getName());
        try{ 
            drawBoxes(shownContainers.get(n).getContainedParcels(), shownContainers.get(n).getValue());
        }
        catch(Exception e){
            drawPentominoes(shownContainers.get(n).getContainedPentominoes(), shownContainers.get(n).getValue());
        }
    }
    public int getSizeShownContainers(){
        return shownContainers.size();
    }
}       
