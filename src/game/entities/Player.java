package game.entities;

import game.InputHandler;
import gfx.Colors;
import gfx.Screen;
import level.Level;

public class Player extends Mob 
{
	private InputHandler input;
	private int color = Colors.get(-1,111,145,321);
	private int scale = 1;

	public Player(Level level, int x, int y, InputHandler input)
	{
		super(level, "Player", x, y, 1);
		this.input = input;


	}


	public void tick() 
	{
		int xa = 0;
		int ya = 0;
		
		if(input.up.isPressed())
		{
			ya--;
		}
		
		if(input.down.isPressed())
		{
			ya++;
		}
		
		if(input.left.isPressed())
		{
			xa--;
		}
		
		if(input.right.isPressed())
		{
			xa++;
		}
		
		if(xa != 0 || ya != 0)
		{
			move(xa,ya);
			isMoving = true;
		}
		
		else
		{
			isMoving = false;
		}
		
		
		
	}

	
	public void render(Screen screen) 
	{
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed ) & 1;
		int flipBottom = (numSteps >> walkingSpeed ) & 1;
		
		if(movingDir == 1)
		{
			xTile +=2;
		}
		
		else if (movingDir > 1)
		{
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1 ) % 2;
			flipBottom = (movingDir - 1 ) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		//upper body
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile *32, color, flipTop,scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile *32, color, flipTop,scale);
		
		//lower body
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile +1) *32, color, flipBottom,scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile +1) + (yTile + 1) *32, color, flipBottom,scale);
		
	}
	
	public boolean hasCollided(int xa, int ya) 
	{
		
		return false;
	}

}
