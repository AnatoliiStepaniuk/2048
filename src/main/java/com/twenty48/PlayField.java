package com.twenty48;

import java.util.ArrayList;
import java.util.Random;

public class PlayField {

    private static int size = 4;
    private Tile[][] currentField;
    private boolean wasActionInLoop;
    private boolean wasActionInTurn;
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 1;

    public PlayField(){
        currentField = new Tile[size][size];

        spawn();
        spawn();
    }

    public void oneTurn() {
        saveOldPositions();
        wasActionInTurn = false; // presumption

        do { // move or merge tiles while there's still some action
            wasActionInLoop = false; // presumption

            for(int y = 0; y < size; y++) {
                for(int x = 0; x < size; x++) {
                    switch(Direction.direction) {
                        case "UP":
                            wasActionInLoop = moveOrMerge(x, y) || wasActionInLoop;
                            break;
                        case "RIGHT":
                            wasActionInLoop = moveOrMerge(size-1 - x, y) || wasActionInLoop;
                            break;
                        case "DOWN":
                            wasActionInLoop = moveOrMerge(x, size-1 - y) || wasActionInLoop;
                            break;
                        case "LEFT":
                            wasActionInLoop = moveOrMerge(x, y) || wasActionInLoop;
                            break;
                        default:
                            break;
                    }
                    wasActionInTurn = wasActionInTurn || wasActionInLoop; // if action occured at least once in the turn
                }
            }

        } while(wasActionInLoop);

        if(wasActionInTurn)
            spawn();
    }

    private void saveOldPositions() {
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(currentField[y][x] != null){
                    currentField[y][x].setMerged(false);
                    currentField[y][x].setPrevX(currentField[y][x].getX());
                    currentField[y][x].setPrevY(currentField[y][x].getY());
                }
            }
        }
    }

    public void spawn() {
        int value;
        Random r = new Random();

        // make an arrayList vacant positions in currentField - {x, y}
        ArrayList<int[]> freeTilesPositions = new ArrayList<>();
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(currentField[y][x] == null)
                    freeTilesPositions.add(new int[]{x, y});
            }
        }

        // put 2 or 4 into on the random position in arrayList
        int position = r.nextInt(freeTilesPositions.size());
        value = (r.nextInt(10) == 0 ? 4 : 2);
        int x = freeTilesPositions.get(position)[X_COORDINATE];
        int y = freeTilesPositions.get(position)[Y_COORDINATE];
        currentField[y][x] = new Tile(x, y, value);
    }

    public boolean actionIsPossible() {

        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (currentField[y][x] == null)
                    return true;

                else if(x != 0 && currentField[y][x-1] != null && currentField[y][x].getValue() == currentField[y][x-1].getValue() ||
                        x != size-1 && currentField[y][x+1] != null && currentField[y][x].getValue() == currentField[y][x+1].getValue() ||
                        y != 0 && currentField[y-1][x] != null && currentField[y][x].getValue() == currentField[y-1][x].getValue() ||
                        y != size-1 && currentField[y+1][x] != null && currentField[y][x].getValue() == currentField[y+1][x].getValue() )
                    return true;
            }
        }

        return false;
    }

    public int getMaxValue() {
        int max = 0;
        for (int y = 0; y < size; y++){
            for(int x = 0; x < size; x++) {
                if (currentField[y][x] != null && currentField[y][x].getValue() > max)
                    max = currentField[y][x].getValue();
            }
        }

        return max;
    }

    public String getTiles() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Tile> tmpList = new ArrayList<>();
        for(int y = 0; y < size; y++)
            for(int x = 0; x < size; x++)
                if(currentField[y][x] != null)
                    tmpList.add(currentField[y][x]);

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
            sb.append(tmpList.get(i).isMerged());
            if(i != tmpList.size())
                sb.append("\n");
        }

        return sb.toString();
    }


    public boolean moveOrMerge(int x, int y) {
        boolean wasAction = false;

            if(canMove(x, y)) {
                move(x, y);
                wasAction = true;
            }
            else
            if(canMerge(x, y)) {
                merge(x, y);
                wasAction = true;
            }

        return wasAction;
    }


    private boolean canMove(int x, int y) {
        if (currentField[y][x] == null)
            return false;
        else
        if (x+Direction.dX >= 0 && x+Direction.dX < size && y+Direction.dY >= 0 && y+Direction.dY < size && currentField[y+Direction.dY][x+Direction.dX] == null)
            return true;
        else
            return false;
    }

    private boolean canMerge(int x, int y) {
        if(currentField[y][x] == null)
            return false;
        else
            if(x+Direction.dX >= 0 && x+Direction.dX < size && y+Direction.dY >= 0 && y+Direction.dY < size
                && currentField[y+Direction.dY][x+Direction.dX] != null
                && currentField[y+Direction.dY][x+Direction.dX].getValue() == currentField[y][x].getValue()
                && !currentField[y+Direction.dY][x+Direction.dX].isMerged() && !currentField[y][x].isMerged()) {
            return true;
        }
        else return false;
    }

    private void move(int x, int y) {
        currentField[y][x].setX(currentField[y][x].getX() + Direction.dX);
        currentField[y][x].setY(currentField[y][x].getY() + Direction.dY);
        currentField[y+Direction.dY][x+Direction.dX] = currentField[y][x];
        currentField[y][x] = null;
    }

    private void merge(int x, int y) {
        currentField[y+Direction.dY][x+Direction.dX].setValue(currentField[y][x].getValue()*2);
        currentField[y+Direction.dY][x+Direction.dX].setMerged(true);
        currentField[y][x] = null;
        Score.setScore(Score.getCurrentScore()+currentField[y+Direction.dY][x+Direction.dX].getValue()); // write down the score for merging

    }


}
