package org.g52.project;

public enum Elements {
    FIRE(1,"fire"), WATER(2, "water"), ELECTRICITY(3, "electricity"), PLANT(4, "plant"), LIGHT(5, "light"), DARK(6, "dark"), NULL(0, "none");

    int value;
    String name;
    Elements(int i, String name) {
        value = i;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName(){
        return name;
    }

    public void setValue(int i){
        this.value = i;
    }

    public Elements getWeakness(){
        if (this.value == 0) return NULL;
        else if (this.value == 5) return DARK;
        else if (this.value == 6) return LIGHT;
        else if (this.value == 4) return FIRE;
        for(Elements v : values()){
            if(v.getValue() == this.getValue() + 1){
                return v;
            }
        }
        return null;
    }

    public Elements getElement(int i){
        for(Elements v : values()){
            if( v.getValue() == i){
                return v;
            }
        }
        return null;
    }


}
