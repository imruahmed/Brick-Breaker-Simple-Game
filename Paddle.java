import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Paddle{
	
	//Paddle class
	//Holds all information of the game paddle
	
	private int x, y, width; 		//Holds x and y position, and width of paddle
	private String pUp;				//Holds paddle's current powerup in use

	
	public Paddle(int x, int y){
		
		//basic paddle set up
		
		this.x = x;
		this.y = y;
		width = 60;
		pUp = "";
	}
	
	public void move(boolean[] keys){
		
		//Moves paddle left and right according to keys pressed and released
		
		if(keys[KeyEvent.VK_LEFT]){
 			if(this.getX() > 0) {
                this.setX(this.getX() - 7);
 			}
        }
        if(keys[KeyEvent.VK_RIGHT]){
        	if(this.getX() + 60 < 500) {
            	this.setX(this.getX() + 7);
            }
        }

	}
	
	public void powerUse(int lives, Ball b){
		
		//Allows paddle to user powerups
		
		if (pUp.equals("expand")){				//Makes paddle bigger
        	width = 90;
        	
        }
        
        else if (pUp.equals("shrink")){			//Makes paddle smaller
        	
        	width = 40;
        	
        } 
        	
        else if(pUp.equals("life")){			//Gives user an extra life
        	lives += 1;
        } 
        
        else if(pUp.equals("fast")){			//Makes ball speed up
        	
        	b.setDY(b.getDY() * 1.5 );
        }
        
        else if (pUp.equals("slow")){			//Makes ball slow down
        	
        	b.setDY(b.getDY() * 0.5 );
        }
    
        
        else{									//Returns paddle to original state
        	width = 60;
        }
	}
	
	//Gets width of paddle
	public int getWidth(){
		return width;
	}
	
	//Gets x position of paddle
	public int getX(){
		return x;
	}
	
	//Gets y position of paddle
	public int getY(){
		return y;
	}
	
	//sets width of paddle to parameter
	public void setWidth(int i){
		width = i;
	}
	
	//sets x position of paddle
	public void setX(int i){
		x = i;
	}
	
	//Sets y position of paddle
	public void setY(int i){
		y = i;
	}
	
	//Sets paddle's current PowerUp
	public void setpUp(String s){
		pUp = s;
	}
	
}