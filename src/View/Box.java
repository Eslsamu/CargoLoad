package View;

import javafx.scene.shape.TriangleMesh;

/**
 * Create a custom Box class using TriangleMesh to avoid bugs with original javafx BOX class.
 * @author Jordan, Basia
 * @version 1.1
 * @date 23.01.2017
 */
public class Box extends TriangleMesh {
    private float w;
    private float h;
    private float d;
    /**
     * Constructor will create a triangle mesh with points, textCoords, faces and faceSmoothingGroups
     * @param w the width of the wanted box
     * @param h the height of the wanted box
     * @param d the depth of the wanted box
     */
    public Box(float w, float h, float d) {
        this.w = w;
        this.h = h;
        this.d = d;
        float hw = w / 2f;
        float hh = h / 2f;
        float hd = d / 2f;

        float points[] = {
            -hw, -hh, -hd,
             hw, -hh, -hd,
             hw,  hh, -hd,
            -hw,  hh, -hd,
            -hw, -hh,  hd,
             hw, -hh,  hd,
             hw,  hh,  hd,
            -hw,  hh,  hd};
        
        float texCoords[] = {0, 0, 1, 0, 1, 1, 0, 1};
        
        int faces[] = {
            0, 0, 2, 2, 1, 1,
            2, 2, 0, 0, 3, 3,
            1, 0, 6, 2, 5, 1,
            6, 2, 1, 0, 2, 3,
            5, 0, 7, 2, 4, 1,
            7, 2, 5, 0, 6, 3,
            4, 0, 3, 2, 0, 1,
            3, 2, 4, 0, 7, 3,
            3, 0, 6, 2, 2, 1,
            6, 2, 3, 0, 7, 3,
            4, 0, 1, 2, 5, 1,
            1, 2, 4, 0, 0, 3,
        };
        
        int faceSmoothingGroups[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        
        this.getFaceSmoothingGroups().setAll(faceSmoothingGroups);
        this.getPoints().setAll(points);
        this.getTexCoords().setAll(texCoords);
        this.getFaces().setAll(faces);
    }
    /**
     * Get the width of the box
     * @return the width of the box
     */
    public float getWidth(){
        return w;
    }
    /**
     * Get the height of the box
     * @return the height of the box
     */
    public float getHeight(){
        return h;
    }
    /**
     * Get the depth of the box
     * @return the depth of the box
     */
    public float getDepth(){
        return d;
    }
}
