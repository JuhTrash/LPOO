package org.g52.project.dungeon.model;

import org.g52.project.dungeon.model.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private DungeonPlayer dungeonPlayer;
    private List<Wall> walls = new ArrayList<>();
    private List<DungeonMonster> dungeonMonsters = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Shopkeeper> shopkeepers = new ArrayList<>();
    private Exit exit;
    private int width, height;
    private int monsterNum = 5, wallNum = 2, itemNum = 10, shopkeeperNum = 1;

    public Dungeon(int width, int height, DungeonPlayer dungeonPlayer) {
        this.width = width;
        this.height = height;
        this.dungeonPlayer = dungeonPlayer;
        this.walls = generateWalls();
        this.dungeonMonsters = generateRandomMonsters(monsterNum);
        this.items = generateItems(itemNum);
        this.shopkeepers = generateShopkeepers(shopkeeperNum);
        exit = new Exit(this);
    }

    public Dungeon(int width, int height, DungeonPlayer dungeonPlayer, List<DungeonMonster> monsters, List<Wall> walls, List<Item> items, List<Shopkeeper> shopkeepers, Exit exit) {
        this.width = width;
        this.height = height;
        this.dungeonPlayer = dungeonPlayer;
        this.walls = walls;
        this.dungeonMonsters = monsters;
        this.items = items;
        this.shopkeepers = shopkeepers;
        this.exit = exit;
    }

    public DungeonMonster getMonster(int i){
        if (i<0 || i >= dungeonMonsters.size()) return null;
        return dungeonMonsters.get(i);
    }

    private List<Wall> generateWalls(){
        List<Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++){
            walls.add(new Wall(x,0));
            walls.add(new Wall(x,height-1));
        }
        for (int y = 0; y < height; y++){
            walls.add(new Wall(0,y));
            walls.add(new Wall(width-1,y));
        }

        return walls;
    }


    private List<DungeonMonster> generateRandomMonsters(int monsterNum){
        List<DungeonMonster> dungeonMonsters = new ArrayList<>();
        for (int i = 0; i < monsterNum; i++){
            DungeonMonster m1 = new DungeonMonster(this);
            dungeonMonsters.add(m1);
        }
        return dungeonMonsters;
    }


    private List<Item> generateItems(int itemNum){
        Random random = new Random();
        List<Item> items = new ArrayList<>();

        for(int i = 0; i < itemNum; i++){
            int chance = random.nextInt(2);
            if (chance%2 == 0)items.add(generateItem());
            else items.add(new Coin(this));
        }

        return items;
    }

    public Consumable generateItem(){
        Random random = new Random();
        int rand = random.nextInt(3);
        Consumable i1;
        if (rand % 3 == 0){
            i1 = new HealingPotion(this);
        }
        else if (rand % 3 == 1){
            i1 = new MPPotion(this);
        }
        else{
            i1 = new ElementChanger(this);
        }
        return i1;
    }

    private List<Shopkeeper> generateShopkeepers(int shopkeeperNum) {
        List<Shopkeeper> shopkeepers = new ArrayList<>();
        for (int i = 0; i < shopkeeperNum; i++){
            Shopkeeper m1 = new Shopkeeper(this);
            shopkeepers.add(m1);
        }
        return shopkeepers;
    }

    public void setMonsters(List<DungeonMonster> dungeonMonsters){
        this.dungeonMonsters = dungeonMonsters;
    }

    public DungeonPlayer getPlayer() { return this.dungeonPlayer; }
    public List<Wall> getWalls() { return this.walls; }
    public List<DungeonMonster> getMonsters() { return this.dungeonMonsters; }
    public List<Item> getItems() { return this.items; }
    public List<Shopkeeper> getShopkeepers() { return this.shopkeepers; }

    public int getWidth(){ return this.width; }
    public int getHeight(){ return this.height; }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Exit getExit() { return exit; }
}
