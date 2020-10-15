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
    private boolean followPlayer;
    public boolean dead;
    private int tickCount;
    public Enemy(int x, int y, Camera camera, Level level, Handler handler, boolean solid)  {
       super(x, y, level, handler, solid, 100);
        this.camera = camera;
        objectSize = 16;
        dead = false;
        pathfinder = new Pathfinder(level);
        followPlayer = false;
        tickCount = 0;
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
                handler.score += 100;
            }
            
            if(tickCount % 10 == 0 && checkPlayer()) {
                pathfinder.pathFinding(level.getTile(this.x, this.y), level.getTile(handler.player.getX(), handler.player.getY()));
            }

            if(followPlayer && pathfinder.path.size() != 0) {

                int dstX = pathfinder.path.get(0).getX() + 8;
                int dstY = pathfinder.path.get(0).getY() + 8;
                if(this.x > dstX) {
                    velX = -1;
                } else if(this.x < dstX) {
                    velX = 1;
                } else {
                    velX = 0;
                }
                if(this.y > dstY) {
                    velY = -1;
                } else if(this.y < dstY) {
                    velY = 1;
                } else {
                    velY = 0;
                }
                move(velX, velY);
            } else {
                if(r.nextInt(10) < 2)
                {
                    velX = r.nextInt(3)-1;
                    velY = r.nextInt(3)-1;
                    move(velX, velY);
                }
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
        if(tickCount > 60) {
            tickCount = 0;
        }
        tickCount++;
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

    private boolean checkPlayer() {
        double distance = Math.sqrt((Math.pow((double)handler.player.getX() - (double)this.x, 2) + Math.pow((double)handler.player.getY() - (double)this.y, 2)));
        if(distance <= 250) {
            followPlayer = true;
            return true;
        } else {
            followPlayer = false;
            return false;
        }
    }

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

            // if(pathfinder.path.size() != 0) {
            //     g.setColor(Color.BLUE);
            //     g.fillRect(pathfinder.path.get(0).getX() - camera.getX(), pathfinder.path.get(0).getY() - camera.getY(), 32, 32);    
            //     // g.setColor(Color.BLUE);
            //     // for(int i = 0; i < pathfinder.path.size(); i++) {
            //     //     g.fillRect(pathfinder.path.get(i).getX() - camera.getX(), pathfinder.path.get(i).getY() - camera.getY(), 32, 32);
            //     // }
            // }   
            
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
