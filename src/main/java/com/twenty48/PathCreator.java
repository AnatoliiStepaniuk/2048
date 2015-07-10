package com.twenty48;

public class PathCreator {

    public static Path[] getPaths(Direction dir, Tile[][] tiles){

            Path[] paths = new Path[4];
            Tile[] temp = new Tile[4];
            switch (dir){
                case UP:
                    for(int j = 0; j < PlayField.getSize(); j++) {
                        for (int i = 0; i < PlayField.getSize(); i++) {
                            temp[i] = tiles[i][j];
                        }
                        paths[j] = new Path(temp);
                    }
                    break;
                case RIGHT:
                    for(int j = 0; j < PlayField.getSize(); j++) {
                        for (int i = 0; i < PlayField.getSize(); i++) {
                            temp[i] = tiles[j][PlayField.getSize()-1 - i];
                        }
                        paths[j] = new Path(temp);
                    }
                    break;
                case DOWN:
                    for(int j = 0; j < PlayField.getSize(); j++) {
                        for (int i = 0; i < PlayField.getSize(); i++) {
                            temp[i] = tiles[PlayField.getSize()-1-i][j];
                        }
                        paths[j] = new Path(temp);
                    }
                    break;
                case LEFT:
                    for(int j = 0; j < PlayField.getSize(); j++) {
                        for (int i = 0; i < PlayField.getSize(); i++) {
                            temp[i] = tiles[j][i];
                        }
                        paths[j] = new Path(temp);
                    }
                    break;
                default:
                    ///
                    break;
        }

        return paths;
    }
}
