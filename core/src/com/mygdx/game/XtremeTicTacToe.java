package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class XtremeTicTacToe extends Game {
	SpriteBatch batch;
	BitmapFont font;

	public XtremeTicTacToe(){
	}

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		super.render();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		this.font.dispose();
	}
}
