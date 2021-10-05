package org.g52.project;

import org.g52.project.battle.model.Battle;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.shop.model.Shop;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface State {
    public abstract Options step() throws IOException, URISyntaxException, FontFormatException;

    public Dungeon getWorld();

    public Battle getBattle();

    public Shop getShop();

    public int getShopkeeper();

    public int getMonster();

    public Screen getScreen();

    public TerminalSize getTerminalSize();

    public Options getCurrentOption();

    public Menus getMenu();

}
