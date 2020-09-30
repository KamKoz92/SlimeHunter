import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Player extends GameObject {

    private KeyInput input;
    private int attackTime;
    private Camera camera;
    private BufferedImage image;
    private int scale;
    private BufferedImage scaledImage;
    private int speed;
    public Player(int x, int y, KeyInput input, Camera camera, Level level, Handler handler, boolean solid,
            String path) {
        super(x, y, level, handler, solid);
        this.input = input;
        this.attackTime = 0;
        this.camera = camera;
        this.objectSize = 32;
        image = null;
        this.speed = 2;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // scale = 2;
        // int new_w = image.getWidth() * scale;
        // int new_h = image.getHeight() * scale;
        // Image newImg = image.getScaledInstance(new_w, new_h, Image.SCALE_FAST);
        // scaledImage = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_ARGB);
        // Graphics2D bGr = scaledImage.createGraphics();
        // bGr.drawImage(newImg, 0, 0, null);
        // bGr.dispose();

    }

    @Override
    public void tick() {
        if (input.verticalMov == 1) {
            velY = speed;
        } else if (input.verticalMov == 2) {
            velY = -speed;
        } else {
            velY = 0;
        }

        if (input.horizontalMov == 2) {
            velX = speed;
        } else if (input.horizontalMov == 1) {
            velX = -speed;
        } else {
            velX = 0;
        }

        if (velX != 0 || velY != 0) {
            move(velX, velY);
        }

        if (input.space == 1) {
            attackTime = 1000;
            input.space = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        // player texture canvas of 64x64

        if (attackTime > 0) {
            g.setColor(Color.cyan);
            g.fillRect(x + 32 - camera.getX(), y + 8 - camera.getY(), 16, 16);
            attackTime--;
        }
        g.setColor(Color.white);
        g.fillRect(x - camera.getX(), y - camera.getY(), objectSize, objectSize);
        //g.drawImage(scaledImage.getSubimage(0, 0, 124, 124), x - camera.getX(), y - camera.getY(), null);
        // g.drawImage(image.getSubimage(0, 0, 64, 64), x - camera.getX(), y - camera.getY(), null);
    }

}
