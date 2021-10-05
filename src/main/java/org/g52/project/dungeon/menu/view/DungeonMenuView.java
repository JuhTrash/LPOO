package org.g52.project.dungeon.menu.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.dungeon.menu.model.PlayerInfo;
import org.g52.project.menu.model.InventoryMenu;
import org.g52.project.menu.model.Menu;

import org.g52.project.menu.view.MenuView;
import org.g52.project.menu.view.InventoryMenuView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DungeonMenuView {

    private final Screen screen;
    private final TextGraphics textGraphics;
    private final TerminalSize terminalSize;
    private final int screenWidth, screenHeight;
    Menu menu;
    MenuView menuView;

    List<String> sprite = Arrays.asList(
            "                                          `-/shdmd\n",
            "                                    -++sddmmdhhmm:\n" ,
            "                                  .smmdhyysssymmo \n" ,
            "                                :smmhssssssshmd:  \n" ,
            "                              :hmmhsssssssymmy`   \n" ,
            "                           `+mmdysssssssyhmm/     \n" ,
            "                         -ymmhsssssssyhmmds/      \n" ,
            "                       /dmdyssssssyhmmh+-         \n" ,
            "                    .smmdysssssydmmh+.            \n" ,
            "                 `/hmmhsssssydmmy/`               \n" ,
            "               .ommdyssssydmmy/`                  \n" ,
            "             /hmmhssssydmmy/`                     \n" ,
            "  :ho     -ommdysssyhmmh/`                        \n" ,
            "  `hmh.`+hmmhsssyhmmh/.                           \n" ,
            "   `ommmmdyssshmmh+.                              \n" ,
            "     ommdsshdmdo.                                 \n" ,
            "   /ymmmmmmds-                                    \n" ,
            "`/dmmy/`omm/                                      \n" ,
            ".hs/`    /mm.                                     ");

    public DungeonMenuView(Screen screen, TerminalSize terminalSize, PlayerInfo player){
        this.screen = screen;
        this.terminalSize = terminalSize;
        this.screenWidth = terminalSize.getColumns();
        this.screenHeight = terminalSize.getRows();
        this.textGraphics = screen.newTextGraphics();
        menu = new InventoryMenu(player.getInventory().getInventory());
        menuView = new InventoryMenuView(screenWidth, screenHeight);
    }

    public Screen getScreen() { return this.screen; }

    public void draw(int currentOption, PlayerInfo player) throws IOException {
        this.screen.clear();
        this.textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.textGraphics.fillRectangle(new TerminalPosition(0,0), terminalSize, ' ');
        this.textGraphics.enableModifiers(SGR.BOLD);

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawPlayerInfo(player);
        drawSprite(player);
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
        textGraphics.putString(new TerminalPosition(screenWidth/2 - string.length()/2 , 16), string);
        screen.refresh();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void drawPlayerInfo(PlayerInfo player) throws IOException {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.putString(new TerminalPosition(3,2), "Level: " + player.getLevel());
        textGraphics.putString(new TerminalPosition(3,3), "Exp: " + player.getExp());
        textGraphics.putString(new TerminalPosition(3,4), "Exp Left To Next Level: " + player.getExpToNext());
        textGraphics.putString(new TerminalPosition(3,6), "HP: " + player.getHp() +"/" + player.getMaxHp());
        textGraphics.putString(new TerminalPosition(3,7), "MP: " + player.getMp() +"/" + player.getMaxMp());
        textGraphics.putString(new TerminalPosition(3,8), "Element: " + player.getElement().getName());
        textGraphics.putString(new TerminalPosition(3,9), "Coins: " + player.getCoins());

        textGraphics.putString(new TerminalPosition(3,11), "STR: " + player.getStrength());
        textGraphics.putString(new TerminalPosition(3,12), "MAG: " + player.getMagic());
        textGraphics.putString(new TerminalPosition(3,13), "DEF: " + player.getDefense());
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void drawSprite(PlayerInfo player){
        String hexColorCode;
        switch(player.getElement()){
            case FIRE:
                hexColorCode = "#FF2200";
                break;
            case WATER:
                hexColorCode = "#0088FF";
                break;
            case ELECTRICITY:
                hexColorCode = "#FFE800";
                break;
            case PLANT:
                hexColorCode = "#00EE00";
                break;
            case DARK:
                hexColorCode = "#C1BCB5";
                break;
            case LIGHT:
                hexColorCode = "#FFFFFF";
                break;
            default:
                return;
        }
        textGraphics.setForegroundColor(TextColor.Factory.fromString(hexColorCode));
        textGraphics.enableModifiers(SGR.BOLD);

        for (int i = 0; i < sprite.size();i++){
            textGraphics.putString(new TerminalPosition(40,  i),sprite.get(i));
        }

    }
}
