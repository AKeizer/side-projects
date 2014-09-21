import java.util.ArrayList;
class PowerUp extends GameObject{
	// destroys some enemies
	
	private int option = 0;

//this is the constructor, it moves the same as an enemy object so it has the same constructor.
//although this also randomly sets the option to 0 or 1 (1 or 2)
	public PowerUp(int speed, int xvalue, int yvalue, int dirX, int dirY){



		setV(speed);
		setX(xvalue);
		setY(yvalue);
		//setDirectionY(dirY);
		//setDirectionX(dirX);
		option = (int)(Math.round(Math.random()))+1;




	}
	
	public int getOption(){
		return option;
	}
	public void setOption(int a){
		option = a;
	}

}
