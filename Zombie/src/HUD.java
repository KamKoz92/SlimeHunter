
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class HUD {
    
    public static float HEALTH = 100;
    private int score = 0;
    private Handler handler;
    private Font font;
    public HUD(Handler handler, Font font){
        this.handler = handler;
        this.font = font;
    }

    public void tick() {
        score = handler.score;
    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(8, 8, 150, 12);
        g.setColor(Color.green);
        g.fillRect(8, 8,(int) ((handler.player.currentHealth/handler.player.maxHealth) * 150) , 12);
        g.setColor(Color.white);
        g.drawRect(8, 8, 150, 12);
        g.setFont(font);
        g.drawString("Score: " + score, 8, 40);
    }
}
