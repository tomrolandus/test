import java.util.ArrayList;


public class Solution {
	
	char[][] grid;
	
	public Solution(char[][] grid) {
		this.grid = grid;
	}
	
	public Solution(FinalBoard board,ArrayList<int[]> matrix, ArrayList<Integer> rows){
		
		int width = board.getWidth();
		
		grid = new char[board.getHeight()][board.getWidth()];
		
		for(int row : rows){
			char type = 0;
			int[] rw = matrix.get(row);
			for(int i = 0; i < 12; i++)
				if(rw[i] == 1)
					type = Pentomino.intToType(i);
					
			for (int col = 0; col < matrix.get(row).length - Pentomino.PENT_AMOUNT; col++) {

				int x = col % width;
				int y = col / width;

				if (matrix.get(row)[col + Pentomino.PENT_AMOUNT] == 1)
					grid[y][x] = Character.toUpperCase(type);
			}
		}
	}
	
	public ArrayList<char[][]> getPlacements(){
		ArrayList<char[][]> placements = new ArrayList<char[][]>();
		ArrayList<Character> chars = new ArrayList<Character>();
		
		for(char[] row : grid)
			for(char c : row)
				if(!chars.contains(c)) chars.add(c);
		
		
		for(char c : chars){
			
			char[][] newGrid = new char[grid.length][grid[0].length];
			
			for(int row = 0; row < newGrid.length; row++)
				for(int col = 0; col < newGrid[row].length; col++)
					newGrid[row][col] = 0;
			
			for(int row = 0; row < grid.length; row++)
				for(int col = 0; col < grid[row].length; col++)
					if(c == grid[row][col]) newGrid[row][col] = c;
			
			placements.add(newGrid);
		}
		
		return placements;
	}
	
	public void print(){
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[row].length; col++)
				System.out.print(grid[row][col]);
			System.out.println();
		}
		System.out.println();
		
	}

}
