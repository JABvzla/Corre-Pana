package com.arepasoftware.correpana.actors;

import java.util.Iterator;

import com.arepasoftware.correpana.GameAction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

public class Block extends Actor
{
	int rail=0;
	int altura=0;
	float marginLeft = 140;
	float marginDown = 330;
	int collisionLine = 60;
	int typeBlock=0;
	private	TextureAtlas blockAtlas;
	private Animation animation;
	private	float deltaTime=0;

	
    public Block(int tipo,int posX)
    {
    	typeBlock= tipo;
    	rail=posX; 
        iniByType();
        this.setPosition(marginLeft+(rail*30),marginDown);
        this.setScale(0.35f); 
        iniAction();

    }
    
    private void iniAction()
    {
    	// TODO Main Action 
        MoveToAction moveAction = new MoveToAction();
        ScaleToAction scaleAction = new ScaleToAction(); 
        //	Travix
        float time = 3f;
        moveAction.setPosition((rail*100)+-20f,-100f); 
        moveAction.setDuration(time);
        
        scaleAction.setScale(1f);
        scaleAction.setDuration(time);

        this.addAction(scaleAction);
        this.addAction(moveAction);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {      	
    	deltaTime += Gdx.graphics.getDeltaTime();	    	
    	batch.draw(animation.getKeyFrame(deltaTime),this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
		this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation());
	 }
    
    @Override
    public void act(float delta) 
    {
	   for(Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();)
	   {iter.next().act(delta);}
	   if(this.getY()<=20)
	   {
		   GameAction.stage.addActor(this);
	   }
	   if(this.getY()<=collisionLine)
	   {
		  if((int)this.getX()/100 == GameAction.player.getX()/100)
		  {	 // TODO Collision

		  }
	   }
	   if(this.getY()<=-100)
	   {
		 this.remove();
	   }
    }
    
    private void iniByType()
    {
    	switch (typeBlock) 
    	{
		case 1:	
	    	setBounds(getX(),getY(),91,93);
			blockAtlas = new TextureAtlas(Gdx.files.internal("BlockSheet.travix"));
			animation = new Animation(0.1f,
					 blockAtlas.findRegion("item2"));
			animation.setPlayMode(PlayMode.LOOP);
			altura = 1;
			break;
		case 2: // Moneda			
	    	setBounds(getX(),getY(),43f,44f);
			blockAtlas = new TextureAtlas(Gdx.files.internal("monedas.travix"));
			animation = new Animation(0.1f,
					 blockAtlas.findRegion("moneda1"),
					 blockAtlas.findRegion("moneda2"),
					 blockAtlas.findRegion("moneda3"),
					 blockAtlas.findRegion("moneda4"));
			animation.setPlayMode(PlayMode.LOOP);
			break;
		default:
			break;
		}
    }
}