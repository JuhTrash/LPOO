package org.g52.project.dungeon;

import org.g52.project.Elements;
import org.g52.project.Position;
import org.g52.project.dungeon.controller.DungeonController;
import org.g52.project.dungeon.controller.DungeonPlayerController;
import org.g52.project.dungeon.model.*;
import org.g52.project.dungeon.model.items.Coin;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.g52.project.dungeon.model.items.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DungeonItemTest {
    @Test
    void catchCoin(){
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        List<Item> items = new ArrayList<>();
        Coin i1 = new Coin(20,21);
        items.add(i1);

        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), items, new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.DOWN);
        Assertions.assertEquals(new Position(20,21),controller.getDungeonPlayer().getPosition());
        controller.checkItemCollision();
        Assertions.assertEquals(i1.getAmount(),controller.getDungeonPlayer().getCoins());

    }
    @Test
    void catchItem(){
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        List<Item> items = new ArrayList<>();
        HealingPotion i1 = new HealingPotion(20,21, 0,20);
        items.add(i1);

        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), items, new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.DOWN);
        Assertions.assertEquals(new Position(20,21),controller.getDungeonPlayer().getPosition());
        controller.checkItemCollision();
        Assertions.assertEquals(1,controller.getDungeonPlayer().getInventory().getAmountInInventory(i1));
    }
}
