package com.mygdx.game;

import com.badlogic.gdx.Game;
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
//Screen shows what a winning board will look like without X's and O's on the board

public class HowToPlayScreen6 implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;

    //sound for when arrows are clicked
    Sound click;

    //Texture for arrows
    Texture arrow;
    Texture backArrow;

    //X Scribble texture and sprites to signify a win in a local board for and X
    Texture xScrib;
    Sprite xScribble, x2Scribble, x3Scribble;

    //O Scribble texture and sprites to signify a win in a local board for and O
    Texture oScrib;
    Sprite oScribble, o2Scribble;

    SpriteBatch spriteBatch;

    //Global board texture and sprite
    Texture global;
    Sprite globalSprite;

    //local board sprites
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

    public HowToPlayScreen6(XtremeTicTacToe gam) {
        spriteBatch = new SpriteBatch();
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sounds
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        //sets scribble textures, sprites, and arrows
        xScrib = new Texture(Gdx.files.internal("xscribble.png"));
        xScribble = new Sprite(xScrib);
        x2Scribble = new Sprite(xScrib);
        x3Scribble = new Sprite(xScrib);
        oScrib = new Texture(Gdx.files.internal("oscribble.png"));
        oScribble = new Sprite(oScrib);
        o2Scribble = new Sprite(oScrib);
        arrow = new Texture(Gdx.files.internal("arrow.png"));
        backArrow = new Texture(Gdx.files.internal("backArrow.jpg"));

        //sets up the board
        global = new Texture(Gdx.files.internal("tictactoeGlobal.png"));
        globalSprite = new Sprite(global);
        globalSprite.setPosition(-125.0f, -220.0f);
        globalSprite.setScale( .48f, .48f);

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

        xScribble.setPosition(-400.0f, -690.0f);
        xScribble.setScale(.065f, .065f);

        x2Scribble.setPosition(-400.0f, -800.0f);
        x2Scribble.setScale(.065f, .065f);

        x3Scribble.setPosition(-400.0f, -580.0f);
        x3Scribble.setScale(.065f, .065f);

        oScribble.setPosition(-518.0f,-690.0f);
        oScribble.setScale(.065f, .065f);

        o2Scribble.setPosition(-625.0f, -800.0f);
        o2Scribble.setScale(.065f, .065f);
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

        //instructions and arrows
        this.game.font.draw(this.game.batch, "This continues until some gets three in a row\n            or no more moves can be made", 110.0f, 760.0f);
        this.game.batch.draw(arrow, 740, 30);
        this.game.batch.draw(backArrow, 15, 30);
        this.game.batch.end();

        //draws sprites for board
        spriteBatch.begin();
        if (drawBoard) {
            xScribble.draw(spriteBatch);
            x2Scribble.draw(spriteBatch);
            x3Scribble.draw(spriteBatch);
            oScribble.draw(spriteBatch);
            o2Scribble.draw(spriteBatch);
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

        //sets arrow functionality
        if(Gdx.input.justTouched()){
            //plays sound when button is clicked
            click.play();

            Vector3 touchpos = new Vector3();

            //sets vector values for touch position
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);

            //to see if back button was clicked
            if(touchpos.x < 50.0F && touchpos.x > 15.0F  && touchpos.y < 460.0F  && touchpos.y > 445.0F){
                this.game.setScreen(new HowToPlayScreen5(this.game));
            }
            //to see if forward button was clicked
            if(touchpos.x < 630.0F  && touchpos.x > 590.0F && touchpos.y < 460.0F && touchpos.y > 440.0F){
                this.game.setScreen(new MainMenuScreen(this.game));
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
        xScrib.dispose();
        oScrib.dispose();
        backArrow.dispose();
        global.dispose();
        arrow.dispose();
        click.dispose();
    }
}