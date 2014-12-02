import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author tomrolandus
 *
 */
public class GameTest {

	@Test
	public void test() {
		Game game = new Game();
		game.start();
		System.out.println(game.getScore());
		
		
		
	}
	

}
