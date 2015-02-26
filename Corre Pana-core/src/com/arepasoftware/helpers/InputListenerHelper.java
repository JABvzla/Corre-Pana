package com.arepasoftware.helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputListenerHelper implements InputProcessor{
	float maxSensyTouch = 100;
	float sensibiltyTouch=maxSensyTouch;	
	
	public boolean touch = false;	
	Vector2 dragDir =new Vector2(0, 0);
	Vector2 touchPosition = new Vector2(0,0);
	
	public enum Direction 
	{
		Up,Down,Right,Left,None,
		RightUp,LeftUp,
		RightDown,LeftDown
	}
	
	public InputListenerHelper()
	{	
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode)
	{

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch = true;
		touchPosition.set(screenX,screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touch = false;
		dragDir.set(0,0);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		dragDir.set(0,0);
		if(touch)
		{	
			float tempX = screenX - touchPosition.x;
			float tempY = screenY - touchPosition.y;			
			if(tempX > 0 && tempX >  sensibiltyTouch){dragDir.x =  1;}
			if(tempX < 0 && tempX < -sensibiltyTouch){dragDir.x = -1;}
			if(tempY > 0 && tempY >  sensibiltyTouch){dragDir.y =  -1;}
			if(tempY < 0 && tempY < -sensibiltyTouch){dragDir.y =   1;}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
	
	
	
	
	public void setSensibiliyTouch(float sensibility)
	{		
		sensibiltyTouch -= sensibility*maxSensyTouch;
		if(sensibility <= 0){ sensibiltyTouch = maxSensyTouch;}
		if(sensibility >= 1){ sensibiltyTouch = 0;}
	}
	
	public Direction getDragDirection()
	{
		 if(dragDir.x ==  1 && dragDir.y ==  0){ return Direction.Right;}
		 if(dragDir.x == -1 && dragDir.y ==  0){ return Direction.Left;}
		 if(dragDir.x ==  1 && dragDir.y ==  1){ return Direction.RightUp;}
		 if(dragDir.x == -1 && dragDir.y ==  1){ return Direction.LeftUp;}
		 if(dragDir.x ==  1 && dragDir.y == -1){ return Direction.RightDown;}
		 if(dragDir.x == -1 && dragDir.y == -1){ return Direction.LeftDown;}
		 if(dragDir.x ==  0 && dragDir.y ==  1){ return Direction.Up;}
		 if(dragDir.x ==  0 && dragDir.y == -1){ return Direction.Down;}
		 return Direction.None;
	}
}