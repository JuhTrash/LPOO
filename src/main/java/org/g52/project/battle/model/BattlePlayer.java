package org.g52.project.battle.model;

import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Player;
import org.g52.project.dungeon.model.items.Consumable;

import java.util.List;
import java.util.Random;


public class BattlePlayer extends Entity implements Player {
    private int exp;
    private int expToNext;
    Inventory inventory;

    public BattlePlayer(int hp, int mp, Elements element, int defense, int strength, int magic, double hitRate, int baseAttackDamage, List<MagicAttack> attacks) {
        super(hp,mp, element, defense, strength, magic, hitRate, baseAttackDamage, attacks);
        expToNext = 10;
        exp = 0;
        level = 1;

        this.inventory = new Inventory();
        this.inventory.initializeInventoryPlayer(maxHp,maxMp);
    }

    public BattlePlayer(Elements element){
        super(element);
        level = 1;
        expToNext = level * 10;
        exp = 0;

        this.inventory = new Inventory();
        this.inventory.initializeInventoryPlayer(maxHp,maxMp);
    }

    public String increaseExp(){
        Random rand = new Random();
        int expGained = 5 + rand.nextInt(expToNext/5);
        exp += expGained;
        if (levelUp()){
            return expGained + ". Level up!";
        }
        return String.valueOf(expGained);
    }

    public Boolean levelUp(){
        if (exp >= expToNext){
            exp -= expToNext;
            level ++;

            hp += 10;
            defense ++;
            strength ++;
            magic ++;

            expToNext *= 2;
            return true;
        }
        return false;
    }

    public String useItem(Consumable consumable){
        if (inventory.getAmountInInventory(consumable) == 0){
            return "No items of this type to use!";
        }
        else{
            consumable.use(this);
            inventory.removeAmount(consumable,1);
            return "You used one " + consumable.getType() + "!";
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setElement(Elements element) {
        this.element = element;
    }

    public int getExpToNext() {
        return expToNext;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
