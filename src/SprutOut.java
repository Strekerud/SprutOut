import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;



public class SprutOut extends BasicGame {

	static AppGameContainer app;
	static int maxFPS = 60;     //Bør flyttes inn i init?
	
	Image menuLogo;
	Image menuClick;
	static private Music openMenuMusic;
	boolean menuTime = true;
	
	// Heisann dere! :D
	
	public SprutOut() {
		super("SprutOut - By Team Retard");
				
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		if(menuTime) {
			//Tegner menyen
			menuLogo.draw(100,100);
			menuClick.draw(230,300);
			
		}
	
		
		
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		//Initialiserer menyen
		menuLogo = new Image("res/img/Menu_Logo.png");
		menuClick = new Image("res/img/Menu_Click.png");
		
	
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		//Alle oppdatering (game logic)
		Input input = arg0.getInput();
		 
	    if(menuTime && input.isKeyDown(Input.KEY_SPACE)) {
	    	menuTime = false;
	    	menuLogo.destroy();
	    	menuClick.destroy();
	    }
		
	}
	
	public static void main(String[] args) throws SlickException {
	 	
	 	app = new AppGameContainer(new SprutOut());
	 	app.setDisplayMode(800, 800, false);
	 	app.setTargetFrameRate(maxFPS);
		openMenuMusic = new Music("res/music/theme.ogg");
		openMenuMusic.play();
	 	app.start();
		 	
	 }
	
	

}
