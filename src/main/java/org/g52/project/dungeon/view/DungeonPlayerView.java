package org.g52.project.dungeon.view;

import org.g52.project.dungeon.model.DungeonPlayer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.g52.project.dungeon.model.Entity;

public class DungeonPlayerView implements DungeonEntityView {
    public DungeonPlayerView(){}

    @Override
    public void drawEntity(Entity entity, TextGraphics graphics) {
        String hexColorCode;
        switch(((DungeonPlayer)entity).getElement()){
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
                hexColorCode = "#000000";
                break;
            case LIGHT:
                hexColorCode = "#FFFFFF";
                break;
            default:
                return;
        }
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColorCode));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "+");
    }
}