package com.twenty48;

import java.util.Random;

public class PlayField {

    private static int size = 4;
    private Tile[][] currentField;
//    private Tile[][] oldField;
    private Path[] paths;
    private boolean wasActionInLoop;
    private boolean wasActionInTurn;

    public PlayField(){
        currentField = new Tile[size][size];
//        oldField = new Tile[size][size];
//        for(int j = 0; j < size; j++)
//            for (int i = 0; i < size; i++) {
//                currentField[j][i] = new Tile();
//                oldField[j][i] = new Tile();
//        }
        spawn();
        spawn();
    }

    public void oneTurn(Direction dir){
        saveOldPositions();
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

    private void saveOldPositions(){
        for(int j = 0; j < PlayField.getSize(); j++){
            for(int i = 0; i < PlayField.getSize(); i++){
                if(currentField[j][i] != null){
                    currentField[j][i].setMerged(false);
                    currentField[j][i].setPrevX(currentField[j][i].getX());
                    currentField[j][i].setPrevY(currentField[j][i].getY());
                }
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
        } while(currentField[y][x] != null);

        //put 2 or 4 there
        value = (r.nextInt(10) == 0 ? 4 : 2);
        currentField[y][x] = new Tile(y, x, value);


    }
    public boolean actionIsPossible(){

        for(int j = 0; j < PlayField.getSize(); j++) {
            for (int i = 0; i < PlayField.getSize(); i++) {
                if (currentField[j][i] == null)
                    return true;

                else if(i != 0 && currentField[j][i-1] != null && currentField[j][i].getValue() == currentField[j][i-1].getValue() ||
                        i != size-1 && currentField[j][i+1] != null && currentField[j][i].getValue() == currentField[j][i+1].getValue() ||
                        j != 0 && currentField[j-1][i] != null && currentField[j][i].getValue() == currentField[j-1][i].getValue() ||
                        j != size-1 && currentField[j+1][i] != null && currentField[j][i].getValue() == currentField[j+1][i].getValue() )
                    return true;
            }
        }

        return false;
    }

/*
    public int getMaxValue(){
        int max = 0;
        for (Tile[] row : currentField){
            for(Tile t : row)
                if(t.getValue() > max)
                    max = t.getValue();
        }
        return max;
    }
*/

    public static int getSize(){
        return size;
    }

    public String getCurrentField(){
        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < PlayField.getSize(); j++){
            for(int i = 0; i < PlayField.getSize(); i++){
                if(currentField[j][i] == null)
                    sb.append("0");
                else
                    sb.append(currentField[j][i].getValue());
                if (i != PlayField.getSize()-1)
                    sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
