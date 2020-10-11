import java.util.ArrayList;
import java.util.List;

public class Spawner {
    

    private int tileNumber;
    private int maxMobs;
    private List<Enemy> mobs = new ArrayList<Enemy>();
    private long spawnTime;
    private long time;
    public Spawner(int tileNumber, Handler handler, Level level, Camera camera) {
        this.tileNumber = tileNumber;
        maxMobs = 1;
        spawnTime = 4000;
        time = 0;
        for(int i = 0; i < maxMobs; i++) {
            int x = (tileNumber % (35)) * 32;
            int y = (tileNumber / (35)) * 32;
            Enemy tempEnemy = new Enemy(x, y, camera, level, handler, true);
            handler.addObject(tempEnemy);
            mobs.add(tempEnemy);
        }
    }



    public void tick() {
        if(mobs.get(0).dead == true) {
            if(time == 0) {
                time = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - time > spawnTime) {
                mobs.get(0).dead = false;
                mobs.get(0).x = (tileNumber % (35)) * 32;
                mobs.get(0).y = (tileNumber / (35)) * 32;
                mobs.get(0).currentHealth = 100;
                System.out.println("mob respawned at " + mobs.get(0).x + " " + mobs.get(0).y);
                time = 0;
            }

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

