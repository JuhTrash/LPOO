package org.g52.project.menu.model;


import org.g52.project.dungeon.model.items.Consumable;

import java.util.ArrayList;
import java.util.List;


public class ItemMenu extends Menu{
    List<Consumable> items;

    public ItemMenu(){
        super();
        items = new ArrayList<>();
    }

    public List<Consumable> getItems() {
        return items;
    }
}
