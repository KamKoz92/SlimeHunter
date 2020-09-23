import java.awt.Color;
import java.awt.Graphics;

public class Box extends GameObject {

    public Box(float x, float y, ID id, Level level) {
        super(x, y, id, level);
        
    }

    @Override
    public void tick() {
        

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 32, 32);

    }
    
}
