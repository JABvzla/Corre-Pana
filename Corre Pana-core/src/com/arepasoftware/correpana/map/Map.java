package com.arepasoftware.correpana.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Map 
{

	public int[][] mapArray;
	Animation backGround;
	SpriteBatch sprite;
	float deltaTime = 0;
	TextureAtlas mapAtlas;
	int posY;
	
	public Map()
	{
		mapArray = new int[4][100];
		sprite = new SpriteBatch();
		mapAtlas = new TextureAtlas(Gdx.files.internal("MapSheet.pack"));
		
		backGround = new Animation(0.5f, 
				mapAtlas.findRegion("track1"),
				mapAtlas.findRegion("track2"),
				mapAtlas.findRegion("track3"),
				mapAtlas.findRegion("track4"),
				mapAtlas.findRegion("track5"),
				mapAtlas.findRegion("track6"));		
		mapArray[0][6]=0; mapArray[1][6]=0; mapArray[2][6]=0; mapArray[3][6]=1;
		mapArray[0][5]=0; mapArray[1][5]=0; mapArray[2][5]=0; mapArray[3][5]=0;
		mapArray[0][4]=0; mapArray[1][4]=0; mapArray[2][4]=0; mapArray[3][4]=0;
		mapArray[0][3]=0; mapArray[1][3]=1; mapArray[2][3]=0; mapArray[3][3]=0;
		mapArray[0][2]=1; mapArray[1][2]=0; mapArray[2][2]=0; mapArray[3][2]=0;
		mapArray[0][1]=0; mapArray[1][1]=1; mapArray[2][1]=0; mapArray[3][1]=0;
		posY = 0;	
	}
	
	public void update()
	{
		sprite.begin();
		deltaTime += Gdx.graphics.getDeltaTime();
		sprite.draw(backGround.getKeyFrame(deltaTime),0,0);
		sprite.end();
						
	}
	
	public int mapBlock(int x)
	{
		return mapArray[x][posY];
	}
	public void nextStep()
	{
		posY++;
	}
}