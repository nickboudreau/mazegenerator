package com.nrboudreau.mazegenerator.generators;

import com.nrboudreau.mazegenerator.model.Cell;
import com.nrboudreau.mazegenerator.model.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RecursiveBacktracking extends Algorithm {

    @Override
    public Maze generate(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new Maze(width, height);
        this.random = new Random();

        this.next = new ArrayList<>();
        this.next.add(0);
        this.next.add(1);
        this.next.add(2);
        this.next.add(3);

        int startingRow = random.nextInt(height);
        int startingColumn = random.nextInt(width);

        this.carve(startingRow, startingColumn, -1);

        return maze;
    }

    private void carve(int row, int column, int prev) {
        if (row < 0 || column < 0 || row >= this.height || column >= this.width) {
            return;
        }

        Cell cell =  this.maze.getCell(row, column);

        if (cell.visited() == true) {
            return;
        }

        cell.visited(true);

        if (prev != -1) {
            cell.getAdjacent()[prev] = Cell.PASSAGE;
        }

        visitRandomAdjacentCell(row, column);
    }

    protected void recursiveStep(int row, int column, int from) {
        this.carve(row, column, from);
    }
}
