import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Level {

    private Camera camera;
    private List<Tile> tileMap = new ArrayList<Tile>();
    private int tileSize;
    private int mapWidth, mapHeight;
    private List<Spawner> spawners = new ArrayList<Spawner>();
    
    public Level(String path, SpriteSheet sheet, Camera camera, Handler handler) {
        this.camera = camera;
        this.tileSize = 32;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file); 
            int y = 0;
            String[] string;
            string = scanner.nextLine().split(",");

            for(int i = 0; i < string.length; i++) {
                spawners.add(new Spawner(Integer.parseInt(string[i]), handler, this, camera));
            }

            while(scanner.hasNextLine()) {
                string = scanner.nextLine().split(",");
                for(int x = 0; x < string.length; x++) {
                    tileMap.add(new Tile(x * tileSize, y * tileSize, Integer.parseInt(string[x]), sheet));
                }
                y++;
            }

            scanner.close();
            this.mapWidth = string.length * tileSize;
            this.mapHeight = y * tileSize;
            this.camera.mapHeight = y + 1;
            this.camera.mapWidth = string.length;
            this.camera.tileSize = tileSize;
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }

    public void render(Graphics g) {
        Tile tempTile;
        for(int i = 0; i < tileMap.size(); i++) {
            tempTile = tileMap.get(i);
            if(!outOfCamera(tempTile)) {
                g.drawImage(tempTile.tileImage, tempTile.getX() - camera.getX(), tempTile.getY() - camera.getY(), null);
            }
        }
    }

    public void tick() {
        for(int i = 0; i < spawners.size(); i++) {
            spawners.get(i).tick();
        }
    }

    public boolean outOfCamera(Tile tile) {
        if(tile.getX() + tileSize < camera.getX() || tile.getY() + tileSize < camera.getY() || 
        tile.getX() > camera.getX() + camera.getW() || tile.getY() > camera.getY() + camera.getH()) {
            return true;
        }
        return false;
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || x >= mapWidth || y < 0 || y >= mapHeight) {
            System.out.println("null pointer returned" + x + ' ' + y + " " + mapWidth + " " + mapHeight);
            return null;  
        }    
        else {
            x = x / tileSize;
            y = (y / tileSize) * (mapWidth / tileSize); 
            return tileMap.get(x + y);
        }
    }

    public int getWidth() {
        return mapWidth;
    }

    public int getHeight() {
        return mapHeight;
    }

	public int tileSize() {
		return tileSize;
    }

    public void newGame() {
        for(int i = 0; i < spawners.size(); i++) {
            spawners.get(i).newGame();
        }
    }
}















    