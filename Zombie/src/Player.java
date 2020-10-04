
import java.awt.Graphics;

public class Player extends GameObject {

    private KeyInput input;
    private long attackTime;
    private Camera camera;
    private int speed;
    private int xOffset, yOffset;
    private boolean attack;
    private Animation anim;

    public Player(int x, int y, KeyInput input, Camera camera, Level level, Handler handler, boolean solid,
            String path) {
        super(x, y, level, handler, solid);
        this.input = input;
        this.camera = camera;
        this.objectSize = 16;
        speed = 2;
        anim = new Animation(path);
        attackTime = 0;        
        attack = false;
        xOffset = 23;
        yOffset = 32;
        anim.setAnimation("idle");

    }
    

    @Override
    public void tick() {

        if(input.space && !attack) {
            attack = true;
            anim.setAnimation("attack");
            attackTime = System.currentTimeMillis();
        }
        else if(attack) {
            if(System.currentTimeMillis() - attackTime > 400) {
                attack = false;
            }
        }
        else {
            moveDir();
            if (velX != 0 || velY != 0) {
                move(velX, velY);   
                anim.setAnimation("walk");
            }
            else {
                anim.setAnimation("idle");
            }
        }
    }

    @Override
    public void render(Graphics g) {      
        g.drawImage(anim.getFrame(), x - camera.getX() - xOffset, y - camera.getY() - yOffset, null);   
    }

    private void moveDir() {
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
    }
}




//g.drawImage(scaledImage.getSubimage(0, 0, 124, 124), x - camera.getX(), y - camera.getY(), null);
// scale = 2;
// int new_w = image.getWidth() * scale;
// int new_h = image.getHeight() * scale;
// Image newImg = image.getScaledInstance(new_w, new_h, Image.SCALE_FAST);
// scaledImage = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_ARGB);
// Graphics2D bGr = scaledImage.createGraphics();
// bGr.drawImage(newImg, 0, 0, null);
// bGr.dispose();
        //1-5
        //6-13
        //14-19
        //20-23
        //24-26
        //27-28