import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    private KeyInput input;
    private int attackTime;
    private Camera camera;
    private int playerSize;
    public Player(int x, int y, ID id, KeyInput input, Camera camera, Level level) {
        super(x, y, id, level);
        this.input = input;
        this.attackTime  = 0;
        this.camera = camera;
        this.playerSize = 32;
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
        g.fillRect((int)x - camera.getX(), (int)y - camera.getY(), 32, 32);
    }
    

    private void move(int velX, int velY) {
        if (velX != 0 && velY != 0) {
            move(velX, 0);
            move(0, velY);
            return;
        }

        if (!hasCollided(velX, velY)) {
            x += velX;
            y += velY;
        }
    }

    private boolean hasCollided(int velX, int velY) {
        int xMin = this.x + velX;
        int xMax = this.x + playerSize + velX;
        int yMin = this.y + velY;
        int yMax = this.y + playerSize + velY;

        if(level.getTile(xMin, yMin).isSolid() || level.getTile(xMin, yMax).isSolid() || level.getTile(xMax, yMin).isSolid() || level.getTile(xMax, yMax).isSolid()) {
            return true;
        }

        return false;
    }
}


