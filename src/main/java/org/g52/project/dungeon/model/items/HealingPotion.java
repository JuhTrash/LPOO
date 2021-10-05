package org.g52.project.dungeon.model.items;

import org.g52.project.Player;
import org.g52.project.dungeon.model.Dungeon;

public class HealingPotion extends Potion{
    public HealingPotion(int x, int y, int type, int restore) {
        super(x, y, type, restore);
    }
    public HealingPotion(Dungeon dungeon, int type) {
        super(dungeon, type);
        assignValueToType(dungeon.getPlayer().getMaxHp());
    }

    public HealingPotion(Dungeon dungeon) {
        super(dungeon);
        assignValueToType(dungeon.getPlayer().getMaxHp());
    }

    public HealingPotion(int type, int hp){
        super(type);
        assignValueToType(hp);
    }

    private void assignValueToType(int hp){
        switch (this.type) {
            case LESSER -> {
                this.restore = 20;
                this.cost = 10;
            }
            case MEDIUM -> {
                this.restore = 50;
                this.cost = 20;
            }
            case GREATER -> {
                this.restore = 100;
                this.cost = 40;
            }
            case FULL -> {
                this.restore = hp;
                this.cost = 80;
            }
        }
    }

    @Override
    public String use(Player player){
        player.setHp(Math.min(player.getHp() + restore, player.getMaxHp()));
        return this.getType() + " restored " + restore + "hp!";
    }

    public String getType(){
        return "hp " + this.type.getName();
    }
}
