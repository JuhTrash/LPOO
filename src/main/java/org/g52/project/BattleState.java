package org.g52.project;

import org.g52.project.battle.controller.BattleController;
import org.g52.project.battle.view.BattleView;
import org.g52.project.dungeon.model.DungeonMonster;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class BattleState extends GameState {
    BattleView battleView;
    BattleController battleController;


    public BattleState(State previousState)throws IOException{
        super(previousState);
        Elements element = previousState.getWorld().getMonster(monster).getElement();
        if (this.battle.getMonster() == null) {
            this.battle.createMonster(element);
            this.menu = BattleController.BATTLEMENUS.MAIN;
            Options.MONSTER.setValue(-4);
        }
        battleView = new BattleView(screen, terminalSize, battle);
        battleController = new BattleController(battle, battleView, menu, currentButton);
    }

    public Options step() throws IOException, URISyntaxException, FontFormatException {
        Options option = battleController.step();
        if (option == Options.DUNGEON) update();
        menu = battleController.getCurrentMenu();
        currentButton = battleController.getCurrentButton();
        return option;
    }

    public void update() throws FontFormatException, IOException, URISyntaxException {
        //player updates
        dungeon.getPlayer().setHp(battle.getPlayer().getHp());
        dungeon.getPlayer().setMp(battle.getPlayer().getMp());

        dungeon.getPlayer().setMaxHp(battle.getPlayer().getMaxHp());
        dungeon.getPlayer().setMaxMp(battle.getPlayer().getMaxMp());

        dungeon.getPlayer().setInventory(battle.getPlayer().getInventory());

        dungeon.getPlayer().setElement(battle.getPlayer().getElement());

        //removing defeated monster
        List<DungeonMonster> d1 = this.dungeon.getMonsters();
        d1.remove(monster);
        this.dungeon.setMonsters(d1);

        //removing defeated monster from battle
        this.battle.setMonster(null);

        dungeonTerminal();
    }
}
