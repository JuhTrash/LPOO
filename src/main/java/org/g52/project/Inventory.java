package org.g52.project;

import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.dungeon.model.items.Consumable;
import org.g52.project.dungeon.model.items.ElementChanger;
import org.g52.project.dungeon.model.items.HealingPotion;
import org.g52.project.dungeon.model.items.MPPotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Inventory {
    Map<Consumable,Integer> inventory;

    public Inventory(){
        inventory = new HashMap<>();
    }

    public void initializeInventoryPlayer(int hp, int mp){
        inventory.put(new HealingPotion(0,hp),0);
        inventory.put(new HealingPotion(1,hp),0);
        inventory.put(new HealingPotion(2,hp),0);
        inventory.put(new HealingPotion(3,hp),0);

        inventory.put(new MPPotion(0,mp),0);
        inventory.put(new MPPotion(1,mp),0);
        inventory.put(new MPPotion(2,mp),0);
        inventory.put(new MPPotion(3,mp),0);

        inventory.put(new ElementChanger(1),0);
        inventory.put(new ElementChanger(2),0);
        inventory.put(new ElementChanger(3),0);
        inventory.put(new ElementChanger(4),0);
        inventory.put(new ElementChanger(5),0);
        inventory.put(new ElementChanger(6),0);
    }

    public void initializeInventoryShopkeeper(Dungeon dungeon){
        Random random = new Random();
        while(inventory.size() < 6){
            Consumable i1 = dungeon.generateItem();
            if (getAmountInInventory(i1) == 0){
                addAmount(i1, random.nextInt(5) + 1);
            }
        }
    }

    public Map<Consumable, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Consumable, Integer> inventory) {
        this.inventory = inventory;
    }

    public void addAmount(Consumable consumable, int amount){
        for (Map.Entry<Consumable, Integer> entry : inventory.entrySet()) {
            if ( entry.getKey().getType().equals(consumable.getType())){
                entry.setValue(entry.getValue() + amount);
                return;
            }
        }
        inventory.put(consumable,amount);
    }

    public void removeAmount(Consumable consumable, int amount){
        for (Map.Entry<Consumable, Integer> entry : inventory.entrySet()) {
            if ( entry.getKey().getType().equals(consumable.getType())){
                if (entry.getValue() - amount >= 0) entry.setValue(entry.getValue() - amount);
                return;
            }
        }
    }

    public int getAmountInInventory(Consumable consumable){
        for (Map.Entry<Consumable, Integer> entry : inventory.entrySet()) {
            if ( entry.getKey().getType().equals(consumable.getType())){
                return entry.getValue();
            }
        }
        return 0;
    }
}
