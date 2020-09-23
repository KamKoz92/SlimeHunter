import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;

public class Level {
    private String[] str;
    //private List<Integer> intmap = new ArrayList<Integer>();
    private Camera camera;
    private List<Tile> tileMap = new ArrayList<Tile>();
    private int tileSize;
    private int width, height;
    public Level(String path, Camera camera) {
        this.camera = camera;
        this.tileSize = 32;
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file); 
            int y = 0;
            while(sc.hasNextLine()) {
                str = sc.nextLine().split(",");
                for(int x = 0; x < str.length; x++) {
                    tileMap.add(new Tile(x * tileSize, y * tileSize, Integer.parseInt(str[x])));
                    //intmap.add(Integer.parseInt(str[x]));
                }
                y++;
            }
            sc.close();
            this.width = str.length * tileSize;
            this.height = y * tileSize;
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
    public void tick() {
        for(int i = 0; i < tileMap.size(); i++) {
            if(tileMap.get(i).getX() == 0)
                System.out.println();
            System.out.print("(" + tileMap.get(i).getX()+", " + tileMap.get(i).getY() + ")");
        }
    }
    public void render(Graphics g) {
        for(int i = 0; i < tileMap.size(); i++) {
             if(!outOfCamera(tileMap.get(i))) {
                if(tileMap.get(i).getType() == 1) {
                    g.setColor(Color.green);
                }
                else {
                    g.setColor(Color.blue);
                }
                g.fillRect((tileMap.get(i).getX()) - camera.getX(), (tileMap.get(i).getY()) - camera.getY(), tileSize, tileSize);
             }
        }
    }

    public boolean outOfCamera(Tile tile) {
        if(tile.getX() + tileSize < camera.getX() || tile.getY() + tileSize < camera.getY() || tile.getX() > camera.getX() + camera.getW() || tile.getY() > camera.getY() + camera.getH()) {
            return true;
        }
        return false;
    }

    public Tile getTile(int x, int y) {
        x = x % tileSize;
        y = y / tileSize; 
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }    
        return tileMap.get(x+y*32);
    }
}















    