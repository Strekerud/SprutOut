import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class SprutOut extends BasicGame {

	static AppGameContainer app;
	static int maxFPS = 60;     //Bør flyttes inn i init?
	
	public SprutOut() {
		super("SprutOut - By Team Retard");
				
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		//All tegning av objekter
		
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		//Her initialiseres alt
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		//Alle oppdatering (game logic)
		
	}
	
	 public static void main(String[] args) 
				throws SlickException
	    {
		 	
		 	app = new AppGameContainer(new SprutOut());
		 	app.setDisplayMode(800, 600, false);
		 	app.setTargetFrameRate(maxFPS);
		 	app.start();

	    }

}
