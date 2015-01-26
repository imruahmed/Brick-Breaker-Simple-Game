import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Scanner;
import java.io.*;

public class MainMenu extends JFrame implements ActionListener{
	
	//MainMenu
	//Allows for user to play, ask for help, or exit the game
	
	MyButton start, help, exit;		//Buttons for user interaction
	MyButton[] buttons;				//Arroy of all buttons
	
	JLabel bg;						//Background image					
	
	public MainMenu(){
		
		//Basic setup of screen
		//Positions and sizes are set for all buttons and images
		super("Brick Breaker");
		
		setLayout(null);
		setSize(800,600);
		
		start = new MyButton("start");
		help = new MyButton("help");
		exit = new MyButton("exit");
		
		start.setSize(170,65);
		help.setSize(380,65);
		exit.setSize(120,65);
		
		buttons = new MyButton[]{start, help, exit};		//Buttons are put in an Array for easy access and manipulation
		
		for(int i=0; i<buttons.length; i++){
			
			MyButton b = buttons[i];
			b.setLocation(400-b.getWidth()/2, 300+i*75);
			b.addActionListener(this);
			add(b);
			
		}
		
		bg = new JLabel(new ImageIcon("bbg1.png"));
		bg.setSize(800,600);
		add(bg);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Program will exit on close
		setVisible(true);									//Makes it visible

	}
	
	public void actionPerformed(ActionEvent e){
		
		//Checks for any actions
		
		Object src = e.getSource();			//Gets source of ActionEvent
		
		
		if(src == start){					//Starts game
			new BB();
			setVisible(false);				//Stops menu
			
		} else if(src == help){				//Loads help frame
			new HelpFrame();
			setVisible(false);				//stops menu
			
		} else if(src == exit){				//Exits program
			System.exit(0);
		}
			
	
		
	}
	
	public static void main(String[] args){
		new MainMenu();
	}
}

class MyButton extends JButton{
	
	//Custom JButton class
	//Gets rid of border and fill
	//Puts in custom icon
	
	public MyButton(String f){
		
		ImageIcon p = new ImageIcon(f+"P.png");			//Custom icon
		ImageIcon d = new ImageIcon(f+"D.png");			//Cutsom icon
		
		this.setIcon(d);								//Sets new icon
		this.setRolloverIcon(p);
		this.setPressedIcon(d);
		this.setContentAreaFilled(false);				//Gets rid of default settings
		this.setBorderPainted(false);
		
	}
}

class HelpFrame extends JFrame implements ActionListener{
	
	//Displays controls for the game
	
	JLabel info;				//An image that has the controls
	MyButton exit;				//Exit button

	
	public HelpFrame(){
		
		//Setsup basic position and sizes of all components in the frame
		
		super();
		
		setLayout(null);
		setSize(800,600);
	
		exit = new MyButton("exit");
		exit.setSize(120,65);
		exit.setLocation(500, 400);
		exit.addActionListener(this);			//allows for be clicked and respond
		add(exit);
		
		info = new JLabel(new ImageIcon("info.png"));
		info.setSize(800,600);
		add(info);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//Progran exits on close
		setVisible(true);										//Makes visible
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		//Displays screen until exited 
		
		Object src = e.getSource();						//gets source of action
		
		if(src == exit){								//Returns to Main Menu
			
			new MainMenu();
			setVisible(false);
			
		}
	}
	
}

class LoseFrame extends JFrame implements ActionListener{
	
	//Frame displayed when User loses all lives
	
	JLabel msg, bg;				//Two images
	MyButton exit;				//Exit button
	
	public LoseFrame(){
		
		//Sets up basic position and sizes of components in the frame
		
		super();
		
		setLayout(null);
		setSize(800,600);
	
		Font f = new Font("Bebas Neue", Font.PLAIN, 60);			//Selects font for use
		
		msg = new JLabel("You lost, you suck.");					//An image that will be displayed
		msg.setFont(f);												//Set up
		msg.setForeground(Color.WHITE);
        msg.setSize(600,100);
        msg.setLocation(225,250);
        add(msg);
		
		exit = new MyButton("exit");								//Exit button set up
		exit.setSize(120,65);
		exit.setLocation(340, 450);
		exit.addActionListener(this);								//Allows button to be clicked and respond
		add(exit);
		
		bg = new JLabel(new ImageIcon("bbg.png"));					//Background image
		bg.setSize(800,600);
		add(bg);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//Program exits on close
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		//Displays screen until exited
		
		Object src = e.getSource();				//Finds source of action
		
		if(src == exit){						//Returns to main menu		
			new MainMenu();
			setVisible(false);
		}
	}
	
}
	