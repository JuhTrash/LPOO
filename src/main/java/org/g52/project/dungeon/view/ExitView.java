package org.g52.project.dungeon.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.g52.project.dungeon.model.Entity;

public class ExitView implements DungeonEntityView{
    @Override
    public void drawEntity(Entity entity, TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#C9F9FA"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "(");
    }
}
