package org.g52.project.dungeon.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.g52.project.dungeon.model.Entity;

public interface DungeonEntityView {
    void drawEntity(Entity entity, TextGraphics graphics);
}
