package com.mygdx.game;

public class Square {
    int xPosition;
    int yPosition;
    int height = 40;
    int width = 40;
    int index;

    State state;

    public Square(int x, int y, int i, State s) {
        xPosition = x;
        yPosition = y;
        this.height = height;
        this.width = width;
        index = i;
        state = s;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void setXPosition(int x) {
        xPosition = x;
    }
    public void setYPosition(int y) {
        yPosition = y;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int i) {
        index = i;
    }
    public State getState() {
        return state;
    }
    public void setState(State s) {
        state = s;
    }
}
