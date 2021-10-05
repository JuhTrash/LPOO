package org.g52.project.dungeon.model.items;

import org.g52.project.Player;
import org.g52.project.Elements;
import org.g52.project.dungeon.model.Dungeon;

import java.util.Random;

public class ElementChanger extends Consumable{
    private final Elements element;
    public ElementChanger(int x, int y, int element) {
        super(x, y);
        this.element = Elements.NULL.getElement(element);
        Random rand = new Random();
        this.cost = 30 + rand.nextInt(11);
    }

    public ElementChanger(Dungeon dungeon, int element) {
        super(dungeon);
        this.element = Elements.NULL.getElement(element);
        Random rand = new Random();
        this.cost = 30 + rand.nextInt(11);
    }


    public ElementChanger(int element) {
        this.element = Elements.NULL.getElement(element);
        Random rand = new Random();
        this.cost = 30 + rand.nextInt(11);
    }

    public ElementChanger(Dungeon dungeon) {
        super(dungeon);

        Random random = new Random();
        int element = random.nextInt(6) + 1;

        this.element = Elements.NULL.getElement(element);
        Random rand = new Random();
        this.cost = 30 + rand.nextInt(11);
    }

    public String getType(){
        return this.element.getName() + " changer";
    }

    public String use(Player player) {
        player.setElement(element);
        return this.getType() + " changed the player's element to " + element.getName() + "!";
    }
}
