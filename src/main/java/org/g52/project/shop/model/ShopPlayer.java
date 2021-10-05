package org.g52.project.shop.model;

import org.g52.project.Inventory;
import org.g52.project.dungeon.model.items.Consumable;


public class ShopPlayer extends Entity{
    public ShopPlayer(int coins, Inventory inventory) {
        super(coins, inventory);
    }

    public String buy(ShopShopkeeper shopkeeper, Consumable consumable) {
        if (shopkeeper.getInventory().getAmountInInventory(consumable) == 0){
            return "The shopkeeper doesn't have that item!\n";
        }
        else if (this.coins >= consumable.getCost()){
            this.inventory.addAmount(consumable,1);
            this.coins -= consumable.getCost();

            Inventory i1 = shopkeeper.getInventory();
            i1.removeAmount(consumable,1);
            shopkeeper.setInventory(i1);
            shopkeeper.setCoins(shopkeeper.getCoins() + consumable.getCost());

            return "Bought 1 " + consumable.getType() + " for " + consumable.getCost() + " coins.\n";
        }
        else return "You don't have enough coins for that item!\n";
    }

    public String sell(ShopShopkeeper shopkeeper, Consumable consumable) {
        if (this.inventory.getAmountInInventory(consumable) == 0) {
            return "You don't have that item!\n";
        } else if (shopkeeper.getCoins() >= consumable.getCost()) {
            Inventory i1 = shopkeeper.getInventory();
            i1.addAmount(consumable,1);
            shopkeeper.setInventory(i1);
            shopkeeper.setCoins(shopkeeper.getCoins() - consumable.getCost());

            this.inventory.removeAmount(consumable,1);
            this.coins += consumable.getCost();

            return "Sold 1 " + consumable.getType() + " for " + consumable.getCost() + " coins.\n";
        } else return "The shopkeeper doesn't have enough coins for that item!\n";
    }
}
