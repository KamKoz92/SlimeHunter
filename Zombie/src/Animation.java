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
    private KeyInput input;
    public Animation(String path, KeyInput input) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.input = input;
        setAnims();
        currentAnimation = "";
        setAnimation("idler");
    }

    public BufferedImage getFrame() {
        if(System.currentTimeMillis() - animTime >= animSpeed) {
            animTime = System.currentTimeMillis();
            currentFrame++;       
        }
        if(currentFrame == frames) {
            if(currentAnimation == "attackl" && !input.space)
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
        animList.put("idler", animation);
        animation = new ArrayList<BufferedImage>();
        
        for(int i = 5; i < 13; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("walkr", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 13; i < 19; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("gethitr", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 19; i < 22; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("attackr", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 22; i < 26; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("deadr", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 26; i < 28; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("blockr", animation);
        animation = new ArrayList<BufferedImage>();
    


        for(int i = 28; i < 33; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("idlel", animation);
        animation = new ArrayList<BufferedImage>();
        
        for(int i = 33; i < 41; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("walkl", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 41; i < 47; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("gethitl", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 47; i < 50; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("attackl", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 50; i < 54; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("deadl", animation);
        animation = new ArrayList<BufferedImage>();

        for(int i = 54; i < 56; i++) {
            animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
        }
        animList.put("blockl", animation);
        animation = new ArrayList<BufferedImage>();
    }
}
