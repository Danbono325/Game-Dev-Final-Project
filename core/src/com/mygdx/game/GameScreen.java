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

        if(Gdx.input.isTouched()) {
            click.play();
            Vector3 touchpos = new Vector3();
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);

            if (touchpos.x > 200.0F && touchpos.x < 130.0F + globalBoard[0].getSquares()[0].getWidth() && touchpos.y < 328.0F && touchpos.y > 308.0F) {
                this.game.setScreen(new HowToPlayScreen(this.game));
            }
            else if(touchpos.x < 390.0F && touchpos.x > 270.0F && touchpos.y > 340.0F && touchpos.y < 375.0F){
                this.game.setScreen(new GameScreen(this.game));
            }
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
