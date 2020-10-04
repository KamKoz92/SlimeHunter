import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animation {
    
    private Map<String,List<BufferedImage>> animList = new HashMap<String,List<BufferedImage>>();
    private List<BufferedImage> animation = new ArrayList<BufferedImage>();
    private BufferedImage image;
    private long animSpeed, animTime;
    private int currentFrame, frames;
    private String currentAnimation;

    public Animation(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAnims();
        currentAnimation = "";
        setAnimation("idle");
    }

    public BufferedImage getFrame() {
        if(System.currentTimeMillis() - animTime >= animSpeed) {
            animTime = System.currentTimeMillis();
            currentFrame++;       
        }
        if(currentFrame == frames) {
            if(currentAnimation == "attack")
            {
                currentFrame--;
            }
            else {
                currentFrame = 0;
            }
        }

        return animation.get(currentFrame);
    }

    public void setAnimation(String anim) {
        if(anim != currentAnimation)
        {
            currentAnimation = anim;
            animTime = System.currentTimeMillis();
            frames = animList.get(anim).size();
            animSpeed = 100;//;
            animation = animList.get(anim);
            currentFrame = 0;
        }
        else {
            return;
        }
    }

    private void setAnims() {
        for(int i = 0; i < 5; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("idle", animation);
        animation = new ArrayList<BufferedImage>();
        
        for(int i = 5; i < 13; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("walk", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 13; i < 19; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("gethit", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 19; i < 23; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("attack", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 23; i < 26; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("dead", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 26; i < 27; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("block", animation);
        animation = new ArrayList<BufferedImage>();
    }

}
