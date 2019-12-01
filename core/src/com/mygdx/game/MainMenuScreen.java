package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.XtremeTicTacToe;

public class MainMenuScreen implements Screen {
    final XtremeTicTacToe game;
    Texture x;
    Texture instructions;
    Texture play;
    OrthographicCamera camera;
    Sound click;
    Music menuMusic;

    public MainMenuScreen(XtremeTicTacToe gam) {
        this.game = gam;
        x = new Texture(Gdx.files.internal("xtreme.jpeg"));
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);

        //set sounds and loop the music
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        menuMusic.setLooping(true);

        //instructions button
        instructions = new Texture(Gdx.files.internal("instructions.png"));

        //play button
        play = new Texture(Gdx.files.internal("play.png"));
    }

    public void render(float delta) {
        //white game screen
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();

        menuMusic.play();

        //logo
        this.game.batch.draw(x, 235, 455);

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);
        //game title
        this.game.font.draw(this.game.batch, "Xtreme Tic-Tac-Toe", 230.0F, 455.0F);

        //instructions button
        this.game.batch.draw(instructions, 247, 250);

        //play button
        this.game.batch.draw(play, 330, 160);

        this.game.batch.end();
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
        x.dispose();
        click.dispose();
        menuMusic.dispose();
    }
}
