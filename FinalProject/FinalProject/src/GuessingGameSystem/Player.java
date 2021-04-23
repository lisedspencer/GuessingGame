package GuessingGameSystem;

import ScoreboardSystem.ScoreboardManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Player {
    //Player class: holds player data
    /*
        Methods:
            printTopScores - prints top 5 scores
            correctGuess - checks player guess & decrements score
            setBirthday - records player birthday if not already in file
            setRandomNumber - generates a random target number
            getGuess - prompts player for a guess
     */

    //Class Variables
    public static int totalScore;
    public static int roundScore;
    public static boolean playAgain;
    protected static int randomNumber;
    protected String name;

    //Internal variables
    private boolean isNew = true;
    private Scanner scan = new Scanner(System.in);
    ScoreboardManager scoreboard = new ScoreboardManager();

    //Default Constructor
    public Player() {
    }

    //Constructor that searches for and recognizes pre-existing player
    public Player(String name) {
        this.totalScore = 0;
        this.playAgain = true;
        this.name = name;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get
                ("FinalProject/src/ScoreboardSystem/scoreboard.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(name)) {
                    System.out.println("Welcome back, " + name + "!");
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                System.out.println("Welcome, " + name + "!");
            }

        } catch (FileNotFoundException noFile) {
            System.out.println("Scoreboard file not found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Check players guess against target
    protected boolean correctGuess(int guess) {
        if (guess == randomNumber) {
            totalScore = totalScore + roundScore;
            return true;
        } else {
            roundScore--;
            return false;
        }
    }

    //Getters & Setters
    //get a guess from user
    protected int getGuess() {
        System.out.println("I'm thinking of a number, 1 to 15...");
        System.out.println("What is my number? ");
        return Integer.parseInt(scan.nextLine());
    }

    // Generate random int from 1-15, inclusive
    protected void setRandomNumber() {
        Random randomizer = new Random();
        this.randomNumber = randomizer.nextInt(15) + 1;
    }

    //Setup scoreboard
    protected void setScoreboard(){
        scoreboard.sortScoreboard();
        scoreboard.setSortedScoreboard();
        scoreboard.printHighScores();
    }

    //add new score to scoreboard
    protected void setScore(){
        scoreboard.setNewScore(name, totalScore);
    }
}
