import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Graphics;

public class Level {
    private String[] str;
    private List<Integer> intmap = new ArrayList<Integer>();

    public Level(String path) {
        try {
            URL url = getClass().getResource(path);
            File file = new File(url.getPath());
            Scanner sc = new Scanner(file); 
            while(sc.hasNextLine()) {
                str = sc.nextLine().split(",");
                for(int i = 0; i < str.length; i++) {
                    intmap.add(Integer.parseInt(str[i]));
                }
            }
            sc.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
    public void tick() {

    }
    public void render(Graphics g) {
    
    }
}















    // for(int i = 0; i < map.size(); i++) {
        //     if(map.get(i) == 1) {
        //         g.setColor(Color.green);
        //     }
        //     else {
        //         g.setColor(Color.blue);
        //     }
        //     g.fillRect((i % 16) * 32, (i / 16) * 32, 32, 32);
        // }
        // g.setColor(Color.green);
        // g.fillRect(56, 32, 32, 32);