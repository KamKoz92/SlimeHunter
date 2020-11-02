
import java.awt.image.BufferedImage;

public class Tile { //extends IHeapItem<Tile>{

    private int x, y;
    private int sheetX, sheetY;
    private boolean isSolid;
    public int tileType;
    public BufferedImage tileImage;

    public int gCost, hCost;
    public Tile pathParent;
    // public int heapIndex;

    public Tile(int x, int y, int tileType, SpriteSheet sheet) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
        this.isSolid = Tileset.solidOrNot(tileType);
        this.sheetX = (tileType % 8) * 32;
        this.sheetY = (tileType / 8) * 32;
        this.tileImage = sheet.image.getSubimage(sheetX, sheetY, 32, 32);
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

    public int getY() {
        return y;
    }

    public int getType() {
        return tileType;
    }

    public boolean isSolid() {
        return isSolid;
    }
}
    
