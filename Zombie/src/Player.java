import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    private KeyInput input;
    private int attackTime;
    private Camera camera;

    public Player(int x, int y, KeyInput input, Camera camera, Level level, Handler handler, boolean solid) {
        super(x, y, level, handler, solid);
        this.input = input;
        this.attackTime  = 0;
        this.camera = camera;
        this.objectSize = 32;
    }

    @Override
    public void tick() {
        if(input.verticalMov == 1) {
            velY = 3;
        }
        else if(input.verticalMov == 2) {
            velY = -3;
        }
        else {
            velY = 0;
        }

        if(input.horizontalMov == 2) {
            velX = 3;
        }
        else if(input.horizontalMov == 1) {
            velX = -3;
        }
        else {
            velX = 0;
        }
        
        if (velX != 0 || velY != 0) {
            move(velX, velY);
        }
        
        if(input.space == 1) {
            attackTime = 1000;
            input.space = 0;
        }
    }

    
    @Override
    public void render(Graphics g) {
        //player texture canvas of 64x64

        if(attackTime > 0) {
            g.setColor(Color.cyan);
            g.fillRect((int) x + 32 - camera.getX(), (int) y + 8 - camera.getY(), 16, 16);
            attackTime--;
        }
        g.setColor(Color.white);
        g.fillRect((int)x - camera.getX(), (int)y - camera.getY(), objectSize, objectSize);
    }
    
}


