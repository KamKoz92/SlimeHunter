
import java.awt.Graphics;

public class Player extends GameObject {

    private KeyInput input;
    private long attackTime;
    private Camera camera;
    private int playerSpeed;
    private boolean attackActive;
    private Animation playerAnimation;
    private String faceDirection;
    private Sound walkSound;
    private Sound attackSwingSound;

    public Player(int x, int y, KeyInput input, Camera camera, Level level, Handler handler, boolean solid, String spriteSheetPath) {
        super(x, y, level, handler, solid, 100);

        this.input = input;
        this.camera = camera;
        this.objectSize = 16;
        playerSpeed = 2;
        playerAnimation = new Animation(spriteSheetPath, "player");
        attackSwingSound = new Sound("res/swing3.wav", 0.1f);
        walkSound = new Sound("res/interface2.wav", 0.05f);
        attackTime = 0;
        attackActive = false;
        faceDirection = "right";
    }

    @Override
    public void render(Graphics g) {
        if (faceDirection == "left") {
            g.drawImage(playerAnimation.getFrame(), x - camera.getX() - 25, y - camera.getY() - 32, null);
        } else {
            g.drawImage(playerAnimation.getFrame(), x - camera.getX() - 23, y - camera.getY() - 32, null);
        }
    }

    @Override
    public void tick() {
        if (input.spaceKey && !attackActive) {
            walkSound.stop();
            attackActive = true;
            attackSwingSound.play();
            checkAtkArea();
            if (faceDirection == "left") {
                playerAnimation.setAnimation("attackLeft");
            } else if (faceDirection == "right") {
                playerAnimation.setAnimation("attackRight");
            }
            attackTime = System.currentTimeMillis();
        } else if (attackActive) {
            if (System.currentTimeMillis() - attackTime > (playerAnimation.getFrames() * 100)) {
                attackActive = false;
                attackSwingSound.stop();
            }
        } else {
            setPlayerVelocities();
            if (velocityX != 0 || velocityY != 0) {
                walkSound.playInLoop();
                moveObject(velocityX, velocityY);
                if (velocityX < 0) {
                    playerAnimation.setAnimation("walkLeft");
                    faceDirection = "left";
                } else if (velocityX > 0) {
                    playerAnimation.setAnimation("walkRight");
                    faceDirection = "right";
                } else {
                    if (faceDirection == "right") {
                        playerAnimation.setAnimation("walkRight");
                    } else {
                        playerAnimation.setAnimation("walkLeft");
                    }
                }
            } else {
                walkSound.stop();
                if (faceDirection == "left") {
                    playerAnimation.setAnimation("idleLeft");
                } else if (faceDirection == "right") {
                    playerAnimation.setAnimation("idleRight");
                }
            }
        }
        playerAnimation.iterateFrame(input.spaceKey);
    }

    private void checkAtkArea() {
        int xMin;
        int xMax;
        int yMin;
        int yMax;

        if (faceDirection == "left") {
            xMin = this.x - 1;
            xMax = this.x - 16;
            yMin = this.y;
            yMax = this.y + 15;
        } else {
            xMin = this.x + 16;
            xMax = this.x + 31;
            yMin = this.y;
            yMax = this.y + 15;
        }

        GameObject tempObject;
        for (int i = 0; i < handler.objects.size(); i++) {
            tempObject = handler.objects.get(i);
            if (tempObject.inCollisionBox(xMin, yMin) || tempObject.inCollisionBox(xMin, yMax) || 
            tempObject.inCollisionBox(xMax, yMin) || tempObject.inCollisionBox(xMax, yMax)) {
                tempObject.gotHit(25);
            }
        }
    }

    private void setPlayerVelocities() {
        if (input.verticalMovement == 1) {
            velocityY = playerSpeed;
        } else if (input.verticalMovement == 2) {
            velocityY = -playerSpeed;
        } else {
            velocityY = 0;
        }

        if (input.horizontalMovement == 2) {
            velocityX = playerSpeed;
        } else if (input.horizontalMovement == 1) {
            velocityX = -playerSpeed;
        } else {
            velocityX = 0;
        }
    }

    @Override
    public void stopSounds() {
        attackSwingSound.stop();
        walkSound.stop();
    }  
}