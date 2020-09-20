import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    private float _acc = 0.5f;
    private float _dcc = 0.25f;
    private KeyInput input;

    public Player(float x, float y, ID id, KeyInput input) {
        super(x, y, id);
        this.input = input;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(input.verticalMov == 1) {
            velY += _acc;
        }
        else if(input.verticalMov == 2) {
            velY -= _acc;
        }
        else {
            if(velY > 0) velY -= _dcc;
            else if(velY < 0) velY += _dcc;
        }
        if(input.horizontalMov == 2) {
            velX += _acc;
        }
        else if(input.horizontalMov == 1) {
            velX -= _acc;
        }
        else {
            if(velX > 0) velX -= _dcc;
            else if(velX < 0) velX += _dcc;
        }


        velX = clamp(velX, 5, -5);
        velY = clamp(velY, 5, -5);
        
    }

    private float clamp(float value, float max, float min) {
        if(value >= max) value = max;
        else if(value <= min) value = min;
        return value;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }
    
}
