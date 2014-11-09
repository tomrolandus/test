import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

	private Board board;
	private FinalBoard fboard;
	private ArrayList<Pentomino> pentominoes;
	private Timer timer;
	private int score;
	private long dropSpeed;
	private int level;
	private Pentomino currentPent;

	private static final double LEVEL_INCREASE = 0.8;
	private static final int LINE_SCORE = 1;
	private static final double BONUS_SCORE = 1;
	private static final long LEVEL_INTERVAL = 20000; // twenty seconds
	private static final long INITIAL_DROP_SPEED = 2000; // 2 seconds

	
	public Game(){
		this(new Board(), Pentomino.getAllPentominoes(), 0);
	}
	
	public Game(Board board, ArrayList<Pentomino> pentominoes, int level) {
		this.board = board;
		this.pentominoes = pentominoes;
		this.score = 0;
		this.dropSpeed = (long) (INITIAL_DROP_SPEED * Math.pow(LEVEL_INCREASE,
				level));
		this.level = level;
		this.fboard = new FinalBoard(board.getWidth(), board.getHeight());
	}
	
	public int getLevel() {
		return this.level;
	}

	public int getScore() {
		return score;
	}

	public double getDropSpeed() {
		return dropSpeed;
	}

	public FinalBoard getFinalBoard() {
		return fboard;
	}

	public Board getBoard() {
		return board;
	}

	private void deleteRows(ArrayList<Integer> rows) {
		for (int row : rows)
			fboard.removeLine(row);
		countScore(rows.size());
	}

	private Pentomino chooseNextPentomino() {
		Random r = new Random();
		return (Pentomino) pentominoes.get(r.nextInt(pentominoes.size()))
				.clone();
	}

	private void initiatePentomino(Pentomino pent) {
		int[] startLocation = {board.getWidth() / 2, 0};
		board.putPentomino(pent, startLocation);
	}

	private void countScore(int amountOfRows) {
		int addScore = (int) (LINE_SCORE * amountOfRows + BONUS_SCORE
				* (amountOfRows - 1));
		score += addScore;
	}

	private void nextLevel() {
		//dropSpeed *= LEVEL_INCREASE;
		level++;
	}
	
	public void rotateCurrentPent(){
		currentPent.rotate();
	}
	
	public void moveCurrentPentLeft(){
		int[] oneLeft = {0,-1};
		board.movePentomino(oneLeft);
	}
	
	public void moveCurrentPentRight(){
		int[] oneRight = {0,1};
		board.movePentomino(oneRight);
	}
	
	public void moveCurrentPentDown(){
		final int[] oneDown = {1,0};
		while(!fboard.checkFloorCollision(currentPent, board.getLocation()))
			board.movePentomino(oneDown);
	}

	public void start() {
		
		timer = new Timer();

		class LevelUp extends TimerTask {
			public LevelUp() {
				super();
			}

			public void run() {
				nextLevel();
			}
		}
		
		LevelUp levelUp = new LevelUp();
		timer.scheduleAtFixedRate(levelUp, LEVEL_INTERVAL, LEVEL_INTERVAL);
		
		game:
		while (!this.checkGameOver()) {

			
			//Initialize pentomino in the top middle of the screen
			this.currentPent = this.chooseNextPentomino();
			initiatePentomino(currentPent);
			
			if(!fboard.checkPlacement(currentPent, board.getLocation()))
				break game;

			while(!fboard.checkFloorCollision(currentPent, board.getLocation())){
				try{
					Thread.sleep(dropSpeed);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				int[] oneDown = {1,0};
				board.movePentomino(oneDown);
			}


			
			//put pentomino on the final board
			fboard.putPentomino(currentPent, board.getLocation());
			
			//Delete rows and count score
			ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
			for (int row = 0; row < currentPent.getHeight(); row++) {
				row += board.getLocation()[1]; //get row in which the pentomino will be placed
				if (fboard.checkFullRow(row)) rowsToRemove.add(row);
			}
			if(!rowsToRemove.isEmpty())
				deleteRows(rowsToRemove);
			
		}
		System.out.println("Game Over!");
	}

	private boolean checkGameOver() {
		if(fboard.checkHitCeiling())
			return true;
		return false;
	}

}
