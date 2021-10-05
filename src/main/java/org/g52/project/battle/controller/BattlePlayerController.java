package org.g52.project.battle.controller;

import org.g52.project.Options;
import org.g52.project.battle.model.BattleMonster;
import org.g52.project.battle.model.BattlePlayer;

public class BattlePlayerController {

    private final BattlePlayer player;
    public BattlePlayerController(BattlePlayer player){
        this.player = player;
    }
    public String attack(BattleMonster monster){
        return "The player " + player.attack(monster);
    }

    public String magicAttack(BattleMonster monster, Options magicOption){
        return "The player " + player.magicAttack(monster, magicOption.getValue());
    }
}
