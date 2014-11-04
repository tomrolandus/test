import java.util.HashMap;

public class Board{
	private int row;
	private int col;
	private HashMap<Pentomino, int[]> pentominoes;
	private char pent;
	private char[][] grid;
	

	public Board(int row, int col) {
		this.row = row;
		this.col = col;
		pentominoes = new HashMap<Pentomino, int[]>();
		grid = new char[12][5];
	}
	
	public Board() {
		this(5, 15);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char getPentomino(){
		return pent;
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
