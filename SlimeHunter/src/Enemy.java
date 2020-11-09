import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Enemy extends GameObject {

    private Camera camera;
    private Random randomNumber;
    private int spawnPointX, spawnPointY;
    private boolean followPlayer, followRespawn;
    private int tickCount;

    private Animation objectAnimation;
    private String moveDirection;
    private boolean attackTrigger;
    private boolean isAttackActive;
    private long attackTime;
    private Sound attackSound;

    public boolean isDead;
    public long deathTime;

    public Enemy(int x, int y, Camera camera, Level level, Handler handler, boolean solid, String path) {
        super(x, y, level, handler, solid, 100);
        
        spawnPointX = x;
        spawnPointY = y;
        this.camera = camera;
        objectSize = 16;
        isDead = false;
        deathTime = 0;
        objectAnimation = new Animation(path, "enemy");
        pathfinder = new Pathfinder(level);
        attackSound = new Sound("res/slime8.wav", 0.1f);
        followPlayer = false;
        followRespawn = false;
        tickCount = 0;
        this.randomNumber = new Random();
    }

    @Override
    public void render(Graphics g) {
        if (!isDead) {
            g.drawImage(objectAnimation.getFrame(), x - camera.getX() - 8, y - camera.getY() - 14, null);

            g.setColor(Color.red);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);
            g.setColor(Color.green);
            g.fillRect(this.x - camera.getX(), this.y - 6 - camera.getY(), (int) ((currentHealth / maxHealth) * 14), 3);
            g.setColor(Color.white);
            g.drawRect(this.x - camera.getX(), this.y - 6 - camera.getY(), 14, 3);
        }
    }

    @Override
    public void tick() {
        if (!isDead) {
            if (currentHealth <= 0) {
                despawnObject();
            } else {
                checkPlayerDist();
                objectAnimation.iterateFrame(attackTrigger);
                if (attackTrigger && !isAttackActive) {
                    attackSound.play();
                    isAttackActive = true;
                    attackTrigger = false;
                    if (moveDirection == "left") {
                        objectAnimation.setAnimation("attackLeft");
                    } else if (moveDirection == "right") {
                        objectAnimation.setAnimation("attackRight");
                    }
                    attackTime = System.currentTimeMillis();
                } else if (isAttackActive) {
                    if (System.currentTimeMillis() - attackTime > (objectAnimation.getFrames() * 100)) {
                        isAttackActive = false;
                        if (playerInRange()) {
                            handler.player.gotHit(25);
                        }
                    }
                } else {
                    if (tickCount % 10 == 0 && followPlayer) {
                        pathfinder.findNewPath(level.getTile(this.x, this.y),level.getTile(handler.player.getX(), handler.player.getY()));
                    } else if (tickCount % 10 == 0 && followRespawn) {
                        pathfinder.findNewPath(level.getTile(this.x, this.y), level.getTile(spawnPointX, spawnPointY));
                    }

                    if ((followPlayer || followRespawn) && pathfinder.path.size() > 0) {
                            int destinationX = pathfinder.path.get(0).getX() + 8;
                            int destinationY = pathfinder.path.get(0).getY() + 8;
                            
                            if (this.x > destinationX) {
                                velocityX = -1;
                            } else if (this.x < destinationX) {
                                velocityX = 1;
                            } else {
                                velocityX = 0;
                            }
                            if (this.y > destinationY) {
                                velocityY = -1;
                            } else if (this.y < destinationY) {
                                velocityY = 1;
                            } else {
                                velocityY = 0;
                            }
                    } else {
                        if (randomNumber.nextInt(20) < 1) {
                            velocityX = randomNumber.nextInt(3) - 1;
                            velocityY = randomNumber.nextInt(3) - 1;
                        }
                    }

                    if (velocityX != 0 || velocityY != 0) {
                        attackSound.stop();
                        moveObject(velocityX, velocityY);
                        if (velocityX < 0) {
                            objectAnimation.setAnimation("walkLeft");
                            moveDirection = "left";
                        } else if (velocityX > 0) {
                            objectAnimation.setAnimation("walkRight");
                            moveDirection = "right";
                        } else {
                            if (moveDirection == "right") {
                                objectAnimation.setAnimation("walkRight");
                            } else {
                                objectAnimation.setAnimation("walkLeft");
                            }
                        }
                    } else {
                        attackSound.stop();
                        if (moveDirection == "left") {
                            objectAnimation.setAnimation("idleLeft");
                        } else if (moveDirection == "right") {
                            objectAnimation.setAnimation("idleRight");
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

    private void despawnObject() {
        isDead = true;
        x = -100;
        y = -100;
        handler.gameScore += 100;
        stopSounds();
    }
    
    @Override
    public void stopSounds() {
        attackSound.stop();
    }

    private void checkPlayerDist() {
        double playerDistance = Math.sqrt((Math.pow(((double) handler.player.getX() + 8) - ((double) this.x + 8), 2) + Math.pow(((double) handler.player.getY() + 8) - ((double) this.y + 8), 2)));
        if (playerDistance <= 20) {
            if(playerInRange()) {
                followPlayer = false;
                attackTrigger = true;
                followRespawn = false;
            } else {
                followPlayer = true;
                attackTrigger = false;
                followRespawn = false;
            } 
        } else if (playerDistance <= 250) {
            followPlayer = true;
            attackTrigger = false;
            followRespawn = false;
        } else {
            followPlayer = false;
            attackTrigger = false;
            double respawnDistance = Math.sqrt((Math.pow(((double) this.spawnPointX + 8) - ((double) this.x + 8), 2) + Math.pow(((double) this.spawnPointY + 8) - ((double) this.y + 8), 2)));
            if(respawnDistance > 160 && !followRespawn) {
                followRespawn = true;
            } else if(respawnDistance < 32) {
                followRespawn = false;
            }
        }
    }

    private boolean playerInRange() {
        int xMin = this.x;
        int xMax = this.x + this.objectSize - 1;
        int yMin = this.y;
        int yMax = this.y + this.objectSize - 1;
        
        if(handler.player.inCollisionBox(xMin + 1, yMin) || handler.player.inCollisionBox(xMin + 1, yMax) || 
        handler.player.inCollisionBox(xMax + 1, yMin) || handler.player.inCollisionBox(xMax + 1, yMax)) {
            //check for 1 pixel right x+1
            return true;
            
        } else if(handler.player.inCollisionBox(xMin - 1, yMin) || handler.player.inCollisionBox(xMin - 1, yMax) ||
        handler.player.inCollisionBox(xMax - 1, yMin) || handler.player.inCollisionBox(xMax - 1, yMax)) {
            //check for 1 pixel for left x-1
            return true;
        } else if(handler.player.inCollisionBox(xMin, yMin + 1) || handler.player.inCollisionBox(xMin, yMax + 1) ||
        handler.player.inCollisionBox(xMax, yMin + 1) || handler.player.inCollisionBox(xMax, yMax + 1)) {
            //check for 1 pixel for up y+1
            return true;
        } else if(handler.player.inCollisionBox(xMin, yMin - 1) || handler.player.inCollisionBox(xMin, yMax - 1) ||
        handler.player.inCollisionBox(xMax, yMin - 1) || handler.player.inCollisionBox(xMax, yMax - 1)){
            //check for 1 pixel for down y-1
            return true;
        }
        return false;
    }

}
