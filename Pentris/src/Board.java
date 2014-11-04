
public class Board {
	private int width, height;
	private Pentomino pent;
	private int[] location;
	final private static int DEFAULT_WIDTH = 5;
	final private static int DEFAULT_HEIGTH = 15;
	

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		pent = null;
		this.location = null;
	}
	
	public Board() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGTH);		
	}
	
	public int getWidth() {
		return width;
	}
	
	public Pentomino getPentomino(){
		return pent;
	}

	public void putPentomino(Pentomino pent, int[] location) {
		this.pent = pent;
		this.location = location;
	}

	public int getHeight() {
		return height;
	}

	public void movePentomino(int[] relativeLocation) {
		location[0] = location[0] + relativeLocation[0];
		location[1] = location[1] + relativeLocation[1];
	}
	
	public int[] getLocation() {
		return location;
	}
}
