package org.g52.project.menu.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    protected List<String> menuOptions;

    public Menu(){
        this.menuOptions = new ArrayList<>();
    }

    public List<String> getMenuOptions(){
        return this.menuOptions;
    }
}
