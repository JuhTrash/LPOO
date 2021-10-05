package org.g52.project.dungeon.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.dungeon.model.Entity;

import java.io.IOException;
import java.util.List;

public class DungeonView {
    private Dungeon dungeon;

    private Screen screen;
    private TextGraphics textGraphics;
    private TerminalSize terminalSize;


    public DungeonView(Dungeon dungeon, Screen screen, TerminalSize terminalSize) throws IOException {
        this.dungeon = dungeon;
        this.screen = screen;
        this.terminalSize = terminalSize;
        textGraphics = screen.newTextGraphics();
    }

    public TerminalSize getTerminalSize() { return this.terminalSize; }

    public Screen getScreen() { return this.screen; }

    public void draw() throws IOException {
        screen.clear();

        this.textGraphics.setBackgroundColor(TextColor.Factory.fromString("#444444"));
        this.textGraphics.fillRectangle(new TerminalPosition(0,0), terminalSize, ' ');
        drawEntities(dungeon.getWalls(), new WallView());
        drawEntities(dungeon.getShopkeepers(), new ShopkeeperView());
        drawEntities(dungeon.getItems(), new ItemView());
        drawEntity(dungeon.getExit(),new ExitView());
        drawEntities(dungeon.getMonsters(), new DungeonMonsterView());
        drawEntity(dungeon.getPlayer(), new DungeonPlayerView());

        screen.refresh();
    }

    public void drawText(String string) throws IOException {
        if (string.equals("")) return;
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(dungeon.getWidth()/2 - string.length()/2 , 0), string);
        screen.refresh();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawEntities(List<? extends Entity> entities, DungeonEntityView view){
        for(Entity e : entities){
            drawEntity(e, view);
        }
    }

    private void drawEntity(Entity entity, DungeonEntityView view){
        view.drawEntity(entity, this.textGraphics);
    }
}

