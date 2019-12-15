package com.mygdx.game;

public class localBoard {
    int xPosition;
    int yPosition;
    int height = 120;
    int width = 125;
    Square board[] = new Square[9];
    State winner; //Has the board been won by player x player o or neither
    boolean nextBoard;

    // Creates a local board object that has a position, an array of 9 squares, the state of each board,
    // and the status of the board which will help to restrict the boards the players can go in
    public localBoard(int x, int y, Square[] b, State w, boolean status){
        xPosition = x;
        yPosition = y;
        winner = w;
        this.height = height;
        this.width = width;
    }
    //Returns the x position of the local board
    public int getXPosition() {
        return xPosition;
    }

    //Returns the y poistion of the local board
    public int getYPosition() {
        return yPosition;
    }

    // Returns the state of the local board (empty = > no winner yet)
    public State getWinner() {
       return winner;
    }

    // Returns the status of the local board
    public boolean getStatus() {
        return nextBoard;
    }

    // Sets the status of the local board
    public void setStatus(boolean b) { nextBoard = b; }

    // Returns the array of the local board
    public Square[] getSquares(){
        return board;
    }


    public void setSquares(Square[] x) {
        board = x;
    }
    public State checkWinner(){
        //Checking rows
        if(board[0].getState() == board[1].getState() && board[1].getState() == board[2].getState()){
            this.winner = board[0].getState();
            return winner;
        }
        else if(board[3].getState() == board[4].getState() && board[4].getState() == board[5].getState()) {
            this.winner = board[3].getState();
            return winner;
        }
        else if(board[6].getState() == board[7].getState() && board[7].getState() == board[8].getState()) {
            this.winner = board[6].getState();
            return winner;
        }
        else if(board[0].getState() == board[3].getState() && board[3].getState() == board[6].getState()) { //Columns
            this.winner = board[0].getState();
            return winner;
        }
        else if(board[1].getState() == board[4].getState() && board[4].getState() == board[7].getState()) {
            this.winner = board[1].getState();
            return winner;
        }
        else if(board[2].getState() == board[5].getState() && board[5].getState() == board[8].getState()) {
            this.winner = board[2].getState();
            return winner;
        }
        else if(board[0].getState() == board[4].getState() && board[4].getState() == board[8].getState()) {//Diagonals
            this.winner = board[0].getState();
            return winner;
        }
        else if(board[2].getState() == board[4].getState() && board[4].getState() == board[6].getState()) {
            this.winner = board[2].getState();
            return winner;
        }
        else {
            this.winner = State.empty;
            return winner;
        }
    }
}
