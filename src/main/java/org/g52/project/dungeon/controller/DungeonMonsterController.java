package org.g52.project.dungeon.controller;

import org.g52.project.dungeon.model.DungeonMonster;
import org.g52.project.Position;
import org.g52.project.dungeon.model.Dungeon;

import java.util.List;
import java.util.Random;

public class DungeonMonsterController extends MoveableEntityController{
    private List<DungeonMonster> dungeonMonsters;
    DungeonMonsterController(Dungeon dungeon) {
        super(dungeon);
        this.dungeonMonsters = dungeon.getMonsters();
    }

    public void moveMonsters(){
        Random random = new Random();
        for(DungeonMonster dungeonMonster : dungeonMonsters){
            Position p1;
            do{
                p1 = chooseDirection(dungeonMonster, random);
            } while (!dungeonMonster.setPosition(p1, dungeon));
        }
    }

    public Position chooseDirection(DungeonMonster dungeonMonster, Random random){
        int x = random.nextInt(3) - 1;
        int y = (x == 0) ? (random.nextInt(3) - 1) : 0;
        return new Position(dungeonMonster.getPosition().getX()+x, dungeonMonster.getPosition().getY()+y);
    }
}
