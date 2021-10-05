package org.g52.project.dungeon.view;

import org.g52.project.dungeon.model.Entity;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.g52.project.dungeon.model.items.Coin;

public class ItemView implements DungeonEntityView {
    @Override
    public void drawEntity(Entity entity, TextGraphics graphics) {
        if (entity instanceof Coin) new CoinView().drawEntity(entity, graphics);
        else new ConsumableView().drawEntity(entity, graphics);
    }
}
