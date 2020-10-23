import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Enemy extends GameObject {

    private Camera camera;
    private Random r;
    private int spawnX, spawnY;
    private boolean followPlayer, followRespawn;
    public boolean dead;
    private int tickCount;

    private Animation anim;
    private String direction;
    private boolean triggerAttack;
    private boolean activeAttack;
    private long attackTime;
    public Enemy(int x, int y, Camera camera, Level level, Handler handler, boolean solid, String path) {
        super(x, y, level, handler, solid, 100);
        spawnX = x;
        spawnY = y;
        this.camera = camera;
        objectSize = 16;
        dead = false;
        anim = new Animation(path, "enemy");
        pathfinder = new Pathfinder(level);
        followPlayer = false;
        followRespawn = false;
        tickCount = 0;
        this.r = new Random();
  
    }

    public void tick() {
        if (!dead) {
            if (currentHealth <= 0) {
                dead = true;
                x = -100;
                y = -100;
                handler.score += 100;
            } else {
                checkPlayerDist();
                anim.iterateFrame(triggerAttack);
                if (triggerAttack && !activeAttack) {
                    activeAttack = true;
                    triggerAttack = false;
                    if (direction == "left") {
                        anim.setAnimation("attackl");
                    } else if (direction == "right") {
                        anim.setAnimation("attackr");
                    }
                    attackTime = System.currentTimeMillis();
                } else if(activeAttack) {
                    if(System.currentTimeMillis() - attackTime > (anim.getFrames() * 100)) {
                        activeAttack = false;
                        if(playerInRange()){
                            handler.player.gotHit(5);
                        }
                        
                    }
                } else {
                    if (tickCount % 10 == 0 && followPlayer) {
                        pathfinder.pathFinding(level.getTile(this.x, this.y),
                                level.getTile(handler.player.getX(), handler.player.getY()));
                    } else if (tickCount % 10 == 0 && followRespawn) {
                        pathfinder.pathFinding(level.getTile(this.x, this.y), level.getTile(spawnX, spawnY));
                    }
                    if ((followPlayer || followRespawn) && pathfinder.path.size() != 0) {
        
                        int dstX = pathfinder.path.get(0).getX() + 8;
                        int dstY = pathfinder.path.get(0).getY() + 8;
                        if (this.x > dstX) {
                            velX = -1;
                        } else if (this.x < dstX) {
                            velX = 1;
                        } else {
                            velX = 0;
                        }
                        if (this.y > dstY) {
                            velY = -1;
                        } else if (this.y < dstY) {
                            velY = 1;
                        } else {
                            velY = 0;
                        }
                    } else {
                        if (r.nextInt(20) < 1) {
                            velX = r.nextInt(3) - 1;
                            velY = r.nextInt(3) - 1;
                        }
                    }
                    if (velX != 0 || velY != 0) {
                        move(velX, velY);
                        if (velX < 0) {
                            anim.setAnimation("walkl");
                            direction = "left";
                        } else if (velX > 0) {
                            anim.setAnimation("walkr");
                            direction = "right";
                        } else {
                            if (direction == "right") {
                                anim.setAnimation("walkr");
                            } else {
                                anim.setAnimation("walkl");
                            }
                        }
        
                    } else {
                        if (direction == "left") {
                            anim.setAnimation("idlel");
                        } else if (direction == "right") {
                            anim.setAnimation("idler");
                        }
                    }
                }
            }
            
        }
        if (tickCount > 60) {
            tickCount = 0;
        }
        tickCount++;
    }

    private void checkPlayerDist() {
        double distance = Math.sqrt((Math.pow(((double) handler.player.getX() + 8) - ((double) this.x + 8), 2) + Math.pow(((double) handler.player.getY() + 8) - ((double) this.y + 8), 2)));
        if (distance <= 20) {
            if(playerInRange()) {
                followPlayer = false;
                triggerAttack = true;
                followRespawn = false;
            } else {
                followPlayer = true;
                triggerAttack = false;
                followRespawn = false;
            } 
        } else if (distance <= 250) {
            followPlayer = true;
            triggerAttack = false;
            followRespawn = false;
        } else {
            followPlayer = false;
            triggerAttack = false;
            double respDistance = Math.sqrt((Math.pow(((double) this.spawnX + 8) - ((double) this.x + 8), 2) + Math.pow(((double) this.spawnY + 8) - ((double) this.y + 8), 2)));
            if(respDistance > 160 && !followRespawn) {
                followRespawn = true;
            } else if(respDistance < 32) {
                followRespawn = false;
            }
        }
    }

    private boolean playerInRange() {
        int xMin = this.x;
        int xMax = this.x + this.objectSize - 1;
        int yMin = this.y;
        int yMax = this.y + this.objectSize - 1;
        
        if(handler.player.inCollisionBox(xMin + 1, yMin) ||
        handler.player.inCollisionBox(xMin + 1, yMax) ||
        handler.player.inCollisionBox(xMax + 1, yMin) ||
        handler.player.inCollisionBox(xMax + 1, yMax)) {
            //check for 1 pixel right x+1
        } else if(handler.player.inCollisionBox(xMin - 1, yMin) ||
        handler.player.inCollisionBox(xMin - 1, yMax) ||
        handler.player.inCollisionBox(xMax - 1, yMin) ||
        handler.player.inCollisionBox(xMax - 1, yMax)) {
            //check for 1 pixel for left x-1
            return true;
        } else if(handler.player.inCollisionBox(xMin, yMin + 1) ||
        handler.player.inCollisionBox(xMin, yMax + 1) ||
        handler.player.inCollisionBox(xMax, yMin + 1) ||
        handler.player.inCollisionBox(xMax, yMax + 1)) {
            //check for 1 pixel for up y+1
            return true;
        } else if(handler.player.inCollisionBox(xMin, yMin - 1) ||
        handler.player.inCollisionBox(xMin, yMax - 1) ||
        handler.player.inCollisionBox(xMax, yMin - 1) ||
        handler.player.inCollisionBox(xMax, yMax - 1)){
            //check for 1 pixel for down y-1
            return true;
        }

        return false;
    }

    @Override
    public void render(Graphics g) {
        if (!dead) {

            g.drawImage(anim.getFrame(), x - camera.getX() - 8, y - camera.getY() - 14, null);

            g.setColor(Color.red);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);
            g.setColor(Color.green);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), (int) ((currentHealth / maxHealth) * 14), 3);
            g.setColor(Color.white);
            g.drawRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);

            
            // if(pathfinder.path.size() != 0) {
            // g.setColor(Color.BLUE);
            // //g.fillRect(pathfinder.path.get(0).getX() - camera.getX(), pathfinder.path.get(0).getY() - camera.getY(), 32, 32);
            //     for(int i = 0; i < pathfinder.path.size(); i++) {
            //         g.fillRect(pathfinder.path.get(i).getX() - camera.getX(),pathfinder.path.get(i).getY() - camera.getY(), 32, 32);
            //     }
            // }
        }
    }
}
