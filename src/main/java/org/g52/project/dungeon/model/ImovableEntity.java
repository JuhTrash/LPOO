package org.g52.project.dungeon.model;

import org.g52.project.Position;
import org.g52.project.dungeon.model.items.Item;

public abstract class ImovableEntity extends Entity{
    public ImovableEntity(int x, int y) {
        super(x, y);
    }

    public ImovableEntity(Dungeon dungeon) {
        super(dungeon);
    }

    public ImovableEntity(){}

    public boolean validPosition(Position position, Dungeon dungeon){
        if (!super.validPosition(position, dungeon)) return false;
        for ( Item item1: dungeon.getItems()){
            if (item1.getPosition().equals(position)) return false;
        }
        if (dungeon.getExit()!= null) {
            if (dungeon.getExit().getPosition().equals(position)) return false;
        }
        return true;
    }
}
