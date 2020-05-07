package com.nrboudreau.mazegenerator.generators;

import com.nrboudreau.mazegenerator.model.Maze;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

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

    protected void visitRandomAdjacentCell(int row, int column) {
        Collections.shuffle(this.next);

        for (Integer i : this.next) {
            int newRow = i == 0 ? row - 1 : i == 2 ? row + 1 : row;
            int newColumn = i == 1 ? column + 1 : i == 3 ? column - 1 : column;
            int from = i == 0 ? 1 : i == 1 ? 3 : i == 2 ? 0 : i == 3 ? 1 : -1;

            this.recursiveStep(newRow, newColumn, from);
        }
    }

    protected abstract Maze generate(int width, int height);
    protected abstract void recursiveStep(int row, int column, int from);

}
