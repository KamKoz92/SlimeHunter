public class Camera {
    private int x, y;
    private Handler handler;
    private GameObject tGameObject;

    public Camera(int x, int y, Handler hanler) {
        this.x = x;
        this.y = y;
        this.handler = hanler;
    }

    public void findPlayer() {
        for(int i = 0; i < handler.objects.size(); i++) {
            if(handler.objects.get(i).getId() == ID.Player) {
                tGameObject = handler.objects.get(i);
                return;
            }
        }
    }

    public void tick() {
        if(tGameObject != null) {
            x = (int) tGameObject.x - Game.WIDTH/2;
            y = (int) tGameObject.y - Game.HEIGHT/2;
        } else findPlayer();
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
}
