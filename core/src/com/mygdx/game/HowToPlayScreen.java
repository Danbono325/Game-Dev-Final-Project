package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.XtremeTicTacToe;

public class HowToPlayScreen implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound click;
    Music menuMusic;
    Texture arrow;
    Texture board;
    Texture o;
    Texture x;

    public HowToPlayScreen(XtremeTicTacToe gam) {
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sounds and loop the music
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        menuMusic.setLooping(true);

        //sets all textures
        arrow = new Texture(Gdx.files.internal("arrow.png"));
        board = new Texture(Gdx.files.internal("board.png"));
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

        menuMusic.play();
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
    }
}
