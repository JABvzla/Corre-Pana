package com.arepasoftware.correpana;

import com.badlogic.gdx.utils.viewport.FitViewport;


public class Global {
	public static FitViewport viewport;
	public static float ScreenWidth = 400;
	public static float ScreenHeight = 680;
	
	public static GameState gameState = GameState.INIT;
	
	public static enum GameState
	{
		INIT,ACTION,GAME_OVER,PAUSE
	}
	
	

}
