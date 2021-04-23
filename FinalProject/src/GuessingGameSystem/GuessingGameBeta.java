package GuessingGameSystem;

import ScoreboardSystem.ScoreboardManager;

import java.util.Scanner;

public class GuessingGameBeta {
    public static void main(String[] args) {
        /*
        Guessing Game
            Has classes:
                Player
                ScoreboardManager

        GuessingGameBeta contains Interface
         */

        System.out.println("Welcome to the Guessing Game!");

        //print current top 5
        ScoreboardManager scoreboard = new ScoreboardManager();
        scoreboard.sortScoreboard();
        scoreboard.setSortedScoreboard();
        scoreboard.printHighScores();


        //Get users nickname for scoreboard, greet
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter your nickname: ");
        Player player = new Player(scan.nextLine().trim().toUpperCase());

        //Spacer for clarity
        System.out.println("\n-----\n");

        //Play rounds until player no longer wishes to continue
        while (player.playAgain) {
            // Variables declared within loop: roundScore, randomNumber, guess
            // Initialize round score & empty guess
            player.roundScore = 10;
            int guess = 0;

            //Generate target number
            player.setRandomNumber();

            //Ask user for a guess at least once, until guess is correct OR roundScore = 0
            while (player.roundScore > 0 && guess != Player.randomNumber) {
                // Get user guess for random number
                guess = player.getGuess();

                // Check value 1-15, reprompt if necessary
                while (guess > 15 || guess < 1) {
                    //Penalty for out-of bounds guess, results in a deduction of -2 after guess check
                    player.roundScore--;
                    System.out.println("No! My number is between 1 and 15, inclusive." +
                            " Try again.");
                    guess = player.getGuess();
                }

                // Check if guess is correct
                if (player.correctGuess(guess)) {
                    System.out.println("\nYes! You got it!");
                    System.out.println("You scored " + player.roundScore + " points this round.");
                } else {
                    //let user know high/low/lost
                    if (player.roundScore <= 0) {
                        System.out.println("\nOh no! That was your last guess. You lose!");
                    } else if (guess < player.randomNumber) {
                        System.out.println("\nYou're too low! Try again.");
                    } else if (guess > player.randomNumber) {
                        System.out.println("\nYou're too high! Try again.");
                    }
                }
            }

            // Check if player would like to quit
            System.out.println("Would you like to play again? Yes or no: ");
            String stringPlayAgain = scan.nextLine();
            if (stringPlayAgain.equalsIgnoreCase("NO")) {
                player.playAgain = false;
            }

        }

        System.out.println("-----\n");

        //Thank user, print total score
        if (player.totalScore > 0) {
            System.out.println("You scored " + player.totalScore + " points!");
        } else if (player.totalScore == 0) {
            System.out.println("Better luck next time...");
        }

        //record player to scoreboard, print new top 5
        player.setScore();

        System.out.println("Thanks for playing, " + player.name + "!");
    }
}
