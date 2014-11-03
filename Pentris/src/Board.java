import java.util.HashMap;

public class Board {
	int width, height;
	HashMap<Pentomino, int[]> pentominoes;
	char[][] grid;
	

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		pentominoes = new HashMap<Pentomino, int[]>();
	}

	public Board() {
		this(5, 15);
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void putPentomino(Pentomino pent, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void movePentomino(Pentomino currentPent, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public int[] getLocation(Pentomino pent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
