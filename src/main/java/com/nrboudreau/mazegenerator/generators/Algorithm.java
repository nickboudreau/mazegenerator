package com.nrboudreau.mazegenerator.generators;

import com.nrboudreau.mazegenerator.model.Maze;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Algorithm {

    private static final int MIN_WIDTH = 2;
    private static final int MIN_HEIGHT = 2;
    private static final int MAX_WIDTH = 500;
    private static final int MAX_HEIGHT = 500;

    protected int width;
    protected int height;
    protected Maze maze;
    protected Random random;
    protected ArrayList<Integer> next;

    // do some width/height error checking
    public Maze generate(String width, String height) {
        int h,w;

        try {
            h = Integer.parseInt(height);
            w = Integer.parseInt(width);

            if (w < MIN_WIDTH || h < MIN_HEIGHT || w > MAX_WIDTH || h > MAX_HEIGHT) {
                throw new NumberFormatException("width=" + width + ", height=" + height + ".");
            }
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Width and height must be numbers greater than or equal to 2, and less than or equal to 2000. " + e.getMessage(), e);
        }

        return generate(w, h);
    }

    /*
        choose a new random direction to move and take a step.
        provide the next step with the direction coming from.
        north: 0
        east: 1
        south: 2
        west: 3
     */
    protected void visitRandomAdjacentCell(int currentRow, int currentColumn) {
        int to = this.random.nextInt(4);

        int newRow = this.newRow(currentRow, to);
        int newColumn = this.newColumn(currentColumn, to);
        int from = this.from(to);

        this.step(newRow, newColumn, from);
    }

    /*
        randomly move to each direction and take a step.
        provide the next step with the direction coming from.
        north: 0
        east: 1
        south: 2
        west: 3
     */
    protected void visitRandomAdjacentCells(int currentRow, int currentColumn) {
        Collections.shuffle(this.next);

        for (Integer to : this.next) {
            int newRow = this.newRow(currentRow, to);
            int newColumn = this.newColumn(currentColumn, to);
            int from = this.from(to);

            this.step(newRow, newColumn, from);
        }
    }

    protected int newRow(int currentRow, int to) {
        return to == 0 ? currentRow - 1 : to == 2 ? currentRow + 1 : currentRow;
    }

    protected int newColumn(int currentColumn, int to) {
        return to == 1 ? currentColumn + 1 : to == 3 ? currentColumn - 1 : currentColumn;
    }

    protected int from(int i) {
        return i == 0 ? 1 : i == 1 ? 3 : i == 2 ? 0 : i == 3 ? 1 : -1;
    }

    protected abstract Maze generate(int width, int height);
    protected abstract void step(int row, int column, int from);

}
