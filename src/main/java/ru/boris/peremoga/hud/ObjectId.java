package ru.boris.peremoga.hud;

/**
 * Created by boris on 21.03.2015.
 */
public enum ObjectId {
    EMPTY(0),
    GRASS(1),
    FOG(2),
    CURSOR(3),
    HUMAN_CIVILIAN(4),
    HUMAN_SOLDIER(5),
    ALIEN(6),
    BULLET(7),
    ALIEN_CORPSE(8),
    HUMAN_CORPSE(9),
    TREE(10),
    WALL(11),
    FLOOR(12),
    WATER(13),
    DOOR(14);

    private int val;

    ObjectId(int val) {
        this.val = val;
    }

    public static String getCaption(ObjectId value) {
        switch (value) {
            case HUMAN_CIVILIAN:
                return "Civilian";
            case HUMAN_SOLDIER:
                return "Soldier";
            case ALIEN:
                return "Alien";
            case ALIEN_CORPSE:
                return "Corpse";
            case HUMAN_CORPSE:
                return "Corpse";
            case BULLET:
                return "Bullet";
            case TREE:
                return "Tree";
            case GRASS:
                return "Grass";
            case WALL:
                return "Wall";
            case FLOOR:
                return "Floor";
            case FOG:
                return "Fog of war";
            case WATER:
                return "Water";
            default:
                return "";
        }
    }


    public int value() {
        return this.val;
    }

}
