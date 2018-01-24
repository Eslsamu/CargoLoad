package Util;

public class Vertex{
    private Coordinates coords;
    int vertexNr;
    private int x;
    private int y;
    private int z;

    public Vertex(Coordinates coords, int i){
        this.coords = coords;
        vertexNr = i;
        this.x = coords.getX();
        this.y = coords.getY();
        this.z = coords.getZ();
    }

    public int getVertexNr(){
        return vertexNr;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
