package Shapes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
 * Class containing the materials used for displaying shapes(the 3D visualisation with PhongMaterial)
 * enums with diffrent possible colours 
 */
public enum ShapeMaterial {
    EMPTY,
    BLUE, //A & L
    GREEN, //B & P
    RED; //C & T
    /**
     * @return a PhongMaterial for each diffrent colour
     */
    public PhongMaterial toMaterial() {
        PhongMaterial material = new PhongMaterial();
        Color ranColor = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        switch (this) {
            case EMPTY	: 
                material = new PhongMaterial(Color.BLACK); break;
            case BLUE	:
                try{
                   material = new PhongMaterial();
                   FileInputStream inputstream = new FileInputStream("BoxC.png");
                   Image image = new Image(inputstream);
                   material.setDiffuseMap(image);
                }
                catch(FileNotFoundException exception){
                    material = new PhongMaterial(ranColor);
                }; break;
            case GREEN	:
                try{
               material = new PhongMaterial();
                FileInputStream inputstream = new FileInputStream("BoxA.png");
                Image image = new Image(inputstream);
                material.setDiffuseMap(image);
                }
                catch(FileNotFoundException exception){
                    material = new PhongMaterial(ranColor);
                }; break;
            case RED	: 
                try{
               material = new PhongMaterial();
                FileInputStream inputstream = new FileInputStream("BoxB.png");
                Image image = new Image(inputstream);
                material.setDiffuseMap(image);
                }
                catch(FileNotFoundException exception){
                    material = new PhongMaterial(ranColor);
                }; break;
        }
        return material;
    }
}
