package com.alibaba.cell;

/**
 * Created by pinghua.wph on 2015/10/12.
 */
public class Cell {
    private int x;
    private int y;

    public Cell(){

    }

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
