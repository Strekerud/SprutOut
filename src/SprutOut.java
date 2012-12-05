import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class SprutOut extends BasicGame {

	static AppGameContainer app;
	static int maxFPS = 60;     //Bør flyttes inn i init?
	
	Image menuLogo;
	Image menuClick;
	
	public SprutOut() {
		super("SprutOut - By Team Retard");
				
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		//Tegner menyen
		menuLogo.draw(100,100);
		menuClick.draw(200,300);
		
		
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		//Initialiserer menyen
		menuLogo = new Image("img/Menu_Logo.png");
		menuClick = new Image("img/Menu_Click.png");
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		//Alle oppdatering (game logic)
		
	}
	
	 public static void main(String[] args) 
				throws SlickException
	    {
		 	
		 	app = new AppGameContainer(new SprutOut());
		 	app.setDisplayMode(800, 800, false);
		 	app.setTargetFrameRate(maxFPS);
		 	app.start();

	    }

}
