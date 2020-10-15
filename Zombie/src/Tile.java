
import java.awt.image.BufferedImage;

public class Tile {//extends IHeapItem<Tile>{

    private int x, y, type;
    private int sX, sY;
    private boolean solid;
    public BufferedImage image;

    public int gCost, hCost;
    public Tile pathParent;
    // public int heapIndex;

    public Tile(int x, int y, int type, SpriteSheet sheet) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.solid = Tileset.solid(type);
        this.sX = (type % 8) * 32;
        this.sY = (type / 8) * 32;
        this.image = sheet.image.getSubimage(sX, sY, 32, 32);
    }

    // @Override
    // public int compareTo(Tile tileToCompare) {
    //     Integer compare = fCost().compareTo(tileToCompare.fCost());
    //     if(compare == 0) {
    //         compare = hCost.compareTo(tileToCompare.hCost);
    //     }
    //     return -compare;
    // }


    public int fCost() {
        return gCost + hCost;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
    
