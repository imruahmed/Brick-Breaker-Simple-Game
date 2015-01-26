import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Brick {
	
	//Brick class
	//Holds all information for Brick objects
    //declare x & y coordinates 
    int x,y, hp;
    String powerup;
    static Image[] brickIcons;		//Array of Brick Images

    
    public Brick(int x, int y, int hp, String powerup){
		//Sets up basic values for Bricks
		
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.powerup = powerup;
        
        brickIcons = new Image[3];
        
        for(int i=0; i<3; i++){
        	brickIcons[i] = new ImageIcon("brick"+Integer.toString(i+1)+".png").getImage();		//Different images for different brick hps
        }
    }
	
	//Gets Brick image based on hp
	public Image getImg(){
		return brickIcons[hp-1];
	}
	
    //gets HP of brick
    public int getHP() {
        return hp;
    }
    
    //gets brick PowerUp
    public String getPU(){
    	return powerup;
    }
 
    //gets x coordinate of brick
    public int getX() {
        return x;
    }

    //gets y coordinate of brick
    public int getY() {
        return y;
    }
    
    //sets brick's HP
    public void setHP(int i) {
        hp = i;
    }
    
    //sets Brick's powerup
    public void setPU(String pu){
    	powerup = pu;
    }

}