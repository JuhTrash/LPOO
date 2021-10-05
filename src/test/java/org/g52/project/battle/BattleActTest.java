package org.g52.project.battle;

import org.g52.project.Elements;
import org.g52.project.battle.model.BattleMonster;
import org.g52.project.battle.model.BattlePlayer;
import org.g52.project.battle.model.MagicAttack;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleActTest {

    @Test
    void normalAttack() {
        List<MagicAttack> attacks = new ArrayList<>();
        for (int i = 0; i< 3; i++){
            MagicAttack m1 = new MagicAttack();
            attacks.add(m1);
        }

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,0,10,attacks);;
        BattleMonster monster = new BattleMonster(Elements.WATER);

        assertEquals("missed!",player.attack(monster));

        player = new BattlePlayer(150,50, Elements.WATER,1,1,1,100,10,attacks);
        String s1 = player.attack(monster);
        int damage = monster.getMaxHp()-monster.getHp();
        assertEquals("dealt " + damage + " damage with a normal Attack!",s1);
    }

    @Test
    void magicAttack(){
        List<MagicAttack> attacks = new ArrayList<>();
        MagicAttack m1 = new MagicAttack(10,Elements.DARK,5);
        MagicAttack m2 = new MagicAttack(10,Elements.WATER,5);
        MagicAttack m3 = new MagicAttack(10,Elements.ELECTRICITY,5);
        attacks.add(m1);
        attacks.add(m2);
        attacks.add(m3);

        BattlePlayer player = new BattlePlayer(150,0, Elements.WATER,1,1,1,100,10,attacks);;
        BattleMonster monster = new BattleMonster(Elements.WATER);

        assertEquals("does not have enough MP!",player.magicAttack(monster,0));

        player = new BattlePlayer(150,50, Elements.WATER,1,1,1,100,10,attacks);
        String s1 = player.magicAttack(monster,0);
        int damage = monster.getMaxHp()-monster.getHp();
        assertEquals("dealt " + damage + " damage with a "+ attacks.get(0).getName() +"!",s1);
        monster.setHp(monster.getMaxHp());

        s1 = player.magicAttack(monster,1);
        damage = monster.getMaxHp()-monster.getHp();
        assertEquals("hit a resistance and " + "dealt " + damage + " damage with a "+ attacks.get(1).getName() +"!",s1);
        monster.setHp(monster.getMaxHp());

        s1 = player.magicAttack(monster,2);
        damage = monster.getMaxHp()-monster.getHp();
        assertEquals("hit a weakness and " + "dealt " + damage + " damage with a "+ attacks.get(2).getName() +"!",s1);

        player = new BattlePlayer(150,10, Elements.WATER,1,1,1,0,10,attacks);;
        s1 = player.magicAttack(monster,2);
        assertEquals("missed!",s1);

    }

    @Test
    void useItem(){
        BattlePlayer player = new BattlePlayer(150,0, Elements.WATER,1,1,1,100,10,new ArrayList<>());;
        HealingPotion hp = new HealingPotion(0,150);
        player.getInventory().addAmount(hp,1);

        assertEquals("You used one " + hp.getType() + "!",player.useItem(hp));
        assertEquals("No items of this type to use!",player.useItem(hp));

    }
}
