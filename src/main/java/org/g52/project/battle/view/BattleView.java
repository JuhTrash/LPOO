package org.g52.project.battle.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.battle.model.Battle;
import org.g52.project.menu.view.MenuView;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class BattleView {
    private final Battle battle;

    private final Screen screen;
    private final TextGraphics textGraphics;
    private final TerminalSize terminalSize;
    private final int screenWidth, screenHeight;
    Menu menu;
    MenuView menuView;

    List<String> spriteMonster = Arrays.asList(
           "\n",
                   "       -+syhdmmmmmdy+-        \n" ,
                   "    `+dmmmdhhhhhhdmmmmmo.     \n" ,
                   "   /mmmhssssssssssssydmmms`   \n" ,
                   "  ommhssssssssssssssssshmmm/  \n" ,
                   " /mmyssssdmssssssssmmssssdmms`\n" ,
                   "`mmhsssssmmysssssssdmysssshmms\n" ,
                   "+mmysssssssssssssssssssssssmmm\n" ,
                   "mmmsssssssssssssssssssssssymmy\n" ,
                   "smmhssssssssssssssssssssshmms`\n" ,
                   " -smmmdhhyyysssssssyyhhdmds-  \n" ,
                   "    -/osyhddmmmmmmdhys+/.   ");


    List<String> spritePlayer = Arrays.asList("\n" ,
            "                      `.:+sydh\n" ,
            "                    -ydhyyshh.\n" ,
            "                 `/hdysssymo` \n" ,
            "               .odhsssshdd/   \n" ,
            "             /hdyssyhdy/`     \n" ,
            "          -odhssyhds:`        \n" ,
            "       `/hdysyhdo-            \n" ,
            " :d- :sdhsyhdo-               \n" ,
            "  :ddhyyhdo-                  \n" ,
            " .+ddmds-                     \n" ,
            ":ho- +d.                      ");

    public BattleView(Screen screen, TerminalSize terminalSize, Battle battle) throws IOException{
        this.terminalSize = terminalSize;
        this.screen = screen;
        this.screenWidth = terminalSize.getColumns();
        this.screenHeight = terminalSize.getRows();
        this.battle = battle;
        textGraphics = screen.newTextGraphics();
    }

    public Screen getScreen() { return this.screen; }

    public void draw(int currentOption, Menu menu, MenuView menuView) throws IOException {
        this.screen.clear();
        this.textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.textGraphics.fillRectangle(new TerminalPosition(0,0), terminalSize, ' ');
        this.textGraphics.enableModifiers(SGR.BOLD);

        drawSprite();

        this.drawPlayerStats();
        this.drawMonsterStats();

        this.menu = menu;
        this.menuView = menuView;


        this.drawMenu(menu, menuView, currentOption);
        this.screen.refresh();

    }

    public Menu getMenu() {
        return menu;
    }

    public void drawMenu(Menu menu, MenuView view, int currentOption){
        view.drawMenu(menu, this.textGraphics, currentOption);
    }

    public void closeBattle() throws IOException {
        this.screen.close();
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void drawText(String string) throws IOException {
        if (string.equals("")) return;
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(screenWidth/2 - string.length()/2 , 18), string);
        screen.refresh();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(new TerminalPosition(screenWidth/2 - string.length()/2 , 18), string);
        screen.refresh();

    }

    public void drawPlayerStats() throws IOException {

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(3, 2),  "You:");
        textGraphics.putString(new TerminalPosition(3, 3),  "HP -> " + String.valueOf(battle.getPlayer().getHp())+"/"+ String.valueOf(battle.getPlayer().getMaxHp()));
        textGraphics.putString(new TerminalPosition(3, 4),  "MP -> " + String.valueOf(battle.getPlayer().getMp())+"/"+ String.valueOf(battle.getPlayer().getMaxMp()));
        textGraphics.putString(new TerminalPosition(3, 5),  "Element -> " + String.valueOf(battle.getPlayer().getElement().getName()));

    }

    public void drawMonsterStats() throws IOException {

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(screenWidth-23, 13),  "Enemy:");
        textGraphics.putString(new TerminalPosition(screenWidth-23, 14),  "HP -> " + String.valueOf(battle.getMonster().getHp())+"/"+ String.valueOf(battle.getMonster().getMaxHp()));
        textGraphics.putString(new TerminalPosition(screenWidth-23, 15),  "MP -> " + String.valueOf(battle.getMonster().getMp())+"/"+ String.valueOf(battle.getMonster().getMaxMp()));
        textGraphics.putString(new TerminalPosition(screenWidth-23, 16),  "Element -> " + String.valueOf(battle.getMonster().getElement().getName()));

    }


    public void drawSprite(){
        String hexColorCode;
        switch(battle.getMonster().getElement()){
            case FIRE:
                hexColorCode = "#DD1100";
                break;
            case WATER:
                hexColorCode = "#1144FF";
                break;
            case ELECTRICITY:
                hexColorCode = "#FFBB00";
                break;
            case PLANT:
                hexColorCode = "#008800";
                break;
            case DARK:
                hexColorCode = "#9B9894";
                break;
            case LIGHT:
                hexColorCode = "#FFFFFF";
                break;
            default:
                return;
        }
        textGraphics.setForegroundColor(TextColor.Factory.fromString(hexColorCode));
        textGraphics.enableModifiers(SGR.BOLD);

        for (int i = 0; i < spriteMonster.size();i++){
            textGraphics.putString(new TerminalPosition(60,  i),spriteMonster.get(i));
        }


        switch(battle.getPlayer().getElement()){
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

        for (int i = 0; i < spritePlayer.size();i++){
            textGraphics.putString(new TerminalPosition(1,  6+i),spritePlayer.get(i));
        }

    }
}
