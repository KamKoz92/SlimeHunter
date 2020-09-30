import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
    private Handler handler;
    private Camera camera;
    private Level level;
    public MouseInput(Handler handler, Camera camera, Level level) {
        this.camera = camera;
        this.handler = handler;
        this.level = level;
    }
    
    public void mousePressed(MouseEvent e) {
        GameObject tGameObject = handler.objects.get(0);
        int mx = e.getX();
        int my = e.getY();
        int angle = (int) Math.atan2(my - tGameObject.y-16+camera.getY(), mx - tGameObject.x-16 + camera.getX());
    
        //handler.addObject(new Bullet(tGameObject.x+16, tGameObject.y+16, angle, handler, level, false));
    }
    
    
}
