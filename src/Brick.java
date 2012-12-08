import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Brick {
	
	public int width;
	public int height;
	
	public int x_pos;
	public int y_pos;
	
	public int lives;
	
	public Image brickImage;
	public Rectangle brickShape;
	
	Brick(int width, int height, int x_pos, int y_pos,int lives, String brickImage) {
		this.width = width;
		this.height = height;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.lives = lives;
		try {
			this.brickImage = new Image(brickImage);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.brickShape = new Rectangle((float)x_pos,(float)y_pos,(float)width,(float)height);
	}
	
}
