package com.twenty48;

import java.util.ArrayList;
import java.util.Random;

public class PlayField {

    private static int size = 4;
    private Tile[][] currentField;
    private Path[] paths;
    private boolean wasActionInLoop;
    private boolean wasActionInTurn;

    public PlayField(){
        currentField = new Tile[size][size];

        for(int j = 0; j < size; j++)
            for (int i = 0; i < size; i++) {
                currentField[j][i] = new Tile(j, i, 0);
        }

        spawn();
        spawn();
    }

    public void oneTurn(){
        saveOldPositions();
        paths = PathCreator.getPaths(currentField);
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
                if(currentField[j][i].getValue() != 0){
                    currentField[j][i].setMerged(false);
                    currentField[j][i].setPrevX(currentField[j][i].getX());
                    currentField[j][i].setPrevY(currentField[j][i].getY());
                }
            }
        }
    }

    public void spawn(){
        int value;
        Random r = new Random();

        // make an arrayList of 0-tiles
        ArrayList<Tile> freeTiles = new ArrayList<>();
        for(int j = 0; j < PlayField.getSize(); j++){
            for(int i = 0; i < PlayField.getSize(); i++){
                if(currentField[j][i].getValue() == 0)
                    freeTiles.add(currentField[j][i]);
            }
        }

        // put 2 or 4 into on the random position in arrayList
        int position = r.nextInt(freeTiles.size());
        value = (r.nextInt(10) == 0 ? 4 : 2);
        freeTiles.get(position).setValue(value);
        freeTiles.get(position).setPrevX(freeTiles.get(position).getPrevX());
        freeTiles.get(position).setPrevY(freeTiles.get(position).getPrevY());

    }
    public boolean actionIsPossible(){

        for(int j = 0; j < PlayField.getSize(); j++) {
            for (int i = 0; i < PlayField.getSize(); i++) {
                if (currentField[j][i].getValue() == 0)
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

    public String getTiles(){
        StringBuilder sb = new StringBuilder();
        ArrayList<Tile> tmpList = new ArrayList<>();
        for(int j = 0; j < PlayField.getSize(); j++)
            for(int i = 0; i < PlayField.getSize(); i++)
                if(currentField[j][i].getValue() != 0)
                    tmpList.add(currentField[j][i]);

        for(int i = 0; i < tmpList.size(); i++) {
            sb.append(tmpList.get(i).getValue());
            sb.append(" ");
            sb.append(tmpList.get(i).getX());
            sb.append(" ");
            sb.append(tmpList.get(i).getY());
            sb.append(" ");
            sb.append(tmpList.get(i).getPrevX());
            sb.append(" ");

            sb.append(tmpList.get(i).getPrevY());
            sb.append(" ");
            sb.append(tmpList.get(i).getMerged());
            if(i != tmpList.size())
                sb.append("\n");
        }

        return sb.toString();
    }


}
