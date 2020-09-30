import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    BufferedImage image;
    public SpriteSheet(String path) {
        image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch(IOException e) {
            e.printStackTrace();
        }
        if(image == null) return;

    }
}