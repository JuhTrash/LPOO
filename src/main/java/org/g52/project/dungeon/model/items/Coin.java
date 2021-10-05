package org.g52.project.dungeon.model.items;

import org.g52.project.dungeon.model.Dungeon;

import java.util.Random;

public class Coin extends Item{
    private int amount;
    public Coin(int x, int y) {
        super(x, y);
        Random rand = new Random();
        amount = 50 + rand.nextInt(101);
    }

    public Coin(Dungeon dungeon) {
        super(dungeon);
        Random rand = new Random();
        amount = 50 + rand.nextInt(101);
    }

    public int getAmount(){
        return amount;
    }

    public String getType(){
        return "coin";
    }
}
