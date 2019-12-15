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
    Music gameMusic;

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

    boolean drawSprite = true;



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
        globalSprite.setPosition(-125.0f, -130.0f);
        globalSprite.setScale( .65f, .65f);

        middle = new Sprite(global);
        middle.setPosition(-125.0f, -130.0f);
        middle.setScale( .18f, .18f);

        topMiddle = new Sprite(global);
        topMiddle.setPosition(-125.0f, 10.0f);
        topMiddle.setScale( .18f, .18f);

        topLeft = new Sprite(global);
        topLeft.setPosition(-260.0f, 10.0f);
        topLeft.setScale( .18f, .18f);

        topRight = new Sprite(global);
        topRight.setPosition(15.0f, 10.0f);
        topRight.setScale( .18f, .18f);

        middleRight = new Sprite(global);
        middleRight.setPosition(-260.0f, -130.0f);
        middleRight.setScale( .18f, .18f);

        middleLeft = new Sprite(global);
        middleLeft.setPosition(15.0f, -130.0f);
        middleLeft.setScale( .18f, .18f);

        bottomLeft = new Sprite(global);
        bottomLeft.setPosition(-260.0f, -270.0f);
        bottomLeft.setScale( .18f, .18f);

        bottomMiddle = new Sprite(global);
        bottomMiddle.setPosition(-125.0f, -270.0f);
        bottomMiddle.setScale( .18f, .18f);

        bottomRight = new Sprite(global);
        bottomRight.setPosition(15.0f, -270.0f);
        bottomRight.setScale( .18f, .18f);




        Square[][]  board = new Square[9][9]; // Initializes 9 X 9 Board with every square as empty
        for (int i = 0; i < 9; i++)
        {
            for(int j =  0; j < 9; j++)
            {
                board[i][j] = Square.EMPTY;
            }
        }



        //sets sounds for clicking and when x and o plays
        //sets background music to loop
        xSound = Gdx.audio.newSound(Gdx.files.internal("x.mp3"));
        oSound = Gdx.audio.newSound(Gdx.files.internal("o.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.mp3"));
        gameMusic.setLooping(true);
    }

    public void render(float delta) {
        //white game screen
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();


        this.game.font.draw(this.game.batch, "Xtreme Tic Tac Toe", 200.0F, 780.0F);

        if(turn%2 == 0)
            this.game.font.draw(this.game.batch, xString, 325.0F, 735.0F);
        else
            this.game.font.draw(this.game.batch, oString, 325.0F, 735.0F);





        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);
        //game title


        gameMusic.play();
        this.game.batch.end();

        spriteBatch.begin();
        if (drawSprite) {
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

            case (1):
                //Set color of local boards
                //Restrict all boards except top left
                break;
            case (2):
                //Set color of local boards
                //Restrict all boards except top middle
                break;
            case (3):
                //Set color of local boards
                //Restrict all boards except top right
                break;
            case (4):
                //Set color of local boards
                //Restrict all boards except middle left
                break;
            case (5):
                //Set color of local boards
                //Restrict all boards except center
                break;
            case (6):
                //Set color of local boards
                //Restrict all boards except middle right
                break;
            case (7):
                //Set color of local boards
                //Restrict all boards except bottom left
                break;
            case (8):
                //Set color of local boards
                //Restrict all boards except bottom middle
                break;
            case (9):
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
        gameMusic.dispose();
        global.dispose();
    }
}
