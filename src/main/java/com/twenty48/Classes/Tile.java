package com.twenty48.Classes;

public class Tile {
    private int value;
    private int x, y, prevX, prevY;
    private boolean merged;

    public Tile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.prevX = -1;
        this.prevY = -1;
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

    public void setMerged(boolean merged){
        this.merged = merged;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }
}
