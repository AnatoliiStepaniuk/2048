package com.twenty48;

import java.util.Random;

public class PlayField {

    private static int size = 4;
    private Tile[][] currentField;
    private Tile[][] oldField;
    private Path[] paths;
    private boolean wasActionInLoop;
    private boolean wasActionInTurn;

    public PlayField(){
        currentField = new Tile[size][size];
        oldField = new Tile[size][size];
        for(int j = 0; j < size; j++)
            for (int i = 0; i < size; i++) {
                currentField[j][i] = new Tile();
                oldField[j][i] = new Tile();
        }
        spawn();
        spawn();
    }

    public void oneTurn(Direction dir){
        saveCurrentField();
        paths = PathCreator.getPaths(dir, currentField);
        wasActionInTurn = false; // presumption

        do { // move or merge tiles while there's still some action
            wasActionInLoop = false; // presumption
            for(Path p : paths){
                wasActionInLoop = p.moveOrMerge() || wasActionInLoop;
                wasActionInTurn = wasActionInTurn || wasActionInLoop; // if action occured at least once in the turn
            }
        } while(wasActionInLoop);

        if(wasActionInTurn)
            spawn();
    }

    private void saveCurrentField(){
        // set all tiles unmerged and copy them to oldField
        for(int j = 0; j < PlayField.getSize(); j++){
            for(int i = 0; i < PlayField.getSize(); i++){
                currentField[j][i].setUnmerged();
                oldField[j][i].setValue(currentField[j][i].getValue()); // just set the values
            }
        }


    }

    public void spawn(){
        int x, y, value;
        Random r = new Random();

        // randomly find emptyslot
        do{
            x = r.nextInt(4);
            y = r.nextInt(4);
        } while(currentField[y][x].getValue() != 0);

        //put 2 or 4 there
        value = (r.nextInt(10) == 0 ? 4 : 2);
        currentField[y][x].setValue(value);

/*
        currentField[0][0].setValue(16);
        currentField[1][0].setValue(8);
        currentField[2][0].setValue(4);
        currentField[3][0].setValue(4);
*/

    }
    public boolean actionIsPossible(){

        for(int j = 0; j < PlayField.getSize(); j++) {
            for (int i = 0; i < PlayField.getSize(); i++) {
                if (currentField[j][i].getValue() == 0)
                    return true;

                else if(i != 0 && currentField[j][i].getValue() == currentField[j][i-1].getValue() ||
                        i != size-1 && currentField[j][i].getValue() == currentField[j][i+1].getValue() ||
                        j != 0 && currentField[j][i].getValue() == currentField[j-1][i].getValue() ||
                        j != size-1 && currentField[j][i].getValue() == currentField[j+1][i].getValue() )
                    return true;
            }
        }

        return false;
    }

    public int getMaxValue(){
        int max = 0;
        for (Tile[] row : currentField){
            for(Tile t : row)
                if(t.getValue() > max)
                    max = t.getValue();
        }
        return max;
    }

    public static int getSize(){
        return size;
    }

    public String getCurrentField(){
        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < PlayField.getSize(); j++){
            for(int i = 0; i < PlayField.getSize(); i++){
                sb.append(currentField[j][i].getValue());
                if (i != PlayField.getSize()-1)
                    sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
