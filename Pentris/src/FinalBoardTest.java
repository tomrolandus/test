import static org.junit.Assert.*;

import org.junit.Test;


public class FinalBoardTest {

	@Test
	public void test() {
		FinalBoard b = new FinalBoard(15,5);
		Pentomino p = new Pentomino('f');
		
		b.checkFloorCollision(p,13,5);
	}

}
