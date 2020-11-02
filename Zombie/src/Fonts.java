import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;


public class Fonts {
    public Font font_12;
    public Font font_48;
    public Fonts(){
        try {
            File fontFile = new File("res/Minecraft.ttf");
            font_12 = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(18f);
            font_48 = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font_12);
            ge.registerFont(font_48);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
