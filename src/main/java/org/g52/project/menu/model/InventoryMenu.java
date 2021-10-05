
package org.g52.project.menu.model;

import org.g52.project.dungeon.model.items.Consumable;

import java.util.Map;

public class InventoryMenu extends ItemMenu {

    public InventoryMenu(Map<Consumable,Integer> inventory){
        super();
        inventory.forEach((k, v) -> {
            menuOptions.add(String.valueOf(v) + "X " + k.getType()+ "\n");
            items.add(k);
        });
    }

}