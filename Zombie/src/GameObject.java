import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y;
    protected int velX, velY;
    protected Level level;
    protected int objectSize;
    protected Handler handler;
    protected boolean solid;
    protected double maxHealth;
    protected double currentHealth;
    protected int path;
    protected Pathfinder pathfinder;
    public GameObject(int x, int y, Level level, Handler handler, boolean solid, double health) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.handler = handler;
        this.solid = solid;
        this.maxHealth = health;
        this.currentHealth = health;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void move(int velX, int velY) {
        if(this.x > 0 && this.x < level.getWidth() * 32 && this.y > 0 && this.y < level.getHeight() * 32) {
            if (velX != 0 && velY != 0) {
                move(velX, 0);
                move(0, velY);
                return;
            }
    
            if(velX > 0) {
                if(!hasCollided(velX, 0)) {
                    x += velX;
                }
                else if(!hasCollided(velX-1, 0)){
                    x += velX-1;
                }
                else if(!hasCollided(velX-2, 0)) {
                    x += velX-2;
                }
            }
            else {
                if(!hasCollided(velX, 0)) {
                    x += velX;
                }
                else if(!hasCollided(velX+1, 0)){
                    x += velX+1;
                }
                else if(!hasCollided(velX+2, 0)) {
                    x += velX+2;
                }
            }
    
            if(velY > 0) {
                if(!hasCollided(0, velY)) {
                    y += velY;
                }
                else if(!hasCollided(0, velY-1)){
                    y += velY-1;
                }
                else if(!hasCollided(0, velY-2)) {
                    y += velY-2;
                }
            }
            else {
                if(!hasCollided(0, velY)) {
                    y += velY;
                }
                else if(!hasCollided(0, velY+1)){
                    y += velY+1;
                }
                else if(!hasCollided(0, velY+2)) {
                    y += velY+2;
                }
            }
        }
    }
    public boolean hasCollided(int velX, int velY) {
        int xMin = this.x + velX;
        int xMax = this.x + objectSize + velX - 1;
        int yMin = this.y + velY;
        int yMax = this.y + objectSize + velY - 1;

        //collsion with tiles
        if(level.getTile(xMin, yMin).isSolid() || level.getTile(xMin, yMax).isSolid() || level.getTile(xMax, yMin).isSolid() || level.getTile(xMax, yMax).isSolid()) {
            return true;
        }

        //collsion with player
        if(this != handler.player) {
            if(handler.player.inCollisionBox(xMin, yMin) || 
                handler.player.inCollisionBox(xMin, yMax) ||
                handler.player.inCollisionBox(xMax, yMin) ||
                handler.player.inCollisionBox(xMax, yMax)) {
                    return true;
                }
        }

        //collision with objects
        GameObject tempO;
        for(int i = 0; i < handler.objects.size(); i++) {
            tempO = handler.objects.get(i);
            if(tempO.equals(this)) {
                continue;
            }
            if(tempO.solid) {
                if(tempO.inCollisionBox(xMin, yMin) || 
                tempO.inCollisionBox(xMin, yMax) ||
                tempO.inCollisionBox(xMax, yMin) ||
                tempO.inCollisionBox(xMax, yMax)) {
                    return true;
                }
            } 
        }
        return false;
    }


    public boolean inCollisionBox(int x, int y) {
        if((this.x <= x && x < this.x + this.objectSize) && 
           (this.y <= y && y < this.y + this.objectSize)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public boolean isSolid() {
        return solid;
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
