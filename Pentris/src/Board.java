
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Pentomino getPentomino(){
		return pent;
	}

	public void putPentomino(Pentomino pent, int[] location) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void movePentomino(int[] relativeLocation) {
		// TODO Auto-generated method stub
		
	}

	public int[] getLocation(Pentomino pent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
