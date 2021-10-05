package org.g52.project.dungeon.model;

public class Wall extends ImovableEntity {
    public Wall(int x, int y) {
        super(x, y);
    }
    Wall(Dungeon dungeon){
        super(dungeon);
    }

}