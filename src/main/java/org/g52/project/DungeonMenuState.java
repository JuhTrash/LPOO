package org.g52.project;

import org.g52.project.dungeon.menu.controller.DungeonMenuController;
import org.g52.project.dungeon.menu.model.PlayerInfo;
import org.g52.project.dungeon.menu.view.DungeonMenuView;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class DungeonMenuState extends GameState{
    DungeonMenuView dungeonMenuView;
    DungeonMenuController dungeonMenuController;

    public DungeonMenuState() throws IOException, FontFormatException, URISyntaxException {
        super();
    }

    public DungeonMenuState(State previousState) throws IOException {
        super(previousState);
        PlayerInfo player = new PlayerInfo(battle.getPlayer(),dungeon.getPlayer());
        dungeonMenuView = new DungeonMenuView(screen, terminalSize, player);
        dungeonMenuController = new DungeonMenuController(player ,dungeonMenuView,currentButton);
    }

    @Override
    public Options step() throws IOException, URISyntaxException, FontFormatException {
        Options nextState = dungeonMenuController.step();
        update();
        if(nextState == Options.QUIT){
            return Options.QUIT;
        }
        else if (nextState == Options.DUNGEON){
            currentButton = Options.OPTION1;
            dungeonTerminal();
            return Options.DUNGEON;
        }
        currentButton = dungeonMenuController.getCurrentButton();
        return nextState;
    }

    public void update() {
        battle.getPlayer().setInventory(dungeonMenuController.getPlayer().getInventory());
        dungeon.getPlayer().setInventory(dungeonMenuController.getPlayer().getInventory());

        battle.getPlayer().setHp(dungeonMenuController.getPlayer().getHp());
        dungeon.getPlayer().setHp(dungeonMenuController.getPlayer().getHp());

        battle.getPlayer().setMp(dungeonMenuController.getPlayer().getMp());
        dungeon.getPlayer().setMp(dungeonMenuController.getPlayer().getMp());

        battle.getPlayer().setElement(dungeonMenuController.getPlayer().getElement());
        dungeon.getPlayer().setElement(dungeonMenuController.getPlayer().getElement());

    }
}
