package org.g52.project.menu.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class InventoryMenuView extends ItemMenuView{
    public InventoryMenuView(int screenWidth, int screenHeight){
        super(14, screenWidth, screenHeight);
    }

    @Override
    protected int getOffsetX(){
        return getWidth()/4;
    }

    @Override
    public void drawMenu(Menu menu, TextGraphics graphics, int currentOption){
        drawUI(graphics);

        int i = 0, max = optionsNumber;
        switch (currentOption / 3) {
            case 3:
                i = 3;
                max = optionsNumber - 2;
                break;
            case 4:
                i = 6;
                max = optionsNumber;
                break;
            default:
                i = 0;
                max = optionsNumber - 5;
                break;
        }

        int offsetX = getOffsetX();
        int offsetY = getOffsetY();
        int y = getStartY();
        int initX = getStartX() + offsetX/2;
        int x = 0;

        //For the drawing the cursor
        for(; i < max; i++){

            if(i % 3 == 0){
                x = initX;
                y += offsetY;
            }
            else{
                x += 4*offsetX/3 - 1;
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
