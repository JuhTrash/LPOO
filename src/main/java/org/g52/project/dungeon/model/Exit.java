package org.g52.project.dungeon.model;


public class Exit extends ImovableEntity{

    public Exit(int x, int y) {
        super(x, y);
    }

    public Exit(Dungeon dungeon) { super(dungeon); }
}
