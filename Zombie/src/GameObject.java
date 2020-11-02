import java.awt.Graphics;

public abstract class GameObject {

    protected int x, y;
    protected int velocityX, velocityY;
    protected Level level;
    protected int objectSize;
    protected Handler handler;
    protected boolean isSolid;
    protected double maxHealth;
    protected double currentHealth;
    protected int path;
    protected Pathfinder pathfinder;
    
    public GameObject(int x, int y, Level level, Handler handler, boolean isSolid, double health) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.handler = handler;
        this.isSolid = isSolid;
        this.maxHealth = health;
        this.currentHealth = health;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void stopSounds();

    public void moveObject(int velocityX, int velocityY) {
        if(this.x > 0 && this.x < level.getWidth() * 32 && this.y > 0 && this.y < level.getHeight() * 32) {
            if (velocityX != 0 && velocityY != 0) {
                moveObject(velocityX, 0);
                moveObject(0, velocityY);
                return;
            }

            if (velocityX > 0) {
                if (!hasCollided(velocityX, 0)) {
                    x += velocityX;
                } else if (!hasCollided(velocityX - 1, 0)) {
                    x += velocityX - 1;
                } else if (!hasCollided(velocityX - 2, 0)) {
                    x += velocityX - 2;
                }
            } else {
                if (!hasCollided(velocityX, 0)) {
                    x += velocityX;
                } else if (!hasCollided(velocityX + 1, 0)) {
                    x += velocityX + 1;
                } else if (!hasCollided(velocityX + 2, 0)) {
                    x += velocityX + 2;
                }
            }

            if (velocityY > 0) {
                if (!hasCollided(0, velocityY)) {
                    y += velocityY;
                } else if (!hasCollided(0, velocityY - 1)) {
                    y += velocityY - 1;
                } else if (!hasCollided(0, velocityY - 2)) {
                    y += velocityY - 2;
                }
            } else {
                if (!hasCollided(0, velocityY)) {
                    y += velocityY;
                } else if (!hasCollided(0, velocityY + 1)) {
                    y += velocityY + 1;
                } else if (!hasCollided(0, velocityY + 2)) {
                    y += velocityY + 2;
                }
            }
        }
    }
    public boolean hasCollided(int velocityX, int velocityY) {
        int xMin = this.x + velocityX;
        int xMax = this.x + objectSize + velocityX - 1;
        int yMin = this.y + velocityY;
        int yMax = this.y + objectSize + velocityY - 1;

        //collsion with tiles
        if(level.getTile(xMin, yMin).isSolid() || level.getTile(xMin, yMax).isSolid() || level.getTile(xMax, yMin).isSolid() || level.getTile(xMax, yMax).isSolid()) {
            return true;
        }

        //collsion with player
        if(this != handler.player) {
            if(handler.player.inCollisionBox(xMin, yMin) || handler.player.inCollisionBox(xMin, yMax) ||
            handler.player.inCollisionBox(xMax, yMin) || handler.player.inCollisionBox(xMax, yMax)) {
                return true;
            }
        }

        //collision with objects
        GameObject tempObject;
        for(int i = 0; i < handler.objects.size(); i++) {
            tempObject = handler.objects.get(i);
            if(tempObject.equals(this)) {
                continue;
            }
            if(tempObject.isSolid) {
                if(tempObject.inCollisionBox(xMin, yMin) || tempObject.inCollisionBox(xMin, yMax) ||
                tempObject.inCollisionBox(xMax, yMin) || tempObject.inCollisionBox(xMax, yMax)) {
                    return true;
                }
            } 
        }
        return false;
    }

    public boolean inCollisionBox(int x, int y) {
        if((this.x <= x && x < this.x + this.objectSize) && (this.y <= y && y < this.y + this.objectSize)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSolid() {
        return isSolid;
    }

	public void gotHit(int i) {
        if(this.currentHealth < i) {
            this.currentHealth = 0;
        }
        else {
            this.currentHealth -= i;
        }
	}
}
