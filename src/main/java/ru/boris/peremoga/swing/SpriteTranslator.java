package ru.boris.peremoga.swing;


import ru.boris.peremoga.hud.ObjectId;

import java.awt.image.BufferedImage;

/**
 * Created by boris on 08.04.2015.
 */
public class SpriteTranslator {
    private SpriteFactory s;

    public SpriteTranslator(SpriteFactory spriteFactory) {
        s = spriteFactory;
    }

    public BufferedImage convert(ObjectId sprite) {
        try {
            switch (sprite) {
                case FOG:
                    return s.getSprite(0);
                case HUMAN_CIVILIAN:
                    return s.getSprite(184);
                case HUMAN_SOLDIER:
                    return s.getSprite(10);
                case ALIEN:
                    return s.getSprite(111); //35
                case WATER:
                    return s.getSprite(25);
                case WALL:
                    return s.getSprite(31);
                case FLOOR:
                    return s.getSprite(58);
                case TREE:
                    return s.getSprite(134); //153 - фиолетовое дерево
                case BULLET:
                    return s.getSprite(225);
                case ALIEN_CORPSE:
                    return s.getSprite(40);
                case HUMAN_CORPSE:
                    return s.getSprite(15);
                case CURSOR:
                    return s.getSprite(200);
                case EMPTY:
                    return s.getSprite(0);
                case GRASS:
                    return s.getSprite(6);
                case DOOR:
                    return s.getSprite(54);
                default:
                    return s.getSprite(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
