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
    private String sheetType;
    public Animation(String path, String sheetType) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sheetType = sheetType;
        setAnims();
        currentAnimation = "";
        setAnimation("idleRight");
    }

    public void iterateFrame(boolean attackTriggered) {
        if(System.currentTimeMillis() - animTime >= animSpeed) {
                animTime = System.currentTimeMillis();
                currentFrame++;       
            }
            if(currentFrame == frames) {
                if((currentAnimation == "attackRight" || currentAnimation == "attackLeft") && !attackTriggered)
                {
                    currentFrame--;
                } else {
                    currentFrame = 0;
                }
            }
    }
    public BufferedImage getFrame() {
        return animation.get(currentFrame);
    }

    public void setAnimation(String anim) {
        if(anim != currentAnimation)
        {
            currentAnimation = anim;
            animTime = System.currentTimeMillis();
            frames = animList.get(anim).size();
            animSpeed = 100;
            animation = animList.get(anim);
            currentFrame = 0;
        }
        else {
            return;
        }
    }

    private void setAnims() {
        if(sheetType == "player") {
            for(int i = 0; i < 5; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("idleRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 5; i < 13; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("walkRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 13; i < 19; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("gethitRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 19; i < 22; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("attackRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 22; i < 26; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("deadRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 26; i < 28; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("blockRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 28; i < 33; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("idleLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 33; i < 41; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("walkLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 41; i < 47; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("gethitLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 47; i < 50; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("attackLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 50; i < 54; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("deadLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 54; i < 56; i++) {
                animation.add(image.getSubimage((i % 7) * 64, (i / 7) * 64, 64, 64));
            }
            animList.put("blockLeft", animation);

        } else if(sheetType == "enemy") {

            for(int i = 0; i < 8; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("idleRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 8; i < 15; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("walkRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 16; i < 24; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("idleLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 24; i < 31; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("walkLeft", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 32; i < 39; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("attackRight", animation);

            animation = new ArrayList<BufferedImage>();
            for(int i = 40; i < 47; i++) {
                animation.add(image.getSubimage((i % 8) * 32, (i / 8) * 32, 32, 32));
            }
            animList.put("attackLeft", animation);
        } else { 
            System.out.println("Unknows sheet type");
        }  
    }
    public int getFrames() {
        return frames;
    }

}
