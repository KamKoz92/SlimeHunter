import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu {

    private Handler handler;
    private BufferedImage backGroundImage;
    private Fonts customFonts;
    public int menuType; //1-main, 2-help, 3-endGame

    public Menu(Handler handler, int width, int height, Fonts customFonts) {
        this.handler = handler;
        this.customFonts = customFonts;
        menuType = 1;

        try {
            backGroundImage = ImageIO.read(new File("res/Menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void render(Graphics g) {
        g.drawImage(backGroundImage, 0, 0, null);

        if(menuType == 1) {
            g.setFont(customFonts.font_48);
            g.setColor(Color.white);
            g.drawString("Slime Hunter", 170, 60);

            g.setColor(Color.gray);
            g.fillRect(245, 105, 140,50);
            g.setColor(Color.black);
            g.drawRect(245, 105, 140,50);

            g.setFont(customFonts.font_12);
            g.setColor(Color.white);
            g.drawString("New Game", 270, 137);

            g.setColor(Color.gray);
            g.fillRect(245, 170, 140,50);
            g.setColor(Color.black);
            g.drawRect(245, 170, 140,50);

            g.setFont(customFonts.font_12);
            g.setColor(Color.white);
            g.drawString("Help", 300, 202);

            g.setColor(Color.gray);
            g.fillRect(245, 235, 140,50);
            g.setColor(Color.black);
            g.drawRect(245, 235, 140,50);

            g.setFont(customFonts.font_12);
            g.setColor(Color.white);
            g.drawString("Exit", 300, 267);
        } else if(menuType == 2) {
            g.setColor(Color.white);
            g.setFont(customFonts.font_48);
            g.drawString("Slime Hunter", 170, 60);

            g.setFont(customFonts.font_12);
            g.drawString("Kill as many slimes as you can.", 190, 120);
            g.drawString("Move with W, S, A, D.", 235, 140);
            g.drawString("Attack with SPACE.", 235, 160);
           
            g.setColor(Color.gray);
            g.fillRect(245, 235, 140,50);
            g.setColor(Color.black);
            g.drawRect(245, 235, 140,50);

            g.setColor(Color.white);
            g.drawString("Back", 295, 267);
        } else if(menuType == 3) {
            g.setColor(Color.gray);
            g.fillRect(245, 235, 140,50);
            g.setColor(Color.black);
            g.drawRect(245, 235, 140,50);

            g.setColor(Color.white);
            g.setFont(customFonts.font_48);
            g.drawString("You died.", 215, 120);

            g.drawString("Your score: " + handler.gameScore, 145, 170);
            
            g.setFont(customFonts.font_12);
            g.drawString("Main Menu", 275, 267);
        }
    }

    public void tick() {
	}

}
