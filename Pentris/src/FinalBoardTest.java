import static org.junit.Assert.*;

import org.junit.Test;


public class FinalBoardTest {

	@Test
	public void test() {
		FinalBoard b = new FinalBoard(5,15);
		Pentomino i = new Pentomino('i');
		b.putPentomino(i, new int[]{0,0});
		b.putPentomino(i, new int[]{0,1});
		b.putPentomino(i, new int[]{0,2});
		b.putPentomino(i, new int[]{0,3});
		b.putPentomino(i, new int[]{0,4});
		b.print();
		System.out.println(b.checkFullRow(1));
	}

}
