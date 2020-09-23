public class Camera {
    
    private int x, y, w, h;

    private Handler handler;
    public Camera(int x, int y, int w, int h, Handler handler) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.handler = handler;
    }

    public void tick() {
        x = (int)handler.objects.get(0).x - Game.WIDTH/2;
        y = (int)handler.objects.get(0).y - Game.HEIGHT/2;
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > (32 * 32) - w) x = (32 * 32) - w;
        if(y > (32 * 32) - h) y = (32 * 32) - h;

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
