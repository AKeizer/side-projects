import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.*;
import java.awt.geom.Rectangle2D.*;
//this is the parent class. governs the behaviour of the powerups and enemies
//its fields are used by the player.

public class GameObject {

    private int velocity = 5;
    private int directionX = 1;
    private int directionY = 1;
    private int acceleration = 1;
    private int maxVelocity = 0;
    private int x = 50;
    private int y = 50;
    private int directionChanger = 0;
    private Object objectShape; //will be an Ellipse2D.Double (in form of a circle) or a rectangle
    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;


	public GameObject(){

			int determineDir = 1;

			if((int)(Math.random()*10)>=5){
				determineDir = -1;
			}
			directionX = determineDir;
			if((int)(Math.random()*10)>=5){
				determineDir = 1;
			}
			directionY = determineDir;

	}
	public GameObject(Object o){
		objectShape = o;
	}



//makes the objects move automatically
    public void move(int width, int height){


    	if(getX()>=(width-20)){
    		wallHit(1);
    	}
    	if(getX()<=0){
    		wallHit(1);
    	}
    	if(getY()<=0){
    		wallHit(2);
    	}
    	if(getY()>=(height-20)){
    		wallHit(2);
    	}


    	x = x + velocity*directionX;
    	y = y + velocity*directionY;
    }
//this method determines what happens when it hits a wall. if it is a 1 hits the
//vertical walls and sets the direction accordingly and for 2 it is for the horizontal walls

    public void wallHit(int a){
		if(a==1){
			directionX = directionX*-1;
		}
		if(a==2){
			directionY = directionY*-1;
		}


    }

//Modifier Methods.
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public void setY(int a){
		y = a;
    }
    public void setX(int a){
  		x = a;
    }
    public void setMoveLeft(boolean a){
    	moveLeft = a;
    }
    public void setMoveRight(boolean a){
    	moveRight = a;
    }
    public void setMoveUp(boolean a){
    	moveUp = a;
    }
    public void setMoveDown(boolean a){
    	moveDown = a;
    }
    public boolean getLeft(){
    	return moveLeft;
    }
    public boolean getRight(){
    	return moveRight;
    }
    public boolean getUp(){
    	return moveUp;
    }
    public boolean getDown(){
    	return moveDown;
    }
    public void setV(int a){
		velocity = a;
    }
    public void setA(int a){
    	acceleration = a;
    }
    public void setMaxV(int a){
    	maxVelocity = a;
    }

    public int getV(){
    	return velocity;
    }
    public int getA(){
    	return acceleration;
    }
    public int getMaxV(){
    	return maxVelocity;
    }
    public int getDirectionX(){
    	return directionX;
   	}
   	public int getDirectionY(){
    	return directionY;
   	}
    public void setObjectShape(Object o){
    	objectShape = o;
    }
    public Object getObjectShape(){
    	return objectShape;
    }
    public void setDirectionX(int a){
    	directionX = a;
    }
    public void setDirectionY(int a){
    	directionY = a;
    }


}
