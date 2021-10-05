package org.g52.project.dungeon;

import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.dungeon.menu.model.PlayerInfo;
import org.g52.project.dungeon.model.items.ElementChanger;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.g52.project.dungeon.model.items.MPPotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DungeonUseItemsTest {
    @Test
    void useHPPotion(){
        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        HealingPotion item = new HealingPotion(0,150);
        inventory.addAmount(item,3);

        PlayerInfo player = new PlayerInfo(0,10,1,150,100,50,50,1,1,1, Elements.DARK,100,inventory);

        Assertions.assertEquals("You used one " + item.getType() + "!",player.useItem(item));
        Assertions.assertEquals(2,player.getInventory().getAmountInInventory(item));
        Assertions.assertEquals(100+ item.getRestore(), player.getHp());

        player.useItem(item);
        player.useItem(item);
        Assertions.assertEquals(0,player.getInventory().getAmountInInventory(item));
        Assertions.assertEquals(150, player.getHp());

    }

    @Test
    void useMPPotion(){
        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        MPPotion item = new MPPotion(0,50);
        inventory.addAmount(item,3);

        PlayerInfo player = new PlayerInfo(0,10,1,150,150,40,50,1,1,1, Elements.DARK,100,inventory);

        Assertions.assertEquals("You used one " + item.getType() + "!",player.useItem(item));
        Assertions.assertEquals(2,player.getInventory().getAmountInInventory(item));
        Assertions.assertEquals(40 + item.getRestore(), player.getMp());

        player.useItem(item);
        player.useItem(item);
        Assertions.assertEquals(0,player.getInventory().getAmountInInventory(item));
        Assertions.assertEquals(50, player.getMp());

    }

    @Test
    void useElementChanger(){
        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        ElementChanger item = new ElementChanger(1);
        inventory.addAmount(item,3);

        PlayerInfo player = new PlayerInfo(0,10,1,150,150,50,50,1,1,1, Elements.DARK,100,inventory);

        Assertions.assertEquals("You used one " + item.getType() + "!",player.useItem(item));
        Assertions.assertEquals(2,player.getInventory().getAmountInInventory(item));
        Assertions.assertEquals(Elements.FIRE, player.getElement());

    }

    @Test
    void noItemToUse(){
        Inventory inventory = new Inventory();
        inventory.initializeInventoryPlayer(150,50);

        HealingPotion item = new HealingPotion(0,150);

        PlayerInfo player = new PlayerInfo(0,10,1,150,100,50,50,1,1,1, Elements.DARK,100,inventory);

        Assertions.assertEquals("No items of this type to use!",player.useItem(item));
    }


}
