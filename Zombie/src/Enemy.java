import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Enemy extends GameObject {

    private Camera camera;
    private Random r;
    private int spawnX, spawnY;

    public Enemy(int x, int y, Camera camera, Level level, Handler handler, boolean solid)  {
        super(x, y, level, handler, solid);
        this.camera = camera;
        this.objectSize = 32;
        this.spawnX = x;
        this.spawnY = y;
        this.r = new Random();
    }

    public void tick() {
        // int max = 3;
        // int min = -2;
        // velX = r.nextInt(max-min) + min;
        // velY = r.nextInt(max-min) + min;

        if(velX != 0 || velY != 0) {
            move(velX, velY);
        }
    }

    

    public void render() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x - camera.getX(), y - camera.getY(), objectSize, objectSize);
    }
}
