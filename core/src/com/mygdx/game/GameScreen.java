package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.XtremeTicTacToe;

public class GameScreen implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;
    Sound xSound;
    Sound oSound;
    Sound click;
    Music gameMusic;

    public GameScreen(XtremeTicTacToe gam) {
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //sets sounds for clicking and when x and o plays
        //sets background music to loop
        xSound = Gdx.audio.newSound(Gdx.files.internal("x.wav"));
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
    }
}
