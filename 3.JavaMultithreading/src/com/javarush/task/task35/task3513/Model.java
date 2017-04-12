package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by ArchMage on 11.04.17.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score;
    protected int maxTile;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;

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

    private void saveState(Tile[][] tiles){
        Tile[][] tempTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempTile[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(tempTile);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
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
        if (isSaveNeeded) { saveState(gameTiles); }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++){
            if (compressTiles(gameTiles[i])) { isChanged = true; }
            if (mergeTiles(gameTiles[i])) { isChanged = true; }
        }
        if (isChanged) {addTile(); }
        isSaveNeeded = true;
    }

    public void up(){
        saveState(gameTiles);
        rotate(gameTiles);
        rotate(gameTiles);
        rotate(gameTiles);
        left();
        rotate(gameTiles);
    }

    public void right(){
        saveState(gameTiles);
        rotate(gameTiles);
        rotate(gameTiles);
        left();
        rotate(gameTiles);
        rotate(gameTiles);
    }

    public void down(){
        saveState(gameTiles);
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

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private boolean hasBoardChanged(){
        Tile[][] prev = (Tile[][]) previousStates.peek();
        int currTile = 0;
        int prevTile = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                currTile += gameTiles[i][j].value;
                prevTile += prev[i][j].value;
            }
        }

        return currTile != prevTile;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency movEff = null;
        move.move();

        if (!hasBoardChanged()) {
            movEff = new MoveEfficiency(-1 ,0 ,move);
        } else {
            movEff = new MoveEfficiency(getEmptyTiles().size() ,score ,move);
        }
        rollback();
        return movEff;
    }

    public void autoMove(){
        PriorityQueue queue = new PriorityQueue(4, Collections.<MoveEfficiency>reverseOrder());
        queue.add(getMoveEfficiency(this::randomMove));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        MoveEfficiency efficientMove = (MoveEfficiency) queue.poll();
        efficientMove.getMove().move();
    }

    private class MoveLeft implements Move{

        @Override
        public void move() {
            Model.this.left();
        }
    }

    private class MoveUp implements Move{

        @Override
        public void move() {
            Model.this.up();
        }
    }

    private class MoveRight implements Move{

        @Override
        public void move() {
            Model.this.right();
        }
    }

    private class MoveDown implements Move{

        @Override
        public void move() {
            Model.this.down();
        }
    }
}
