//Hope
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.XtremeTicTacToe;

public class MainMenuScreen implements Screen {
    final XtremeTicTacToe game;
    OrthographicCamera camera;

    public MainMenuScreen(XtremeTicTacToe gam) {
        this.game = gam;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800.0F, 800.0F);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.2F, 1.0F);
        Gdx.gl.glClear(16384);
        this.camera.update();
        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();
        this.game.font.draw(this.game.batch, "Xtreme Tic Tac Toe", 400.0F, 400.0F);
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
