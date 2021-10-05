package org.g52.project.menu.view;

import org.g52.project.menu.model.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class MenuView {
    protected int optionsNumber;
    private final int screenWidth, screenHeight;

    public MenuView(int optNum, int screenWidth, int screenHeight){
        this.optionsNumber = optNum;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public int getOptionsNumber(){
        return this.optionsNumber;
    }

    protected int getStartX(){
        return this.screenWidth/12;
    }

    protected int getStartY(){
        return screenHeight - screenHeight/3;
    }

    private int getEndX(){
        return 11*screenWidth/12;
    }

    private int getEndY(){
        return screenHeight - screenHeight/10 + 1;
    }

    protected int getWidth(){
        return getEndX() - getStartX();
    }

    protected int getHeight(){
        return getEndY() - getStartY();
    }

    protected abstract int getOffsetX();

    protected abstract int getOffsetY();

    abstract public void drawMenu(Menu menu, TextGraphics graphics, int currentOption);

    protected void drawUI(TextGraphics graphics){
        int startX = getStartX();
        int endX = getEndX();
        int startY = getStartY();
        int endY = getEndY();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        //left and right bars of the frame
        for(int y = startY; y <= endY; y++){
            graphics.putString(new TerminalPosition(startX, y), "O");
            graphics.putString(new TerminalPosition(endX, y), "O");
        }
        //create top and bottom bars of the frame
        for(int x = startX + 1; x < endX; x++){
            graphics.putString(new TerminalPosition(x, startY), "O");
            graphics.putString(new TerminalPosition(x, endY), "O");
        }

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    protected void drawCursor(TextGraphics graphics, int drawX, int drawY){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(drawX-2, drawY), "->");
    }
}
