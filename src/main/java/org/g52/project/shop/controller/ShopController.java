package org.g52.project.shop.controller;

import org.g52.project.Menus;
import org.g52.project.Options;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.menu.controller.MenuInput;
import org.g52.project.menu.controller.MenuInputController;
import org.g52.project.menu.model.*;
import org.g52.project.menu.view.InventoryMenuView;
import org.g52.project.menu.view.MenuView;
import org.g52.project.menu.view.ShopBuyMenuView;
import org.g52.project.menu.view.ShopMenuView;
import org.g52.project.shop.model.Shop;
import org.g52.project.shop.view.ShopView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class ShopController {
    Shop shop;
    ShopView shopView;
    ShopPlayerController shopPlayerController;
    Options currentButton;
    MenuInputController shopInputController;
    Menu menu;
    MenuView menuView;
    SHOPMENUS currentMenu;


    public ShopController(Shop shop, ShopView shopView, Menus menu, Options currentButton){
        this.shop = shop;
        this.shopView = shopView;
        this.shopPlayerController = new ShopPlayerController(shop.getPlayer());
        this.currentButton = currentButton;
        this.currentMenu = (SHOPMENUS) menu;

        switch(this.currentMenu){
            case START -> {
                this.menu = new ShopMenu();
                this.menuView = new ShopMenuView(shopView.getScreenWidth(),shopView.getScreenHeight());
                shopInputController = new MenuInputController(2-1,3,currentButton, this.menu, this.menuView);
            }
            case BUY -> {
                this.menu = new ShopBuyMenu(shop.getShopkeeper().getInventory().getInventory());
                this.menuView = new ShopBuyMenuView(shopView.getScreenWidth(),shopView.getScreenHeight());
                shopInputController = new MenuInputController(6-1,2,currentButton, this.menu, this.menuView);
            }
            case SELL -> {
                this.menu = new InventoryMenu(shop.getPlayer().getInventory().getInventory());
                this.menuView = new InventoryMenuView(shopView.getScreenWidth(),shopView.getScreenHeight());
                shopInputController = new MenuInputController(14-1,3, currentButton, this.menu, this.menuView);
            }
        }
    }

    public Menus getCurrentMenu() {
        return currentMenu;
    }

    public enum SHOPMENUS implements Menus {START, BUY, SELL}

    private MenuInput getInput() throws IOException {
        KeyStroke keyStroke = shopView.getScreen().readInput();

        if (keyStroke.getKeyType() == KeyType.EOF) return MenuInput.QUIT;

        switch(keyStroke.getKeyType()){
            case ArrowLeft: return MenuInput.LEFT;
            case ArrowRight: return MenuInput.RIGHT;
            case ArrowUp: return MenuInput.UP;
            case ArrowDown: return MenuInput.DOWN;
            case Enter: return MenuInput.ENTER;
            case Character:
                switch(keyStroke.getCharacter()){
                    case 'a':
                    case 'A': return MenuInput.LEFT;
                    case 'd':
                    case 'D': return MenuInput.RIGHT;
                    case 'w':
                    case 'W': return MenuInput.UP;
                    case 's':
                    case 'S': return MenuInput.DOWN;
                    case 'q':
                    case 'Q': return MenuInput.QUIT;
                }
        }

        return MenuInput.NONE;
    }

    public Options getCurrentButton() {
        return currentButton;
    }

    public Options step() throws IOException {
        shopView.draw(currentButton.getValue(), this.menu, this.menuView, this.shop.getPlayer().getCoins());
        MenuInput input = getInput();

        switch (this.currentMenu) {
            case START -> {
                return stepStart(input);
            }
            case BUY -> {
                return stepBuy(input);
            }
            case SELL -> {
                return stepSell(input);
            }
            default -> {
                return Options.QUIT;
            }
        }
    }

    private Options stepStart(MenuInput input) throws IOException{
        if(input == MenuInput.QUIT){
            return Options.QUIT;
        }

        Options option = shopInputController.step(input);
        currentButton = shopInputController.getCurrentButton();

        if(option == Options.CONTINUE)
            return Options.CONTINUE;

        switch(currentButton){
            case OPTION1 -> {
                this.currentMenu = SHOPMENUS.BUY;
            }
            case OPTION2 -> {
                this.currentMenu = SHOPMENUS.SELL;
            }
        }
        return Options.CONTINUE;
    }

    private Options stepBuy(MenuInput input) throws IOException{
        if (input == MenuInput.QUIT){
            this.currentMenu = SHOPMENUS.START;
            this.currentButton = Options.OPTION1;
            return Options.CONTINUE;
        }

        Options option = shopInputController.step(input);
        currentButton = shopInputController.getCurrentButton();


        if (option == Options.CONTINUE){
            return Options.CONTINUE;
        }
        else{
            return buy(option);
        }
    }


    private Options stepSell(MenuInput input) throws IOException{
        if (input == MenuInput.QUIT){
            this.currentMenu = SHOPMENUS.START;
            this.currentButton = Options.OPTION2;
            return Options.CONTINUE;
        }

        Options option = shopInputController.step(input);
        currentButton = shopInputController.getCurrentButton();

        if (option == Options.CONTINUE){
            return Options.CONTINUE;
        }
        else{
            return sell(option);
        }
    }

    private Options buy(Options option) throws IOException{
        int i = option.getValue();
        ItemMenu menu = (ItemMenu) shopView.getMenu();
        Consumable s1 = menu.getItems().get(i);
        shopView.drawText(shop.getPlayer().buy(shop.getShopkeeper(),s1));
        currentButton = shopInputController.getCurrentButton();
        return Options.CONTINUE;
    }

    private Options sell(Options option) throws IOException{
        int i = option.getValue();
        ItemMenu menu = (ItemMenu) shopView.getMenu();
        Consumable s1 = menu.getItems().get(i);
        shopView.drawText(shop.getPlayer().sell(shop.getShopkeeper(),s1));
        currentButton = shopInputController.getCurrentButton();
        return Options.CONTINUE;
    }
}
