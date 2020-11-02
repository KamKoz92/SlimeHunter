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
        mapWidth = 32;
        mapHeight = 32;
        tileSize = 32;
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

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
