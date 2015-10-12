package com.alibaba.worm;

import java.util.Arrays;

import com.alibaba.cell.Cell;

/**
 * Created by pinghua.wph on 2015/10/12.
 */
public class Worm {
    public static final int DEFAULT_LENGTH = 12;

    private Cell[] cells;

    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 2;
    public static final int RIGHT = -2;

    private int currentDirection;

    public Worm(){
        cells = new Cell[DEFAULT_LENGTH];
        for(int i=0;i<cells.length;i++){
            cells[i] = new Cell(i,0);
        }
        currentDirection = DOWN;
    }

    public Cell[] getCells() {
        return Arrays.copyOf(cells, cells.length);
    }

    public String toString(){
        return Arrays.toString(cells);
    }

    public boolean contains(int x, int y) {
        for (int i=0;i<cells.length; i++){
            Cell node = cells[i];
            if (node.getX() == x && node.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean creep(int direction, Cell food){
        if(currentDirection + direction == 0){
            return false;
        }
        currentDirection = direction;
        Cell head = createHead(direction);
        boolean eat = false;
        if(head.getX() == food.getX() && head.getY() == food.getY()){
            eat = true;
        }
        if(eat){
            cells = Arrays.copyOf(cells, cells.length+1);
        }
        for(int i=cells.length-1; i>=1; i--){
            cells[i] = cells[i-1];
        }
        cells[0] = head;
        return eat;
    }

    public boolean hit(){
        return hit(currentDirection);
    }

    public boolean hit(int direction){
        Cell head = createHead(direction);
        if(head.getX()<0 || head.getX() >= WormStage.COLS ||
                head.getY() < 0 || head.getY() >= WormStage.ROWS){
            return true;
        }
        for(int i=0; i<cells.length-1; i++){
            Cell node = cells[i];
            if(node.getX() == head.getX() && node.getY() == head.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean creep(Cell food){
        return creep(currentDirection, food);
    }

    public void creep(){
        for(int i=cells.length-1; i>=1; i--){
            cells[i] = cells[i-1];
        }
        cells[0] = createHead(currentDirection);
    }

    public Cell createHead(int direction){
        int x = cells[0].getX();
        int y = cells[0].getY();
        switch (direction){
            case DOWN:  y++;break;
            case UP:    y--;break;
            case LEFT:  x--;break;
            case RIGHT: x++;break;
        }
        return new Cell(x, y);
    }
}
