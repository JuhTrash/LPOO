package org.g52.project;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.g52.project.battle.model.Battle;

import org.g52.project.battle.model.MagicAttack;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.battle.model.BattlePlayer;
import org.g52.project.dungeon.model.DungeonPlayer;
import org.g52.project.shop.model.Shop;
import org.g52.project.shop.model.ShopPlayer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements State {
    Dungeon dungeon;
    Screen screen;
    TerminalSize terminalSize;
    Battle battle;
    Shop shop;
    int monster = -1;
    int shopkeeper = -1;
    Options currentButton = Options.OPTION1;
    Menus menu;

    public GameState() throws IOException, FontFormatException, URISyntaxException {

        dungeonTerminal();

        List<MagicAttack> attacks = new ArrayList<>();
        for (int i = 0; i< 3; i++){
            MagicAttack m1 = new MagicAttack();
            attacks.add(m1);
        }

        battle = new Battle(new BattlePlayer(150,50,Elements.WATER,1,1,1,90,10,attacks));
        dungeon = new Dungeon(50, 20, new DungeonPlayer(1,1, battle.getPlayer().getMaxHp(), battle.getPlayer().getMaxMp(), battle.getPlayer().getElement()));
        shop = new Shop(new ShopPlayer(0,dungeon.getPlayer().getInventory()),null);
    }
    public GameState(State previousState) throws IOException{
        screen = previousState.getScreen();
        terminalSize = previousState.getTerminalSize();
        battle = previousState.getBattle();
        dungeon = previousState.getWorld();
        monster = previousState.getMonster();
        shop = previousState.getShop();
        shopkeeper = previousState.getShopkeeper();
        currentButton = previousState.getCurrentOption();
        menu = previousState.getMenu();
    }

    protected void defaultTerminal() throws IOException {
        screen.close();
        terminalSize = new TerminalSize(100, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    protected void dungeonTerminal() throws IOException, FontFormatException, URISyntaxException {
        if (screen != null)screen.close();
        URL resource = getClass().getClassLoader().getResource("Lpoo-Regular1.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        Font loadedFont = font.deriveFont(Font.PLAIN, 30);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        terminalSize = new TerminalSize(50, 20);
        factory.setInitialTerminalSize(terminalSize);

        Terminal terminal = factory.createTerminal();
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        screen.setCharacter(10, 10, TextCharacter.fromCharacter('C')[0]);
        screen.refresh();
    }

    public abstract Options step() throws IOException, URISyntaxException, FontFormatException;

    public Dungeon getWorld(){
        return this.dungeon;
    }

    public Battle getBattle(){
        return this.battle;
    }

    public int getMonster(){return this.monster;}

    public Screen getScreen() {
        return screen;
    }

    public TerminalSize getTerminalSize() {
        return terminalSize;
    }

    public Shop getShop(){
        return shop;
    }

    public int getShopkeeper(){
        return shopkeeper;
    }

    public Options getCurrentOption() {
        return currentButton;
    }

    public Menus getMenu() {
        return menu;
    }
}
