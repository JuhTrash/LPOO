package org.g52.project.dungeon.model;

import org.g52.project.Inventory;
import java.util.Random;

public class Shopkeeper extends ImovableEntity{
    Inventory inventory;
    int coins;
    final int itemNum = 6;

    public Shopkeeper(int x, int y, Inventory inventory, int coins) {
        super(x, y);
        this.inventory = inventory;
        this.coins = coins;
    }

    public Shopkeeper(Dungeon dungeon) {
        super(dungeon);
        this.inventory = new Inventory();
        Random random = new Random();
        inventory.initializeInventoryShopkeeper(dungeon);
        coins = random.nextInt(51) + 100;
    }

    public int getCoins() {
        return this.coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
