package org.g52.project.shop;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Options;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.dungeon.model.DungeonPlayer;
import org.g52.project.shop.controller.ShopController;
import org.g52.project.shop.model.Shop;
import org.g52.project.shop.model.ShopPlayer;
import org.g52.project.shop.model.ShopShopkeeper;
import org.g52.project.shop.view.ShopView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ShopChangeStateTest {

    Screen screen =  Mockito.mock(Screen.class);
    TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
    TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

    void forMockito() throws IOException {
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        Mockito.doNothing().when(screen).refresh();
        Mockito.doNothing().when(screen).clear();
    }

    @Test
    void leaveStore() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke('q',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer dungeonPlayer = new DungeonPlayer(0, 0, 150, 50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, dungeonPlayer);

        Inventory playerInventory = new Inventory();
        playerInventory.initializeInventoryPlayer(150, 50);
        ShopPlayer player = new ShopPlayer(100, playerInventory);

        Inventory shopkeeperInventory = new Inventory();
        shopkeeperInventory.initializeInventoryShopkeeper(dungeon);
        ShopShopkeeper shopkeeper = new ShopShopkeeper(100, shopkeeperInventory);
        Shop shop = new Shop(player, shopkeeper);

        ShopView shopView = new ShopView(screen, terminalSize);
        ShopController shopController = new ShopController(shop, shopView, ShopController.SHOPMENUS.START, Options.OPTION1);

        Assertions.assertEquals(Options.QUIT, shopController.step());

    }

    @Test
    void enteringBuyMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer dungeonPlayer = new DungeonPlayer(0, 0, 150, 50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, dungeonPlayer);

        Inventory playerInventory = new Inventory();
        playerInventory.initializeInventoryPlayer(150, 50);
        ShopPlayer player = new ShopPlayer(100, playerInventory);

        Inventory shopkeeperInventory = new Inventory();
        shopkeeperInventory.initializeInventoryShopkeeper(dungeon);
        ShopShopkeeper shopkeeper = new ShopShopkeeper(100, shopkeeperInventory);
        Shop shop = new Shop(player, shopkeeper);

        ShopView shopView = new ShopView(screen, terminalSize);
        ShopController shopController = new ShopController(shop, shopView, ShopController.SHOPMENUS.START, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE, shopController.step());
        Assertions.assertEquals( ShopController.SHOPMENUS.BUY, shopController.getCurrentMenu());

    }

    @Test
    void enteringSellMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer dungeonPlayer = new DungeonPlayer(0, 0, 150, 50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, dungeonPlayer);

        Inventory playerInventory = new Inventory();
        playerInventory.initializeInventoryPlayer(150, 50);
        ShopPlayer player = new ShopPlayer(100, playerInventory);

        Inventory shopkeeperInventory = new Inventory();
        shopkeeperInventory.initializeInventoryShopkeeper(dungeon);
        ShopShopkeeper shopkeeper = new ShopShopkeeper(100, shopkeeperInventory);
        Shop shop = new Shop(player, shopkeeper);

        ShopView shopView = new ShopView(screen, terminalSize);
        ShopController shopController = new ShopController(shop, shopView, ShopController.SHOPMENUS.START, Options.OPTION2);

        Assertions.assertEquals(Options.CONTINUE, shopController.step());
        Assertions.assertEquals( ShopController.SHOPMENUS.SELL, shopController.getCurrentMenu());

    }

    @Test
    void leavingBuyMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke('q',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer dungeonPlayer = new DungeonPlayer(0, 0, 150, 50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, dungeonPlayer);

        Inventory playerInventory = new Inventory();
        playerInventory.initializeInventoryPlayer(150, 50);
        ShopPlayer player = new ShopPlayer(100, playerInventory);

        Inventory shopkeeperInventory = new Inventory();
        shopkeeperInventory.initializeInventoryShopkeeper(dungeon);
        ShopShopkeeper shopkeeper = new ShopShopkeeper(100, shopkeeperInventory);
        Shop shop = new Shop(player, shopkeeper);

        ShopView shopView = new ShopView(screen, terminalSize);
        ShopController shopController = new ShopController(shop, shopView, ShopController.SHOPMENUS.BUY, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE, shopController.step());
        Assertions.assertEquals(ShopController.SHOPMENUS.START, shopController.getCurrentMenu());

    }

    @Test
    void leavingSellMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke('q',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        DungeonPlayer dungeonPlayer = new DungeonPlayer(0, 0, 150, 50, Elements.DARK);
        Dungeon dungeon = new Dungeon(300, 100, dungeonPlayer);

        Inventory playerInventory = new Inventory();
        playerInventory.initializeInventoryPlayer(150, 50);
        ShopPlayer player = new ShopPlayer(100, playerInventory);

        Inventory shopkeeperInventory = new Inventory();
        shopkeeperInventory.initializeInventoryShopkeeper(dungeon);
        ShopShopkeeper shopkeeper = new ShopShopkeeper(100, shopkeeperInventory);
        Shop shop = new Shop(player, shopkeeper);

        ShopView shopView = new ShopView(screen, terminalSize);
        ShopController shopController = new ShopController(shop, shopView, ShopController.SHOPMENUS.SELL, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE, shopController.step());
        Assertions.assertEquals(ShopController.SHOPMENUS.START, shopController.getCurrentMenu());

    }

}
