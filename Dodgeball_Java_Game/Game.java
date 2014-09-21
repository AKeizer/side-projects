/**
 * @(#)Game.java
 * A game... Controls are arrow keys for movement and space bar to speed up.
 */
//import statements
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Time;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.*;
import java.awt.geom.Rectangle2D.*;

public class Game extends Applet implements KeyListener {

//fields, arraylists are stuff that appears in the game
	private PlayerObject me = new PlayerObject();
	private ArrayList<EnemyObject> enemies = new ArrayList<EnemyObject>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();

//counters
	private int score = 0;
	private int timeCtr = 0;
	private int timeCtr2 = 0;
	private int timeCtr3 = 0;
	private int powerUpsGotten = 0;
	//to find the height and width of the applet.
	private int width = this.getWidth();
	private int height = this.getHeight();
	private boolean gameOver = false;


	public void init(){
//these values are used in the move and wallhit methods in other classes
		width = this.getWidth();
		height = this.getHeight();

//this timer does everything in the game, updates it and keeps track of counters
		ActionListener painter = new ActionListener() {

    		public void actionPerformed(ActionEvent evt) {

			//this code moves things on the screen
				me.move(width, height);
				for(int i = 0; i<enemies.size(); i++){
					enemies.get(i).move(width, height);
				}
				for(int i = 0; i<powerUps.size(); i++){
					powerUps.get(i).move(width, height);
				}


//updating counters
				timeCtr++;
				timeCtr2++;
				timeCtr3++;

				//events that result from certain times in ctrs.
				//Ctr 1 deals with scores, 2 deals with enemies being created
				//3 deals with powerups being created.
				if(timeCtr == 30){
					score++;
					timeCtr = 0;
				}
				if(timeCtr2 == 50){

					EnemyObject p = new EnemyObject(5, (int)(Math.random()*width), (int)(Math.random()*height));
					enemies.add(p);
					timeCtr2 = 0;
				}

				if(timeCtr3 == 100){

					
					int determineDir = 1;
					int determineDir2 = 1;
					if((int)(Math.random()*10)>=5){
						determineDir = -1;
					}
					if((int)(Math.random()*10)>=5){
						determineDir = 1;
					}

					PowerUp p = new PowerUp(5, (int)(Math.random()*width), (int)(Math.random()*height), determineDir, determineDir2);
					powerUps.add(p);
					timeCtr3 = 0;
				}

				//this loop structure checks to see if any powerups have been hit
				
				//the counter powerUpsGotten is what determines when you get extra life
				for(int i = 0; i<powerUps.size(); i++){
					me.setPowered(isIntersectingPowerUp(powerUps.get(i)));
					if(me.getPowered()){
						powerUps.remove(i);
						score = score+5;
						powerUpsGotten++;
						
						int num = enemies.size()/3;
						for(int j = 0; j<num; j++){
							enemies.remove(j);
						}
						break;
						
					}
				}
				//this checks if you are touching an enemy and removes lives accordingly
				for(int i = 0; i<enemies.size(); i++){
					me.setDead(isIntersectingEnemy(enemies.get(i)));

					if(me.getDead()){
						enemies.remove(i);
						if(me.getGodMode()==false){

							me.setLives(me.getLives()-1);
							break;
						}
					}
				}
				//adds lifes at 10 powerups.
				if(powerUpsGotten == 10){
					me.setLives(me.getLives()+4);
					powerUpsGotten = 0;
				}

//updates the screen.
				if(!gameOver)
					repaint();

      		}

  		};

//timer is started.
		final Timer t = new Timer(30, painter);
		t.start();
		


//keylistener is added.
		addKeyListener(this);
	}


//these check if you hit a key
//for movement switches are turned on and off (boolean variables) when you are hitting
//the right movement key, when they are false they don't move.
//when space bar is held the speed is added to by 5, it is doubled.

			public void keyPressed(KeyEvent e){

				if(e.getKeyCode() == KeyEvent.VK_UP){
					me.setMoveUp(true);

				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){

					me.setMoveDown(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){

					me.setMoveLeft(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){

					me.setMoveRight(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE){


					me.setV(10);

					
				}
			}

 	        public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_UP){
					me.setMoveUp(false);

				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){

					me.setMoveDown(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){

					me.setMoveLeft(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){

					me.setMoveRight(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					me.setV(5);
				}
 	        }

			public void keyTyped(KeyEvent e) {

			}

	//}

	//the paint method. it paints stuff
	public void paint(Graphics g) {

//if you are still alive it goes and does the user interface.
//if your dead, it says game over.
		if(me.getLives()>0&&!gameOver){

			g.fillRect(me.getX(), me.getY(), 20, 20);

			g.drawString("Score: "+score, width/8, height - height/5);
			g.drawString("Lives: "+me.getLives(), width - width/8, height - height/5);
			g.drawString("PowerUps until more Life: "+(10-powerUpsGotten), width/2, height - height/5);

			//g.drawString("PowerUp: "+me.getPower().getOption(), width/2, height - height/5);


			for(int i = 0; i<enemies.size(); i++){
				g.drawOval(enemies.get(i).getX(),enemies.get(i).getY(), 20, 20);
			}
			for(int i = 0; i<powerUps.size(); i++){
				g.fillOval(powerUps.get(i).getX(), powerUps.get(i).getY(), 20, 20);
			}
		}
		else{
			g.drawString("GAME OVER", width/2, height/2);
			g.drawString("Score: "+score, width/8, height - height/5);
			gameOver = true;

		}

	}

	//two methods that check for intersection 
	public boolean isIntersectingEnemy(EnemyObject enemy){
		Rectangle2D.Double r = new Rectangle2D.Double(me.getX(), me.getY(), 20, 20);
		Ellipse2D.Double e = new Ellipse2D.Double(enemy.getX(), enemy.getY(), 20, 20);
		return e.intersects(r);

	}
	public boolean isIntersectingPowerUp(PowerUp a){
		Rectangle2D.Double r = new Rectangle2D.Double(me.getX(), me.getY(), 20, 20);
		Ellipse2D.Double e = new Ellipse2D.Double(a.getX(), a.getY(), 20, 20);
		return e.intersects(r);
	}

}
