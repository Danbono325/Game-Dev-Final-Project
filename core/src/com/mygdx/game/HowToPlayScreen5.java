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

public class HowToPlayScreen5 implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound click;
    Texture arrow;
    Texture backArrow;
    Texture x, x2, x3, x4;
    Texture o, o2, o3, o4;
    Sprite oSprite, o2Sprite, o3Sprite, o4Sprite;
    Sprite xSprite, x2Sprite, x3Sprite, x4Sprite;

    Texture xScrib;
    Sprite xScribble;

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

    public HowToPlayScreen5(XtremeTicTacToe gam) {
        spriteBatch = new SpriteBatch();
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sounds and loop the music
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        //sets all textures
        xScrib = new Texture(Gdx.files.internal("xscribble.png"));
        xScribble = new Sprite(xScrib);
        arrow = new Texture(Gdx.files.internal("arrow.png"));
        backArrow = new Texture(Gdx.files.internal("backArrow.jpg"));
        x = new Texture(Gdx.files.internal("x.png"));
        x2 = new Texture(Gdx.files.internal("x.png"));
        x3 = new Texture(Gdx.files.internal("x.png"));
        x4 = new Texture(Gdx.files.internal("x.png"));
        xSprite = new Sprite(x);
        x2Sprite = new Sprite(x2);
        x3Sprite = new Sprite(x3);
        x4Sprite = new Sprite(x4);
        o = new Texture(Gdx.files.internal("o.png"));
        o2 = new Texture(Gdx.files.internal("o.png"));
        o3 = new Texture(Gdx.files.internal("o.png"));
        o4 = new Texture(Gdx.files.internal("o.png"));
        oSprite = new Sprite(o);
        o2Sprite = new Sprite(o2);
        o3Sprite = new Sprite(o3);
        o4Sprite = new Sprite(o4);

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

        xScribble.setPosition(-400.0f, -690.0f);
        xScribble.setScale(.065f, .065f);

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

        xSprite.setPosition(175.0f, -45.0f);
        xSprite.setScale(.13f, .13f);

        x2Sprite.setPosition(175.0f, -79.0f);
        x2Sprite.setScale(.13f, .13f);

        x3Sprite.setPosition(175.0f, -109.0f);
        x3Sprite.setScale(.13f, .13f);

        x4Sprite.setPosition(92.0f, 0.0f);
        x4Sprite.setScale(.13f, .13f);

        oSprite.setPosition(20.0f, 0.0f);
        oSprite.setScale(.08f);

        o2Sprite.setPosition(-15.0f, -155.0f);
        o2Sprite.setScale(.08f);

        o3Sprite.setPosition(130.0f, -187.0f);
        o3Sprite.setScale(.08f);

        o4Sprite.setPosition(245.0f, -187.0f);
        o4Sprite.setScale(.08f);
    }

    public void render(float delta) {
        //white game screen
        Gdx.gl.glClearColor(0.0F, 1.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(2,2);
        this.game.font.setColor(Color.BLACK);

        this.game.font.draw(this.game.batch, "When a small board is won it can no longer be played\n       on. If a player is sent to a dead board, they\n" +
                "                        can move anywhere", 80.0F , 760.0F);
        this.game.batch.draw(arrow, 740, 30);
        this.game.batch.draw(backArrow, 15, 30);
        this.game.batch.end();

        spriteBatch.begin();
        if (drawBoard) {
            oSprite.draw(spriteBatch);
            o2Sprite.draw(spriteBatch);
            o3Sprite.draw(spriteBatch);
            o4Sprite.draw(spriteBatch);
            xSprite.draw(spriteBatch);
            x2Sprite.draw(spriteBatch);
            x3Sprite.draw(spriteBatch);
            x4Sprite.draw(spriteBatch);
            xScribble.draw(spriteBatch);
            globalSprite.draw(spriteBatch);
            middle.draw(spriteBatch);
            topMiddle.draw(spriteBatch);
            topLeft.draw(spriteBatch);
            topRight.draw(spriteBatch);
            middleRight.draw(spriteBatch);
            middleRight.setPackedColor(255);
            middleLeft.draw(spriteBatch);
            bottomLeft.draw(spriteBatch);
            bottomMiddle.draw(spriteBatch);
            bottomRight.draw(spriteBatch);
        }
        spriteBatch.end();

        if(Gdx.input.justTouched()){
            click.play();
            Vector3 touchpos = new Vector3();
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);
            if(touchpos.x < 50.0F && touchpos.x > 15.0F  && touchpos.y < 460.0F  && touchpos.y > 445.0F){
                this.game.setScreen(new HowToPlayScreen4(this.game));
            }
            if(touchpos.x < 630.0F  && touchpos.x > 590.0F && touchpos.y < 460.0F && touchpos.y > 440.0F){
                //this.game.setScreen(new HowToPlayScreen6(this.game));
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
        click.dispose();
    }
}