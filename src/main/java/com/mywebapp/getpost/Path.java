package com.mywebapp.getpost;

public class Path {
//    private Tile[] tiles;
    private Tile[] tiles = new Tile[PlayField.getSize()];

    public Path(Tile[] tiles){
        for(int i = 0; i < PlayField.getSize(); i++)
            this.tiles[i] = tiles[i];
//            this.tiles[i] = new Tile(tiles[i].getValue());

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
        if (tiles[index].getValue() == 0)
            return false;
        else
            if (tiles[index-1].getValue() == 0)
                return true;
            else
                return false;
    }
    private void move(int index){
        tiles[index-1].setValue(tiles[index].getValue());
        tiles[index].setValue(0);
    }
    private boolean canMerge(int index){
        if(tiles[index].getValue() == 0)
            return false;
        else
            if(tiles[index-1].getValue() == tiles[index].getValue() && !tiles[index].isMerged() && !tiles[index-1].isMerged()){
                return true;
            }
            else return false;
    }
    private void merge(int index){
        tiles[index-1].setValue(tiles[index-1].getValue()*2); // double value
        tiles[index-1].setMerged(); // will not merge anymore during this turn
        tiles[index].setValue(0);
        Score.setScore(Score.getCurrentScore()+tiles[index-1].getValue()); // write down the score for merging
    }


}
