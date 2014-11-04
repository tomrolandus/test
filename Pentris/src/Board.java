
public class Board {
	int width, height;
	Pentomino pent;
	int[] location;
	char[][] grid;
	

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Board() {
		this(5, 15);
	}
	
	public int getWidth() {
		return width;
	}
	
	public Pentomino getPentomino(){
		return pent;
	}

	public void putPentomino(Pentomino pent, int[] location) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		return height;
	}

	public void movePentomino(int[] relativeLocation) {
		// TODO Auto-generated method stub
	}
	
	public int[] getLocation(Pentomino pent) {
		return null;
	}

	public void putPentomino(Pentomino pent, int row, int col) {
		grid[width][height] = pent;
	}

	public void movePentomino(int row, int col) {		
		grid[row][col] = pent;
	}	
}
