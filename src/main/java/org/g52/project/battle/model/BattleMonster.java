package org.g52.project.battle.model;

import org.g52.project.Elements;

import java.util.List;
import java.util.Random;

public class BattleMonster extends Entity{
    public BattleMonster(int hp, int mp, Elements element, int defense, int strength, int magic, double hitRate, int baseAttackDamage, List<MagicAttack> attacks) {
        super(hp, mp, element, defense, strength, magic, hitRate, baseAttackDamage, attacks);

    }

    public BattleMonster(Elements element){
        super(element);
        Random rand = new Random();
        this.level = rand.nextInt(10);
    }
}
