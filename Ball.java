import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball{
	
	//Ball class
	//Holds all information of game ball
	
	private double x, y, dx, dy; //Holds x and y position and velocities
	
	public Ball(double x, double y){
		
		//Sets ball's x and y position to parameters
		
		this.x = x;
		this.y = y;
		
	}
	
	public void move(){
		
		//Moves ball
		//Adds x and y velocities to x and y positions, respectively
		
		this.setX(this.getX() + this.getDX());
        this.setY(this.getY() + this.getDY());
	}
	
	//gets Image of ball
	public Image getImage(){
		
		return new ImageIcon("ball.png").getImage();
	}
    //gets x velocity of the ball
    public double getDX() {
        return dx;
    }

    //gets y velocity of ball
    public double getDY() {
        return dy;
    }

    //gets x-coordinate of ball
    public double getX() {
        return x;
    }

    //gets y-coordinate of ball
    public double getY() {
        return y;
    }
    
    //sets x velocity of ball
    public void setDX(double dx) {
        this.dx = dx;
    }
    
    //sets y velocity of ball
    public void setDY(double dy) {
        this.dy = dy;
    }
    
    //sets x-coordinate of ball
    public void setX(double x) {
        this.x = x;
    }

    //sets y-coordinate of ball
    public void setY(double y) {
        this.y = y;
    }

}