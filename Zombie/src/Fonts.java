import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;


public class Fonts {
    public Font font2_12;
    public Font font2_48;
    public Fonts(){
        //create the font
        try {
            //create the font to use. Specify the size!
            File fontTemplate2 = new File("res/Minecraft.ttf");
            font2_12 = Font.createFont(Font.TRUETYPE_FONT, fontTemplate2).deriveFont(18f);
            font2_48 = Font.createFont(Font.TRUETYPE_FONT, fontTemplate2).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font2_12);
            ge.registerFont(font2_48);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
