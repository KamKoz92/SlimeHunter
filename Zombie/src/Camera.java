public class Camera {
    
    private int x, y, w, h;

    private Handler handler;
    public int mapWidth, mapHeight, tileSize;
    public Camera(int x, int y, int w, int h, Handler handler) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.handler = handler;
        this.mapWidth = 32;
        this.mapHeight = 32;
        this.tileSize = 32;
    }

    public void tick() {
        x = handler.player.x - Game.WIDTH/2;
        y = handler.player.y - Game.HEIGHT/2;
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > (mapWidth * tileSize) - w) x = (mapWidth * tileSize) - w;
        if(y > (mapHeight * tileSize) - h) y = (mapHeight * tileSize) - h;

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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
