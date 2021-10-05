package org.g52.project.battle;

import org.g52.project.Elements;
import org.g52.project.battle.model.BattlePlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleLevelProgression {

    @Test
    void levelUp(){
        BattlePlayer player = new BattlePlayer(150,0, Elements.WATER,1,1,1,100,10,new ArrayList<>());;
        player.setExp(player.getExpToNext()-1);
        assertEquals(false,player.levelUp());
        assertEquals(1,player.getLevel());

        player.setExp(player.getExpToNext());
        assertEquals(true,player.levelUp());
        assertEquals(2,player.getLevel());
        assertEquals(0,player.getExp());

        player.setExp(player.getExpToNext()+5);
        assertEquals(true,player.levelUp());
        assertEquals(3,player.getLevel());
        assertEquals(5,player.getExp());
    }
}
