import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Level {
    private String[] str;
    private Camera camera;
    private List<Tile> tileMap = new ArrayList<Tile>();
    private int tileSize;
    private int width, height;
    private List<Spawner> spawners = new ArrayList<Spawner>();
    public Level(String path, SpriteSheet sheet, Camera camera, Handler handler) {
        this.camera = camera;
        this.tileSize = 32;
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file); 
            int y = 0;
            str = sc.nextLine().split(",");
            for(int i = 0; i < str.length; i++) {
                spawners.add(new Spawner(Integer.parseInt(str[i]), handler, this, camera));
            }
            while(sc.hasNextLine()) {
                str = sc.nextLine().split(",");
                for(int x = 0; x < str.length; x++) {
                    tileMap.add(new Tile(x * tileSize, y * tileSize, Integer.parseInt(str[x]), sheet));
                }
                y++;
            }
            sc.close();
            this.width = str.length * tileSize;
            this.height = y * tileSize;
            this.camera.mapHeight = y + 1;
            this.camera.mapWidth = str.length;
            this.camera.tileSize = tileSize;
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
    public void tick() {
        for(int i = 0; i < spawners.size(); i++) {
            spawners.get(i).tick();
        }
    }

    public void render(Graphics g) {
        Tile tempTile;
        for(int i = 0; i < tileMap.size(); i++) {
            tempTile = tileMap.get(i);
            if(!outOfCamera(tempTile)) {
                g.drawImage(tempTile.image, tempTile.getX() - camera.getX(), tempTile.getY() - camera.getY(), null);
            }
        }
        for(int i = 0; i < spawners.size(); i++) {
            int x = (spawners.get(i).getTileNumber() % (width/tileSize)) * tileSize;
            int y = (spawners.get(i).getTileNumber() / (width/tileSize)) * tileSize;
            g.drawRect(x - camera.getX(), y - camera.getY(), 32, 32);
            
        }
    }

    public boolean outOfCamera(Tile tile) {
        if(tile.getX() + tileSize < camera.getX() || tile.getY() + tileSize < camera.getY() || tile.getX() > camera.getX() + camera.getW() || tile.getY() > camera.getY() + camera.getH()) {
            return true;
        }
        return false;
    }

    public Tile getTile(int x, int y) {
        
        if(x < 0 || x >= width || y < 0 || y >= height) {
            System.out.println("null pointer returned" + x + ' ' + y + " " + width + " " + height);
            return null;  
        }    
        else {
            x = x / tileSize;
            y = (y / tileSize) * (width / tileSize); 
            return tileMap.get(x + y);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}















    