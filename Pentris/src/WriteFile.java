import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * 
 * @author Kareem Horstink
 */
public class WriteFile
{
	private String path;
	private boolean append = false;
	
	/**
         * Creates the file path when creating the object and see if the user wants to amend
         * or over write over the file
         * @param file_path is the file path of the text file you want to edit
         * @param append_value is a boolean value. True = mean you want to amend. False = means you don't want to amend.
         */
	public WriteFile(String file_path, boolean append_value)
	{
		path = file_path;
		append = append_value;
	}
	
	/**
         * creates the file path when creating the object and assume that we want over write the file
         * @param file_path is the file path of text file you want to edit
         */
	public WriteFile(String file_path)
	{
		path = file_path;
	}
	
        /**
         * Writes to text file based on the constructor
         * @param textLine what string to write
         * @throws IOException throws an error if the file doesnt exist for example
         */
        
	public void writeToFile(String textLine ) throws IOException 
	{
		FileWriter writer = new FileWriter( path , append);
		
		//This PrintWriter ensures that it can write something other then bytes
		PrintWriter print_line = new PrintWriter(writer);
		
		//handles the formatting of the text
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
	}
        
        /**
         * Writes to text file based on what you have parameter
         * @param textLine what string to write 
         * @param append2 sets whether you would want to amend or not
         * @throws IOException throws an error if the file doesnt exist for example
         */
        public void writeToFile(String textLine,boolean append2 ) throws IOException 
	{
                append = append2;
		FileWriter writer = new FileWriter( path , append);
		
		//This PrintWriter ensures that it can write something other then bytes
		PrintWriter print_line = new PrintWriter(writer);
		
		//handles the formatting of the text
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
	}
}