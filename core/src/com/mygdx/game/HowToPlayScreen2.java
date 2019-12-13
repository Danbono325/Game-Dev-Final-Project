package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.XtremeTicTacToe;

public class HowToPlayScreen2 implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound click;
    Music menuMusic;
    Texture arrow;
    Texture backArrow;
    Texture emptyBoard;
    Texture o;
    Texture x;

    public HowToPlayScreen2(XtremeTicTacToe gam) {
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sounds and loop the music
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        menuMusic.setLooping(true);

        //sets all textures
        arrow = new Texture(Gdx.files.internal("arrow.png"));
        emptyBoard = new Texture (Gdx.files.internal("emptyBoard.png"));
        backArrow = new Texture(Gdx.files.internal("backArrow.jpg"));
        x = new Texture(Gdx.files.internal("x.png"));
        o = new Texture(Gdx.files.internal("o.png"));
    }

    public void render(float delta) {
        //white game screen
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);

        this.game.font.draw(this.game.batch, "In a game of Xtreme TicTacToe, \nyou have 9 regular games that \ncombined makes a single game", 95.0F , 700.0F);
        this.game.batch.draw(arrow, 740, 30);
        this.game.batch.draw(backArrow, 15, 30);
        this.game.batch.draw(emptyBoard, 180, 30);
        //plays music
        menuMusic.play();
        this.game.batch.end();

        if(Gdx.input.isTouched()){
            click.play();
            Vector3 touchpos = new Vector3();
            touchpos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);
            if(touchpos.x < 50.0F && touchpos.x > 15.0F  && touchpos.y < 460.0F  && touchpos.y > 445.0F){
                this.game.setScreen(new HowToPlayScreen(this.game));
            }
            if(touchpos.x < 630.0F  && touchpos.x > 590.0F && touchpos.y < 460.0F && touchpos.y > 440.0F){
                //this.game.setScreen(new HowToPlayScreen2(this.game));
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
        emptyBoard.dispose();
        x.dispose();
        o.dispose();
        menuMusic.dispose();
        click.dispose();
    }
}
