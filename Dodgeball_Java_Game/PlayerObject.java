import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.*;
import java.awt.geom.Rectangle2D.*;
import java.util.ArrayList;
public class PlayerObject extends GameObject {

	private int lives = 10;
	private boolean dead = false;
	private boolean godMode = false;
	private PowerUp power = new PowerUp(0,0,0,0,0);
	private boolean powered = false;

	public PlayerObject(){
		Rectangle2D.Double rectangle = new Rectangle2D.Double(getX(), getY(), 20, 20);
		setObjectShape(rectangle);

	}
	//overwrites move from gameobject.....this makes you move after button presses
	public void move(int width, int height){
//
		if(getLeft()&&getX()>=getV()){
			setX(getX()-getV());
		}
		if(getRight()) {
			if (getX()<=(width-(20 + getV()))){
				setX(getX()+getV());
			}
		}

		if(getDown()&&getY()<=(height-(20 + getV()))){
			setY(getY()+getV());
		}
		if(getUp()&&getY()>=getV()){
			setY(getY()-getV());
		}

	}



	//The Modifier Methods
	public int getLives(){
		return lives;
	}
	public void setLives(int a){
		lives = a;
	}
	public void setDead(boolean a){
		dead = a;
	}
	public boolean getDead(){
		return dead;
	}
	public void setGodMode(boolean a){
		godMode = a;
	}
	public boolean getGodMode(){
		return godMode;
	}
	public void setPower(PowerUp a){
		power = a;
	}
	public PowerUp getPower(){
		return power;
	}
	public boolean getPowered(){
		return powered;
	}
	public void setPowered(boolean a){
		powered = a;
	}




}
