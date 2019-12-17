package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.Arrays;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.XtremeTicTacToe;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Here for animation - Chris
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameScreen implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound xSound;
    Sound oSound;
    Sound click;

    SpriteBatch spriteBatch; // Used to draw the global and local sprites
    Texture global; // Texture used to draw the global and local boards
    Sprite globalSprite; // Sprite used to draw the global board

    // Sprites for the local boards
    Sprite middle;
    Sprite topLeft;
    Sprite topMiddle;
    Sprite topRight;
    Sprite middleLeft;
    Sprite middleRight;
    Sprite bottomLeft;
    Sprite bottomMiddle;
    Sprite bottomRight;

    boolean drawBoard = true; // Draw the board
    boolean drawMsg = false; // Display the winner message
    boolean firstMove = true; // Helps with the first move


    //Determines the winner of the game
    State globalWinner;
    localBoard[] globalBoard = new localBoard[9]; // The global board is an array of local board objects

    // Used to draw the strings to the screen
    String xString = ("X's turn");
    String oString = ("O's turn");

    // Previous move information used to determine where the next player can go
    localBoard previousLocalBoard;
    Square[] previousSquares;
    int previousMoveIndex;

    //Helps restrict where the user can click
    localBoard nextLocalBoard;
    Square[] currentSquares;


    // Determines whether it is X's turn or O's
    int turn = 0;

    //Square arrays for the local boards
    Square[] topLeftS = new Square[9];
    Square[] topMiddleS = new Square[9];
    Square[] topRightS = new Square[9];
    Square[] middleLeftS = new Square[9];
    Square[] centerS = new Square[9];
    Square[] middleRightS = new Square[9];
    Square[] bottomLeftS = new Square[9];
    Square[] bottomMiddleS = new Square[9];
    Square[] bottomRightS = new Square[9];

    // Texture Atlas for x and o - Chris
    private TextureAtlas xtextureAtlas;
    private TextureAtlas otextureAtlas;

    // Animation for x and o - Chris
    private Animation xanimation;
    private Animation oanimation;

    //Sprite[] arrayXO = new Sprite[65];
    ArrayList<Sprite> listXO = new ArrayList<Sprite>();
    boolean drawXO = true;
    Texture X;
    Texture O;

    // Used for the animation
    private float elapsedTime = 0;

    public GameScreen(XtremeTicTacToe gam) {
        spriteBatch = new SpriteBatch();
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        // Position and scale the global board sprite
        global = new Texture(Gdx.files.internal("tictactoeGlobal.png"));
        globalSprite = new Sprite(global);
        globalSprite.setPosition(-125.0f, -180.0f);
        globalSprite.setScale( .6f, .6f);

        // Position and scale the middle local board sprite
        middle = new Sprite(global);
        middle.setPosition(-125.0f, -180.0f);
        middle.setScale( .18f, .18f);

        // Position and scale the top middle local board sprite
        topMiddle = new Sprite(global);
        topMiddle.setPosition(-125.0f, -40.0f);
        topMiddle.setScale( .18f, .18f);

        // Position and scale the top left local board sprite
        topLeft = new Sprite(global);
        topLeft.setPosition(-265.0f, -40.0f);
        topLeft.setScale( .18f, .18f);

        // Position and scale the top right local board sprite
        topRight = new Sprite(global);
        topRight.setPosition(15.0f, -40.0f);
        topRight.setScale( .18f, .18f);

        // Position and scale the middle right local board sprite
        middleRight = new Sprite(global);
        middleRight.setPosition(15.0f, -180.0f);
        middleRight.setScale( .18f, .18f);

        // Position and scale the middle left local board sprite
        middleLeft = new Sprite(global);
        middleLeft.setPosition(-265.0f, -180.0f);
        middleLeft.setScale( .18f, .18f);

        // Position and scale the bottom left local board sprite
        bottomLeft = new Sprite(global);
        bottomLeft.setPosition(-265.0f, -320.0f);
        bottomLeft.setScale( .18f, .18f);

        // Position and scale the bottom middle board sprite
        bottomMiddle = new Sprite(global);
        bottomMiddle.setPosition(-125.0f, -320.0f);
        bottomMiddle.setScale( .18f, .18f);

        // Position and scale the bottom right local board sprite
        bottomRight = new Sprite(global);
        bottomRight.setPosition(15.0f, -320.0f);
        bottomRight.setScale( .18f, .18f);

        // Position and initialize the squares in the top left board as empty
        topLeftS[0] = new Square(130,75,0, State.empty);
        topLeftS[1] = new Square(175,75,1, State.empty);
        topLeftS[2] = new Square(215,75,2, State.empty);
        topLeftS[3] = new Square(130,115,3, State.empty);
        topLeftS[4] = new Square(175,115,4, State.empty);
        topLeftS[5] = new Square(215,115,5, State.empty);
        topLeftS[6] = new Square(130,160,6, State.empty);
        topLeftS[7] = new Square(175,160,7, State.empty);
        topLeftS[8] = new Square(215,160,8, State.empty);

        // Position and initialize the squares in the top middle board as empty
        topMiddleS[0] = new Square(275,75,0, State.empty);
        topMiddleS[1] = new Square(317,75,1, State.empty);
        topMiddleS[2] = new Square(360,75,2, State.empty);
        topMiddleS[3] = new Square(275,120,3, State.empty);
        topMiddleS[4] = new Square(317,120,4, State.empty);
        topMiddleS[5] = new Square(360,120,5, State.empty);
        topMiddleS[6] = new Square(275,160,6, State.empty);
        topMiddleS[7] = new Square(317,160,7, State.empty);
        topMiddleS[8] = new Square(360,160,8, State.empty);

        // Position and initialize the squares in the top right board as empty
        topRightS[0] = new Square(415,75,0, State.empty);
        topRightS[1] = new Square(458,75,1, State.empty);
        topRightS[2] = new Square(500,75,2, State.empty);
        topRightS[3] = new Square(415,120,3, State.empty);
        topRightS[4] = new Square(458,120,4, State.empty);
        topRightS[5] = new Square(500,120,5, State.empty);
        topRightS[6] = new Square(415,160,6, State.empty);
        topRightS[7] = new Square(458,160,7, State.empty);
        topRightS[8] = new Square(500,160,8, State.empty);

        // Position and initialize the squares in the middle left board as empty
        middleLeftS[0] = new Square(130,218,0, State.empty);
        middleLeftS[1] = new Square(170,218,1, State.empty);
        middleLeftS[2] = new Square(215,218,2, State.empty);
        middleLeftS[3] = new Square(130,258,3, State.empty);
        middleLeftS[4] = new Square(170,258,4, State.empty);
        middleLeftS[5] = new Square(215,258,5, State.empty);
        middleLeftS[6] = new Square(130,302,6, State.empty);
        middleLeftS[7] = new Square(170,302,7, State.empty);
        middleLeftS[8] = new Square(215,302,8, State.empty);

        // Position and initialize the squares in the center board as empty
        centerS[0] = new Square(275,218,0, State.empty);
        centerS[1] = new Square(317,218,1, State.empty);
        centerS[2] = new Square(360,218,2, State.empty);
        centerS[3] = new Square(275,258,3, State.empty);
        centerS[4] = new Square(317,258,4, State.empty);
        centerS[5] = new Square(360,258,5, State.empty);
        centerS[6] = new Square(275,302,6, State.empty);
        centerS[7] = new Square(317,302,7, State.empty);
        centerS[8] = new Square(360,302,8, State.empty);

        // Position and initialize the squares in the middle right board as empty
        middleRightS[0] = new Square(415,218,0, State.empty);
        middleRightS[1] = new Square(458,218,1, State.empty);
        middleRightS[2] = new Square(500,218,2, State.empty);
        middleRightS[3] = new Square(415,258,3, State.empty);
        middleRightS[4] = new Square(458,258,4, State.empty);
        middleRightS[5] = new Square(500,258,5, State.empty);
        middleRightS[6] = new Square(415,302,6, State.empty);
        middleRightS[7] = new Square(458,302,7, State.empty);
        middleRightS[8] = new Square(500,302,8, State.empty);

        // Position and initialize the squares in the bottom left board as empty
        bottomLeftS[0] = new Square(130,358,0, State.empty);
        bottomLeftS[1] = new Square(175,358,1, State.empty);
        bottomLeftS[2] = new Square(215,358,2, State.empty);
        bottomLeftS[3] = new Square(130,400,3, State.empty);
        bottomLeftS[4] = new Square(175,400,4, State.empty);
        bottomLeftS[5] = new Square(215,400,5, State.empty);
        bottomLeftS[6] = new Square(130,440,6, State.empty);
        bottomLeftS[7] = new Square(175,440,7, State.empty);
        bottomLeftS[8] = new Square(215,440,8, State.empty);

        // Position and initialize the squares in the bottom middle board as empty
        bottomMiddleS[0] = new Square(275,358,0, State.empty);
        bottomMiddleS[1] = new Square(317,358,1, State.empty);
        bottomMiddleS[2] = new Square(360,358,2, State.empty);
        bottomMiddleS[3] = new Square(275,400,3, State.empty);
        bottomMiddleS[4] = new Square(317,400,4, State.empty);
        bottomMiddleS[5] = new Square(360,400,5, State.empty);
        bottomMiddleS[6] = new Square(275,440,6, State.empty);
        bottomMiddleS[7] = new Square(317,440,7, State.empty);
        bottomMiddleS[8] = new Square(360,440,8, State.empty);

        // Position and initialize the squares in the bottom right board as empty
        bottomRightS[0] = new Square(415,358,0, State.empty);
        bottomRightS[1] = new Square(458,358,1, State.empty);
        bottomRightS[2] = new Square(500,358,2, State.empty);
        bottomRightS[3] = new Square(415,400,3, State.empty);
        bottomRightS[4] = new Square(458,400,4, State.empty);
        bottomRightS[5] = new Square(500,400,5, State.empty);
        bottomRightS[6] = new Square(415,440,6, State.empty);
        bottomRightS[7] = new Square(458,440,7, State.empty);
        bottomRightS[8] = new Square(500,440,8, State.empty);

        // Position and initialize the global board
        globalBoard[0] = new localBoard(130,75, State.empty,true);
        globalBoard[1] = new localBoard(275,75, State.empty,true);
        globalBoard[2] = new localBoard(415,75, State.empty,true);
        globalBoard[3] = new localBoard(130,215, State.empty,true);
        globalBoard[4] = new localBoard(275,215, State.empty,true);
        globalBoard[5] = new localBoard(415,215, State.empty,true);
        globalBoard[6] = new localBoard(130,358, State.empty,true);
        globalBoard[7] = new localBoard(275,358, State.empty,true);
        globalBoard[8] = new localBoard(415,358, State.empty,true);

        // Sets the local boards to the Square arrays laid out
        globalBoard[0].setSquares(topLeftS);
        globalBoard[1].setSquares(topMiddleS);
        globalBoard[2].setSquares(topRightS);
        globalBoard[3].setSquares(middleLeftS);
        globalBoard[4].setSquares(centerS);
        globalBoard[5].setSquares(middleRightS);
        globalBoard[6].setSquares(bottomLeftS);
        globalBoard[7].setSquares(bottomMiddleS);
        globalBoard[8].setSquares(bottomRightS);


        // Sets sounds for clicking and when x and o plays
        xSound = Gdx.audio.newSound(Gdx.files.internal("x.mp3"));
        oSound = Gdx.audio.newSound(Gdx.files.internal("o.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        // The texture atlas and the animation
        xtextureAtlas = new TextureAtlas(Gdx.files.internal("Animations/XTexture/XAnimation.atlas"));
        xanimation = new Animation(1/15f, xtextureAtlas.getRegions());

        // The texture atlas and the animation
        otextureAtlas = new TextureAtlas(Gdx.files.internal("Animations/OTexture/OAnimation.atlas"));
        oanimation = new Animation(1/15f, otextureAtlas.getRegions());

        // Textures for the X and O's sprites to be made
        X = new Texture(Gdx.files.internal("x.png"));
        O = new Texture(Gdx.files.internal("o.png"));
    }

    public void render(float delta) {
        //Green game screen
        Gdx.gl.glClearColor(0F, 1.0F, 0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.game.font.draw(this.game.batch, "Xtreme Tic Tac Toe", 200.0F, 780.0F);

        // While the game has not been won alternate between X and O diplay a message with whose turn it is
        if (!(drawMsg)) {
            if (turn % 2 == 0)
                this.game.font.draw(this.game.batch, xString, 325.0F, 735.0F);
            else
                this.game.font.draw(this.game.batch, oString, 325.0F, 735.0F);
        }

        // If a winner is determined the game will stop rendering whose turn it is and displays the winner and a message to go back to the home scrren
        if (drawMsg) {
            if (globalWinner == State.playerX)
                this.game.font.draw(this.game.batch, "X wins, press space to return to the main menu", 125.0F, 735.0F);
            else if (globalWinner == State.playerO)
                this.game.font.draw(this.game.batch, "O wins, press space to return to the main menu", 125.0F, 735.0F);
            // Sends the user back to the main menu screen
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                this.game.setScreen(new MainMenuScreen(this.game));
            }
        }

        if(drawXO) { // Draws the sprites -- this was used to get the sprites to actually stay on the board instead of flashing
            for(int i = 0; i < listXO.size(); i++){
                listXO.get(i).draw(this.game.batch);
            }
        }

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3, 3);
        this.game.font.setColor(Color.BLACK);
        //game title and turn

        //When the mouse is clicked for the first time the first x is placed in its spot
        if (Gdx.input.justTouched() && firstMove) { // First Move only
            Vector3 touchpos = new Vector3();
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);
            //camera.unproject(touchpos);
            for (int i = 0; i < 9; i++) {
                if (touchpos.x > globalBoard[i].getXPosition() && touchpos.x < globalBoard[i].getXPosition() + globalBoard[i].getWidth() && touchpos.y > globalBoard[i].getYPosition() && touchpos.y < globalBoard[i].getYPosition() + globalBoard[i].getHeight()) {
                    xSound.play();
                    previousLocalBoard = globalBoard[i];
                    //System.out.println(previousLocalBoard.getXPosition()); -- Used for testing
                    previousSquares = previousLocalBoard.getSquares();
                    //this.game.batch.draw((TextureRegion)(xanimation.getKeyFrame(elapsedTime, true)), 150, 150);
                    drawXO = true;
                    //System.out.println(previousSquares[2].getxPosition()); -- Used for testing
                    for (int j = 0; j < 9; j++) {
                        if (touchpos.x > previousSquares[j].getxPosition() && touchpos.x < previousSquares[j].getxPosition() + previousSquares[j].getWidth() && touchpos.y > previousSquares[j].getyPosition() && touchpos.y < previousSquares[j].getyPosition() + previousSquares[j].getHeight()) {
                            previousSquares[j].setState(State.playerX);
                            previousMoveIndex = j;
                            //this.game.batch.draw((TextureRegion)(xanimation.getKeyFrame(elapsedTime, false)), previousSquares[j].getxPosition(), previousSquares[j].getyPosition());
                            //camera.unproject(touchpos);
                            //this.game.font.draw(this.game.batch, "X", previousSquares[j].getxPosition()+45, 735 - previousSquares[j].getyPosition());
                            Sprite temp = new Sprite(X);        // Creates an X sprite that stays on the board -- just in the wrong position
                            temp.setPosition(touchpos.x,touchpos.y);
                            temp.setScale(1/5f);
                            listXO.add(temp);
                            globalBoard[i].setSquares(previousSquares); // Sets the global board's local board with the altered squares
                            System.out.println(previousMoveIndex);
                            nextLocalBoard = globalBoard[j];
                            nextLocalBoard.setStatus(true);               // Sets the status as true for the first move
                            currentSquares = nextLocalBoard.getSquares(); // Sets the new current squares
                            firstMove = false;                            // First move is complete
                            turn++;
                            break;
                        }
                    }
                }
            }
        }
        if(globalWinner == State.empty){ //As long as there is not a winner keep checking mouse clicks
            if (Gdx.input.justTouched() && !firstMove) {
                Vector3 touchpos = new Vector3();
                touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);   //If touchpos is in a local board find the square it was clicked in
                if (touchpos.x > nextLocalBoard.getXPosition() && touchpos.x < nextLocalBoard.getXPosition() + nextLocalBoard.getWidth() && touchpos.y > nextLocalBoard.getYPosition() && touchpos.y < nextLocalBoard.getYPosition() + nextLocalBoard.getHeight() && nextLocalBoard.getStatus()) {
                    for (int j = 0; j < 9; j++) {
                        if (touchpos.x > currentSquares[j].getxPosition() && touchpos.x < currentSquares[j].getxPosition() + currentSquares[j].getWidth() && touchpos.y > currentSquares[j].getyPosition() && touchpos.y < currentSquares[j].getyPosition() + currentSquares[j].getHeight()) {
                            if (currentSquares[j].getState() == State.empty) {
                                if (turn % 2 == 0) {
                                    xSound.play();
                                    //Draw X Animation at current squares[j] position creat(x y)
                                    Sprite temp = new Sprite(X);        // Creates an X sprite that stays on the board -- just in the wrong position
                                    temp.setPosition(touchpos.x,touchpos.y);
                                    temp.setScale(1/5f);
                                    listXO.add(temp);
                                    currentSquares[j].setState(State.playerX);
                                    globalBoard[previousMoveIndex].setSquares(currentSquares);
                                    turn++;
                                } else {
                                    oSound.play();
                                    //Draw O animation at current squares[j]
                                    Sprite temp = new Sprite(O);        // Creates an O sprite that stays on the board -- just in the wrong position
                                    temp.setPosition(touchpos.x,touchpos.y);
                                    temp.setScale(1/7f);
                                    listXO.add(temp);
                                    currentSquares[j].setState(State.playerO);
                                    globalBoard[previousMoveIndex].setSquares(currentSquares);
                                    turn++;
                                }
                                previousMoveIndex = j;
                                nextLocalBoard = globalBoard[previousMoveIndex];
                                currentSquares = nextLocalBoard.getSquares();
                            }
                        }
                    }
                }
            }
        }
        if(nextLocalBoard != null) {                    //Null pointer exception was being thrown
            nextLocalBoard.checkWinner();
            if (nextLocalBoard.getWinner() == State.empty) {  //Check next local board to make sure it doesn't have a winner
                nextLocalBoard.setStatus(true);
                currentSquares = nextLocalBoard.getSquares(); // get the next boards squares
            } else {
                nextLocalBoard.setStatus(false);               // If it does have a winner set the status to false s it cant be played in anymore
                for (int i = 0; i < 9; i++) {
                    if (globalBoard[i].getWinner() == State.empty) {  // and make every board not won a board possible to play in
                        globalBoard[i].setStatus(true);
                    }
                }
            }
        }
        this.checkWinner();
        this.game.batch.end();

        //TO DO:
        // Animation
        // Get sprites to actually show up in the squares they were clicked in
        // TEST

        spriteBatch.begin();
        if (drawBoard) {
            globalSprite.draw(spriteBatch);
            middle.draw(spriteBatch);
            topMiddle.draw(spriteBatch);
            topLeft.draw(spriteBatch);
            topRight.draw(spriteBatch);
            middleRight.draw(spriteBatch);
            middleLeft.draw(spriteBatch);
            bottomLeft.draw(spriteBatch);
            bottomMiddle.draw(spriteBatch);
            bottomRight.draw(spriteBatch);
        }
        spriteBatch.end();


    }


    public void resize(int width, int height) {
    }

    public void show() {
    }

    public void hide() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
        xSound.dispose();
        oSound.dispose();
        click.dispose();
        global.dispose();
        X.dispose();
        O.dispose();
    }
    public void checkWinner(){
            // Top Row
            if(globalBoard[0].getWinner() == globalBoard[1].getWinner() && globalBoard[1].getWinner() == globalBoard[2].getWinner() && globalBoard[0].getWinner() != State.empty) {
                this.globalWinner = globalBoard[0].getWinner();
                drawMsg = true;
            } // Middle Row
            else if(globalBoard[3].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[5].getWinner() && globalBoard[3].getWinner() != State.empty) {
                this.globalWinner = globalBoard[3].getWinner();
                drawMsg = true;
            } // Bottom Row
            else if (globalBoard[6].getWinner() == globalBoard[7].getWinner() && globalBoard[7].getWinner() == globalBoard[8].getWinner() && globalBoard[6].getWinner() != State.empty) {
                this.globalWinner = globalBoard[6].getWinner();
                drawMsg = true;
            } // Left Column
            else if (globalBoard[0].getWinner() == globalBoard[3].getWinner() && globalBoard[3].getWinner() == globalBoard[6].getWinner() && globalBoard[0].getWinner() != State.empty) {
                this.globalWinner = globalBoard[0].getWinner();
                drawMsg = true;
            } // Middle Column
            else if (globalBoard[1].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[5].getWinner() && globalBoard[1].getWinner() != State.empty) {
                this.globalWinner = globalBoard[1].getWinner();
                drawMsg = true;
            } // Right Column
            else if (globalBoard[2].getWinner() == globalBoard[5].getWinner() && globalBoard[5].getWinner() == globalBoard[8].getWinner() && globalBoard[2].getWinner() != State.empty) {
                this.globalWinner = globalBoard[2].getWinner();
                drawMsg = true;
            } // Diagonals
            else if (globalBoard[0].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[8].getWinner() && globalBoard[0].getWinner() != State.empty) {
                this.globalWinner = globalBoard[0].getWinner();
                drawMsg = true;
            }
            else if (globalBoard[3].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[6].getWinner() && globalBoard[3].getWinner() != State.empty) {
                this.globalWinner = globalBoard[3].getWinner();
                drawMsg = true;
            }
            else
                this.globalWinner = State.empty;
    }
}
