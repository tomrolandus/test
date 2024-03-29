import java.util.ArrayList;

/**
The Pentomino class creates every possible pentomino. 
	
*/
public class Pentomino {
	
	public static int PENT_AMOUNT=12;
	
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
	
	public static int typeToInt(char type){
		int index;
		switch (type) {
		case 'f':
			index = 0;
			break;
		case 'p':
			index = 1;
			break;
		case 'x':
			index = 2;
			break;
		case 'v':
			index = 3;
			break;
		case 'w':
			index = 4;
			break;
		case 'y':
			index = 5;
			break;
		case 'i':
			index = 6;
			break;
		case 't':
			index = 7;
			break;
		case 'z':
			index = 8;
			break;
		case 'u':
			index = 9;
			break;
		case 'n':
			index = 10;
			break;
		case 'l':
			index = 11;
			break;
		default:
			index = 0;
		}
		return index;
	}
	
	public static char intToType(int type){
		char index = 0;
		switch (type) {
		case 0:
			index = 'f';
			break;
		case 1:
			index = 'p';
			break;
		case 2:
			index = 'x';
			break;
		case 3:
			index = 'v';
			break;
		case 4:
			index = 'w';
			break;
		case 5:
			index = 'y';
			break;
		case 6:
			index = 'i';
			break;
		case 7:
			index = 't';
			break;
		case 8:
			index = 'z';
			break;
		case 9:
			index = 'u';
			break;
		case 10:
			index = 'n';
			break;
		case 11:
			index = 'l';
			break;
		default:
			index = 0;
		}
		return index;
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
	
	/**
	 * Rotates the pentomino a specified amount of times
	 * @param times
	 */
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
		
		@return amount the amount of shapes the pentomino can take.
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
