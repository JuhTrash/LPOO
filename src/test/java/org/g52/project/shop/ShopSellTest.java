package org.g52.project.shop;

import org.g52.project.Inventory;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.dungeon.model.items.ElementChanger;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.g52.project.dungeon.model.items.MPPotion;
import org.g52.project.shop.model.ShopPlayer;
import org.g52.project.shop.model.ShopShopkeeper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopSellTest {
    @Test
    void sellItem(){

        Consumable itemToSell = new HealingPotion(0,150);
        Consumable itemToKeep = new MPPotion(0,50);
        Consumable itemInStock = new ElementChanger(1);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemToSell, 2);
        inventory.addAmount(itemToKeep, 1);

        ShopPlayer shopPlayer = new ShopPlayer(100, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemInStock, 5);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(200, inventory2);

        Assertions.assertEquals("Sold 1 " + itemToSell.getType() + " for " + itemToSell.getCost() + " coins.\n",
                                        shopPlayer.sell(shopkeeper, itemToSell));

        Assertions.assertEquals(100 + itemToSell.getCost(), shopPlayer.getCoins());
        Assertions.assertEquals(200 - itemToSell.getCost(), shopkeeper.getCoins());

        Assertions.assertEquals(1, shopkeeper.getInventory().getAmountInInventory(itemToSell));
        Assertions.assertEquals(1, shopPlayer.getInventory().getAmountInInventory(itemToSell));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToKeep));
        Assertions.assertEquals(1, shopPlayer.getInventory().getAmountInInventory(itemToKeep));

        Assertions.assertEquals(5, shopkeeper.getInventory().getAmountInInventory(itemInStock));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemInStock));

    }

    @Test
    void notEnoughCoins(){

        Consumable itemToSell = new HealingPotion(0,150);
        Consumable itemToKeep = new MPPotion(0,50);
        Consumable itemInStock = new ElementChanger(1);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemToSell, 2);
        inventory.addAmount(itemToKeep, 1);

        ShopPlayer shopPlayer = new ShopPlayer(100, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemInStock, 5);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(5, inventory2);

        Assertions.assertEquals("The shopkeeper doesn't have enough coins for that item!\n",
                                        shopPlayer.sell(shopkeeper, itemToSell));

        Assertions.assertEquals(100, shopPlayer.getCoins());
        Assertions.assertEquals(5, shopkeeper.getCoins());

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToSell));
        Assertions.assertEquals(2, shopPlayer.getInventory().getAmountInInventory(itemToSell));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToKeep));
        Assertions.assertEquals(1, shopPlayer.getInventory().getAmountInInventory(itemToKeep));

        Assertions.assertEquals(5, shopkeeper.getInventory().getAmountInInventory(itemInStock));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemInStock));
    }

    @Test
    void playerDoesntHaveItem(){

        Consumable itemToSell = new HealingPotion(0,150);
        Consumable itemToKeep = new MPPotion(0,50);
        Consumable itemInStock = new ElementChanger(1);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemToSell, 0);
        inventory.addAmount(itemToKeep, 1);

        ShopPlayer shopPlayer = new ShopPlayer(100, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemInStock, 5);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(200, inventory2);

        Assertions.assertEquals("You don't have that item!\n",
                                        shopPlayer.sell(shopkeeper, itemToSell));

        Assertions.assertEquals(100, shopPlayer.getCoins());
        Assertions.assertEquals(200, shopkeeper.getCoins());

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToSell));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemToSell));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToKeep));
        Assertions.assertEquals(1, shopPlayer.getInventory().getAmountInInventory(itemToKeep));

        Assertions.assertEquals(5, shopkeeper.getInventory().getAmountInInventory(itemInStock));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemInStock));

    }
}
