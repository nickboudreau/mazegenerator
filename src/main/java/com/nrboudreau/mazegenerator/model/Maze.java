package com.nrboudreau.mazegenerator.model;

public class Maze {

    private int width;
    private int height;

    private Cell[][] rows;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        this.initialize();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Cell[][] getRows() {
        return this.rows;
    }

    public Cell getCell(int row, int column) {
        return rows[row][column];
    }

    private void initialize() {
        this.rows = new Cell[height][width];

        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                this.rows[row][column] = new Cell();
            }
        }
    }

}
