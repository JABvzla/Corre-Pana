package com.arepasoftware.correpana.actors;


import java.util.Iterator;

import com.arepasoftware.helpers.InputListenerHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;


public class Player extends Actor
{
	
	private	Animation player;
	private	float coldown = 0;
	private	float gravity = 2;
	private	float iniY = 20;
	private	TextureAtlas playerAtlas;
	private	InputListenerHelper inputProcessor;
	private	float deltaTime=0;

	public Player() 
	{
		playerAtlas = new TextureAtlas(Gdx.files.internal("PlayerSheet.pack"));
		player = new Animation(0.1f,
				 playerAtlas.findRegion("run1"),
				 playerAtlas.findRegion("run2"),
				 playerAtlas.findRegion("run3")	);
		player.setPlayMode(PlayMode.LOOP_PINGPONG);
		inputProcessor = new InputListenerHelper();
		inputProcessor.setSensibiliyTouch(0.5f);
		this.setPosition(100,iniY);
	}

	@Override
	public void act(float delta)
	{
		
		for(Iterator<Action> iter = this.getActions().iterator();iter.hasNext();)
		{iter.next().act(delta);}
		touche();
		coldown -=Gdx.graphics.getDeltaTime();
		if(this.getY() >= iniY){this.setY(this.getY()-gravity);}
		
	}


	public void runAction(PlayerAction action)
	{
		if(coldown<=0)
		{
			switch (action) 
			{
			case Move_Right:
				if(this.getX()<300)
				{
					MoveToAction moveAction = new MoveToAction();				  
				    moveAction.setPosition(this.getX()+100,this.getY());
				    moveAction.setDuration(0.1f);
				    this.addAction(moveAction);
					inputProcessor.touch = false;
					coldown = 0.3f;
				}
				break;
			case Move_Left:				
				if(this.getX()>0)
				{
					MoveToAction moveAction = new MoveToAction();				  
				    moveAction.setPosition(this.getX()-100,this.getY());
				    moveAction.setDuration(0.1f);
				    this.addAction(moveAction);
					inputProcessor.touch = false;
					coldown = 0.3f;
				}				
				break;
			case Jump:
				if(this.getY()<=iniY)
				{			
					MoveToAction moveAction = new MoveToAction();				  
					moveAction.setPosition(this.getX(),this.getY()+80);
					moveAction.setDuration(0.1f);
					this.addAction(moveAction);
					inputProcessor.touch = false;
					coldown = 0.8f;
				}
				break;
			case Duck:

				break;
				default:
				break;
			}
		}
	}
	
	private void touche()
	{
		switch (inputProcessor.getDragDirection()) {
		case Left :
			runAction(PlayerAction.Move_Left);
		case Right:
			runAction(PlayerAction.Move_Right);
		case Up:
			runAction(PlayerAction.Jump);
		case Down :
			runAction(PlayerAction.Duck);
		default:
			break;
		}
	}

	
	@Override
	public void draw(Batch batch, float parentAlpha) 
	{
		deltaTime += Gdx.graphics.getDeltaTime();
		batch.draw(player.getKeyFrame(deltaTime),this.getX(),this.getY());
	}
	
	private enum PlayerAction
	{
		Move_Right,Move_Left,Jump,Duck
	}
	

}
