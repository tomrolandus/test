import java.util.ArrayList;

public class Solver {

	private int gridHeight;
	private int gridWidth;

	public Solver(int gridHeight, int gridWidth) {
		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;
	}

	public static void main(String[] args) {
		Solver solver = new Solver(12, 5);

		// solve exact cover problem
		solver.solve(solver.getPentominoes());

		// prune solutions
		System.out.println(allSolutions.size());
		solver.pruneSolutions();
		System.out.println(allSolutions.size());

		// find solution index and relevant max score
		int score = 0;
		int solution = 0;

		ArrayList<ArrayList<char[][]>> order = null;
		for (int i = 0; i < allSolutions.size(); i++) {
			ArrayList<ArrayList<char[][]>> temp = solver
					.orderMaxScore(allSolutions.get(i));
			allSolutions.get(i).print();
			for (int j = 0; j < temp.size(); j++) {
				int newScore = solver.getScore(temp.get(j));
				System.out.println(newScore);
				if (newScore >= score) {
					score = newScore;
					solution = i;
					order = temp;
				}
			}
		}

		// print one of the solutions
		System.out.println(score);
		allSolutions.get(solution).print();
		for (int k = 0; k < 12; k++)
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 5; j++)
					System.out.print(order.get(0).get(k)[i][j]);
				System.out.println();
			}

	}

	private void pruneSolutions() {
		ArrayList<Solution> toRemove = new ArrayList<Solution>();
		for (Solution sol : allSolutions)
			if (prune(sol))
				toRemove.add(sol);

		for (Solution sol : toRemove)
			allSolutions.remove(sol);
	}

	private boolean prune(Solution sol) {
		char[][] grid = sol.getGrid();

		ArrayList<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < grid.length; i++)
			r.add(i);

		rowLoop: for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
				if (grid[row][col] == 'L' || grid[row][col] == 'I') {
					r.remove((Integer) row);
					continue rowLoop;
				}

		// should be pruned if I and L are not upright or overlapping
		if (r.size() > 3)
			return true;

		// rows cannot be spanned by a single piece if the rows are
		// discontinuous
		if (r.get(2) - r.get(0) > 2)
			return true;

		for (int col = 0; col < grid[0].length; col++) {
			int row = r.get(0);
			char type = grid[row][col];
			if (grid[row + 1][col] == type && grid[row + 2][col] == type)
				return false;
		}

		return true;

	}

	public ArrayList<ArrayList<char[][]>> orderMaxScore(Solution sol) {
		ArrayList<char[][]> placements = sol.getPlacements();

		possibleOrders = new ArrayList<ArrayList<char[][]>>();
		searchOrders(placements);

		int maxScore = 0;
		ArrayList<ArrayList<char[][]>> maxOrders = new ArrayList<ArrayList<char[][]>>();

		for (ArrayList<char[][]> order : possibleOrders) {
			int score = getScore(order);
			if (score > maxScore) {
				maxScore = score;
				maxOrders.clear();
				maxOrders.add(order);
			}
		}
		for(int i = 0; i < 12; i++){
			for(int j = 0; j < 12; j++){
				for(int k = 0; k < 5; k++)
					System.out.print(maxOrders.get(0).get(i)[j][k]);
				System.out.println();
			}
			System.out.println();
		}
		return maxOrders;
	}

	public int getScore(ArrayList<char[][]> placements) {
		
		
		
		int result = 0;
		int count = 0;
		FinalBoard board = new FinalBoard(gridWidth, gridHeight);
		
		ArrayList<Integer> openRows = new ArrayList<Integer>();
		for(int i = 0; i < board.getHeight(); i++)
			openRows.add(i);
		
		for (char[][] placement : placements) {
			board.putPentomino(placement);
			
			
			
			ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
			
			for (int row : openRows)
				if (board.checkFullRow(row)){
					count++;
					rowsToRemove.add(row);
				}
			
			if (!rowsToRemove.isEmpty()) {
				int score = Game.calculateScore(rowsToRemove.size());
				result += score;
				for(int row : rowsToRemove)
					openRows.remove((Integer) row);
			}
		}
		return result;

	}

	private ArrayList<ArrayList<char[][]>> possibleOrders = new ArrayList<ArrayList<char[][]>>();
	private ArrayList<char[][]> currentPlacements = new ArrayList<char[][]>();

	public void searchOrders(ArrayList<char[][]> placements) {

		if (placements.isEmpty()) {
			possibleOrders.add((ArrayList<char[][]>) currentPlacements.clone());

			return;
		}

		char[][] present = new char[gridHeight][gridWidth];
		for (int row = 0; row < present.length; row++)
			for (int col = 0; col < present[row].length; col++)
				present[row][col] = 0;

		for (char[][] placement : currentPlacements)
			placePlacement(present, placement);

		for (int i = 0; i < placements.size(); i++) {
			char[][] placement = placements.get(i);
			if (checkPlacable(present, placement)) {
				currentPlacements.add(placement);
				placements.remove(placement);
				searchOrders(placements);
				placements.add(placement);
				currentPlacements.remove(placement);
			}
		}

		return;
	}

	public void placePlacement(char[][] goal, char[][] source) {
		for (int row = 0; row < goal.length; row++)
			for (int col = 0; col < goal[row].length; col++)
				if (source[row][col] != 0)
					goal[row][col] = source[row][col];
	}

	public boolean checkPlacable(char[][] present, char[][] input) {

		for (int row = 0; row < input.length; row++)
			for (int col = 0; col < input[row].length; col++)
				if (input[row][col] != 0 && row == present.length - 1)
					return true;
				else if (input[row][col] != 0 && present[row + 1][col] != 0)
					return true;
		return false;
	}

	private static ArrayList<Solution> allSolutions = new ArrayList<Solution>();
	Integer solutionCount = 0;
	ArrayList<Integer> solutions = new ArrayList<Integer>();

	ArrayList<int[]> matrix;

	ArrayList<Integer> possibleRows = new ArrayList<Integer>();
	ArrayList<Integer> possibleCols = new ArrayList<Integer>();

	public ArrayList<Solution> solve(ArrayList<Pentomino> pentominoes) {

		matrix = generatePossibilityMatrix(pentominoes);

		for (int row = 0; row < matrix.size(); row++)
			possibleRows.add(row);
		for (int col = 0; col < matrix.get(0).length; col++)
			possibleCols.add(col);

		solveMatrix(matrix, possibleRows, possibleCols);

		return allSolutions;
	}

	private int solution = 0;

	private void solveMatrix(ArrayList<int[]> matrix,
			ArrayList<Integer> possibleRows, ArrayList<Integer> possibleCols) {

		// if (solution >= 10)
		// return;

		if (possibleCols.isEmpty()) {

			solution++;

			if (solutionCount % 25 == 0) {
				String s = "";
				s += "|";
				for (int i = 0; i < solutionCount / 25; i++)
					s += "*";
				for (int i = solutionCount / 25; i < 40; i++)
					s += " ";
				s += "|\r";
				System.out.print(s);
			}

			allSolutions.add(new Solution(
					new FinalBoard(gridWidth, gridHeight), matrix, solutions));
			solutionCount++;
			return;
		}

		if (possibleRows.isEmpty())
			return;

		// select one column
		int selectedCol = 0;
		int minCol = matrix.size();
		for (Integer col : possibleCols) {
			int colCount = 0;

			// count rows with one
			for (Integer row : possibleRows)
				if (matrix.get(row)[col] == 1)
					colCount++;

			// update mincol and selected col
			if (colCount < minCol) {
				minCol = colCount;
				selectedCol = col;
			}
		}

		ArrayList<Integer> rowsWithOne = new ArrayList<Integer>();

		for (Integer row : possibleRows)
			if (matrix.get(row)[selectedCol] == 1)
				rowsWithOne.add((Integer) row);

		for (Integer selectedRow : rowsWithOne) {
			ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
			ArrayList<Integer> colsToRemove = new ArrayList<Integer>();

			// find all intersecting columns and rows to remove
			// check all columns

			for (Integer col : possibleCols) {

				if (matrix.get(selectedRow)[col] == 1) {
					colsToRemove.add((Integer) col);
					// check all rows in the selected columns
					for (Integer row : possibleRows)
						if (matrix.get(row)[col] == 1)
							if (!rowsToRemove.contains((Integer) row))
								rowsToRemove.add(row);

				}
			}

			// add row to solution
			solutions.add(selectedRow);

			// remove rows and columns to remove
			for (Integer row : rowsToRemove)
				possibleRows.remove(row);
			for (Integer col : colsToRemove)
				possibleCols.remove(col);

			// recursive step
			solveMatrix(matrix, possibleRows, possibleCols);

			for (Integer row : rowsToRemove)
				possibleRows.add(row);
			for (Integer col : colsToRemove)
				possibleCols.add(col);

			solutions.remove((solutions.size() - 1));
		}

	}

	public ArrayList<int[]> generatePossibilityMatrix(
			ArrayList<Pentomino> pentominoes) {

		FinalBoard board = new FinalBoard(gridWidth, gridHeight);

		ArrayList<int[]> result = new ArrayList<int[]>();

		ArrayList<Pentomino> pents = getPentominoes();

		for (Pentomino pent : pents) {
			int[][] shape = pent.getShape();
			char type = pent.getType();

			for (int x = 0; x < board.getWidth(); x++)
				for (int y = 0; y < board.getHeight(); y++)
					if (board.checkPlacement(pent, new int[] { y, x })) {

						int[] newRow = new int[Pentomino.PENT_AMOUNT
								+ board.getHeight() * board.getWidth()];

						for (int i = 0; i < newRow.length; i++)
							newRow[i] = 0;

						int index = Pentomino.typeToInt(type);

						newRow[index] = 1;

						for (int row = 0; row < shape.length; row++)
							for (int col = 0; col < shape[row].length; col++)
								if (shape[row][col] != 0)
									newRow[(x + col) + (y + row)
											* board.getWidth()
											+ Pentomino.PENT_AMOUNT] = 1;
						result.add(newRow);
					}
		}

		return result;
	}

	public ArrayList<Pentomino> getPentominoes() {
		ArrayList<Pentomino> result = new ArrayList<Pentomino>();

		Pentomino f = new Pentomino('f');
		Pentomino p = new Pentomino('p');
		Pentomino x = new Pentomino('x');
		Pentomino v = new Pentomino('v');
		Pentomino w = new Pentomino('w');
		Pentomino y = new Pentomino('y');
		Pentomino i = new Pentomino('i');
		Pentomino t = new Pentomino('t');
		Pentomino z = new Pentomino('z');
		Pentomino u = new Pentomino('u');
		Pentomino n = new Pentomino('n');
		Pentomino l = new Pentomino('l');

		Pentomino[][] pentses = new Pentomino[][] { getPermutations(f),
				getPermutations(p), getPermutations(x), getPermutations(v),
				getPermutations(w), getPermutations(y), getPermutations(i),
				getPermutations(t), getPermutations(z), getPermutations(u),
				getPermutations(n), getPermutations(l) };

		for (Pentomino[] pents : pentses)
			for (Pentomino pent : pents)
				result.add(pent);

		return result;
	}

	public Pentomino[] getPermutations(Pentomino pent) {
		Pentomino[] pentominoes;
		char type = pent.getType();
		pentominoes = new Pentomino[pent.getAmountOfPermutations()];

		for (int i = 0; i < pentominoes.length; i++) {
			Pentomino newPent = new Pentomino(type);
			newPent.rotate(i);
			pentominoes[i] = newPent;
		}

		if (type == 'f' || type == 'l' || type == 'p' || type == 'z'
				|| type == 'y' || type == 'n')
			for (int i = pentominoes.length / 2; i < pentominoes.length; i++)
				pentominoes[i].mirror();

		return pentominoes;
	}
}
