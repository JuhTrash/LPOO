package org.g52.project;

public enum Options {
     CONTINUE(-1), QUIT(-2), DUNGEON(-3), MONSTER(-4),SHOPKEEPER(-5), BATTLE(-6), SHOP(-7), MENU(-8), OPTION1(0), OPTION2(1), OPTION3(2), OPTION4(3), OPTION5(4), OPTION6(5),
    OPTION7(6), OPTION8(7), OPTION9(8), OPTION10(9), OPTION11(10), OPTION12(11), OPTION13(12), OPTION14(13);

    int value;
    private Options(int i){
        value = i;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int i){
        this.value = i;
    }

    public Options back(){
        for(Options v : values()){
            if( v.getValue() == this.getValue()-1){
                return v;
            }
        }
        return null;
    }

    public Options next(){
        for(Options v : values()){
            if( v.getValue() == this.getValue()+1){
                return v;
            }
        }
        return null;
    }

    public Options up(int num){
        for(Options v : values()){
            if( v.getValue() == this.getValue()-num){
                return v;
            }
        }
        return null;
    }

    public Options down(int num){
        for(Options v : values()){
            if( v.getValue() == this.getValue()+num){
                return v;
            }
        }
        return null;
    }
}
