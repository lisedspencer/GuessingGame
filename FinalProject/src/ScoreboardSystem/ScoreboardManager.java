package ScoreboardSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Collections.reverseOrder;

public class ScoreboardManager {
    /* Sorts and saves high scores to scoreboard.csv
        Methods:
            sortScores - sorts read scoreboard
            printHighScores - prints top 5 scores
            setNewScore - adds a new score to the scoreboard
            setSortedScoreboard - writes new scoreboard to csv
     */

    //Class Variables
    HashMap<String, Integer> currentScoreboard = new HashMap<>();
    LinkedHashMap<String, Integer> sortedScoreboard = new LinkedHashMap<>();

    //default constructor, fills currentScoreboard
    public ScoreboardManager() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get
                ("FinalProject/src/ScoreboardSystem/scoreboard.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                int nameEnd = line.indexOf(",");
                currentScoreboard.put(line.substring(0, nameEnd), Integer.parseInt(line.substring(nameEnd+1)));
            }
        } catch (FileNotFoundException noFile) {
            System.out.println("No scoreboard file!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //sort scores
    public void sortScoreboard() {
        currentScoreboard.entrySet().stream().sorted(reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(entry -> sortedScoreboard.put(entry.getKey(), entry.getValue()));
    }

    //print top 5
    public void printHighScores() {
        int scoreNum = 1;
        System.out.println("Current High Scores:");
        for (Map.Entry<String, Integer> entry : sortedScoreboard.entrySet()) {
            System.out.println(entry.getKey() + "    " + entry.getValue());
            if (scoreNum == 5) {
                break;
            } else {
                scoreNum++;
            }
        }
    }

    //Getters & Setters
    //record score
    public void setNewScore(String name, Integer score) {
        currentScoreboard.put(name, score);
        sortScoreboard();
        setSortedScoreboard();
    }

    public void setSortedScoreboard() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get
                ("FinalProject/src/ScoreboardSystem/scoreboard.csv"))) {
            for (Map.Entry<String, Integer> entry : sortedScoreboard.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (FileNotFoundException noFile) {
            System.out.println("No scoreboard file found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
