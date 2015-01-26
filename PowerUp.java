import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PowerUp{
	
	//PowerUp class
	//Holds information for powerups that drop from bricks after being hit
	
	int x, y, dx, dy;					//x and y position,
										//x and y velocities
	boolean act, use;					//act - flag if powerup has dropped from brick
										//user - if user can use powerup
	String name;						//Name of powerup
	

	
	public PowerUp(int x, int y, String n){
		
		//Sets basic information for objects of the class
		
		this.x=x;
		this.y=y;
		dx=0;
		dy=0;
		act=false;
		name = n;
	}
	
	public void move(){		
		
		//Allows powerup to drop if its brick has been hit
		
		if (!use && act){			//Only continues to drop until it has left the screen
    		if (y<500){
    			y += dy;
    		}	        		
    	}
	}
	
	public Image getImage(){
		//Returns powerup image
		return new ImageIcon("powerup.png").getImage();
	}
	public String getName(){
		//Returns powerup name
		return name;
	}
	
	public boolean getUse(){
		//Returns true if user can use powerup (has made contact with it)
		return use;
	}
	
	public boolean getAct(){
		//Returns true if powerup has been hit and is dropping
		return act;
	}
	
	public int getX(){
		//Returns x position
		return x;
	}
	
	public int getY(){
		//Returns y position
		return y;
	}
	
	public int getDY(){
		//Return y velocity
		return dy;
	}
	
	public void setName(String s){
		//Sets name to parameter
		name = s;
	}
	
	public void setUse(boolean b){
		//Sets use boolean to parameter (if user can use powerup)
		use = b;
	}
	
	public void setAct(boolean b){
		//Sets act boolean to parameter (if powerup is dropping)
		act = b;
	}
	
	public void setX(int i){
		//sets x position
		x = i;
	}
	
	public void setY(int i){
		//sets y position
		y = i;
	}
	
	public void setDY(int i){
		//sets y velocity
		dy = i;
	}
	
	
}