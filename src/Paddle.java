

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class Paddle {
	
	double width;
	double height;
	
	double x_pos;
	double y_pos;
	
	Image paddleImage;
	Rectangle paddleShape;
	
	int paddleSpeed = 6;
	
	Paddle(double width, double height, double x_pos, double y_pos) {
		this.width = width;
		this.height = height;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		paddleShape = new Rectangle((float)x_pos,(float)y_pos,(float)width,(float)height);
	}
	
	public void setPaddleImage(Image paddleImage) {
		this.paddleImage = paddleImage;
	}
	
	public void setPaddleShape(Rectangle paddleShape) {
		this.paddleShape = paddleShape;
	}
	
	public Rectangle getPaddleShape() {
		return paddleShape;
	}
	
	public Image getPaddleImage() {
		return paddleImage;
	}
	
	public void setPaddleSpeed(int newSpeed) {
		this.paddleSpeed = newSpeed;
	}
	
}
