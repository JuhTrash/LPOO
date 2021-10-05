package org.g52.project.dungeon.controller;

import org.g52.project.Options;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.g52.project.dungeon.model.Dungeon;
import org.g52.project.dungeon.view.DungeonView;

import java.io.IOException;

public class DungeonController {
    Dungeon dungeon;
    DungeonView dungeonView;

    DungeonPlayerController dungeonPlayerController;
    DungeonMonsterController dungeonMonsterController;


    public DungeonController(Dungeon dungeon, DungeonView dungeonView){
        this.dungeon = dungeon;
        this.dungeonView = dungeonView;

        this.dungeonPlayerController = new DungeonPlayerController(dungeon);
        this.dungeonMonsterController = new DungeonMonsterController(dungeon);
    }

    public Options step() throws IOException {
        dungeonView.draw();

        INPUT input = getInput();
        emptyBuffer();

        Options state  = parseInput(input);

        if (state != Options.CONTINUE) {
            emptyBuffer();
            return state;
        }

        state = checkForExit();
        if (state != Options.CONTINUE){
            return state;
        }

        checkForItem();

        state = checkForMonster();
        if (state != Options.CONTINUE){
            dungeonView.draw();
            return state;
        }

        dungeonMonsterController.moveMonsters();

        state = checkForMonster();
        if (state != Options.CONTINUE) dungeonView.draw();

        return state;
    }


    public enum INPUT {UP, DOWN, LEFT, RIGHT, QUIT, MENU, SHOP, NONE}

    private INPUT getInput() throws IOException {
        KeyStroke keyStroke = dungeonView.getScreen().readInput();

        if (keyStroke.getKeyType() == KeyType.EOF) return INPUT.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter() == 'q' ||keyStroke.getCharacter() == 'Q')) return INPUT.QUIT;

        switch(keyStroke.getKeyType()){
            case ArrowUp: return INPUT.UP;
            case ArrowDown: return INPUT.DOWN;
            case ArrowLeft: return INPUT.LEFT;
            case ArrowRight: return INPUT.RIGHT;
            case Enter: return INPUT.SHOP;
            case Character:
                switch(keyStroke.getCharacter()){
                    case 'w':
                    case 'W': return INPUT.UP;
                    case 's':
                    case 'S': return INPUT.DOWN;
                    case 'a':
                    case 'A': return INPUT.LEFT;
                    case 'd':
                    case 'D': return INPUT.RIGHT;
                    case 'M':
                    case 'm': return INPUT.MENU;
                }
        }

        return INPUT.NONE;
    }

    private Options parseInput(INPUT input) throws IOException{
        if(input == INPUT.QUIT) return Options.QUIT;
        else if (input == INPUT.SHOP) return checkShop();
        else if (input == INPUT.MENU) return Options.MENU;
        else dungeonPlayerController.parseInput(input);
        return Options.CONTINUE;
    }

    private void checkForItem() throws IOException{
        String itemCaught = dungeonPlayerController.checkItemCollision();
        if (!itemCaught.equals(""))dungeonView.draw();
        dungeonView.drawText(itemCaught);
    }


    public Options checkForMonster(){
        int monster = dungeonPlayerController.checkMonsterCollisions();
        if (monster == -1) return Options.CONTINUE;
        else {
            Options.MONSTER.setValue(monster);
            return Options.MONSTER;
        }
    }

    public Options checkForExit(){
        if (dungeonPlayerController.checkExit()) return Options.DUNGEON;
        return Options.CONTINUE;
    }

    private void emptyBuffer() throws IOException {
        KeyStroke keyStroke;
        do{
            keyStroke = dungeonView.getScreen().pollInput();
        }
        while (keyStroke != null);
    }

    private Options checkShop(){
        int shop = dungeonPlayerController.checkShop();
        if (shop == -1) return Options.CONTINUE;
        else {
            Options.SHOPKEEPER.setValue(shop);
            return Options.SHOPKEEPER;
        }
    }
}
