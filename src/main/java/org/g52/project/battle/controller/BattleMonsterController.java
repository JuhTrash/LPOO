package org.g52.project.battle.controller;

import org.g52.project.battle.model.BattleMonster;
import org.g52.project.battle.model.BattlePlayer;

import java.util.Random;

public class BattleMonsterController {
    private final BattleMonster battleMonster;
    public BattleMonsterController(BattleMonster battleMonster){
        this.battleMonster = battleMonster;
    }
    public String attack(BattlePlayer battlePlayer){
        int chance;
        Random rand = new Random();

        chance = rand.nextInt(2);
        if (chance == 0){
            return "The enemy " + battleMonster.attack(battlePlayer);
        }
        else{
            chance = rand.nextInt(3);
            return "The enemy " + battleMonster.magicAttack(battlePlayer,chance);
        }
    }
}
