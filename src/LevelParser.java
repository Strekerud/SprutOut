import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * 
 * Formatet på levelene
 * Én linje per brick
 * Bredde - Høyde - x_pos - y_pos - Liv - BildefilLokasjon
 */



public class LevelParser {
	
	LinkedList<Brick> bricks = new LinkedList<Brick>();
	
	public LinkedList<Brick> parseLevel(String levelPath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(levelPath));
		while(s.hasNext()) {
			String[] brickString = s.nextLine().split(" ");
			bricks.add(new Brick(Integer.parseInt(brickString[0]),Integer.parseInt(brickString[1]),Integer.parseInt(brickString[2]),Integer.parseInt(brickString[3]),Integer.parseInt(brickString[4]), brickString[5]));
			
		}
		return bricks;
	}
	
	

	
}
