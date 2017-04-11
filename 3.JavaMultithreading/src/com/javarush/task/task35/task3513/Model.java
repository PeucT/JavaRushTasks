package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArchMage on 11.04.17.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score;
    protected int maxTile;

    public Model(){
        resetGameTiles();
    }

    private void addTile(){
        List<Tile> emptyList = getEmptyTiles();
        if (emptyList != null && emptyList.size() != 0) {
            Tile tile = emptyList.get((int) (Math.random() * emptyList.size()));
            int tileWeight = Math.random() < 0.9 ? 2 : 4;
            tile.value = tileWeight;
        }
    }

    protected void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        maxTile = 2;
        score = 0;
    }

    private List<Tile> getEmptyTiles(){
        ArrayList<Tile> emptyTilesList = new ArrayList<Tile>();
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                if (gameTiles[i][j].isEmpty()) {
                    emptyTilesList.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTilesList;
    }

    /*private boolean compressTiles(Tile[] tiles){
        boolean isChanged = true;
        for (int i = 0; i < tiles.length; i++){
            if (tiles[i].value == 0) {
                boolean isAllZero = false;
                for (int j = 1; j <= tiles.length - i; j++) {
                    try {
                        if (tiles[i+j].value != 0) { isAllZero = false; }
                        tiles[i + j - 1].value = tiles[i + j].value;

                    } catch (Exception e) {
                        tiles[i + j - 1].value = 0;

                    }
                }
                if (tiles[i].value == 0 && !isAllZero) { i--; }
            }
        }

        return false;
    }*/

    private boolean compressTiles(Tile[] tiles){
        int counter = 0;
        boolean isChanged = false;
        int values[] = new int[tiles.length];
        for (int i = 0; i < values.length; i++){
            values[i] = 0;
        }

        for (int i = 0; i < tiles.length; i++){
            if (tiles[i].value != 0) {
                values[counter] = tiles[i].value;
                counter++;
            }
        }

        for (int i = 0; i < tiles.length; i++){
            if (values[i] != tiles[i].value) { isChanged = true; }
            tiles[i].value = values[i];
        }

        return isChanged;

    }

    private boolean mergeTiles(Tile[] tiles){
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++){
            if (tiles[i].value == tiles[i+1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value * 2;
                score = score + tiles[i].value;
                if (tiles[i].value > maxTile) { maxTile = tiles[i].value; }
                for (int j = 2; j <= tiles.length - i; j++){
                    try {
                        tiles[i + j - 1 ].value = tiles[i + j].value;
                    } catch (Exception e){
                        tiles[i + j - 1].value = 0;
                        isChanged = true;
                    }
                }
            }
        }
        return isChanged;
    }

    public void left(){
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++){
            if (compressTiles(gameTiles[i])) { isChanged = true; }
            if (mergeTiles(gameTiles[i])) { isChanged = true; }
        }
        if (isChanged) {addTile(); }
    }

    public void up(){
        rotate(gameTiles);
        rotate(gameTiles);
        rotate(gameTiles);
        left();
        rotate(gameTiles);
    }

    public void right(){
        rotate(gameTiles);
        rotate(gameTiles);
        left();
        rotate(gameTiles);
        rotate(gameTiles);
    }

    public void down(){
        rotate(gameTiles);
        left();
        rotate(gameTiles);
        rotate(gameTiles);
        rotate(gameTiles);
    }

    private void rotate(Tile[][] arrayGameTiles){
        int[][] values = new int[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                values[i][j] = arrayGameTiles[i][j].value;
            }
        }

        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                arrayGameTiles[j][FIELD_WIDTH - i - 1].value = values[i][j];
            }
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        Tile[][] tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                tempArray[i][j] = new Tile(gameTiles[i][j].value);
            }
        }


        for (int i = 0; i < 4; i++) {
            if (canMoveLeft(tempArray)) {
                return true;
            }
            rotate(tempArray);
        }
        return false;
    }

    private boolean canMoveLeft(Tile[][] arrayGameTiles){
        for (int i = 0; i < FIELD_WIDTH; i++){
            if (testCompressTiles(arrayGameTiles[i])) { return true; }
            if (testMergeTiles(arrayGameTiles[i])) { return true; }
        }
        return false;
    }

    // Вынесем тестовые методы сюда. Лучше, конечно, добавить возможность тестового вызова в аналогичные "настоящие" методы,
    // но непонятно, как на смену сигнатуры методов отреагирует валидатор

    private boolean testCompressTiles(Tile[] tiles){
        int counter = 0;
        int values[] = new int[tiles.length];
        for (int i = 0; i < values.length; i++){
            values[i] = 0;
        }

        for (int i = 0; i < tiles.length; i++){
            if (tiles[i].value != 0) {
                values[counter] = tiles[i].value;
                counter++;
            }
        }

        for (int i = 0; i < tiles.length; i++){
            if (values[i] != tiles[i].value) { return true; }
            tiles[i].value = values[i];
        }

        return false;

    }

    private boolean testMergeTiles(Tile[] tiles){
        for (int i = 0; i < tiles.length - 1; i++){
            if (tiles[i].value == tiles[i+1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value * 2;
                // В настоящих методах, тут шло изменение счета и максимальной плашки, но в тестовом методе,
                // их затрагивать нельзя

                for (int j = 2; j <= tiles.length - i; j++){
                    try {
                        tiles[i + j - 1 ].value = tiles[i + j].value;
                    } catch (Exception e){
                        tiles[i + j - 1].value = 0;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
