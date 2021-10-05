package org.g52.project.dungeon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Options;
import org.g52.project.dungeon.controller.DungeonController;
import org.g52.project.dungeon.controller.DungeonPlayerController;
import org.g52.project.dungeon.model.*;
import org.g52.project.dungeon.model.items.Item;
import org.g52.project.dungeon.view.DungeonView;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DungeonChangeStateTest {

    Screen screen =  Mockito.mock(Screen.class);
    TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
    TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

    void forMockito() throws IOException {
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        Mockito.doNothing().when(screen).refresh();
        Mockito.doNothing().when(screen).clear();
    }

    @Test
    void nextToMonsterDetected(){
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(21, 20));
        walls.add(new Wall(19, 20));

        List<DungeonMonster> monster = new ArrayList<>();
        monster.add(new DungeonMonster(20, 21,Elements.DARK));


        List<Shopkeeper> shopkeeper = new ArrayList<>();
        shopkeeper.add(new Shopkeeper(20, 19, new Inventory(), 100));

        Dungeon dungeon = new Dungeon(300, 100, player, monster, walls, new ArrayList<>(), shopkeeper, null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(0, controller.checkMonsterCollisions());

        dungeon = new Dungeon(300, 100, player, new ArrayList<>(), walls, new ArrayList<>(), shopkeeper,null);
        controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(-1, controller.checkMonsterCollisions());

        monster.clear();
        monster.add(new DungeonMonster(0, 0,Elements.DARK));
        monster.add(new DungeonMonster(20, 21,Elements.DARK));

        dungeon = new Dungeon(300, 100, player, monster, walls, new ArrayList<>(), shopkeeper,null);
        controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(1, controller.checkMonsterCollisions());
    }

    @Test
    void onExitDetected(){
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);
        Exit exit = new Exit(20,20);
        Exit exit2 = new Exit(30,30);


        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), exit);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);
        Assertions.assertEquals(true, controller.checkExit());

        dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), exit2);
        controller = new DungeonPlayerController(dungeon);
        Assertions.assertEquals(false, controller.checkExit());

    }

    @Test
    void nextToShopKeeperDetected() {
        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);

        List<Shopkeeper> shopkeeper = new ArrayList<>();
        shopkeeper.add(new Shopkeeper(20, 19, new Inventory(), 100));

        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<Item>(), shopkeeper,null);
        DungeonPlayerController controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(0,controller.checkShop());

        shopkeeper.clear();

        dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<Item>(), shopkeeper,null);
        controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(-1,controller.checkShop());

        shopkeeper = new ArrayList<>();
        shopkeeper.add(new Shopkeeper(30, 30, new Inventory(), 100));
        shopkeeper.add(new Shopkeeper(20, 19, new Inventory(), 100));

        dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<Item>(), shopkeeper,null);
        controller = new DungeonPlayerController(dungeon);

        Assertions.assertEquals(1,controller.checkShop());
    }

    @Test
    void triedToOpenMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke('m',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);

        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Exit(0,0));
        DungeonView dungeonView = new DungeonView(dungeon,screen,terminalSize);
        DungeonController dungeonController = new DungeonController(dungeon,dungeonView);

        Assertions.assertEquals(Options.MENU,dungeonController.step());
    }

    @Test
    void triedToOpenShop() throws IOException{

        forMockito();
        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);

        List<Shopkeeper> shopkeeper = new ArrayList<>();
        shopkeeper.add(new Shopkeeper(20, 19, new Inventory(), 100));

        Dungeon dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), shopkeeper,new Exit(0,0));
        DungeonView dungeonView = new DungeonView(dungeon,screen,terminalSize);
        DungeonController dungeonController = new DungeonController(dungeon,dungeonView);

        Assertions.assertEquals(Options.SHOPKEEPER,dungeonController.step());

        shopkeeper.clear();

        dungeon = new Dungeon(300, 100, player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), shopkeeper,new Exit(0,0));
        dungeonView = new DungeonView(dungeon,screen,terminalSize);
        dungeonController = new DungeonController(dungeon,dungeonView);

        Assertions.assertEquals(Options.CONTINUE,dungeonController.step());

    }

    @Test
    void initiateBattle() throws IOException {

        forMockito();
        KeyStroke key = new KeyStroke('P',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);

        List<DungeonMonster> monster = new ArrayList<>();
        monster.add(new DungeonMonster(20, 21,Elements.DARK));

        Dungeon dungeon = new Dungeon(300, 100, player,monster, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new Exit(0,0));
        DungeonView dungeonView = new DungeonView(dungeon,screen,terminalSize);
        DungeonController dungeonController = new DungeonController(dungeon,dungeonView);


        Assertions.assertEquals(Options.MONSTER,dungeonController.step());

        monster.clear();

        dungeon = new Dungeon(300, 100, player,monster, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new Exit(0,0));
        dungeonView = new DungeonView(dungeon,screen,terminalSize);
        dungeonController = new DungeonController(dungeon,dungeonView);

        Assertions.assertEquals(Options.CONTINUE,dungeonController.step());
    }

    @Test
    void leaveRoom() throws IOException {

        forMockito();
        KeyStroke key = new KeyStroke('P',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer player = new DungeonPlayer(20,20,150,50, Elements.DARK);

        Exit e = new Exit(20,20);

        Dungeon dungeon = new Dungeon(300, 100, player,new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),e);
        DungeonView dungeonView = new DungeonView(dungeon,screen,terminalSize);
        DungeonController dungeonController = new DungeonController(dungeon,dungeonView);


        Assertions.assertEquals(Options.DUNGEON,dungeonController.step());



        dungeon = new Dungeon(300, 100, player,new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new Exit(0,0));
        dungeonView = new DungeonView(dungeon,screen,terminalSize);
        dungeonController = new DungeonController(dungeon,dungeonView);

        Assertions.assertEquals(Options.CONTINUE,dungeonController.step());
    }
}
