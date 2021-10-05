package org.g52.project.battle.model;

import org.g52.project.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Entity {
    //stats
    protected int maxHp;
    protected int hp;
    protected int maxMp;
    protected int mp;
    protected int defense;
    protected int strength;
    protected int magic;
    protected double hitRate;

    protected int level;

    protected Elements element;

    protected int baseAttackDamage;

    protected List<MagicAttack> attacks = new ArrayList<>();


    public Entity(int maxHp, int maxMp, Elements element, int defense, int strength, int magic, double hitRate, int baseAttackDamage, List <MagicAttack> attacks){
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.maxMp = maxMp;
        this.mp = this.maxMp;
        this.element = element;
        this.defense = defense;
        this.strength = strength;
        this.magic = magic;
        this.hitRate = hitRate;
        this.baseAttackDamage = baseAttackDamage;
        this.attacks = attacks;

    }

    public Entity(Elements element){
        Random rand = new Random();
        this.level = rand.nextInt(3) + 1;
        this.maxHp = 10 * level;
        this.hp = this.maxHp;
        this.maxMp = 50 * level;
        this.mp = this.maxMp;
        this.element = element;

        this.defense = level + rand.nextInt(2);

        this.strength = level + rand.nextInt(2) ;

        this.magic = level + rand.nextInt(2);

        this.hitRate = (rand.nextInt(10) + 80);

        this.baseAttackDamage = rand.nextInt(20) + 10;


        for (int i = 0; i<3; i++){ // just 3 randomly generated attacks

            MagicAttack atk = new MagicAttack();
            attacks.add(atk);
        }

    }

    public boolean misses(){
        Random rand = new Random();
        double chance = rand.nextDouble();
        if (chance < hitRate){
            return false;
        }
        return true;
    }

    public String attack(Entity entity){
        if (misses()){
            return "missed!";
        }
        double damage = this.baseAttackDamage * this.strength * (entity.getDefense()/((Math.log10(entity.getDefense())+1)*10));

        int finalDamage = (int)damage;

        entity.setHp(entity.getHp()-finalDamage);

        return "dealt " + finalDamage + " damage with a normal Attack!";

    }

    public String magicAttack(Entity entity, int attackNumber){
        if (misses()){
            return "missed!";
        }
        String text = "";
        if (this.mp >= this.attacks.get(attackNumber).getMpCost()){
            this.mp -= this.attacks.get(attackNumber).getMpCost();
            double damage = this.attacks.get(attackNumber).getBaseMagicDamage() * this.magic;
            if (entity.getElement() == this.attacks.get(attackNumber).getElement()){
                damage *= 0.50;
                text = "hit a resistance and ";
            }
            else if(entity.getElement().getWeakness() == this.attacks.get(attackNumber).getElement() ){
                damage *= 2;
                text = "hit a weakness and ";
            }

            int finalDamage = (int)damage;

            entity.setHp(entity.getHp()-finalDamage);
            return text + "dealt " + finalDamage + " damage with a "+ this.attacks.get(attackNumber).getName() +"!";

        }
        else{
            return "does not have enough MP!";
        }
    }

    public boolean isDead(){
        if (this.hp <= 0){
            return true;
        }
        return false;
    }

    public List<MagicAttack> getAttacks() {
        return attacks;
    }

    public int getHp() {
        return hp;
    }
    public int getMaxHp() {
        return maxHp;
    }

    public int getMp() {
        return mp;
    }
    public int getMaxMp() {
        return maxMp;
    }

    public double getDefense() {
        return defense;
    }

    public Elements getElement() {
        return element;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMp(int mp) { this.mp = mp; }
    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getLevel() {
        return level;
    }

    public int getMagic() {
        return magic;
    }

    public int getStrength() {
        return strength;
    }
}
