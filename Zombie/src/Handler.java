import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Handler {

    public List<GameObject> objects = Collections.synchronizedList(new LinkedList<GameObject>());

    // public Handler(float x, float y, ID id) {
    //     super(x, y, id);
        
    // }

    public void tick() {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).tick();
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }
    
    public void addObject(GameObject object) { 
        objects.add(object);
    }
    public void removeObject(GameObject object) {
        objects.remove(object);
    }
}
