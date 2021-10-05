package org.g52.project.dungeon.model;

import org.g52.project.Position;

import java.util.Random;

abstract public class Entity{
    protected Position position;

    public Entity(int x, int y) {
        this.position = new Position(x, y);
    }

    public Entity(Dungeon dungeon) {
        Random random = new Random();
        int y, x;
        Position pos;
        do{
            y = random.nextInt(dungeon.getHeight());
            x = random.nextInt(dungeon.getWidth());
            pos = new Position(x, y);
        } while (!setPosition(pos, dungeon));
    }

    public Entity(){}

    public boolean setPosition(Position position, Dungeon dungeon){
        if (validPosition(position, dungeon)){
            this.position = position;
            return true;
        }
        return false;
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean validPosition(Position position, Dungeon dungeon){
        if (position.getX() < 0||
            position.getY() < 0 ||
            position.getX() >= dungeon.getWidth() ||
            position.getY() >= dungeon.getHeight()) return false;
        for (Wall wall: dungeon.getWalls()){
            if (wall.getPosition().equals(position)) return false;
        }
        for (Shopkeeper shopkeeper: dungeon.getShopkeepers()){
            if (shopkeeper.getPosition().equals(position)) return false;
        }
        for (DungeonMonster dungeonMonster : dungeon.getMonsters()){
            if (dungeonMonster.getPosition().equals(position)) return false;
        }
        if (position.equals(dungeon.getPlayer().getPosition())) return false;
        return true;
    }
}

