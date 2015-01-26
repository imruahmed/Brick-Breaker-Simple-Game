import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.io.*;


//Imran Ahmed
//Brick Breaker
//User has ball and paddle and tries to clear the screen of bricks

public class BB extends JFrame implements ActionListener{
	
	//Main class
	//Runs the game loop
	
	private GamePanel game;						//Panel with all game images
	private javax.swing.Timer myTimer;			//Game timer
	
	JLabel bg, score, lives, level;				//Labels for score, lives and levels
	
	public BB(){
		//Constructor
			
		super("Brick Breaker");
		
		setLayout(null);
		setSize(800,600);
		
		game = new GamePanel();					//Instantiates a GamePanel aka starts the game
		game.setSize(500, 500);
		game.setLocation(25,25);
		add(game);
		
		Font f = new Font("Bebas Neue", Font.PLAIN, 60);		//Starting Font
		
		level = new JLabel("Level 1");			//Basic Setup for various labels
		level.setFont(f);
		level.setForeground(Color.WHITE);
        level.setSize(300,100);
        level.setLocation(585,50);
        add(level);
        
		score = new JLabel("Score: 0");
		score.setFont(f);
		score.setForeground(Color.WHITE);
        score.setSize(300,100);
        score.setLocation(575,150);
        add(score);
        
        lives = new JLabel("Lives: 3");
		lives.setFont(f);
		lives.setForeground(Color.WHITE);
        lives.setSize(300,100);
        lives.setLocation(580,250);
        add(lives);
        
        
        
		bg = new JLabel(new ImageIcon("bbg.png"));				//Sets up Background pictures
		bg.setSize(800,600);
		add(bg);
		
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//Causes program to stop running when exited
		setVisible(true);										//Makes screen visible
		
		myTimer = new javax.swing.Timer(10,this);				//Starts clock
		myTimer.start();
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		//Game Loop
		//Updates GamePanel and interface labels
		
		game.update(myTimer);
		score.setText("Score: "+Integer.toString(game.getScore()));			//Updates lives, score, and level
		lives.setText("Lives: "+Integer.toString(game.getLives()));
		level.setText("Level "+Integer.toString(game.getLVL()));
		
	}
	
	public static void main(String[] args){
		
		new MainMenu();
		
	}
}

class GamePanel extends JPanel implements KeyListener{
	
	//Holds most game information and performs most game tasks
	
	Brick[] bricks;					//Array of all Bricks
	Paddle paddle;					//Paddle used to bounce ball
	Ball ball;						//
	ArrayList<PowerUp> pUps;		//ArrayList of all PowerUps in level

	
	boolean[] keys;					//Holds pressed/released values of keyboard
	boolean started;				//Flag for when ball is in play
	
	private int level, lives, score, left;		//left - number of bricks still left on the screen
	
	public GamePanel(){
		
		super();
		
		ball = new Ball(245, 450);
		paddle = new Paddle(220, 475);
		pUps = new ArrayList<PowerUp>();
		bricks = load(1);					//Loads bricks from a text file

		
		keys = new boolean[2000];
		
		started = false;					//Ball is not in play
		
		lives = 3;							//Sets up basic user info
		score = 0;
		left = bricks.length;				//All bricks are on screen
		
		addKeyListener(this);				//Allows keys to be used in this class
        this.setFocusable(true);
        this.grabFocus();
			
	}
	
