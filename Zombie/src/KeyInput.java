import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean keys[] = new boolean[4];
    private int key;
    
    public int verticalMov = 0;
    public int horizontalMov = 0;
    public boolean space = false;
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        if(key == KeyEvent.VK_D) {
            keys[0] = true; 
            horizontalMov = 2;
        } 
        else if(key == KeyEvent.VK_A) {
            keys[1] = true;
            horizontalMov = 1;
        }
        else if(key == KeyEvent.VK_S) {
            keys[2] = true;
            verticalMov = 1;
        }
        else if(key == KeyEvent.VK_W) {
            keys[3] = true;
            verticalMov = 2;
        }
        else if(key == KeyEvent.VK_SPACE) {
            space = true;
        }
        
    }
    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        if(key == KeyEvent.VK_D) {
            keys[0] = false;
            if(keys[1]) {
                horizontalMov = 1;
            }
            else {
                horizontalMov = 0;
            }
        } 
        else if(key == KeyEvent.VK_A) {
            keys[1] = false;
            if(keys[0]) {
                horizontalMov = 2;
            }
            else {
                horizontalMov = 0;
            }
        } 
        else if(key == KeyEvent.VK_S) {
            keys[2] = false;
            if(keys[3]) {
                verticalMov = 2;
            }
            else {
                verticalMov = 0;
            }
        }
        else if(key == KeyEvent.VK_W) {
            keys[3] = false;
            if(keys[2]) {
                verticalMov = 1;
            }
            else {
                verticalMov = 0;
            }
        }
        else if(key == KeyEvent.VK_SPACE) {
            space = false;
        }

    }
    
}
