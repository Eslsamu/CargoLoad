package View;

import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author danyp
 */
public class Box extends TriangleMesh {
    private float w;
    private float h;
    private float d;
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
    public float getWidth(){
        return w;
    }
    public float getHeight(){
        return h;
    }
    public float getDepth(){
        return d;
    }
}
