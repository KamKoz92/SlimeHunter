import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    private int x, y, type;
    public int sX, sY;
    private boolean solid;
    public BufferedImage image;
    public Tile(int x, int y, int type, SpriteSheet sheet) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.solid = Tileset.solid(type);
        this.sX = (type % 8) * 32;
        this.sY = (type / 8) * 32;
        this.image = sheet.image.getSubimage(sX, sY, 32, 32);
    }
    public void tick() {

    }


    public void render(Graphics g) {
        
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
    
