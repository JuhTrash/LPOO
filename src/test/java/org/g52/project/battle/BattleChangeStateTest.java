package org.g52.project.battle;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.Elements;
import org.g52.project.Options;
import org.g52.project.battle.controller.BattleController;
import org.g52.project.battle.model.Battle;
import org.g52.project.battle.model.BattleMonster;
import org.g52.project.battle.model.BattlePlayer;
import org.g52.project.battle.model.MagicAttack;
import org.g52.project.battle.view.BattleView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BattleChangeStateTest {

    Screen screen =  Mockito.mock(Screen.class);
    TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
    TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

    void forMockito() throws IOException {
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        Mockito.doNothing().when(screen).refresh();
        Mockito.doNothing().when(screen).clear();
    }


    @Test
    void monsterIsDead() throws IOException {

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        monster.setHp(0);
        Battle battle = new Battle(player);
        battle.setMonster(monster);


        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAIN, Options.OPTION1);

        Assertions.assertEquals(Options.DUNGEON,controller.step());

    }

    @Test
    void monsterAndMonsterAreAlive() throws IOException {

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        monster.setHp(10000);
        Battle battle = new Battle(player);
        battle.setMonster(monster);

        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAIN, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE,controller.step());

    }

    @Test
    void playerIsDead() throws IOException {

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        BattlePlayer player = new BattlePlayer(0,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        Battle battle = new Battle(player);
        battle.setMonster(monster);

        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAIN, Options.OPTION1);

        Assertions.assertEquals(Options.QUIT,controller.step());

    }

    @Test
    void leavingMagicMenu() throws IOException{
        forMockito();

        KeyStroke key = new KeyStroke('q',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        List<MagicAttack> attacks = new ArrayList<>();
        for (int i = 0; i< 3; i++){
            MagicAttack m1 = new MagicAttack();
            attacks.add(m1);
        }

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,attacks);
        BattleMonster monster = new BattleMonster(Elements.WATER);
        Battle battle = new Battle(player);
        battle.setMonster(monster);

        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAGIC, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE,controller.step());
        Assertions.assertEquals(BattleController.BATTLEMENUS.MAIN,controller.getCurrentMenu());

    }

    @Test
    void leavingItemMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke('q',false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        Battle battle = new Battle(player);
        battle.setMonster(monster);

        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.INVENTORY, Options.OPTION1);

        Assertions.assertEquals(Options.CONTINUE,controller.step());
        Assertions.assertEquals(BattleController.BATTLEMENUS.MAIN,controller.getCurrentMenu());

    }

    @Test
    void enteringMagicMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);

        List<MagicAttack> attacks = new ArrayList<>();
        for (int i = 0; i< 3; i++){
            MagicAttack m1 = new MagicAttack();
            attacks.add(m1);
        }

        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        Battle battle = new Battle(player);
        battle.setMonster(monster);


        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAIN, Options.OPTION2);

        Assertions.assertEquals(Options.CONTINUE,controller.step());
        Assertions.assertEquals(BattleController.BATTLEMENUS.MAGIC,controller.getCurrentMenu());

    }

    @Test
    void enteringItemMenu() throws IOException{

        forMockito();

        KeyStroke key = new KeyStroke(KeyType.Enter,false,false,false);
        Mockito.when(screen.readInput()).thenReturn(key);


        BattlePlayer player = new BattlePlayer(150,50, Elements.WATER,1,1,1,90,10,new ArrayList<>());
        BattleMonster monster = new BattleMonster(Elements.WATER);
        Battle battle = new Battle(player);
        battle.setMonster(monster);


        BattleView battleView = new BattleView(screen,terminalSize,battle);
        BattleController controller = new BattleController(battle,battleView, BattleController.BATTLEMENUS.MAIN, Options.OPTION3);

        Assertions.assertEquals(Options.CONTINUE,controller.step());
        Assertions.assertEquals(BattleController.BATTLEMENUS.INVENTORY,controller.getCurrentMenu());

    }

}
