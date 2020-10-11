import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Enemy extends GameObject {

    private Camera camera;
    private Random r;
    // private int spawnX, spawnY;

    // private int dstX, dstY;
    // private double diffX, diffY;
    // private double distance;
    // private int moveDelay;
    // private long lastMoveTime;
    // private boolean timeCheck;
    public boolean dead;
    public Enemy(int x, int y, Camera camera, Level level, Handler handler, boolean solid)  {
       super(x, y, level, handler, solid, 100);
        this.camera = camera;
        objectSize = 16;
        dead = false;
       
       
       
    //    this.spawnX = x;
    //    this.spawnY = y;
       this.r = new Random();
    //    this.moveDelay = 1000;
    //    this.timeCheck = true;
    //    newDst();
    }

    public void tick() {
        if(!dead) {
            if(currentHealth <= 0) {
                dead = true;
                x = -100;
                y = -100;
            }
            if(r.nextInt(10) < 2)
            {
                velX = r.nextInt(3)-1;
                velY = r.nextInt(3)-1;
                move(velX, velY);
            }
        }
        
        

        // checkDst();

        // if(dstX < 0 || dstY < 0) { 
        //     if(timeCheck) {
        //         lastMoveTime = System.currentTimeMillis();
        //         System.out.println("new time");
        //         timeCheck = false;
        //     }
        //     if(System.currentTimeMillis() - lastMoveTime > moveDelay) {
        //         newDst();
        //         timeCheck = true;
        //     }
        // }
        // else {
        //     move(velX, velY);
        // }
    }
    

    // private void newDst() {
    //     this.dstX = spawnX + r.nextInt(200) - 100;
    //     this.dstY = spawnY + r.nextInt(200) - 100;
    //     this.diffX = this.x - this.dstX;
    //     this.diffY = this.y - this.dstY;
    //     distance = (int) Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));
    //     velX = (int) (((-1.0/distance) * diffX) * 2);
    //     velY = (int) (((-1.0/distance) * diffY) * 2);
    //     velX = clamp(velX,-2,2);
    //     velY = clamp(velY,-2,2);

    // }

    // private int clamp(int vel, int i, int j) {
    //     if(vel > j) return j;
    //     else if(vel < i) return i;
    //     else return vel;
    // }

    @Override
    public void render(Graphics g) {
        if(!dead) {
            g.setColor(Color.red);
            g.fillRect(x - camera.getX(), y - camera.getY(), objectSize, objectSize);

            g.setColor(Color.red);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);
            g.setColor(Color.green);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), (int)((currentHealth/maxHealth) * 14), 3);
            g.setColor(Color.white);
            g.drawRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);
        }
    }

    // private void checkDst() {
    //     if(velX < 0)
    //     {
    //         if(this.x <= dstX) {
    //             velX = 0;
    //             dstX = -1;
    //         }
    //     }
    //     else if(velX > 0) {
    //         if(this.x >= dstX) {
    //             velX = 0;
    //             dstX = -1;
    //         } 
    //     }
    //     if(velY < 0) {
    //         if(this.y <= dstY) {
    //             velY = 0;
    //             dstY = -1;
    //         }
    //     }
    //     else if(velY > 0) {
    //         if(this.y >= dstY) {
    //             velY = 0;
    //             dstY = -1;
    //         } 
    //     }
    // }
}
