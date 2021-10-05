package org.g52.project;

import org.g52.project.dungeon.controller.DungeonController;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.dungeon.view.DungeonView;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class DungeonState extends GameState {
    DungeonView dungeonView;
    DungeonController dungeonController;

    public DungeonState() throws IOException, FontFormatException, URISyntaxException {
        super();
    }

    public DungeonState(State previousState) throws IOException {
        super(previousState);
        dungeonView = new DungeonView(dungeon,screen,terminalSize);
        dungeonController = new DungeonController(dungeon, dungeonView);
    }

    public Options step() throws IOException {
        Options nextState = dungeonController.step();
        update();
        if (nextState == Options.MONSTER){
            monster = Options.MONSTER.getValue();
            defaultTerminal();
            return Options.BATTLE;
        }
        if (nextState == Options.SHOPKEEPER){
            defaultTerminal();
            shopkeeper = Options.SHOPKEEPER.getValue();
            return Options.SHOP;
        }
        if (nextState == Options.DUNGEON){
            this.dungeon = new Dungeon(dungeon.getWidth(),dungeon.getHeight(),dungeon.getPlayer());
            return Options.CONTINUE;
        }
        if (nextState == Options.MENU) defaultTerminal();
        return nextState;
    }

    public void update() throws IOException {
        battle.getPlayer().setHp(dungeon.getPlayer().getHp());
        battle.getPlayer().setMaxHp(dungeon.getPlayer().getMaxHp());

        battle.getPlayer().setMp(dungeon.getPlayer().getMp());
        battle.getPlayer().setMaxMp(dungeon.getPlayer().getMaxMp());

        battle.getPlayer().setInventory(dungeon.getPlayer().getInventory());
        shop.getPlayer().setInventory(dungeon.getPlayer().getInventory());

        shop.getPlayer().setCoins(dungeon.getPlayer().getCoins());

        battle.getPlayer().setElement(dungeon.getPlayer().getElement());

    }

}
