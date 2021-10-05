package org.g52.project.battle.controller;

import org.g52.project.Menus;
import org.g52.project.Options;
import org.g52.project.battle.model.Battle;
import org.g52.project.battle.view.BattleView;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.menu.controller.MenuInput;
import org.g52.project.menu.controller.MenuInputController;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.g52.project.menu.model.*;
import org.g52.project.menu.view.BattleMenuView;
import org.g52.project.menu.view.InventoryMenuView;
import org.g52.project.menu.view.MagicMenuView;
import org.g52.project.menu.view.MenuView;

import java.io.IOException;


public class BattleController {

    Battle battle;
    BattleView battleView;

    Options currentButton;
    BATTLEMENUS currentMenu;

    Menu menu;
    MenuView menuView;
    MenuInputController menuInputController;

    BattleMonsterController battleMonsterController;
    BattlePlayerController battlePlayerController;

    public BattleController(Battle battle, BattleView battleView, Menus menu,Options currentButton){
        this.battle = battle;
        this.battleView = battleView;
        this.currentMenu = (BATTLEMENUS) menu;
        battlePlayerController = new BattlePlayerController(battle.getPlayer());
        battleMonsterController = new BattleMonsterController(battle.getMonster());
        this.currentButton = currentButton;

        switch ((BATTLEMENUS) menu) {
            case MAGIC -> {
                this.menuView = new MagicMenuView(battleView.getScreenWidth(), battleView.getScreenHeight());
                this.menu = new MagicMenu(battle.getPlayer().getAttacks());
                this.menuInputController = new MenuInputController(3-1,3, currentButton,this.menu, menuView);
            }
            case MAIN -> {
                this.menu = new BattleMenu();
                this.menuView = new BattleMenuView(battleView.getScreenWidth(), battleView.getScreenHeight());
                this.menuInputController = new MenuInputController(3-1,3, currentButton,this.menu, menuView);            }
            case INVENTORY -> {
                this.menuView = new InventoryMenuView(battleView.getScreenWidth(), battleView.getScreenHeight());
                this.menu = new InventoryMenu(battle.getPlayer().getInventory().getInventory());
                this.menuInputController = new MenuInputController(14-1,3, currentButton,this.menu, menuView);
            }
        }

    }

    public enum BATTLEMENUS implements Menus {MAIN, MAGIC, INVENTORY}

    private MenuInput getInput() throws IOException {
        KeyStroke keyStroke = battleView.getScreen().readInput();

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

    public Options step() throws IOException {
        battleView.draw(currentButton.getValue(), menu, menuView);
        MenuInput input = getInput();
        switch (currentMenu) {
            case MAIN -> {
                return stepAttack(input);
            }
            case MAGIC -> {
                return stepMagic(input);
            }
            case INVENTORY -> {
                return stepItem(input);
            }
            default -> {
                return Options.QUIT;
            }
        }
    }

    private Options stepAttack( MenuInput input) throws IOException{

        Options option = menuInputController.step(input);
        currentButton = menuInputController.getCurrentButton();

        switch (option){
            case OPTION1:
                return attack();
            case OPTION2:
                this.currentMenu = BATTLEMENUS.MAGIC;
                break;
            case OPTION3:
                this.currentMenu = BATTLEMENUS.INVENTORY;
                break;
        }
        return Options.CONTINUE;

    }

    private Options stepMagic( MenuInput input) throws IOException {
        Options magicOption = menuInputController.step(input);
        emptyBuffer();
        currentButton = menuInputController.getCurrentButton();

        if(magicOption == Options.QUIT){
            this.currentMenu = BATTLEMENUS.MAIN;
            this.currentButton = Options.OPTION1;
            return Options.CONTINUE;
        }
        else if (magicOption == Options.CONTINUE){
            return Options.CONTINUE;
        }
        else{
            return attackMagic(magicOption);
        }
    }

    private Options stepItem( MenuInput input) throws IOException {

        Options itemOption = menuInputController.step(input);
        currentButton = menuInputController.getCurrentButton();

        if(itemOption == Options.QUIT) {
            this.currentMenu = BATTLEMENUS.MAIN;
            this.currentButton = Options.OPTION1;
            return Options.CONTINUE;
        }
        else if (itemOption == Options.CONTINUE){
            return Options.CONTINUE;
        }
        else return useItem(itemOption);
    }

    private Options retaliation() throws IOException{
        battleView.drawText(battleMonsterController.attack(battle.getPlayer()));

        if (battle.getPlayer().isDead()){
            this.battleView.getScreen().clear();
            battleView.drawText("Game over!!");
            return Options.QUIT;
        }

        return Options.CONTINUE;
    }

    private Options attack() throws IOException{
        battleView.drawText(battlePlayerController.attack(battle.getMonster()));
        if (battle.getMonster().isDead()) {
            this.battleView.getScreen().clear();
            battleView.drawText("Enemy Defeated! Exp gained: " + battle.getPlayer().increaseExp());
            return Options.DUNGEON;
        }
        return retaliation();
    }

    private Options attackMagic(Options magicOption) throws IOException{
        String s = battlePlayerController.magicAttack(battle.getMonster(),magicOption);
        battleView.drawText(s);

        if (s.equals("The player does not have enough MP!")){
            return Options.CONTINUE;
        }
        if (battle.getMonster().isDead()) {
            this.battleView.getScreen().clear();
            battleView.drawText("Enemy Defeated! Exp gained: " + battle.getPlayer().increaseExp());
            return Options.DUNGEON;
        }

        return retaliation();
    }

    private Options useItem(Options itemOption)  throws IOException{
        ItemMenu menu = (ItemMenu) battleView.getMenu();
        Consumable item = menu.getItems().get(itemOption.getValue());
        battleView.drawText(battle.getPlayer().useItem(item));

        return retaliation();
    }

    public BATTLEMENUS getCurrentMenu() {
        return currentMenu;
    }

    public Options getCurrentButton() {
        return currentButton;
    }

    public BattleMonsterController getBattleMonsterController() {
        return battleMonsterController;
    }

    private void emptyBuffer() throws IOException {
        KeyStroke keyStroke;
        do{
            keyStroke = battleView.getScreen().pollInput();
        }
        while (keyStroke != null);
    }
}
