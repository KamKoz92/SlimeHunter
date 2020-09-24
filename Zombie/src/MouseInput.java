import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
    private Handler handler;
    private GameObject tGameObject = null;
    private Camera camera;
    private Level level;
    public MouseInput(Handler handler, Camera camera, Level level) {
        this.camera = camera;
        this.handler = handler;
        this.level = level;
    }

    public void findPlayer() {
        for(GameObject oGameObject: handler.objects) {
            if(oGameObject.getId() == ID.Player) {
                tGameObject = oGameObject;
                return;
            }
        }
    }
    
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        int angle = (int) Math.atan2(my - tGameObject.y-16+camera.getY(), mx - tGameObject.x-16 + camera.getX());
    
        handler.addObject(new Bullet(tGameObject.x+16, tGameObject.y+16,ID.Bullet, angle, handler, level));
    }
    
    
}
