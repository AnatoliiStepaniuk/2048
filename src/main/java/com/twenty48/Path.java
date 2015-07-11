package com.twenty48;

public class Path {
    private Tile[] tiles = new Tile[PlayField.getSize()];

    public Path(Tile[] tiles){
        for(int i = 0; i < PlayField.getSize(); i++)
            this.tiles[i] = tiles[i];

    }

    public boolean moveOrMerge(){
        boolean wasAction = false;

        for(int i = 1; i <= 3; i++){
            if(canMove(i)){
                move(i);
                wasAction = true;
            }
            else
                if(canMerge(i)){
                    merge(i);
                    wasAction = true;
                }
        }

        return wasAction;
    }

    private boolean canMove(int index){
        if (tiles[index] == null)
            return false;
        else
            if (tiles[index-1] == null)
                return true;
            else
                return false;
    }
    private void move(int index){
        tiles[index-1] = tiles[index];
        tiles[index] = null;
    }
    private boolean canMerge(int index){
        if(tiles[index] == null)
            return false;
        else
            if(tiles[index-1] != null && tiles[index-1].getValue() == tiles[index].getValue() && !tiles[index].getMerged() && !tiles[index-1].getMerged()){
                return true;
            }
            else return false;
    }
    private void merge(int index){
        tiles[index-1].setValue(tiles[index-1].getValue()*2); // double value
        tiles[index-1].setMerged(true); // will not merge anymore during this turn
        tiles[index] = null;
        Score.setScore(Score.getCurrentScore()+tiles[index-1].getValue()); // write down the score for merging
    }


}
