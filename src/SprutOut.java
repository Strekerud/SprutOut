import java.io.FileNotFoundException;
import java.util.LinkedList;

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
	static int maxFPS = 60;
	
	final int screenHeight = 600;
	final int screenWidth = 800;
	
	final int leftBorder = 100;
	final int rightBorder = screenWidth - 200;
	
	Image menuLogo;
	Image menuClick;
	Image ball;
	Image background;
	
	static private Music openMenuMusic;
	boolean menuTime = true;
	boolean paused = false;
	boolean music = true;
	
	Image pause;
	
	
	Paddle p; 
	Ball b;
	
	LevelParser lp;
	LinkedList<Brick> bricks;
	
	Image borderImage;
	char dir;
	
		
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
		else {
			
			arg1.drawImage(background, 0, 0);
			//Tegner paddle (må erstattes av bilde)
			arg1.drawRect(p.getPaddleShape().getX(),p.getPaddleShape().getY(),(float) p.width,(float) p.height);
			arg1.drawImage(ball, b.getBallShape().getX(), b.getBallShape().getY());
			//Tegner borders (også midlertidig)
			arg1.drawRect(0,0,100,screenHeight-1);
			arg1.drawRect(screenWidth - 100,0,100,screenHeight-1);
			arg1.drawImage(borderImage,0,0);
			arg1.drawImage(borderImage,screenWidth - 100,0);
			for(int i = 0; i < bricks.size();i++) {
				Brick tmp = bricks.get(i);
				arg1.drawImage(tmp.brickImage,tmp.x_pos,tmp.y_pos);
			}
		}
	
		
		
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		//Initialiserer menyen
		//Setter ikoner til tittellinjen + alt-tab + Startmeny
		String[] icons = { "res/img/icons/icon16_2.png", "res/img/icons/icon24_2.png", "res/img/icons/icon32_2.png"};
		arg0.setIcons(icons);
		
		menuLogo = new Image("res/img/Menu_Logo.png");
		menuClick = new Image("res/img/Menu_Click.png");
		ball = new Image("res/img/ball.png");
		background = new Image("res/img/bground.png");
		
		
		//Oppretter paddelen
		p = new Paddle(100,25,200,screenHeight - 26);
		//Oppretter ballen
		b = new Ball(25,150,150);
		dir = 'n';
		
		lp = new LevelParser();
	    try {
			bricks = lp.parseLevel("res/levels/testLevel.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		borderImage = new Image("res/img/border_placeholder.png");
		
	
	}
	public void moveBall(double cur_x_pos,double cur_y_pos,char direction){
		if(direction == 'n'){ // opp til høyre
			b.x_pos = b.x_pos + b.ballSpeed;
			b.y_pos = b.y_pos - b.ballSpeed;
			
		}else if(direction == 's'){ //ned til venstre
			b.x_pos = b.x_pos - b.ballSpeed;
			b.y_pos = b.y_pos + b.ballSpeed;
			
		}else if(direction == 'e'){ // opp til venstre
			b.x_pos = b.x_pos - b.ballSpeed;
			b.y_pos = b.y_pos - b.ballSpeed;
			;
		}else if(direction == 'w'){ // ned til høyre
			b.x_pos = b.x_pos + b.ballSpeed;
			b.y_pos = b.y_pos + b.ballSpeed;
		
		}
		
		b.ballShape.setX((float)b.x_pos);
		b.ballShape.setY((float)b.y_pos);
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		//System.out.println("Ball x: " + b.ballShape.getX() + " y: " +  b.ballShape.getY());
		//System.out.println("Paddle x: " + p.paddleShape.getX() + " y: " +  p.paddleShape.getY());
		
		//Alle oppdatering (game logic)
		Input input = arg0.getInput();
		if(menuTime == false&&paused == false){
			
			
			
			if(p.paddleShape.intersects(b.ballShape) && b.getBallShape().getY() >= (p.paddleShape.getY() - p.height)) {
				//System.out.println("BALL TREFFER PADDLE");
				if(dir == 's'){
					dir = 'e';
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 'w'){
					dir = 'n';
					moveBall(b.x_pos,b.y_pos,dir);
				}
				
			}
			
			//Brick collisions
			for(int i = 0; i < bricks.size();i++) {
				if(b.ballShape.intersects(bricks.get(i).brickShape)) {
					System.out.println("BALL -> BRICK COLLISION");
					bricks.remove(i);
				}
			}
			
			if(b.x_pos-b.ballSpeed<=leftBorder){
				if(dir == 'e'){
					dir = 'n';
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 's'){
					dir = 'w';
					moveBall(b.x_pos,b.y_pos,dir);
				}
			}else if(b.x_pos+b.ballSpeed+b.radius-100>=rightBorder){
				if(dir == 'n'){
					dir = 'e';
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 'w'){
					dir = 's';
					moveBall(b.x_pos,b.y_pos,dir);
				}
			}else if(b.y_pos+b.ballSpeed<=0.0){
				if(dir == 'n'){
					dir = 'w';
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 'e'){
					dir = 's';
					moveBall(b.x_pos,b.y_pos,dir);
				}
			}else if(b.y_pos+b.ballSpeed+b.radius >= screenHeight){
				if(dir == 's'){
					dir = 'e';
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 'w'){
					dir = 'n';
					moveBall(b.x_pos,b.y_pos,dir);
				}
			}
			
			
			else{
				moveBall(b.x_pos,b.y_pos,dir);
			}
	}
		
	    if(menuTime && input.isKeyDown(Input.KEY_SPACE)) {
	    	menuTime = false;
	    	menuLogo.destroy();
	    	menuClick.destroy();
	    		    			 
	    }
	    else if(input.isKeyPressed(Input.KEY_P)){
	    	if(paused == false){
	    		paused = true;
	    	}else if (paused == true){
	    		paused = false;
	    	}
	    }
	    else if(input.isKeyDown(Input.KEY_RIGHT)&&paused == false) {
	    	if(p.x_pos >= rightBorder){
	    		p.x_pos += 0.0;
	    	}else{
	    		p.x_pos += p.paddleSpeed;
	    	}
	    	p.paddleShape.setX((float)p.x_pos);
	    	p.paddleShape.setY((float)p.y_pos);
	    }
	    
	    else if(input.isKeyDown(Input.KEY_LEFT)&&paused == false) {
	    	if(p.x_pos <= leftBorder){
	    		p.x_pos += 0.0;
	    	}else{	
	    		p.x_pos -= p.paddleSpeed;
	    	}
	    	p.paddleShape.setX((float)p.x_pos);
	    	p.paddleShape.setY((float)p.y_pos);
	    }else if(input.isKeyPressed(Input.KEY_M)){
	    	if(music){
	    		music = false;
	    		openMenuMusic.pause();
	    	}else{
	    		music = true;
	    		openMenuMusic.resume();
	    	}
	    
	    }
	    		
	}
	
	public static void main(String[] args) throws SlickException {
	 	
	 	app = new AppGameContainer(new SprutOut());
	 	app.setDisplayMode(800, 600, false);
	 	app.setTargetFrameRate(maxFPS);
	 	app.setShowFPS(false);
		openMenuMusic = new Music("res/music/theme.ogg");
		openMenuMusic.loop();
	 	app.start();
		 	
	 }
	
	

}
