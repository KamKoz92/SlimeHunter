import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    // private float _acc = 0.5f;
    // private float _dcc = 0.25f;
    private KeyInput input;
    private int attackTime;
    public Player(float x, float y, ID id, KeyInput input) {
        super(x, y, id);
        this.input = input;
        this.attackTime  = 0;
    }

    @Override
    public void tick() {
        
        x += velX;
        y += velY;

        if(input.verticalMov == 1) {
            // velY += _acc;
            velY = 4;
        }
        else if(input.verticalMov == 2) {
            // velY -= _acc;
            velY = -4;
        }
        else {
            // if(velY > 0) velY -= _dcc;
            // else if(velY < 0) velY += _dcc;
            velY = 0;
        }

        if(input.horizontalMov == 2) {
            // velX += _acc;
            velX = 4;
        }
        else if(input.horizontalMov == 1) {
            // velX -= _acc;
            velX = -4;
        }
        else {
            // if(velX > 0) velX -= _dcc;
            // else if(velX < 0) velX += _dcc;
            velX = 0;
        }
        // velX = clamp(velX, 5, -5);
        // velY = clamp(velY, 5, -5);
        if(input.space == 1) {
            attackTime = 1000;
            input.space = 0;
        }
        
    }
    // private float clamp(float value, float max, float min) {
    //     if(value >= max) value = max;
    //     else if(value <= min) value = min;
    //     return value;
    // }
    
    @Override
    public void render(Graphics g) {
        if(attackTime > 0) {
            g.setColor(Color.cyan);
            g.fillRect((int) x + 32, (int) y + 8, 16, 16);
            attackTime--;
        }
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }
    
}
