
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Kareem Horstink
 */
public class HighScore {

    //Keeps working score here
    private final ArrayList<String[]> FINAL_SCORE = new ArrayList<>();

    /**
     * Add the intended score with name into the arrayList
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
     *  Gets the score of a particular person
     * @param position Which person to see the score to obtain
     * @return Returns a string which contains both the score and the persons name
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
        String[] tmp1 = new String[FINAL_SCORE.size()];
        String[] tmp2 = new String[FINAL_SCORE.size()];
        for (int i = 0; i < FINAL_SCORE.size(); i++) {
            tmp1[i] = FINAL_SCORE.get(i)[0];
            tmp2[i] = FINAL_SCORE.get(i)[1];
        }

        String tmp3 ="";
        String tmp4 ="";

        for (int i = 1; i < tmp1.length; i++) {
            for (int j = 0; j < tmp1.length - 1; j++) {
                if (tmp2[j].compareToIgnoreCase(tmp2[i]) < 0) {
                    tmp3 = tmp1[j];
                    tmp4 = tmp2[j];
                    tmp1[j] = tmp1[i];
                    tmp2[j] = tmp2[i];
                    tmp1[i] = tmp3;
                    tmp2[i] = tmp4;
                }
            }
        }

        FINAL_SCORE.clear();

        for (int i = 0; i < tmp1.length; i++) {
            String[] tmp = {tmp2[i], tmp1[i]};
            FINAL_SCORE.add(tmp);
        }
    }

    /**
     * Loads previous scores into the system.
     * Uses the object ReadFile
     */
    public void loadFromFile() {
        //sorts Final List
        sortList();
        
        //Creates a string array to hold all the strings in the textfile
        String[] arrayLines = new String[0];
        try {
            //need to make this relative to where the jar is located
            //creates a read object
            ReadFile file = new ReadFile("c:/Users/Kareem/Google Drive/Project/HighScore.txt");
            //throws away the pointer
            arrayLines = null;
            //set the pointer to location of the array of strings from the text file
            arrayLines = file.OpenFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //clears Final Score
        //so index are correct
        FINAL_SCORE.clear();
        for (int i = 0; i < arrayLines.length; i = i + 2) {
            //creates the string array that is to be added to FINAL_SCORE
            String[] tmp = {arrayLines[i], arrayLines[i + 1]};
            FINAL_SCORE.add(tmp);
        }
        sortList();
    }

    /**
     * Save the current scores into a text file to loaded up later.
     * Uses the object WriteFile
     */
    public void saveToFile() {
        //sorts FINAL_SCORE
        sortList();
        //creates a writer object
        WriteFile writer = new WriteFile("c:/Users/Kareem/Google Drive/Project/HighScore.txt", true);
        try {
            //it has to first clear the text file hence the false in append
            writer.writeToFile(FINAL_SCORE.get(0)[0],false);
            //then we want append the text file with the rest of the data
            writer.writeToFile(FINAL_SCORE.get(0)[1],true);
            for (int i = 1; i < FINAL_SCORE.size(); i++) {
                //debug code; ignore
                System.out.println(FINAL_SCORE.get(i)[0]);
                System.out.println(FINAL_SCORE.get(i)[1]);
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
}
