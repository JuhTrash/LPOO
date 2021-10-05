package org.g52.project.menu.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class GenericMenuView extends MenuView {
    GenericMenuView(int screenWidth, int screenHeight){
        super(3, screenWidth, screenHeight);
    }

    @Override
    protected int getOffsetX(){
        return getWidth()/4;
    }
    @Override
    protected int getOffsetY() {
        return getHeight()/2;
    }

    public void drawMenu(Menu menu, TextGraphics graphics, int currentOption){
        drawUI(graphics);
        int offsetX = getOffsetX();
        int x = getStartX();
        int y = getStartY() + getOffsetY();

        int max = optionsNumber;
        int drawX;
        //centered options
        for(int i = 0; i < max; i++){
            x += offsetX;
            String option = menu.getMenuOptions().get(i);
            drawX = x - option.length()/2;
            if(currentOption == i){
                drawCursor(graphics, drawX, y);
            }
            graphics.putString(new TerminalPosition(drawX, y), option);
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        }
    }
}
