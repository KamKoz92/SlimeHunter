import java.util.ArrayList;
import java.util.List;

public class Spawner {
    

    private int tileNumber;
    private int maxMobs;
    private List<Enemy> mobs = new ArrayList<Enemy>();
    private long spawnTime;
    private long time;
    private Camera camera;
    private Level level;
    private int x,y;
    public Spawner(int tileNumber, Handler handler, Level level, Camera camera) {
        this.tileNumber = tileNumber;
        maxMobs = 1;
        spawnTime = 8000;
        time = 0;   
        x = (tileNumber % (35)) * 32;
        y = (tileNumber / (35)) * 32;
        this.camera = camera;
        this.level = level;
        for(int i = 0; i < maxMobs; i++) {
            Enemy tempEnemy = new Enemy(x, y, camera, level, handler, true, "res/slime.png");
            handler.addObject(tempEnemy);
            mobs.add(tempEnemy);
        }
    }
    
    public void tick() {
        if(mobs.get(0).dead == true) {
            if(time == 0) {
                time = System.currentTimeMillis();
            }
            if(x + level.tileSize() < camera.getX() || y + level.tileSize() < camera.getY() || x > camera.getX() + camera.getW() || y > camera.getY() + camera.getH()) {
                if(System.currentTimeMillis() - time > spawnTime) {
                    mobs.get(0).dead = false;
                    mobs.get(0).x = (tileNumber % (35)) * 32;
                    mobs.get(0).y = (tileNumber / (35)) * 32;
                    mobs.get(0).currentHealth = 100;
                    time = 0;
                }
            }
        }
    }
    public void newGameSpawn() {
        for(int i = 0; i < mobs.size(); i++) {
            Enemy tempEnemy = mobs.get(i);
            tempEnemy.currentHealth = tempEnemy.maxHealth;
            tempEnemy.dead = false;
            tempEnemy.x = this.x;
            tempEnemy.y = this.y;
        }
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public int getMaxMobs() {
        return maxMobs;
    }

    public void setMaxMobs(int maxMobs) {
        this.maxMobs = maxMobs;
    }
}

