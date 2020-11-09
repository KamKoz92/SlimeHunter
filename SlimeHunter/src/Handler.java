import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Handler {

    public List<GameObject> objects = Collections.synchronizedList(new LinkedList<GameObject>());
    public Player player;
    public int gameScore = 0;
    private boolean isGameOver = false;


    public void tick() {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).tick();
        }
        player.tick();
    }

    public void render(Graphics g) {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
        player.render(g);
    }
    
    public void addObject(GameObject object) { 
        objects.add(object);
    }
    public void removeObject(GameObject object) {
        objects.remove(object);
    }

	public void newPlayer(Player player) {
        this.player = player;    
	}

	public void newGame() {
        player.currentHealth = player.maxHealth;
        gameScore = 0;
        player.x = 32;
        player.y = 32;
        isGameOver = false;
	}

	public boolean isEndGame() {
        if(player.currentHealth <= 0 && !isGameOver) {
            isGameOver = true;
            player.stopSounds();
            for(int i = 0; i < objects.size(); i++) {
                objects.get(i).stopSounds();
            }
            return true;
        } else {
            return false;
        }
	}
}
