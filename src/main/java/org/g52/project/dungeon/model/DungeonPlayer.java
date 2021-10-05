package org.g52.project.dungeon.model;

import org.g52.project.Elements;
import org.g52.project.Inventory;
import org.g52.project.Player;
import org.g52.project.Position;
import org.g52.project.dungeon.model.items.*;
import java.util.List;

public class DungeonPlayer extends MovableEntity implements Player {

    private int maxHp;
    private int maxMp;
    private int hp;
    private int mp;
    private Inventory inventory;
    int coins = 0;


    public DungeonPlayer(int x, int y, int maxHp, int maxMp, Elements element) {
        super(x, y, element);
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.maxMp = maxMp;
        this.mp = this.maxMp;
        this.inventory = new Inventory();
        inventory.initializeInventoryPlayer(maxHp,maxMp);
    }

    public int getHp() {return hp;}
    public int getMaxHp() {
        return maxHp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMp() {return mp;}
    public int getMaxMp() {
        return maxMp;
    }

    public void setMp(int mp) { this.mp = mp; }
    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    DungeonPlayer(Dungeon dungeon){
        super(dungeon);
    }

    public int beginBattle(Dungeon dungeon){
        for (int i = 0; i < dungeon.getMonsters().size(); i++){
            if (dungeon.getMonsters().get(i).getPosition().equals(new Position(this.getPosition().getX()-1,this.getPosition().getY()))||
                    dungeon.getMonsters().get(i).getPosition().equals(new Position(this.getPosition().getX()+1,this.getPosition().getY()))||
                    dungeon.getMonsters().get(i).getPosition().equals(new Position(this.getPosition().getX(),this.getPosition().getY()-1))||
                    dungeon.getMonsters().get(i).getPosition().equals(new Position(this.getPosition().getX(),this.getPosition().getY()+1))){
                return i;
            }
        }
        return -1;
    }

    public boolean exitRoom(Dungeon dungeon){
        return dungeon.getExit().getPosition().equals(position);
    }

    public int enterShop(Dungeon dungeon){
        for (int i = 0; i < dungeon.getShopkeepers().size(); i++){
            if (dungeon.getShopkeepers().get(i).getPosition().equals(new Position(this.getPosition().getX()-1,this.getPosition().getY()))||
                    dungeon.getShopkeepers().get(i).getPosition().equals(new Position(this.getPosition().getX()+1,this.getPosition().getY()))||
                    dungeon.getShopkeepers().get(i).getPosition().equals(new Position(this.getPosition().getX(),this.getPosition().getY()-1))||
                    dungeon.getShopkeepers().get(i).getPosition().equals(new Position(this.getPosition().getX(),this.getPosition().getY()+1))){
                return i;
            }
        }
        return -1;
    }

    public String pickUpItem(Dungeon dungeon){
        String text = "";
        for (int i = 0; i < dungeon.getItems().size(); i++){
            if (dungeon.getItems().get(i).getPosition().equals(this.position)){
                if (dungeon.getItems().get(i) instanceof Coin){
                    coins += ((Coin) dungeon.getItems().get(i)).getAmount();
                    text = "Got " + ((Coin) dungeon.getItems().get(i)).getAmount() + " Coins!";
                }
                else{
                    inventory.addAmount((Consumable) dungeon.getItems().get(i),1);
                    text = "Got " + dungeon.getItems().get(i).getType() + "!";
                }
                List<Item> i1 = dungeon.getItems();
                i1.remove(i);
                dungeon.setItems(i1);
                return text;
            }
        }
        return "";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() { return coins; }
}
