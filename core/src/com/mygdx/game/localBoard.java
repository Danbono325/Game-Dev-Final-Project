package com.mygdx.game;

public class localBoard {
    int xPosition;
    int yPosition;
    Square board[] = new Square[9];
    State winner; //Has the board been won by player x player o or neither
    boolean nextBoard;

    public localBoard(int x, int y, Square[] b, State w){ //Initialize an empty board
        xPosition = x;
        yPosition = y;
//        for(int i = 0; i < 9; i++){
//            board[i] = new Square( ,i,State.empty);
//        }
//        winner = w;
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
    public State getWinner() {
       return winner;
    }
    public boolean getStatus() {
        return nextBoard;
    }
    public void setStatus(boolean b) {
        nextBoard = b;
    }
    public Square[] getSquares(){
        return board;
    }
    public void setSquares(Square[] x) {
        board = x;
    }
    public void checkWinner(){
        //Checking rows
        if(board[0].getState() == board[1].getState() && board[1].getState() == board[2].getState())
            this.winner = board[0].getState();
        else if(board[3].getState() == board[4].getState() && board[4].getState() == board[5].getState())
            this.winner = board[3].getState();
        else if(board[6].getState() == board[7].getState() && board[7].getState() == board[8].getState())
            this.winner = board[6].getState();
        else if(board[0].getState() == board[3].getState() && board[3].getState() == board[6].getState()) //Columns
            this.winner = board[0].getState();
        else if(board[1].getState() == board[4].getState() && board[4].getState() == board[7].getState())
            this.winner = board[1].getState();
        else if(board[2].getState() == board[5].getState() && board[5].getState() == board[8].getState())
            this.winner = board[2].getState();
        else if(board[0].getState() == board[4].getState() && board[4].getState() == board[8].getState()) //Diagonals
            this.winner = board[0].getState();
        else if(board[2].getState() == board[4].getState() && board[4].getState() == board[6].getState())
            this.winner = board[2].getState();
        else
            this.winner = State.empty;
    }
}
