package com.twenty48;

public class Tile {
    private int value;
    private boolean merged;

    public Tile() {
        value = 0;
        merged = false;
    }
    public Tile(int value) {
        this.value = value;
        merged = false;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean isMerged(){
        return merged;
    }

    public void setUnmerged(){
        merged = false;
    }

    public void setMerged(){
        merged = true;
    }
}
