package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.XtremeTicTacToe;

//Hope Davis
//Instructions for after X moves and is O's turn

public class HowToPlayScreen3 implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;

    //sound for when arrows are clicked
    Sound click;

    //Textures for arrows
    Texture arrow;
    Texture backArrow;

    //Texture and sprite for x
    Texture x;
    Sprite xSprite;

    SpriteBatch spriteBatch;

    //texture and sprite for global board
    Texture global;
    Sprite globalSprite;

    //sprites for local boards
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

    public HowToPlayScreen3(XtremeTicTacToe gam) {
        spriteBatch = new SpriteBatch();
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sound
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        //arrow textures
        arrow = new Texture(Gdx.files.internal("arrow.png"));
        backArrow = new Texture(Gdx.files.internal("backArrow.jpg"));

        //x texture
        x = new Texture(Gdx.files.internal("x.png"));
        xSprite = new Sprite(x);

        //sets up global board
        global = new Texture(Gdx.files.internal("tictactoeGlobal.png"));
        globalSprite = new Sprite(global);
        globalSprite.setPosition(-125.0f, -220.0f);
        globalSprite.setScale( .48f, .48f);

        //sets local boards
        middle = new Sprite(global);
        middle.setPosition(-125.0f, -220.0f);
        middle.setScale( .14f, .14f);

        topMiddle = new Sprite(global);
        topMiddle.setPosition(-125.0f, -110.0f);
        topMiddle.setScale( .14f, .14f);

        topLeft = new Sprite(global);
        topLeft.setPosition(-235.0f, -110.0f);
        topLeft.setScale( .14f, .14f);

        topRight = new Sprite(global);
        topRight.setPosition(-10.0f, -110.0f);
        topRight.setScale( .14f, .14f);

        middleRight = new Sprite(global);
        middleRight.setPosition(-10.0f, -220.0f);
        middleRight.setScale( .14f, .14f);

        middleLeft = new Sprite(global);
        middleLeft.setPosition(-235.0f, -220.0f);
        middleLeft.setScale( .14f, .14f);

        bottomLeft = new Sprite(global);
        bottomLeft.setPosition(-235.0f, -330.0f);
        bottomLeft.setScale( .14f, .14f);

        bottomMiddle = new Sprite(global);
        bottomMiddle.setPosition(-125.0f, -330.0f);
        bottomMiddle.setScale( .14f, .14f);

        bottomRight = new Sprite(global);
        bottomRight.setPosition(-10.0f, -330.0f);
        bottomRight.setScale( .14f, .14f);

        //sets x sprite
        xSprite.setPosition(175.0f, -45.0f);
        xSprite.setScale(.13f, .13f);
    }

    public void render(float delta) {
        //green game screen
        Gdx.gl.glClearColor(0.0F, 1.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(2,2);
        this.game.font.setColor(Color.BLACK);

        //instructions
        this.game.font.draw(this.game.batch, "X played in the top-left corner, so\nO must move in the top-left board", 180.0F , 760.0F);
        this.game.batch.draw(arrow, 740, 30);

        //arrows
        this.game.batch.draw(backArrow, 15, 30);
        this.game.batch.end();

        //sets board
        spriteBatch.begin();
        if (drawBoard) {
            xSprite.draw(spriteBatch);
            globalSprite.draw(spriteBatch);
            middle.draw(spriteBatch);

            //use this to signify you can't move in this board
            middle.setPackedColor(255);

            topMiddle.draw(spriteBatch);
            topMiddle.setPackedColor(255);
            topLeft.draw(spriteBatch);
            topRight.draw(spriteBatch);
            topRight.setPackedColor(255);
            middleRight.draw(spriteBatch);
            middleRight.setPackedColor(255);
            middleLeft.draw(spriteBatch);
            middleLeft.setPackedColor(255);
            bottomLeft.draw(spriteBatch);
            bottomLeft.setPackedColor(255);
            bottomMiddle.draw(spriteBatch);
            bottomMiddle.setPackedColor(255);
            bottomRight.draw(spriteBatch);
            bottomRight.setPackedColor(255);
        }
        spriteBatch.end();

        //button functionality
        if(Gdx.input.justTouched()){
            //plays noise when button is clicked
            click.play();

            Vector3 touchpos = new Vector3();

            //sets vector values for touch position
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);

            //to see if back arrow was clicked
            if(touchpos.x < 50.0F && touchpos.x > 15.0F  && touchpos.y < 460.0F  && touchpos.y > 445.0F){
                this.game.setScreen(new HowToPlayScreen2(this.game));
            }
            //to see if forward arrow was clicked
            if(touchpos.x < 630.0F  && touchpos.x > 590.0F && touchpos.y < 460.0F && touchpos.y > 440.0F){
                this.game.setScreen(new HowToPlayScreen4(this.game));
            }
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
        arrow.dispose();
        x.dispose();
        global.dispose();
        backArrow.dispose();
        click.dispose();
    }
}
