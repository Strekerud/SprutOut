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
	
	int[] score = {0,0,0,0};
	boolean[] life = {true,true,true};
	
	Image menuLogo;
	Image menuClick;
	Image ball;
	Image background;
	Image winnerLogo;
	Image scorePic;
	Image one,two,three,four,five,six,seven,eight,nine,zero;
	Image lifeImg;
	Image deadLife;
	
	Image lifeScore[];
	Image totalScore[];
	
	static private Music openMenuMusic;
	boolean menuTime = true;
	boolean paused = false;
	boolean music = true;
	boolean winner = false;
		
	boolean slept = false;
	
	Paddle p; 
	Ball b;
	
	LevelParser lp;
	LinkedList<Brick> bricks;
	
	Image borderImage;
	char dir;
	
		
	public SprutOut() {
		super("SprutOut - By Team Retard");
				
	}
	public void setScore(){
		for(int i = 0;i<4;i++){
			if(score[i] == 0){
				totalScore[i] = zero;
			}else if(score[i] == 1){
				totalScore[i] = one;
			}else if(score[i] == 2){
				totalScore[i] = two;
			}else if(score[i] == 3){
				totalScore[i] = three;
			}else if(score[i] == 4){
				totalScore[i] = four;
			}else if(score[i] == 5){
				totalScore[i] = five;
			}else if(score[i] == 6){
				totalScore[i] = six;
			}else if(score[i] == 7){
				totalScore[i] = seven;
			}else if(score[i] == 8){
				totalScore[i] = eight;
			}else if(score[i] == 9){
				totalScore[i] = nine;
			}
		}
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		if(menuTime) {
			//Tegner menyen
			menuLogo.draw(100,100);
			menuClick.draw(230,300);
			
			
		}else if(winner){
			winnerLogo.draw(100,100);
			scorePic.draw(230,300);
			totalScore[0].draw(400,300);
			totalScore[1].draw(420,300);
			totalScore[2].draw(440,300);
			totalScore[3].draw(460,300);
		}
		else {
			arg1.drawImage(background, 100, 0);
			//Tegner paddle (må erstattes av bilde)
			arg1.drawRect(p.getPaddleShape().getX(),p.getPaddleShape().getY(),(float) p.width,(float) p.height);
			arg1.drawImage(ball, b.getBallShape().getX(), b.getBallShape().getY());
			//Tegner borders (også midlertidig)
			arg1.drawRect(0,0,100,screenHeight-1);
			arg1.drawRect(screenWidth - 100,0,100,screenHeight-1);
			arg1.drawImage(borderImage,0,0);
			arg1.drawImage(borderImage,screenWidth - 100,0);
			int lifePos = 100;
			for(int i = 0;i<3;i++){
				lifeScore[i].draw(screenWidth-lifePos,100);
				lifePos-=30;
			}
			lifePos = 100;
			totalScore[0].draw(screenWidth-100,150);
			totalScore[1].draw(screenWidth-80,150);
			totalScore[2].draw(screenWidth-60,150);
			totalScore[3].draw(screenWidth-40,150);
			for(int i = 0; i < bricks.size();i++) {
				Brick tmp = bricks.get(i);
				arg1.drawImage(tmp.brickImage,tmp.x_pos,tmp.y_pos);
			}
			if(!slept) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				slept = true;
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
		winnerLogo = new Image("res/img/winner.png");
		scorePic = new Image("res/img/score.png");
		lifeImg = new Image("res/img/life.png");
		deadLife = new Image("res/img/life_dead.png");
		
		//Init score numbers
		
		one = new Image("res/img/numbers/one.png");
		two = new Image("res/img/numbers/two.png");
		three = new Image("res/img/numbers/three.png");
		four = new Image("res/img/numbers/four.png");
		five = new Image("res/img/numbers/five.png");
		six = new Image("res/img/numbers/six.png");
		seven = new Image("res/img/numbers/seven.png");
		eight = new Image("res/img/numbers/eight.png");
		nine = new Image("res/img/numbers/nine.png");
		zero = new Image("res/img/numbers/zero.png");
		
		totalScore = new Image[]{zero,zero,zero,zero};
		lifeScore = new Image[]{lifeImg,lifeImg,lifeImg};
		// Nice hva?
		
		//Oppretter paddelen
		p = new Paddle(100,25,200,screenHeight - 26);
		//Oppretter ballen
		b = new Ball(25,150,350);
		dir = 'w';
		
		lp = new LevelParser();
	    try {
			bricks = lp.parseLevel("res/levels/testLevel.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		borderImage = new Image("res/img/border_placeholder.png");
		
	
	}
	public void resetBall(){
		b = new Ball(25,150,350);
		dir = 'w';
		paused = true;
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
		double curX = b.x_pos;
		double curY = b.y_pos;
		Input input = arg0.getInput();
		if(menuTime == false&&paused == false){
			if(life[2] == false){
				winner = true;
			}
			if(bricks.isEmpty()){
				winner = true;
			}
			if(p.paddleShape.intersects(b.ballShape) && b.getBallShape().getY() >= (p.paddleShape.getY() - p.height)) {
				//System.out.println("BALL TREFFER PADDLE");
								
				if(dir == 's'){
					if(b.x_pos > p.x_pos+p.width/2&&b.x_pos <= b.x_pos+p.width){
						dir = 'n';
					}else{
						dir = 'e';
					}
					moveBall(b.x_pos,b.y_pos,dir);
				}else if(dir == 'w'){
					if(b.x_pos >= p.x_pos&&b.x_pos <= p.x_pos+p.width/2){
						dir = 'e';
					}else{
						dir = 'n';
					}
					moveBall(b.x_pos,b.y_pos,dir);
				}
				
			}
			
			//Brick collisions
			for(int i = 0; i < bricks.size();i++) {
				if(b.ballShape.intersects(bricks.get(i).brickShape)) {
					System.out.println("BALL -> BRICK COLLISION");
					if(score[1] <= 9){
						score[1]++;
					}else{
						score[0]++;
						score[1] = 0;
					}
					setScore();
					if(bricks.get(i).lives == 1) {
						bricks.remove(i);
					}
					else {
						bricks.get(i).lives--;
						bricks.get(i).brickImage = new Image("res/img/brick_placeholder"+bricks.get(i).lives+".png");
					}
					
					//n er opp til høyre
					//s er ned til venstre
					//e opp til venstre
					//w ned til høyre
					if(dir == 'n') {
						dir = 'w';
						moveBall(b.x_pos,b.y_pos,dir);
					}
					else if(dir == 'w') {
						dir = 'n';
						moveBall(b.x_pos,b.y_pos,dir);
					}
					else if(dir == 's') {
						dir = 'e';
						moveBall(b.x_pos,b.y_pos,dir);
					}
					else if(dir == 'e') {
						dir = 's';
						moveBall(b.x_pos,b.y_pos,dir);
					}
					
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
				//minus life
				for(int i = 0;i<3;i++){
					if(life[i]){
						lifeScore[i] = deadLife;
						life[i] = false;
						break;
					}
				}
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
			if(b.x_pos == curX && b.y_pos == curY){
				resetBall();
			}
	}
		
	    if(menuTime && input.isKeyDown(Input.KEY_SPACE)) {
	    	menuTime = false;
	    	menuLogo.destroy();
	    	menuClick.destroy();
	    		    			 
	    }else if(input.isKeyPressed(Input.KEY_P)){
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
	    
	    }else if(input.isKeyPressed(Input.KEY_ESCAPE)){
	    	app.exit();
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
