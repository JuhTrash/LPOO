package org.g52.project.battle.model;

import org.g52.project.Elements;

public class Battle {
    BattlePlayer player;
    BattleMonster monster;
    public Battle (BattlePlayer player) {
        this.player = player;
    }

    public void createMonster(Elements element) {
        this.monster = new BattleMonster(element);
    }

    public void setMonster(BattleMonster monster){
        this.monster = monster;
    }

    public BattleMonster getMonster() {
        return monster;
    }

    public BattlePlayer getPlayer() {
        return player;
    }

}
