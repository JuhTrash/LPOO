package org.g52.project.dungeon.menu.model;

import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Player;
import org.g52.project.battle.model.BattlePlayer;
import org.g52.project.dungeon.model.DungeonPlayer;
import org.g52.project.dungeon.model.items.Consumable;

public class PlayerInfo implements Player {
    Inventory inventory;

    private int exp;
    private int expToNext;
    private int level;

    private int maxHp;
    private int hp;

    private int maxMp;
    private int mp;

    private double defense;
    private int strength;
    private int magic;

    private int coins;

    private Elements element;

    public PlayerInfo(int exp,int expToNext,int level,int maxHp,int hp, int mp, int maxMp, int defense, int strength, int magic, Elements element, int coins, Inventory inventory){
        this.exp = exp;
        this.expToNext = expToNext;
        this.level = level;
        this.maxHp = maxHp;
        this.hp = hp;
        this.mp = mp;
        this.maxMp = maxMp;
        this.defense = defense;
        this.strength = strength;
        this.magic = magic;
        this.element = element;
        this.coins = coins;
        this.inventory = inventory;

    }

    public PlayerInfo(BattlePlayer bPlayer, DungeonPlayer dPlayer){
        this.exp = bPlayer.getExp();
        this.expToNext = bPlayer.getExpToNext();
        this.level = bPlayer.getLevel();
        this.maxHp = bPlayer.getMaxHp();
        this.hp = bPlayer.getHp();
        this.mp = bPlayer.getMp();
        this.maxMp = bPlayer.getMaxMp();
        this.defense = bPlayer.getDefense();
        this.strength = bPlayer.getStrength();
        this.magic = bPlayer.getMagic();
        this.element = bPlayer.getElement();
        this.inventory = dPlayer.getInventory();
        this.coins = dPlayer.getCoins();
    }

    public int getMagic() {
        return magic;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    public Elements getElement() {
        return element;
    }

    public int getExpToNext() {
        return expToNext;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMp() {
        return mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getStrength() {
        return strength;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setElement(Elements element) {
        this.element = element;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setExpToNext(int expToNext) {
        this.expToNext = expToNext;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }
}

