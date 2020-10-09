
    import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static float HEALTH = 100;
    private int score = 0;
    private Handler handler;
    public HUD(Handler handler){
        this.handler = handler;
    }


    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(8, 8, 150, 12);
        g.setColor(Color.green);
        g.fillRect(8, 8,(int) ((handler.player.currentHealth/handler.player.maxHealth) * 150) , 12);
        g.setColor(Color.white);
        g.drawRect(8, 8, 150, 12);
        g.drawString("Score: " + score, 8, 36);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
