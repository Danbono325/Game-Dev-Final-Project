package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.XtremeTicTacToe;

public class MainMenuScreen implements Screen {
    final XtremeTicTacToe game;
    Texture x;
    OrthographicCamera camera;

    public MainMenuScreen(XtremeTicTacToe gam) {
        this.game = gam;
        x = new Texture(Gdx.files.internal("xtreme.jpeg"));
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);
    }

    public void render(float delta) {
        //white game screen
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();

        //logo
        this.game.batch.draw(x, 235, 405);

        //setScale changes font size, setting font color to black
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);

        //game title
        this.game.font.draw(this.game.batch, "Xtreme Tic-Tac-Toe", 230.0F, 780.0F);

        //how to play button
        this.game.font.getData().setScale(2,2);
        this.game.font.draw(this.game.batch, "How to Play", 100, 300);

        //two player button
        this.game.font.draw(this.game.batch, "Two Player", 585, 300);
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
    }
}
