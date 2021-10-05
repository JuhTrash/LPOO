package org.g52.project.dungeon.model.items;

import org.g52.project.Player;
import org.g52.project.dungeon.model.Dungeon;

public class MPPotion extends Potion{
    public MPPotion(int x, int y, int type, int restore) {
        super(x, y, type, restore);
    }
    public MPPotion(Dungeon dungeon, int type) {
        super(dungeon, type);
        assignValueToType(dungeon.getPlayer().getMaxMp());
    }

    public MPPotion(Dungeon dungeon) {
        super(dungeon);
        assignValueToType(dungeon.getPlayer().getMaxMp());
    }

    public MPPotion(int type, int mp){
        super(type);
        assignValueToType(mp);
    }

    private void assignValueToType(int mp){
        switch (this.type) {
            case LESSER -> {
                this.restore = 5;
                this.cost = 10;
            }
            case MEDIUM -> {
                this.restore = 10;
                this.cost = 15;
            }
            case GREATER -> {
                this.restore = 20;
                this.cost = 30;
            }
            case FULL -> {
                this.restore = mp;
                this.cost = 60;
            }
        }
    }

    @Override
    public String use(Player player){
        player.setMp(Math.min(player.getMp() + restore, player.getMaxMp()));
        return this.getType() + " restored " + restore + "mp!";
    }

    public String getType(){
        return "mp " + this.type.getName();
    }
}
