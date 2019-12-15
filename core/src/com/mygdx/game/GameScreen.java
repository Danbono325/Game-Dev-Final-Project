package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.Arrays;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.XtremeTicTacToe;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound xSound;
    Sound oSound;
    Sound click;
    //Music gameMusic;

    SpriteBatch spriteBatch;
    Texture global;
    Sprite globalSprite;

    Sprite middle;
    Sprite topLeft;
    Sprite topMiddle;
    Sprite topRight;
    Sprite middleLeft;
    Sprite middleRight;
    Sprite bottomLeft;
    Sprite bottomMiddle;
    Sprite bottomRight;

    boolean drawBoard = true;
    boolean drawMsg = false;

    //boolean hasWinner = false;
    State winner;
    localBoard[] globalBoard = new localBoard[9];

    String xString = new String("X's turn");
    String oString = new String("O's turn");

    int turn = 0;
    int previousMoveIndex = 0;

    public GameScreen(XtremeTicTacToe gam) {
        spriteBatch = new SpriteBatch();
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        global = new Texture(Gdx.files.internal("tictactoeGlobal.png"));
        globalSprite = new Sprite(global);
        globalSprite.setPosition(-125.0f, -180.0f);
        globalSprite.setScale( .6f, .6f);

        middle = new Sprite(global);
        middle.setPosition(-125.0f, -180.0f);
        middle.setScale( .18f, .18f);

        topMiddle = new Sprite(global);
        topMiddle.setPosition(-125.0f, -40.0f);
        topMiddle.setScale( .18f, .18f);

        topLeft = new Sprite(global);
        topLeft.setPosition(-265.0f, -40.0f);
        topLeft.setScale( .18f, .18f);

        topRight = new Sprite(global);
        topRight.setPosition(15.0f, -40.0f);
        topRight.setScale( .18f, .18f);

        middleRight = new Sprite(global);
        middleRight.setPosition(15.0f, -180.0f);
        middleRight.setScale( .18f, .18f);

        middleLeft = new Sprite(global);
        middleLeft.setPosition(-265.0f, -180.0f);
        middleLeft.setScale( .18f, .18f);

        bottomLeft = new Sprite(global);
        bottomLeft.setPosition(-265.0f, -320.0f);
        bottomLeft.setScale( .18f, .18f);

        bottomMiddle = new Sprite(global);
        bottomMiddle.setPosition(-125.0f, -320.0f);
        bottomMiddle.setScale( .18f, .18f);

        bottomRight = new Sprite(global);
        bottomRight.setPosition(15.0f, -320.0f);
        bottomRight.setScale( .18f, .18f);

        Square[] topLeftS = new Square[9];
        topLeftS[0] = new Square(130,75,0, State.empty);
        topLeftS[1] = new Square(175,75,1, State.empty);
        topLeftS[2] = new Square(215,75,2, State.empty);
        topLeftS[3] = new Square(130,115,3, State.empty);
        topLeftS[4] = new Square(175,115,4, State.empty);
        topLeftS[5] = new Square(215,115,5, State.empty);
        topLeftS[6] = new Square(130,160,6, State.empty);
        topLeftS[7] = new Square(175,160,7, State.empty);
        topLeftS[8] = new Square(215,160,8, State.empty);

        Square[] topMiddleS = new Square[9];
        topMiddleS[0] = new Square(275,75,0, State.empty);
        topMiddleS[1] = new Square(317,75,1, State.empty);
        topMiddleS[2] = new Square(360,75,2, State.empty);
        topMiddleS[3] = new Square(275,120,3, State.empty);
        topMiddleS[4] = new Square(317,120,4, State.empty);
        topMiddleS[5] = new Square(360,120,5, State.empty);
        topMiddleS[6] = new Square(275,160,6, State.empty);
        topMiddleS[7] = new Square(317,160,7, State.empty);
        topMiddleS[8] = new Square(360,160,8, State.empty);

        Square[] topRightS = new Square[9];
        topRightS[0] = new Square(415,75,0, State.empty);
        topRightS[1] = new Square(458,75,1, State.empty);
        topRightS[2] = new Square(500,75,2, State.empty);
        topRightS[3] = new Square(415,120,3, State.empty);
        topRightS[4] = new Square(458,120,4, State.empty);
        topRightS[5] = new Square(500,120,5, State.empty);
        topRightS[6] = new Square(415,160,6, State.empty);
        topRightS[7] = new Square(458,160,7, State.empty);
        topRightS[8] = new Square(500,160,8, State.empty);

        Square[] middleLeftS = new Square[9];
        middleLeftS[0] = new Square(130,215,0, State.empty);
        middleLeftS[1] = new Square(170,215,1, State.empty);
        middleLeftS[2] = new Square(215,215,2, State.empty);
        middleLeftS[3] = new Square(130,258,3, State.empty);
        middleLeftS[4] = new Square(170,258,4, State.empty);
        middleLeftS[5] = new Square(215,258,5, State.empty);
        middleLeftS[6] = new Square(130,302,6, State.empty);
        middleLeftS[7] = new Square(170,302,7, State.empty);
        middleLeftS[8] = new Square(215,302,8, State.empty);

        Square[] centerS = new Square[9];
        centerS[0] = new Square(275,218,0, State.empty);
        centerS[1] = new Square(317,218,1, State.empty);
        centerS[2] = new Square(360,218,2, State.empty);
        centerS[3] = new Square(275,258,3, State.empty);
        centerS[4] = new Square(317,258,4, State.empty);
        centerS[5] = new Square(360,258,5, State.empty);
        centerS[6] = new Square(275,302,6, State.empty);
        centerS[7] = new Square(317,302,7, State.empty);
        centerS[8] = new Square(360,302,8, State.empty);

        Square[] middleRightS = new Square[9];
        middleRightS[0] = new Square(415,218,0, State.empty);
        middleRightS[1] = new Square(458,218,1, State.empty);
        middleRightS[2] = new Square(500,218,2, State.empty);
        middleRightS[3] = new Square(415,258,3, State.empty);
        middleRightS[4] = new Square(458,258,4, State.empty);
        middleRightS[5] = new Square(500,258,5, State.empty);
        middleRightS[6] = new Square(415,302,6, State.empty);
        middleRightS[7] = new Square(458,302,7, State.empty);
        middleRightS[8] = new Square(500,302,8, State.empty);

        Square[] bottomLeftS = new Square[9];
        bottomLeftS[0] = new Square(130,358,0, State.empty);
        bottomLeftS[1] = new Square(175,358,1, State.empty);
        bottomLeftS[2] = new Square(215,358,2, State.empty);
        bottomLeftS[3] = new Square(130,400,3, State.empty);
        bottomLeftS[4] = new Square(175,400,4, State.empty);
        bottomLeftS[5] = new Square(215,400,5, State.empty);
        bottomLeftS[6] = new Square(130,440,6, State.empty);
        bottomLeftS[7] = new Square(175,440,7, State.empty);
        bottomLeftS[8] = new Square(215,440,8, State.empty);

        Square[] bottomMiddleS = new Square[9];
        bottomMiddleS[0] = new Square(275,358,0, State.empty);
        bottomMiddleS[1] = new Square(317,358,1, State.empty);
        bottomMiddleS[2] = new Square(360,358,2, State.empty);
        bottomMiddleS[3] = new Square(275,400,3, State.empty);
        bottomMiddleS[4] = new Square(317,400,4, State.empty);
        bottomMiddleS[5] = new Square(360,400,5, State.empty);
        bottomMiddleS[6] = new Square(275,440,6, State.empty);
        bottomMiddleS[7] = new Square(317,440,7, State.empty);
        bottomMiddleS[8] = new Square(360,440,8, State.empty);

        Square[] bottomRightS = new Square[9];
        bottomRightS[0] = new Square(415,358,0, State.empty);
        bottomRightS[1] = new Square(458,358,1, State.empty);
        bottomRightS[2] = new Square(500,358,2, State.empty);
        bottomRightS[3] = new Square(415,400,3, State.empty);
        bottomRightS[4] = new Square(458,400,4, State.empty);
        bottomRightS[5] = new Square(500,400,5, State.empty);
        bottomRightS[6] = new Square(415,440,6, State.empty);
        bottomRightS[7] = new Square(458,440,7, State.empty);
        bottomRightS[8] = new Square(500,440,8, State.empty);

        globalBoard[0] = new localBoard(130,75, topLeftS, State.empty,true);
        globalBoard[1] = new localBoard(275,75, topMiddleS, State.empty,true);
        globalBoard[2] = new localBoard(415,75, topRightS, State.empty,true);
        globalBoard[3] = new localBoard(130,215, middleLeftS, State.empty,true);
        globalBoard[4] = new localBoard(275,215, centerS, State.empty,true);
        globalBoard[5] = new localBoard(415,215, middleRightS, State.empty,true);
        globalBoard[6] = new localBoard(130,358, bottomLeftS, State.empty,true);
        globalBoard[7] = new localBoard(275,358, bottomMiddleS, State.empty,true);
        globalBoard[8] = new localBoard(415,358, bottomRightS, State.empty,true);

        //sets sounds for clicking and when x and o plays
        //sets background music to loop
        xSound = Gdx.audio.newSound(Gdx.files.internal("x.mp3"));
        oSound = Gdx.audio.newSound(Gdx.files.internal("o.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        //gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.mp3"));
        //gameMusic.setLooping(true);
    }

    public void render(float delta) {
        //Green game screen
        Gdx.gl.glClearColor(0F, 1.0F, 0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();


        this.game.font.draw(this.game.batch, "Xtreme Tic Tac Toe", 200.0F, 780.0F);


        if(!(drawMsg)) {
            if (turn % 2 == 0)
                this.game.font.draw(this.game.batch, xString, 325.0F, 735.0F);
            else
                this.game.font.draw(this.game.batch, oString, 325.0F, 735.0F);
        }
        //this.game.font.draw(this.game.batch, "X wins, press space to \n return to the main menu", 325.0F, 735.0F);


        if(Gdx.input.justTouched()) {
            Vector3 touchpos = new Vector3();
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);
            for(int i = 0; i < 9; i++) {
                if(touchpos.x > globalBoard[i].xPosition && touchpos.x < globalBoard[i].xPosition+globalBoard[i].width && touchpos.y > globalBoard[i].yPosition  && touchpos.y < globalBoard[i].yPosition+globalBoard[i].height){
                   xSound.play();
                   //draw animation at touch position if
                    break;
                }
            }
            //get index of animation just drawn
            //get square[]
            //check to see if there is a winner
            //check to see if it is x or o


//
        }


        if(drawMsg) {
            if (winner == State.playerX)
                this.game.font.draw(this.game.batch, "X wins, press space to return to the main menu", 325.0F, 735.0F);
            else if (winner == State.playerO)
                this.game.font.draw(this.game.batch, "O wins, press space to return to the main menu", 325.0F, 735.0F);
        }
        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);
        //game title


        //gameMusic.play();
        this.game.batch.end();

        spriteBatch.begin();
        if (drawBoard) {
            // batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(), sprite.getOriginY(),
            //         sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(),
            //        sprite.getRotation());
            // or just do this
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

    public void nextBoard()
    {
        switch (previousMoveIndex){

            case (0):
                //Set color of local boards
                //Restrict all boards except top left
                //Find the next board
                break;
            case (1):
                //Set color of local boards
                //Restrict all boards except top middle
                break;
            case (2):
                //Set color of local boards
                //Restrict all boards except top right
                break;
            case (3):
                //Set color of local boards
                //Restrict all boards except middle left
                break;
            case (4):
                //Set color of local boards
                //Restrict all boards except center
                break;
            case (5):
                //Set color of local boards
                //Restrict all boards except middle right
                break;
            case (6):
                //Set color of local boards
                //Restrict all boards except bottom left
                break;
            case (7):
                //Set color of local boards
                //Restrict all boards except bottom middle
                break;
            case (8):
                //Set color of local boards
                //Restrict all boards except bottom right
                break;
        }
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
        //gameMusic.dispose();
        global.dispose();
    }
    public void checkWinner(){
            //Top Row
            if(globalBoard[0].getWinner() == globalBoard[1].getWinner() && globalBoard[1].getWinner() == globalBoard[2].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[0].getWinner();
                drawMsg = true;
            } //Middle Row
            else if(globalBoard[3].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[5].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[3].getWinner();
                drawMsg = true;
            } //Bottom Row
            else if (globalBoard[6].getWinner() == globalBoard[7].getWinner() && globalBoard[7].getWinner() == globalBoard[8].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[6].getWinner();
                drawMsg = true;
            } //left Column
            else if (globalBoard[0].getWinner() == globalBoard[3].getWinner() && globalBoard[3].getWinner() == globalBoard[6].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[0].getWinner();
                drawMsg = true;
            } //Middle Column
            else if (globalBoard[1].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[5].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[1].getWinner();
                drawMsg = true;
            } //Right Column
            else if (globalBoard[2].getWinner() == globalBoard[5].getWinner() && globalBoard[5].getWinner() == globalBoard[8].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[2].getWinner();
                drawMsg = true;
            } //Diagonals
            else if (globalBoard[0].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[8].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[0].getWinner();
                drawMsg = true;
            }
            else if (globalBoard[3].getWinner() == globalBoard[4].getWinner() && globalBoard[4].getWinner() == globalBoard[6].getWinner()) {
                drawBoard = false;
                this.winner = globalBoard[3].getWinner();
                drawMsg = true;
            }
            else
                this.winner = State.empty;
    }
}
