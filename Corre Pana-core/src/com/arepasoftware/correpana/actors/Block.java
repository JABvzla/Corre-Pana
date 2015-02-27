package com.arepasoftware.correpana.actors;

import java.util.Iterator;

import com.arepasoftware.correpana.GameAction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

public class Block extends Actor
{
	Texture texture = new Texture(Gdx.files.internal("item2.png"));
	public boolean started = false;
	
	float marginLeft = 140;
	float marginDown = 330;
	
	
    public Block(int x)
    {
        setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
        this.setPosition(marginLeft+(x*30),marginDown);
        this.setScale(0.35f);
        iniAction(x);
    }
    
    private void iniAction(int x)
    {
        MoveToAction moveAction = new MoveToAction();
        ScaleToAction scaleAction = new ScaleToAction(); 

        moveAction.setPosition((x*100)+-20f,-100f);
        moveAction.setDuration(5f);
        scaleAction.setScale(1f);
        scaleAction.setDuration(5f);

        this.addAction(scaleAction);
        this.addAction(moveAction);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
    	batch.draw(texture,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
        this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
        texture.getWidth(),texture.getHeight(),false,false);
    }
    
    @Override
    public void act(float delta) 
    {
	   for(Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();)
	   {iter.next().act(delta);}
	   if(this.getY()<=100)
	   {
		  if((int)this.getX()/100 == GameAction.player.getX()/100)
		  {
			  // TODO Collision
			 // Gdx.app.log("", "Colision!");
		  }
	   }
	   if(this.getY()<=-100)
	   {
		 this.remove();
	   }
    }
}