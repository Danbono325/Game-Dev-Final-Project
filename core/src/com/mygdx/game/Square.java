package com.mygdx.game;

public class Square {
    private int xPosition;
    private int yPosition;
    private int height = 40;
    private int width = 40;
    private int index;

    private State state;

    // Creates a square object with its position, index, and state (Initialized to empty until clicked on)
    public Square(int x, int y, int i, State s) {
        xPosition = x;
        yPosition = y;
        this.height = height;
        this.width = width;
        index = i;
        state = s;
    }
    // Returns the x postion of the square
    public int getxPosition() { return xPosition; }
    // Returns the y position of the square
    public int getyPosition() {
        return yPosition;
    }
    // Returns height of the square
    public int getHeight(){
        return height;
    }
    // Returns the width of the square
    public int getWidth(){
        return width;
    }
    // Returns the index of the array
    public int getIndex() {
        return index;
    }
    // Returns the state of the square
    public State getState() {
        return state;
    }
    // Sets the state of the square (empty, playerO, playerX)
    public void setState(State s) {
        state = s;
    }
}
