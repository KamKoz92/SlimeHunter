import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Map {
    
    public Map(String path) {
        try {
            FileReader reader = new FileReader(path);
            int i;
            while((i=reader.read()) != -1) {
                System.out.println((char) i);
            }
            reader.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
}
