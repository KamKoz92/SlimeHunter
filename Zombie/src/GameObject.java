import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y;
    protected int velX, velY;
    protected Level level;
    protected int objectSize;
    protected Handler handler;
    protected boolean solid;
    public GameObject(int x, int y, Level level, Handler handler, boolean solid) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.handler = handler;
        this.solid = solid;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void move(int velX, int velY) {
        if (velX != 0 && velY != 0) {
            move(velX, 0);
            move(0, velY);
            return;
        }

        if (!hasCollided(velX, velY)) {
            x += velX;
            y += velY;
        }
    }

    public boolean hasCollided(int velX, int velY) {
        int xMin = this.x + velX;
        int xMax = this.x + objectSize + velX;
        int yMin = this.y + velY;
        int yMax = this.y + objectSize + velY;

        if(level.getTile(xMin, yMin).isSolid() || level.getTile(xMin, yMax).isSolid() || level.getTile(xMax, yMin).isSolid() || level.getTile(xMax, yMax).isSolid()) {
            System.out.println("hit");
            return true;
        }

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
                    System.out.println("hit");
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
}
