package org.g52.project.shop;

import org.g52.project.Inventory;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.g52.project.dungeon.model.items.MPPotion;
import org.g52.project.shop.model.ShopPlayer;
import org.g52.project.shop.model.ShopShopkeeper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ShopBuyTest {
    @Test
    void buyItem(){

        Consumable itemToBuy = new HealingPotion(0,150);
        Consumable itemFound = new MPPotion(0,50);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemFound,3);

        ShopPlayer shopPlayer = new ShopPlayer(100, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemToBuy,2);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(200, inventory2);


        Assertions.assertEquals("Bought 1 " + itemToBuy.getType() + " for " + itemToBuy.getCost() + " coins.\n",
                                        shopPlayer.buy(shopkeeper, itemToBuy));

        Assertions.assertEquals(100 - itemToBuy.getCost(), shopPlayer.getCoins());
        Assertions.assertEquals(200 + itemToBuy.getCost(), shopkeeper.getCoins());

        Assertions.assertEquals(1, shopkeeper.getInventory().getAmountInInventory(itemToBuy));
        Assertions.assertEquals(1, shopPlayer.getInventory().getAmountInInventory(itemToBuy));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemFound));
        Assertions.assertEquals(3, shopPlayer.getInventory().getAmountInInventory(itemFound));

    }

    @Test
    void notEnoughCoins(){

        Consumable itemToBuy = new HealingPotion(0,150);
        Consumable itemFound = new MPPotion(0,50);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemFound,3);

        ShopPlayer shopPlayer = new ShopPlayer(5, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemToBuy,2);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(200, inventory2);

        Assertions.assertEquals("You don't have enough coins for that item!\n",
                                        shopPlayer.buy(shopkeeper, itemToBuy));

        Assertions.assertEquals(5, shopPlayer.getCoins());
        Assertions.assertEquals(200, shopkeeper.getCoins());

        Assertions.assertEquals(2, shopkeeper.getInventory().getAmountInInventory(itemToBuy));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemToBuy));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemFound));
        Assertions.assertEquals(3, shopPlayer.getInventory().getAmountInInventory(itemFound));

    }

    @Test
    void ShopkeeperDoesntHaveItem(){

        Consumable itemToBuy = new HealingPotion(0,150);
        Consumable itemFound = new MPPotion(0,50);

        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        inventory.addAmount(itemFound,3);

        ShopPlayer shopPlayer = new ShopPlayer(100, inventory);

        Inventory inventory2 = new Inventory();
        inventory2.addAmount(itemToBuy,0);

        ShopShopkeeper shopkeeper = new ShopShopkeeper(200, inventory2);


        Assertions.assertEquals("The shopkeeper doesn't have that item!\n",
                                        shopPlayer.buy(shopkeeper, itemToBuy));

        Assertions.assertEquals(100, shopPlayer.getCoins());
        Assertions.assertEquals(200, shopkeeper.getCoins());

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemToBuy));
        Assertions.assertEquals(0, shopPlayer.getInventory().getAmountInInventory(itemToBuy));

        Assertions.assertEquals(0, shopkeeper.getInventory().getAmountInInventory(itemFound));
        Assertions.assertEquals(3, shopPlayer.getInventory().getAmountInInventory(itemFound));

    }
}
