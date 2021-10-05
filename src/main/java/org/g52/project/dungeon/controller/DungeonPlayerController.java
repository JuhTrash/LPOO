package org.g52.project.dungeon.controller;

import org.g52.project.dungeon.model.DungeonPlayer;
import org.g52.project.Position;
import org.g52.project.dungeon.model.Dungeon;

public class DungeonPlayerController extends MoveableEntityController{
    private final DungeonPlayer dungeonPlayer;

    public DungeonPlayerController(Dungeon dungeon){
        super(dungeon);
        this.dungeonPlayer = this.dungeon.getPlayer();
    }

    public void parseInput(DungeonController.INPUT input) {
        Position pos;
        switch(input){
            case UP:
                pos = new Position(dungeonPlayer.getPosition().getX(), dungeonPlayer.getPosition().getY() - 1);
                break;
            case DOWN:
                pos = new Position(dungeonPlayer.getPosition().getX(), dungeonPlayer.getPosition().getY() + 1);
                break;
            case LEFT:
                pos = new Position(dungeonPlayer.getPosition().getX() - 1, dungeonPlayer.getPosition().getY());
                break;
            case RIGHT:
                pos = new Position(dungeonPlayer.getPosition().getX() + 1, dungeonPlayer.getPosition().getY());
                break;
            default:
                return;
        }
        dungeonPlayer.setPosition(pos, dungeon);
    }

    public int checkMonsterCollisions(){
        return dungeonPlayer.beginBattle(dungeon);
    }

    public boolean checkExit(){
        return dungeonPlayer.exitRoom(dungeon);
    }

    public String checkItemCollision(){
        return dungeonPlayer.pickUpItem(dungeon);
    }

    public DungeonPlayer getDungeonPlayer() {
        return dungeonPlayer;
    }

    public int checkShop() { return dungeonPlayer.enterShop(dungeon); }
}