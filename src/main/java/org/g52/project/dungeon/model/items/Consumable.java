package org.g52.project.dungeon.model.items;

import org.g52.project.Player;
import org.g52.project.dungeon.model.Dungeon;

public abstract class Consumable extends Item{
    protected int cost;

    Consumable(int x, int y) {
        super(x, y);
    }
    Consumable(Dungeon dungeon) {
        super(dungeon);
    }

    Consumable() {}

    public int getCost(){
        return this.cost;
    }
    public abstract String use(Player player);
    public abstract String getType();
}
