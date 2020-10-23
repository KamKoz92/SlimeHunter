
import java.awt.Graphics;

public class Player extends GameObject {

    private KeyInput input;
    private long attackTime;
    private Camera camera;
    private int speed;
    private boolean activeAttack;
    private Animation anim;
    private String direction;

    public Player(int x, int y, KeyInput input, Camera camera, Level level, Handler handler, boolean solid,
            String path) {
        super(x, y, level, handler, solid, 100);
        this.input = input;
        this.camera = camera;
        this.objectSize = 16;
        speed = 2;
        anim = new Animation(path, "player");
        attackTime = 0;
        activeAttack = false;
        direction = "right";
    }

    @Override
    public void tick() {

        if (input.space && !activeAttack) {
            activeAttack = true;
            checkAtkArea();
            if (direction == "left") {
                anim.setAnimation("attackl");
            } else if (direction == "right") {
                anim.setAnimation("attackr");
            }
            attackTime = System.currentTimeMillis();
        } else if (activeAttack) {
            if (System.currentTimeMillis() - attackTime > (anim.getFrames() * 100)) {
                activeAttack = false;
            }
        } else {
            moveDir();
            if (velX != 0 || velY != 0) {
                move(velX, velY);
                if (velX < 0) {
                    anim.setAnimation("walkl");
                    direction = "left";
                } else if (velX > 0) {
                    anim.setAnimation("walkr");
                    direction = "right";
                } else {
                    if(direction == "right") {
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
        anim.iterateFrame(input.space);
    }

    private void checkAtkArea() {
        int xMin;
        int xMax;
        int yMin;
        int yMax;

        if (direction == "left") {
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

        GameObject tempO;
        for (int i = 0; i < handler.objects.size(); i++) {
            tempO = handler.objects.get(i);
            if (tempO.inCollisionBox(xMin, yMin) || tempO.inCollisionBox(xMin, yMax) || tempO.inCollisionBox(xMax, yMin)
                    || tempO.inCollisionBox(xMax, yMax)) {
                tempO.gotHit(25);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (direction == "left") {
            g.drawImage(anim.getFrame(), x - camera.getX() - 25, y - camera.getY() - 32, null);
        } else {
            g.drawImage(anim.getFrame(), x - camera.getX() - 23, y - camera.getY() - 32, null);
        }
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