import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {
    private int bulletVel = 10;
    private Handler handler;
    public Bullet(float x, float y, ID id, float angle, Handler handler) {
        super(x, y, id);
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        velX = (float) ((bulletVel) * Math.cos(angle));
        velY = (float) ((bulletVel) * Math.sin(angle));
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(x > Game.WIDTH || x < 0 || y > Game.HEIGHT|| y < 0) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, 8, 8);

    }

    
}
