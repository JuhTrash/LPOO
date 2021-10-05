package org.g52.project.dungeon.view;

import org.g52.project.dungeon.model.Entity;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class CoinView implements DungeonEntityView {
    @Override
    public void drawEntity(Entity entity, TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFDD00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "%");
    }
}
