import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author Kareem Horstink
 */
public final class HighScore {
	String[] arrayLines;
    public HighScore() {
        loadFromFile();
    }

    public int getSize(){
    	return arrayLines.length/2;
    }
    //Keeps working score here
    private final ArrayList<String[]> FINAL_SCORE = new ArrayList<String[]>();

    /**
     * Add the intended score with name into the arrayList
     *
     * @param name Name of the person who has gain the score (String)
     * @param score The score of said person (int)
     */
    public void addScore(String name, int score) {
        //sorts FINAL_SCORE
        sortList();

        //creates a StringBuilder object to convert integer to strings
        StringBuilder sb = new StringBuilder();
        sb.append(score);
        //creates a String array with the name and now converted integer
        String[] tmp = {name, sb.toString()};
        //inputs it into the Array List of FINAL_SCORE
        FINAL_SCORE.add(tmp);

        //sorts FINAL_SCORE to ensure proper placements
        sortList();
    }

    /**
     * Gets the score of a particular person
     *
     * @param position Which person to see the score to obtain
     * @return Returns a string which contains both the score and the persons
     * name
     */
    public String[] getScore(int position) {
        //Sorts the array
        sortList();
        //returns a string array which contains the name and the score of index location
        return FINAL_SCORE.get(position);
    }

    /**
     * Sorts the arrayList (currently buggy)
     */
    private void sortList() {
        String[] name = new String[FINAL_SCORE.size()];
        String[] score = new String[FINAL_SCORE.size()];
        for (int i = 0; i < FINAL_SCORE.size(); i++) {

            name[i] = FINAL_SCORE.get(i)[0];
            score[i] = FINAL_SCORE.get(i)[1];
        }
        System.gc();

        String tmp1 = "";

        for (int i = 1; i < name.length; i++) {
            for (int j = 0; j < name.length - 1; j++) {
                if (score[j].compareToIgnoreCase(score[i]) < 0) {

                    tmp1 = name[j];
                    name[j] = name[i];
                    name[i] = tmp1;

                    tmp1 = score[j];
                    score[j] = score[i];
                    score[i] = tmp1;

                }
            }
        }

        FINAL_SCORE.clear();
        System.gc();

        for (int i = 0; i < name.length; i++) {
            String[] tmp = {name[i], score[i]};
            FINAL_SCORE.add(tmp);
        }
    }

    /**
     * Loads previous scores into the system. Uses the object ReadFile
     */
    public void loadFromFile() {
        //sorts Final List
        sortList();

        //Creates a string array to hold all the strings in the textfile
        arrayLines = new String[0];
        try {
            //need to make this relative to where the jar is located
            //creates a read object
            ReadFile file = new ReadFile("HighScores.txt");
            //throws away the pointer
            arrayLines = null;
            //set the pointer to location of the array of strings from the text file
            arrayLines = file.OpenFile();
        } catch (IOException e) {
        	Writer writer = null;

        	try {
        	    writer = new BufferedWriter(new OutputStreamWriter(
        	          new FileOutputStream("HighScores.txt"), "utf-8"));
        	    writer.write("");
        	} catch (IOException ex) {
        	  // report
        	} finally {
        	   try {writer.close();} catch (Exception ex) {}
        	}
            System.out.println(e.getMessage());
        }

        //clears Final Score
        //so index are correct
        //and calls the grabge collectors
        FINAL_SCORE.clear();
        System.gc();
        //System.out.println(arrayLines.length+ " dsadsad");
        for (int i = 0; i < arrayLines.length; i = i + 2) {
            //creates the string array that is to be added to FINAL_SCORE
            //System.out.println(arrayLines[i]);
            //System.out.println(arrayLines[i+1]);
            String[] tmp = {arrayLines[i], arrayLines[i + 1]};
            FINAL_SCORE.add(tmp);
        }
        sortList();
        System.out.println(arrayLines.length+"size");
        System.out.println(arrayLines[0]);
        System.out.println(arrayLines[1]);
    }

    /**
     * Save the current scores into a text file to loaded up later. Uses the
     * object WriteFile
     */
    public void saveToFile() {
        //sorts FINAL_SCORE
        sortList();
        //creates a writer object
        WriteFile writer = new WriteFile("HighScores.txt", true);
        try {
            //it has to first clear the text file hence the false in append
            writer.writeToFile(FINAL_SCORE.get(0)[0], false);
            //then we want append the text file with the rest of the data
            writer.writeToFile(FINAL_SCORE.get(0)[1], true);
            for (int i = 1; i < FINAL_SCORE.size(); i++) {
                //debug code; ignore
                //System.out.println(FINAL_SCORE.get(i)[0]);
                //System.out.println(FINAL_SCORE.get(i)[1]);
                //puts the rest of the data into the text file
                writer.writeToFile(FINAL_SCORE.get(i)[0]);
                writer.writeToFile(FINAL_SCORE.get(i)[1]);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //sorts FINAL_SCORE
        sortList();
    }

    public int scorelength() {
        return FINAL_SCORE.size();
    }
}
