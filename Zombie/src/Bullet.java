import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {
    private int bulletVel = 10;
    private Handler handler;
    private int count;
    public Bullet(int x, int y, int angle, Handler handler, Level level, boolean solid) {
        super(x, y, level, handler, solid);
        this.x = x;
        this.y = y;
        velX = (int) ((bulletVel) * Math.cos(angle));
        velY = (int) ((bulletVel) * Math.sin(angle));
        count = 0;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        count++;
        if(count > 1500) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 8, 8);

    }

    
}
