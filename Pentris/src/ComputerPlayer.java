import java.util.Observable;
import java.util.Observer;


public class ComputerPlayer implements Observer{
	
	Game game;
	Board board;
	FinalBoard fboard;
	Pentomino cPent;

	public ComputerPlayer(Game game) {
		this.game = game;
		board = game.getBoard();
		fboard = game.getFinalBoard();
		cPent = board.getPentomino();
	}
	
	public void update(Observable obs, Object obj){
		if(obs == game){
			board = game.getBoard();
			fboard = game.getFinalBoard();
			cPent = board.getPentomino();
			
			
			
			
			
		}
	}
	
	public int[][] possibilities(FinalBoard fboard, Pentomino pent){
		int[][] result = null;
		
		
		
		
		return result;
	}

}
