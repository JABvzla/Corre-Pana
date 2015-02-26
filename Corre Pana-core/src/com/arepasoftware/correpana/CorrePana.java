package com.arepasoftware.correpana;

import com.arepasoftware.correpana.Global.GameState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
public class CorrePana extends ApplicationAdapter {

	OrthographicCamera camera;
	GameAction game;
	
	@Override
	public void create () 
	{		
		game = new GameAction();		
		camera = new OrthographicCamera();
		camera.position.set(Global.ScreenWidth,Global.ScreenHeight ,0);		
		Global.viewport = new FitViewport(Global.ScreenWidth,Global.ScreenHeight,camera);
		Global.gameState = GameState.ACTION;
	}

	@Override
	public void render () 
	{
		Gdx.gl.glClearColor(0.5f,0.5f ,0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		updateScene();
		//drawScene();
	}
	
	private void updateScene()
	{
		switch (Global.gameState)
		{
		case INIT:
			
		case ACTION:			
		   game.update();
		default:
			break;
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			if(Global.gameState==GameState.ACTION){
				Global.gameState = GameState.PAUSE;
			}else{
				Global.gameState = GameState.ACTION;
			}
		}
	}
	
	
	@Override
	public void resize(int width, int height) 
	{
		Global.viewport.update(width, height);
	}
	
	@Override
	public void dispose() 
	{
		super.dispose();
	}
}
