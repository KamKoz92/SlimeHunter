import java.util.ArrayList;
import java.util.List;

public class Spawner {
    
    private int spawnerTileNumber;
    private int maxMobs;
    private List<Enemy> spawnerMobs = new ArrayList<Enemy>();
    private long respawnTimeInMsec;
    private Camera camera;
    private Level level;
    private int x,y;

    public Spawner(int spawnerTileNumber, Handler handler, Level level, Camera camera) {
        this.spawnerTileNumber = spawnerTileNumber;
        maxMobs = 1;
        respawnTimeInMsec = 8000; 
        x = (spawnerTileNumber % (35)) * 32;
        y = (spawnerTileNumber / (35)) * 32;
        this.camera = camera;
        this.level = level;
        for(int i = 0; i < maxMobs; i++) {
            Enemy tempEnemy = new Enemy(x, y, camera, level, handler, true, "res/slime.png");
            handler.addObject(tempEnemy);
            spawnerMobs.add(tempEnemy);
        }
    }
    
    public void tick() {
        for(int i = 0; i < maxMobs; i++) {
            if(spawnerMobs.get(i).isDead == true) {
                if(spawnerMobs.get(i).deathTime == 0) {
                    spawnerMobs.get(i).deathTime = System.currentTimeMillis();
                }
                if(spawnerOutOfCamera()) {
                    if(System.currentTimeMillis() - spawnerMobs.get(i).deathTime > respawnTimeInMsec) {
                        spawnerMobs.get(i).isDead = false;
                        spawnerMobs.get(i).x = (spawnerTileNumber % (35)) * 32;
                        spawnerMobs.get(i).y = (spawnerTileNumber / (35)) * 32;
                        spawnerMobs.get(i).currentHealth = 100;
                        spawnerMobs.get(i).deathTime = 0;
                    }
                }
            }
        }
    }

    public boolean spawnerOutOfCamera() {
        if(x + level.tileSize() < camera.getX() || y + level.tileSize() < camera.getY() || x > camera.getX() + camera.getW() || y > camera.getY() + camera.getH()) {
            return true;
        } 
        return false;
    }

    public void newGame() {
        for(int i = 0; i < spawnerMobs.size(); i++) {
            Enemy tempEnemy = spawnerMobs.get(i);
            tempEnemy.currentHealth = tempEnemy.maxHealth;
            tempEnemy.isDead = false;
            tempEnemy.x = this.x;
            tempEnemy.y = this.y;
        }
    }
}

