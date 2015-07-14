package com.twenty48;

public class Tile {
    private int value;
    private int x, y, prevX, prevY;
    private boolean merged;

    public Tile(int y, int x, int value) {
        this.value = value;
        this.y = y;
        this.x = x;
        this.prevX = x;
        this.prevY = y;
        merged = false;
    }

    public Tile(Tile tile) {
        this.value = tile.value;
        this.x = tile.y;
        this.y = tile.y;
        this.prevX = tile.prevX;
        this.prevY = tile.prevY;
        this.merged = tile.merged;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean getMerged(){
        return merged;
    }

    public void setMerged(boolean merged){
        this.merged = merged;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }
}