	public Brick[] load(int lvl){		//Takes in level number
		
		//Makes Brick objects and appends into an Array of Bricks
		//Returns an array of Bricks
		
		Scanner inFile=null;			//Catches IOException
		
		try{		
    			inFile = new Scanner (new BufferedReader (new FileReader("bricks"+Integer.toString(lvl-1)+".txt")));
    			
		} catch(IOException ex){
			
			System.out.println("File not available"+ex);
			System.exit(0);
			
		}
		
		String[] p = new String[] {"","","","","","","","","","","","","","shrink","expand","life","life","life","fast","slow"};	//Array of possible powerups
		Brick[] temp = new Brick[16];												//Empty Brick array
		
		for(int i = 0; i < temp.length; i++){										
            Brick b;
            int r = (int)(Math.random()*p.length);									//Randomly selects a powerup from the array
            int hp = (int)(Math.random()*3);										//Randomly gives brick hp
            
          
	        b = new Brick(inFile.nextInt(), inFile.nextInt(), hp+1, p[r]);			//New object is made
	        temp[i] = b;
	        pUps.add(new PowerUp(b.getX(), b.getY(), p[r]));						//Powerup is added to ArrayList

        }
        
        return temp;																//Brick array is returned
	}
	
	private void checkContact(){
		
		//Checks for contact between the ball and other Objects and sets the velocities accordingly
			
        //sets boundaries for ball in the horizontal direction
        if(ball.getX() + 10 >= 500 || ball.getX() <= 0){
            ball.setDX(-1 * ball.getDX());
        }
        
        //sets bounndary for upper wall in vertical direction
        if(ball.getY() < 0){
            ball.setDY(-1 * ball.getDY());
        }
        
        //checks if ball touches paddle; if so allows ball to go in reverse y velocity
        if(ball.getY() + 10 >= paddle.getY() && (ball.getX() >= paddle.getX() && ball.getX() <= (paddle.getX() + 60))){
            if(ball.getY() + 10 <= paddle.getY() + 10){
            	
            	double ballPos = ball.getX() - paddle.getX();			
				double newx = (ballPos/paddle.getWidth()) - .5;			//Changes balls angle/x velocity based on the position it touches paddle
	
				ball.setDX(newx*5);										//Sets the new x and y velocities
				ball.setDY(-1 * ball.getDY());
            }
        }

        //if the ball drops below the paddle loses a life and resets to initial position
        if(ball.getY() + 10 > paddle.getY() + 10){
   			
   			lives-=1;
   			reset();					
            
        }

       //checks if ball hits a brick; if so resets x velocity and sets brick to being hit; update score
        for(Brick b: bricks){
        	
            if(ball.getX() + 10 >= b.getX() && ball.getX() <= b.getX() + 55){
            	
                if(ball.getY() + 10 >= b.getY() && ball.getY() <= b.getY() + 20){
                	
                    if(b.getHP()>0){
                    	
                        if(ball.getX() + 10 - ball.getDX() <= b.getX() || ball.getX() - ball.getDX() >= b.getX() + 55){
   
							ball.setDX(-1 * ball.getDX());
                            b.setHP(b.getHP()-1);
                            
                            
                        } else{
                        	
                     		ball.setDY(-1 * ball.getDY());
                        	b.setHP(b.getHP()-1);
                       
                        } 
                        score += b.getHP()*5;
                    } 
                }
            }
          	
            if(b.getHP()<=0){
            	
            	for (PowerUp p: pUps){									//Allows powerup to drop if Brick containing PowerUp is hit
                	if (p.getX()==b.getX() && p.getY()==b.getY()){
                		
                		p.setDY(3);
                		p.setAct(true);
                	}
                }
                
            }
            
        }
        
        for (PowerUp p: pUps){											//Allows user to use powerup if it comes in contact with the paddle
        
			if ((p.getX()+50>paddle.getX() && p.getX()<=paddle.getX()+paddle.getWidth()) && p.getY()+20>=paddle.getY() && p.getY()<=paddle.getY()+10){					
				paddle.setpUp(p.getName());
				score += 50;
			}
		}
		
		int temp = 0;
		
		for(Brick b: bricks){											//Counts how many bricks are left
			
			if(b.getHP()> 0){
				temp ++;
			}
		}
		
		left = temp;
        
        //if all bricks have been hit, go to the next level
        if(left == 0)
        {
        	
            level++;
            
            if(level>3){						//Returns to main menu if all levels have been beaten
            	new MainMenu();
            	setVisible(false);
            }
            	
            reset();		
        }
        
        //if loses all lives resets everything
        if(lives <= 0){
        	
        	new LoseFrame();
        	setVisible(false);
        	
        }
	
    }
    
