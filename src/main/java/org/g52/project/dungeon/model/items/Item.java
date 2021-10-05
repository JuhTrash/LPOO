package org.g52.project.dungeon.model.items;

import org.g52.project.dungeon.model.Dungeon;

import org.g52.project.dungeon.model.ImovableEntity;

public abstract class Item extends ImovableEntity {
    Item(int x, int y) {
        super(x, y);
    }
    Item(Dungeon dungeon) {
        super(dungeon);
    }
    Item(){}

    public abstract String getType();
}