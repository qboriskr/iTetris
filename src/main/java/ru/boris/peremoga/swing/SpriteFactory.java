package ru.boris.peremoga.swing;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteFactory {


    public void setSpriteScale(int spriteScale) {

        this.spriteScale = spriteScale;
        this.sprites = spriteSets.get(spriteScale - 1);
    }

    private int spriteScale = 1;

    public static final int SCALES = 4;

    public final int getSpriteSize() {
        switch (spriteScale) {
            case 1:
                return 64;
            case 2:
                return 32;
            case 3:
                return 16;
            default:
                return 8;
        }
    }

    public final int SRC_SPRITE_SIZE = 64;

    public BufferedImage[] sprites;
    private List<BufferedImage[]> spriteSets = new ArrayList<>();

    private int spritesCount;

    public BufferedImage getSprite(int spriteNumber) {
        if (spriteNumber > spritesCount) {
            //syslog("Sprite number " + spriteNumber + " is out of bounds (" + spritesCount + ")");
            return getSprite(0);
        }
        return sprites[spriteNumber];
    }

    public void init() {
        String fn = "sprites.png";
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(fn));
        int tilesByY = sheet.getHeight() / SRC_SPRITE_SIZE;
        int tilesByX = sheet.getWidth() / SRC_SPRITE_SIZE;
        spritesCount = tilesByY * tilesByX;

        for (int i = 0; i < SCALES; i++) spriteSets.add(new BufferedImage[spritesCount]);

        // while there are a lot of unused tiles, we skip them
        spritesCount = 256;

        int scale = 1;
        for (BufferedImage[] ss : spriteSets) {
            setSpriteScale(scale++);

            int pos = 0;
            for (int ts = 1; ts <= SCALES; ts++) {
                for (int y = 0; y < tilesByY; y++) {
                    for (int x = 0; x < tilesByX; x++) {
                        BufferedImage img = sheet.crop(x * SRC_SPRITE_SIZE, y * SRC_SPRITE_SIZE, SRC_SPRITE_SIZE, SRC_SPRITE_SIZE);
                        ss[pos] = Scalr.resize(img, getSpriteSize());
                        pos++;
                        if (pos >= spritesCount) break;
                    }
                }
            }
        }
    }
}
