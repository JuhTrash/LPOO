package org.g52.project.dungeon.model;

import org.g52.project.Elements;

import java.util.Random;

public class DungeonMonster extends MovableEntity {
    public DungeonMonster(int x, int y, Elements element) {
        super(x, y, element);
    }
    public DungeonMonster(Dungeon dungeon){
        super(dungeon);
        Random rand = new Random();
        int i = rand.nextInt(6) + 1;

        this.element = Elements.NULL.getElement(i);
    }


}
