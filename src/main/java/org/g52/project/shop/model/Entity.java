package org.g52.project.shop.model;

import org.g52.project.Inventory;

abstract public class Entity {
    protected int coins;
    Inventory inventory = new Inventory();

    public Entity(){

    }

    public Entity(int coins, Inventory inventory){
        this.coins = coins;
        this.inventory = inventory;
    }

    public int getCoins() {
        return coins;
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
