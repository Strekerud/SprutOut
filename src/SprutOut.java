import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class SprutOut extends BasicGame {

	

	public SprutOut() {
		super("SprutOut - By Team Retard");
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	 public static void main(String[] args) 
				throws SlickException
	    {
	         AppGameContainer app = 
				new AppGameContainer(new SprutOut());
	 
	         app.setDisplayMode(800, 600, false);
	         app.start();
	    }

}
