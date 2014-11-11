import java.util.ArrayList;

/**
The Pentomino class creates every possible pentomino. 
	
*/
public class Pentomino {
	
	// The following code has every possible pentomino saved as an array. The array type is integer to ensure that it would be easy to input into grid 
	// (plus it has benefit to see a clash will occur) 
	final private static int[][] F = {{0,1,1},{1,1,0},{0,1,0}}; //red
	final private static int[][] P = {{1,1},{1,1},{1,0}}; //blue
	final private static int[][] X = {{0,1,0},{1,1,1},{0,1,0}}; //green
	final private static int[][] V = {{1,0,0},{1,0,0},{1,1,1}}; //cyan
	final private static int[][] W = {{1,0,0},{1,1,0},{0,1,1}}; //gray
	final private static int[][] Y = {{0,1},{1,1},{0,1},{0,1}}; //magenta
	final private static int[][] I = {{1},{1},{1},{1},{1}}; //orange
	final private static int[][] T = {{1,1,1},{0,1,0},{0,1,0}}; //pink
	final private static int[][] Z = {{1,1,0},{0,1,0},{0,1,1}}; //yellow
	final private static int[][] U = {{1,0,1},{1,1,1}}; //black
	final private static int[][] N = {{1,1,0,0},{0,1,1,1}}; //darkGray
	final private static int[][] L = {{0,0,0,1},{1,1,1,1}}; //lightGray
	
	//This variable is a holder for each shape of the instance.
	private int[][] pent;
	//This variable holds the char which connects to the required pentomino.
	private char type;
	
	public static ArrayList<Pentomino> getAllPentominoes(){
		return getAllPentominoes(true);
	}
	
	public static ArrayList<Pentomino> getAllPentominoes(boolean mirrors){
		ArrayList<Pentomino> result = new ArrayList<Pentomino>();
		Pentomino[] pentominoes;
		if(mirrors){
			pentominoes = new Pentomino[18];
			pentominoes[0] = new Pentomino('f');
			pentominoes[1] = new Pentomino('f');
			pentominoes[2] = new Pentomino('p');
			pentominoes[3] = new Pentomino('p');
			pentominoes[4] = new Pentomino('x');
			pentominoes[5] = new Pentomino('v');
			pentominoes[6] = new Pentomino('w');
			pentominoes[7] = new Pentomino('y');
			pentominoes[8] = new Pentomino('y');
			pentominoes[9] = new Pentomino('i');
			pentominoes[10] = new Pentomino('t');
			pentominoes[11] = new Pentomino('z');
			pentominoes[12] = new Pentomino('z');
			pentominoes[13] = new Pentomino('u');
			pentominoes[14] = new Pentomino('n');
			pentominoes[15] = new Pentomino('n');
			pentominoes[16] = new Pentomino('l');
			pentominoes[17] = new Pentomino('l');
			
			pentominoes[1].mirror();
			pentominoes[3].mirror();
			pentominoes[12].mirror();
			pentominoes[15].mirror();
			pentominoes[17].mirror();
			
			
		}else{
			pentominoes = new Pentomino[12];
			pentominoes[0] = new Pentomino('f');
			pentominoes[1] = new Pentomino('p');
			pentominoes[2] = new Pentomino('x');
			pentominoes[3] = new Pentomino('v');
			pentominoes[4] = new Pentomino('w');
			pentominoes[5] = new Pentomino('y');
			pentominoes[6] = new Pentomino('i');
			pentominoes[7] = new Pentomino('t');
			pentominoes[8] = new Pentomino('z');
			pentominoes[9] = new Pentomino('u');
			pentominoes[10] = new Pentomino('n');
			pentominoes[11] = new Pentomino('l');
		}
		
		for(Pentomino pent : pentominoes)
			result.add(pent);
		
		return result;
	}
	
	
	
	/**
		Creates the object based on what shape the users wants it to be 

		@param type of every possible shape.
	*/
	public Pentomino(char type){
		if(type == 'f') this.pent = F;
		else if(type == 'p') this.pent = P;
		else if(type == 'x') this.pent = X;
		else if(type == 'v') this.pent = V;
		else if(type == 'w') this.pent = W;
		else if(type == 'y') this.pent = Y;
		else if(type == 'i') this.pent = I;
		else if(type == 't') this.pent = T;
		else if(type == 'z') this.pent = Z;
		else if(type == 'u') this.pent = U;
		else if(type == 'n') this.pent = N;
		else if(type == 'l') this.pent = L;
		else System.out.println("Invalid pentomino type submitted");
		
		this.type = type;
	}
	
	public Object clone(){
		Object pent = new Pentomino(this.getType());
		return pent;
	}
	
	public boolean equals(Pentomino pent){
		return this.getType() == pent.getType();
	}
	
	public char getType(){
		return type;
	}
	
	/**
	 	This method rotates the array by first transposing the array then switching 
		the columns of the arrays 
	*/
	public void rotate(){
		int[][] transpose = new int[pent[0].length][pent.length];
		
		for(int i = 0; i < transpose.length; i++)			
			for(int j = 0; j < transpose[i].length; j++)				
				transpose[i][j] = pent[j][i];
		
		int[][] columnSwitch = new int[pent[0].length][pent.length];
		
		for(int i = 0; i < columnSwitch.length; i++)
			for(int j = 0; j < columnSwitch[i].length; j++)
				columnSwitch[i][j] = transpose[i][pent.length-j-1];
		
		pent = columnSwitch;
	}
	
	public void rotate(int times){
		for ( int i = 0; i < times; i ++ )
			this.rotate();
	}


	/**
		It mirrors the shape of the pentomino.
	*/
	public void mirror(){
		
		int[][] columnSwitch = new int[pent.length][pent[0].length];
		
		for(int i = 0; i < columnSwitch.length; i++)
			for(int j = 0; j < columnSwitch[i].length; j++)
				columnSwitch[i][j] = pent[i][pent[0].length-j-1];
		
		this.pent = columnSwitch;
	}


	/**
		@return pent  Shape of the pentomino.
	*/
	public int[][] getShape(){
		return pent;
	}
	

	/**
		Returns an integer of the amount of shapes the pentomino can take.
		
		@return amount
	*/
	public int getAmountOfPermutations(){
		int amount = 1;
		if(type == 'f' || type == 'l' || type == 'p' || type =='n' || type =='y') amount = 8;
		else if(type == 'm' || type == 'z' || type == 't' || type == 'u' || type == 'v') amount = 4;
		else if(type == 'i') amount = 2;
		else if(type == 'x') amount = 1;
		return amount;
	}
	
	/**
		@return pentominoes Array of all permutations of a particular pentomino
	*/
	public Pentomino[] getPermutations() {
		Pentomino[] pentominoes;

		pentominoes = new Pentomino[this.getAmountOfPermutations()];

		for (int i = 0; i < pentominoes.length; i++){
			Pentomino pent = new Pentomino(this.type);
			pent.rotate(i);
			pentominoes[i] = pent;
		}
		
		if (type == 'f' || type == 'l' || type == 'p' || type == 'z'
				|| type == 'y' || type == 'n')
			for (int i = pentominoes.length / 2; i < pentominoes.length; i++)
				pentominoes[i].mirror();

		return pentominoes;
	}
	

	/**
		Getter method which returns the width of a pentomino.

		return width
	*/
	public int getWidth(){
		return pent[0].length;
	}

	/**
		Getter method which returns the height of a pentomino.

		@return height
	*/
	public int getHeight(){
		return pent.length;
	}


}
