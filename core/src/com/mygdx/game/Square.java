package com.mygdx.game;

public class Square {
    int xPosition;
    int yPosition;
    int height;
    int width;
    int index;
    //Rectangle

    State state;

    public Square(int x, int y, int i, State s) {
        xPosition = x;
        yPosition = y;
        index = i;
        state = s;
    }

    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
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
