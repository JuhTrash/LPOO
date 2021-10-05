package org.g52.project.battle.model;

import org.g52.project.Elements;

import java.util.Random;

public class MagicAttack {
    private Elements element;
    protected int baseMagicDamage;
    private int mpCost;

    public MagicAttack(){
        Random rand = new Random();
        this.baseMagicDamage = 5+rand.nextInt(20);

        int i = rand.nextInt(6) + 1;

        this.element = Elements.NULL.getElement(i);

        this.mpCost = rand.nextInt(5) + 1;
    }

    public MagicAttack(int baseMagicDamage, Elements element, int mpCost){
        this.element = element;
        this.baseMagicDamage = baseMagicDamage;
        this.mpCost = mpCost;
    }

    public String getName(){
        return element.getName() + " Attack";
    }

    public Elements getElement() {
        return element;
    }
    public int getBaseMagicDamage(){
        return baseMagicDamage;
    }

    public int getMpCost() {
        return mpCost;
    }
}
