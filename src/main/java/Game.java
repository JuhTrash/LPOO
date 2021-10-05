import org.g52.project.*;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) {
        try {
            startGame();
        }
        catch (IOException | FontFormatException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    private static void startGame() throws IOException, FontFormatException, URISyntaxException {
        State gameState = new DungeonState();
        Options option;
        int state = 0;
        while(true){
            switch (state) {
                case 0 -> {
                    gameState = new DungeonState(gameState);
                    option = gameState.step();
                    if (option == Options.BATTLE) state = 1;
                    if (option == Options.SHOP) state = 3;
                    if (option == Options.MENU) state = 4;
                    if (option == Options.QUIT) state = 2;
                }
                case 1 -> {
                    gameState = new BattleState(gameState);
                    option = gameState.step();
                    if (option == Options.QUIT) state = 2;
                    if (option == Options.DUNGEON) state = 0;
                }
                case 2 -> {
                    gameState.getScreen().close();
                    return;
                }
                case 3 -> {
                    gameState = new ShopState(gameState);
                    option = gameState.step();
                    if (option == Options.DUNGEON) state = 0;
                }
                case 4 ->{
                    gameState = new DungeonMenuState(gameState);
                    option = gameState.step();
                    if (option == Options.DUNGEON) state = 0;
                }
            }
        }
    }
}
