package org.g52.project.dungeon.model.items;

import org.g52.project.dungeon.model.Dungeon;

import java.util.Random;

public abstract class Potion extends Consumable{
    protected enum PotionType{
        LESSER(0, "lesser potion"), MEDIUM(1, "medium potion"), GREATER(2, "greater potion"), FULL(3, "full potion"), NULL(-1, "");
        private int v;
        private String name;
        PotionType(int i, String name){
            v = i;
            this.name = name;}

        public String getName(){
            return name;
        }

        public PotionType getType(int i){
            for(PotionType v : values()){
                if( v.getV() == i){
                    return v;
                }
            }
            return null;
        }

        public int getV() { return v; }
    }
    protected PotionType type;
    protected int restore;

    Potion(int x, int y, int type, int restore) {
        super(x, y);
        this.type = PotionType.NULL.getType(type);
        this.restore = restore;
    }

    Potion(Dungeon dungeon, int type) {
        super(dungeon);
        this.type = PotionType.NULL.getType(type);
    }

    Potion(int type) {
        this.type = PotionType.NULL.getType(type);
    }

    Potion(Dungeon dungeon) {
        super(dungeon);

        Random random = new Random();
        double type1 = random.nextDouble();

        if (type1 < 0.1) this.type = PotionType.NULL.getType(3);
        else if (type1 < 0.25) this.type = PotionType.NULL.getType(2);
        else if (type1 < 0.5) this.type = PotionType.NULL.getType(1);
        else this.type = PotionType.NULL.getType(0);
    }

    public int getRestore() {
        return restore;
    }
}
