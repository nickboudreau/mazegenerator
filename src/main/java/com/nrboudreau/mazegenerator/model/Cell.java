package com.nrboudreau.mazegenerator.model;

public class Cell {

    public static final String WALL = "W";
    public static final String PASSAGE = "P";
    public static final String ENTRANCE = "E";

    private String[] adjacent;
    private boolean visited;

    public Cell() {
        this.visited = false;

        this.adjacent = new String[4];
        adjacent[0] = WALL;
        adjacent[1] = WALL;
        adjacent[2] = WALL;
        adjacent[3] = WALL;
    }

    public String[] getAdjacent() {
        return this.adjacent;
    }

    public boolean visited() {
        return this.visited;
    }

    public void visited(boolean visited) {
        this.visited = visited;
    }

}
