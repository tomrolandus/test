
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
		this.location = new int[2];
	}
	
	public Board() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);		
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
		for(int loc: location)
			location[loc] = location[loc] + relativeLocation[loc];
	}
	
	public int[] getLocation() {
		return location;
	}
}
