package org.g52.project.menu.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class ItemMenuView extends MenuView {
    ItemMenuView(int optionsNumber, int screenWidth, int screenHeight){
        super(optionsNumber, screenWidth, screenHeight);
    }

    @Override
    protected abstract int getOffsetX();

    @Override
    protected int getOffsetY() {
        return getHeight()/3;
    }

    public abstract void drawMenu(Menu menu, TextGraphics graphics, int currentOption);
}
