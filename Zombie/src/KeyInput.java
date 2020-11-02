import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean directionKeys[] = new boolean[4];
    private int keyCode;
    
    public int verticalMovement = 0;
    public int horizontalMovement = 0;
    public boolean spaceKey = false;

    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            directionKeys[0] = true;
            horizontalMovement = 2;
        } else if (keyCode == KeyEvent.VK_A) {
            directionKeys[1] = true;
            horizontalMovement = 1;
        } else if (keyCode == KeyEvent.VK_S) {
            directionKeys[2] = true;
            verticalMovement = 1;
        } else if (keyCode == KeyEvent.VK_W) {
            directionKeys[3] = true;
            verticalMovement = 2;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            spaceKey = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            directionKeys[0] = false;
            if (directionKeys[1]) {
                horizontalMovement = 1;
            } else {
                horizontalMovement = 0;
            }
        } else if (keyCode == KeyEvent.VK_A) {
            directionKeys[1] = false;
            if (directionKeys[0]) {
                horizontalMovement = 2;
            } else {
                horizontalMovement = 0;
            }
        } else if (keyCode == KeyEvent.VK_S) {
            directionKeys[2] = false;
            if (directionKeys[3]) {
                verticalMovement = 2;
            } else {
                verticalMovement = 0;
            }
        } else if (keyCode == KeyEvent.VK_W) {
            directionKeys[3] = false;
            if (directionKeys[2]) {
                verticalMovement = 1;
            } else {
                verticalMovement = 0;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            spaceKey = false;
        }
    }
}
