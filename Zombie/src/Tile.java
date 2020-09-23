import java.awt.Graphics;

public class Tile {

    private int x, y, type;
    private boolean solid;
    public Tile(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        if(type < 1) {
            solid = true;
        }
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
    
