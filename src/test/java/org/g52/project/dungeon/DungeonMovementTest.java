package org.g52.project.dungeon;

import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Position;
import org.g52.project.dungeon.model.items.Item;
import org.g52.project.dungeon.controller.DungeonController;
import org.g52.project.dungeon.controller.DungeonPlayerController;
import org.g52.project.dungeon.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class DungeonMovementTest {

    @Test
    void playerCanMoveUp() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), new ArrayList<Item>(), new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.UP);
        Assertions.assertEquals(new Position(20,19),controller.getDungeonPlayer().getPosition());
    }
    @Test
    void playerCanMoveDown() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), new ArrayList<Item>(), new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.DOWN);
        Assertions.assertEquals(new Position(20,21),controller.getDungeonPlayer().getPosition());

    }
    @Test
    void playerCanMoveLeft() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), new ArrayList<Item>(), new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.LEFT);
        Assertions.assertEquals(new Position(19,20),controller.getDungeonPlayer().getPosition());

    }
    @Test
    void playerCanMoveRight() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<DungeonMonster>(), new ArrayList<Wall>(), new ArrayList<Item>(), new ArrayList<Shopkeeper>(),null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.RIGHT);
        Assertions.assertEquals(new Position(21,20),controller.getDungeonPlayer().getPosition());
    }
    @Test
    void playerBlockedPath() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(21, 20));
        walls.add(new Wall(19, 20));

        List<DungeonMonster> monster = new ArrayList<>();
        monster.add(new DungeonMonster(20, 21,Elements.DARK));


        List<Shopkeeper> shopkeeper = new ArrayList<>();
        shopkeeper.add(new Shopkeeper(20, 19, new Inventory(), 100));

        Dungeon dungeon = new Dungeon(300, 100, player, monster, walls, new ArrayList<Item>(), shopkeeper,null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        controller.parseInput(DungeonController.INPUT.UP);
        Assertions.assertEquals(new Position(20,20),controller.getDungeonPlayer().getPosition());

        controller.parseInput(DungeonController.INPUT.DOWN);
        Assertions.assertEquals(new Position(20,20),controller.getDungeonPlayer().getPosition());

        controller.parseInput(DungeonController.INPUT.RIGHT);
        Assertions.assertEquals(new Position(20,20),controller.getDungeonPlayer().getPosition());

        controller.parseInput(DungeonController.INPUT.LEFT);
        Assertions.assertEquals(new Position(20,20),controller.getDungeonPlayer().getPosition());
    }

}

