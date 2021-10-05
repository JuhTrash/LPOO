package org.g52.project.dungeon.view;

import org.g52.project.dungeon.model.DungeonMonster;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.g52.project.dungeon.model.Entity;

public class DungeonMonsterView implements DungeonEntityView {
    @Override
    public void drawEntity(Entity entity, TextGraphics graphics) {
        String hexColorCode;
        switch(((DungeonMonster)entity).getElement()){
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
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), ";");
    }
}
