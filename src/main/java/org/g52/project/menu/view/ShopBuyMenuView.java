package org.g52.project.menu.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ShopBuyMenuView extends ItemMenuView {

    public ShopBuyMenuView(int screenWidth, int screenHeight) {
        super(6, screenWidth, screenHeight);
    }

    @Override
    protected int getOffsetX(){
        return getWidth()/3;
    }

    @Override
    protected int getOffsetY() {
        return getHeight()/3;
    }

    public void drawMenu(Menu menu, TextGraphics graphics, int currentOption){
        drawUI(graphics);

        int offsetX = getOffsetX();
        int offsetY = getOffsetY();
        int y = getStartY();
        int initX = getStartX() + offsetX - menu.getMenuOptions().get(0).length()/2;
        int x = 0;

        //For the drawing the cursor
        for(int i = 0; i < optionsNumber; i++){

            //lots of magic numbers, but it's
            //much more aesthetically pleasing this way
            if(i % 2 == 0){
                x = initX;
                y += offsetY;
            }
            else{
                x += offsetX;
            }

            String option = menu.getMenuOptions().get(i);
            if(currentOption == i){
                drawCursor(graphics, x, y);
            }
            graphics.putString(new TerminalPosition(x, y), option);

            //we change the foreground and background when drawing the cursor,
            //and also want to change the option's appearance
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        }
    }
}
