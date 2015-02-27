package com.arepasoftware.correpana;

import com.arepasoftware.correpana.actors.Block;
import com.arepasoftware.correpana.actors.Player;
import com.arepasoftware.correpana.map.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameAction
{
	private Map map;
	private Stage stage;
	public static Player player;
	private float deltaTime;
	
	public GameAction()
	{
		map = new Map();
		stage = new Stage();        
		player = new Player();           	
        stage.addActor(player);           
        deltaTime=0;
	}
	
	public void update()
	{
		deltaTime+=Gdx.graphics.getDeltaTime();
		 
		map.update();
		if(deltaTime>1)
		{
		    for(int x = 0;x<4;x++)
		    {
			    if(map.mapBlock(x)==1)
			    {
			    	stage.addActor(new Block(x));
			    }
		    }
		    map.nextStep();
		    deltaTime=0;
		}
		   stage.addActor(player);  
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw(); 
	}
}
