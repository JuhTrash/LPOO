package org.g52.project.dungeon.menu.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.g52.project.Options;
import org.g52.project.dungeon.menu.model.PlayerInfo;
import org.g52.project.dungeon.menu.view.DungeonMenuView;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.menu.controller.MenuInput;
import org.g52.project.menu.controller.MenuInputController;
import org.g52.project.menu.model.ItemMenu;
import org.g52.project.menu.view.InventoryMenuView;

import java.io.IOException;


public class DungeonMenuController {

    PlayerInfo player;
    DungeonMenuView dungeonMenuView;
    Options currentButton;
    MenuInputController dungeonMenuInputController;

    public DungeonMenuController(PlayerInfo player, DungeonMenuView dungeonMenuView, Options currentButton){
        this.player = player;
        this.dungeonMenuView = dungeonMenuView;
        this.currentButton = currentButton;
        dungeonMenuInputController = new MenuInputController(14-1, 3, currentButton,new ItemMenu(),new InventoryMenuView(dungeonMenuView.getScreenWidth(),dungeonMenuView.getScreenHeight()));
    }

    public PlayerInfo getPlayer() {
        return player;
    }


    private MenuInput getInput() throws IOException {
        KeyStroke keyStroke = dungeonMenuView.getScreen().readInput();

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
        dungeonMenuView.draw(currentButton.getValue(),player);
        MenuInput input = getInput();
        Options result = dungeonMenuInputController.step(input);
        if(result == Options.QUIT) {
            return Options.DUNGEON;
        }
        else if (result == Options.CONTINUE){
            currentButton = dungeonMenuInputController.getCurrentButton();
            return Options.CONTINUE;
        }
        else{
            int i = result.getValue();
            ItemMenu menu = (ItemMenu) dungeonMenuView.getMenu();
            Consumable s1 = menu.getItems().get(i);
            dungeonMenuView.drawText(player.useItem(s1));
            currentButton = dungeonMenuInputController.getCurrentButton();
            return Options.CONTINUE;
        }
    }
}
