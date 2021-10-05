package org.g52.project;

import org.g52.project.shop.controller.ShopController;
import org.g52.project.shop.model.ShopShopkeeper;
import org.g52.project.shop.view.ShopView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class ShopState extends GameState {
    ShopView shopView;
    ShopController shopController;

    public ShopState() throws IOException, FontFormatException, URISyntaxException {
        super();
    }

    public ShopState(State previousState) throws IOException {
        super(previousState);
        if (this.shop.getShopkeeper() == null){
            ShopShopkeeper s1 = new ShopShopkeeper(dungeon.getShopkeepers().get(shopkeeper).getCoins(), dungeon.getShopkeepers().get(shopkeeper).getInventory());
            shop.setShopkeeper(s1);
            Options.SHOPKEEPER.setValue(-5);
            this.menu = ShopController.SHOPMENUS.START;
        }
        this.shopView = new ShopView(screen, terminalSize);
        shopController = new ShopController(shop, shopView, menu, currentButton);
    }

    @Override
    public Options step() throws IOException, URISyntaxException, FontFormatException {
        Options nextState = shopController.step();
        if (nextState == Options.CONTINUE){
            currentButton = shopController.getCurrentButton();
            this.menu = shopController.getCurrentMenu();
        }else if(nextState == Options.QUIT){
            update();
            return Options.DUNGEON;
        }
        return nextState;
    }

    public void update() throws FontFormatException, IOException, URISyntaxException {
        battle.getPlayer().setInventory(shop.getPlayer().getInventory());
        dungeon.getPlayer().setInventory(shop.getPlayer().getInventory());

        dungeon.getPlayer().setCoins(shop.getPlayer().getCoins());

        dungeon.getShopkeepers().get(shopkeeper).setInventory(shop.getShopkeeper().getInventory());
        dungeon.getShopkeepers().get(shopkeeper).setCoins(shop.getShopkeeper().getCoins());

        currentButton = Options.OPTION1;

        shop.setShopkeeper(null);

        dungeonTerminal();
    }
}
