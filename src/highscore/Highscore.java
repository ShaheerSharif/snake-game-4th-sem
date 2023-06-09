package highscore;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;
// import java.nio.*;
// import java.nio.file.Paths;

public final class Highscore {

    private static final String filePath = "src/highscore/highscore.json";
    private static final String key = "highscore";

    /**
     * The function updates the high score if the new score is higher than the
     * current high score.
     * 
     * @param newHighscore an integer representing the new high score that needs to
     *                     be checked and updated if it is higher than the current
     *                     high score.
     */
    public static void updateHighscore(int newHighscore) {
        if (newHighscore > readHighScore()) {
            writeHighScore(newHighscore);
        }
    }

    /**
     * The function resets the high score to zero.
     */
    public static void resetHighScore() {
        writeHighScore(0);
    }

    /**
     * This function reads the high score value from a JSON file and returns it, or
     * returns -1 if there
     * is an error.
     * 
     * @return The method is returning an integer value, which is either the high
     *         score read from a JSON file or -1 if there is an error while reading
     *         the file or if the high score key is not found in the file.
     * @throws JSONException
     */
    public static int readHighScore() {
        try {
            return readJsonFromFile().getInt(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    /**
     * This function creates a new JSON file if one doesn't already exist
     * for storing high scores.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void createHighScoreJSON() {
        try {
            new File(filePath).createNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in createHighScoreJSON()");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function writes a new high score to a JSON file.
     * 
     * @param newHighscore an integer representing the new high score to be written
     *                     to a file.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws JSONException
     */
    private static void writeHighScore(int newHighscore) {
        JSONObject jsonObject;

        createHighScoreJSON();

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            jsonObject = new JSONObject();

            if (jsonObject != null) {
                jsonObject.put(key, newHighscore);
            }

            fileWriter.write(jsonObject.toString());

        } catch (FileNotFoundException e) {
            System.out.println("File not found in writeHighScore()");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function reads a JSON file from a specified file path and returns a
     * JSONObject.
     * 
     * @return The method is returning a JSONObject. If there is an exception, it
     *         will return null.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject readJsonFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        String line;

        createHighScoreJSON();

        try (FileReader fileReader = new FileReader(filePath)) {
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return new JSONObject(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            System.out.println("File not found in readJsonFromFile()");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