    public void reset(){
		
		//Resets all variables to original positions or values
		
    	ball.setX(245);
        ball.setY(460);
        ball.setDX(0);
        ball.setDY(0);
        
        paddle.setX(220);
        paddle.setY(475);
        
      	score = 0;
      	
      	if(lives>0){					//If player still has lives, current level is reloaded
      		
      		pUps.clear();				
      		bricks = load(level+1);
      		score += 10;
      		
      	} else{							//Otherwise player is sent back to the Main Menu
      		
      		new MainMenu();
      		setVisible(false);
      		
      	}
      	
        started = false;				//Ball is out of play
    }
    
	public void keyPressed(KeyEvent event){			
		//Checks to see if keys are pressed
    	int i = event.getKeyCode();
    	keys[i] = true;
    }
	
	public void keyReleased(KeyEvent evt){
		//Checks to see if keys are released
		int i = evt.getKeyCode();
		keys[i]=false;
		
	}
	
	public void keyTyped(KeyEvent evt){} //Not used, but is part of implementing KeyListener
	
	public void paintComponent(Graphics g){
        
        //Draws or blits all GamePanel components
        //Including ball paddle, bricks, powerups
        
        g.setColor(new Color(222,222,222));					//Fills background
		g.fillRect(0,0,getWidth(),getHeight());

        //paints  bricks only if they are not hit
        for(Brick b: bricks){
            if(b.getHP()>0){
            
                g.drawImage(b.getImg(), b.getX(), b.getY(), this);
            }
        }
		
		for (PowerUp p: pUps){
        	if (!p.getUse() && p.getAct()){
        		if (p.getY()<550 && !p.getName().equals("")){
        			g.drawImage(p.getImage(), p.getX(), p.getY(), this);
        		}	        		
        	}
        }
        
        
        //paints paddle
        g.setColor(Color.BLACK);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), 10);

        //paints ball
        g.drawImage(ball.getImage(),(int)(ball.getX()), (int)(ball.getY()), this);
    }
    
    public void update(javax.swing.Timer mT){
    	
    	//Updates position of all moving game components 
    	//Ball, paddle, powerups
    	
    	if(keys[KeyEvent.VK_H]){		//Directs to a help screen if player needs
    		
    		mT.stop();					//Stops timer so that ball does not continue in play
    		new HelpFrame();
    		setVisible(false);
    			
    	}
    	
    	if(keys[KeyEvent.VK_M]){		//Returns to menu if player chooses to do so
    		
    		mT.stop();					//Stops timer so that ball does not continue in play
    		new MainMenu();
    		setVisible(false);
    		
    	}
    	
    	if(!started){						//If the ball is not in play
    		
    		if(keys[KeyEvent.VK_SPACE]){	//Space must be pressed to launch it
    			
    			int i = (int)(Math.random()*6);		//Launches at random velocity and angle
    			ball.setDX(i);
    			ball.setDY(-5);
    			started = true;						//Ball is in play
    			//mT.start();
    		}
    		
    	} else{
    		
    		checkContact();							//Checks contatc
    		paddle.move(keys);						//moves paddle
    		paddle.powerUse(lives, ball);			//Allows user to user powerups
    		
    		ball.move();							//ball moves
    		
    		for(PowerUp p: pUps){					//powerups drop
    			p.move();
    		}
    		
        	repaint();								//Redraws all components
    		
    	}
    }
    
    public int getLVL(){
    	//Returns the current level	
    	return level;
    }
    
    public int getLives(){
    	//Returns the current amount of lives
    	return lives;
    }
    
    public int getScore(){
    	//Returns the curent score
    	return score;
    }
}



