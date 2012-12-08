
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;


public class Ball {

	double radius;
	
	double x_pos;
	double y_pos;
	
	Image ballImage;
	Circle ballShape;
	
	double ballSpeed = 4.0;
	
	Ball(double radius,double x_pos,double y_pos) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.radius = radius;
		ballShape = new Circle((float)x_pos,(float)y_pos,(float)radius);
	}
	
	public void setBallImage(Image ballImage){
		this.ballImage = ballImage;
	}
	
	public Image getBallImage(){
		return ballImage;
	}
	
	public Circle getBallShape(){
		return ballShape;
	}
	
	public void setBallSpeed(int speed){
		this.ballSpeed = speed;
	}
	
	
	
}



