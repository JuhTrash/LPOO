package org.g52.project.shop.view;

import org.g52.project.menu.model.Menu;
import org.g52.project.menu.view.MenuView;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ShopView {
    private final Screen screen;
    private final TextGraphics textGraphics;
    private final TerminalSize terminalSize;
    private final int screenWidth, screenHeight;
    Menu menu;
    MenuView menuView;

    List<String> sprite = Arrays.asList(" `:osyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyso/`  \n" ,
            "-yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy: \n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy`\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy.\n" ,
            "+yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyys \n" ,
            " :syyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy/` \n" ,
            "   .-::+yy::::::::::::::::::::::::::::::::ys-.    \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "       -ys                               `yo      \n" ,
            "-------/ys--------------------------------ys------\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" ,
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

    public ShopView(Screen screen, TerminalSize terminalSize) {
        this.screen = screen;
        this.terminalSize = terminalSize;
        this.screenWidth = terminalSize.getColumns();
        this.screenHeight = terminalSize.getRows();
        this.textGraphics = screen.newTextGraphics();
    }

    public Screen getScreen() { return this.screen; }

    public void draw(int currentOption, Menu menu, MenuView menuView, int playerCoins) throws IOException {
        this.screen.clear();
        this.textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.textGraphics.fillRectangle(new TerminalPosition(0,0), terminalSize, ' ');
        textGraphics.putString(new TerminalPosition(3,9), "Coins: " + playerCoins);
        this.textGraphics.enableModifiers(SGR.BOLD);

        this.menu = menu;
        this.menuView = menuView;

        this.drawSprite();
        this.drawMenu(menu, menuView, currentOption);
        this.screen.refresh();

    }

    public Menu getMenu() {
        return menu;
    }

    public void drawMenu(Menu menu, MenuView view, int currentOption){
        view.drawMenu(menu, this.textGraphics, currentOption);
    }

    public void closeShop() throws IOException {
        this.screen.close();
    }

    public void drawText(String string) throws IOException {
        if (string.equals("")) return;
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(screenWidth/2 - string.length()/2 , 1), string);
        screen.refresh();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void drawSprite(){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#77614B"));
        textGraphics.enableModifiers(SGR.BOLD);

        for (int i = 0; i < sprite.size();i++){
            textGraphics.putString(new TerminalPosition(40,  i),sprite.get(i));
        }

    }
}
