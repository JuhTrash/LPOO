package org.g52.project.menu.controller;

import org.g52.project.Options;
import org.g52.project.menu.model.Menu;
import org.g52.project.menu.view.MenuView;

import java.io.IOException;

public class MenuInputController {
    protected Options currentButton;
    protected int columns;
    protected int buttonNum;
    protected Menu menu;
    protected MenuView menuView;

    public MenuInputController(int buttonNum, int columns, Options currentButton, Menu menu, MenuView menuView){
        this.menu = menu;
        this.menuView = menuView;
        this.buttonNum = buttonNum;
        this.currentButton = currentButton;
        this.columns = columns;
    }

    protected Options parseInput(MenuInput input) throws IOException{
        if (input == MenuInput.LEFT){
            if (currentButton.getValue() > 0) currentButton = currentButton.back();
        }
        if (input == MenuInput.RIGHT){
            if (currentButton.getValue() < buttonNum) currentButton = currentButton.next();
        }

        if (input == MenuInput.UP){
            if (currentButton.getValue() - columns >= 0) currentButton = currentButton.up(columns);
        }
        if (input == MenuInput.DOWN){
            if (currentButton.getValue()  + columns <= buttonNum) currentButton = currentButton.down(columns);
        }
        if(input == MenuInput.QUIT){
            return Options.QUIT;
        }

        if (input == MenuInput.ENTER){
            return currentButton;
        }

        return Options.CONTINUE;
    }

    public Options step(MenuInput input) throws IOException{
        return parseInput(input);
    }

    public Options getCurrentButton() {
        return currentButton;
    }
}
