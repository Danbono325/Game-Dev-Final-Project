//Hope
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
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();
        this.game.batch.draw(x, 235, 405);
        this.game.font.getData().setScale(3,3);
        this.game.font.setColor(Color.BLACK);
        this.game.font.draw(this.game.batch, "Xtreme Tic-Tac-Toe", 235.0F, 780.0F);
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
