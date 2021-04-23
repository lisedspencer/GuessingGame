package GuessingGameSystem;

import java.util.Random;
import java.util.Scanner;

public class GuessingGameAlpha {
    public static void main (String[] args) {
        /* --- Guessing Game Alpha ---
            A guessing game which:
                - randomly generates a number from 1-15
                - tracks round score (starts @ 10)
                - tracks total player score
                - takes input Name (First MI Last), DOB (MM/DD/YYYY stored as 3 var) and Guess (between 1-15 incl)
                - Determine if player correct
                    - if correct -> print score
                    - if incorrect -> subtract from score
                        - if score = 0, "You lose."
                        - if score > 0, print whether guess = low/high
                    - if player didn't lose, ask if replay
                        - add round score to total score
                        - new game -> loop
                        - quit -> print total score
         */
        // Variables outside loop: totalScore, playAgain, fullName, dateOfBirth, month, day, year

        // Welcome user, initalize starting score & ensure loop runs at least once
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("----");
        int totalScore = 0;
        boolean playAgain = true;

        // Get user stat inputs name and DOB, split DOB
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your full name (First MI Last): ");
        String fullName = scan.nextLine().trim();

        System.out.println("Great! Please enter your date of birth (MM/DD/YYYY): ");
        String dateOfBirth = scan.nextLine();
        int month = Integer.parseInt(dateOfBirth.substring(0,2));
        int day = Integer.parseInt(dateOfBirth.substring(3,5));
        int year = Integer.parseInt(dateOfBirth.substring(6,10));

        //Spacer for clarity
        System.out.println("\n-----\n");

        // Begin Game
        while(playAgain) {
            // Variables declared within loop: roundScore, randomNumber, guess, stringPlayAgain
            // Initialize round score & empty guess
            int roundScore = 10;
            int guess = 0;

            // Generate random int from 1-15, inclusive
            Random randomizer = new Random();
            int randomNumber = randomizer.nextInt(15) + 1;

            //Ask user for a guess at least once, until guess is correct OR roundScore = 0
            while (roundScore > 0 && guess != randomNumber) {
                // Get user guess for random number
                System.out.println("I'm thinking of a number, 1 to 15...");
                System.out.println("What is my number? ");
                guess = Integer.parseInt(scan.nextLine());

                // Check value 1-15, reprompt if necessary
                while (guess > 15 || guess < 1) {
                    System.out.println("No! My number is between 1 and 15, inclusive.");
                    System.out.println("Try again. What is my number? ");
                    guess = Integer.parseInt(scan.nextLine());
                }


                // Check if guess is correct
                if (guess == randomNumber) {
                    totalScore = totalScore + roundScore;
                    System.out.println("\nYes! You got it!");
                    System.out.println("You scored " + roundScore + " points this round.");
                } else {
                    //decrement roundScore, let user know high/low/lost
                    roundScore--;
                    if (roundScore == 0) {
                        System.out.println("\nOh no! That was your last guess. You lose!");
                    } else if (guess < randomNumber) {
                        System.out.println("\nYou're to low! Try again.");
                    } else if (guess > randomNumber) {
                        System.out.println("\nYou're too high! Try again.");
                    }
                }
            }

            // Check if player would like to quit
            System.out.println("Would you like to play again? Yes or no: ");
            String stringPlayAgain = scan.nextLine();
            if (stringPlayAgain.equalsIgnoreCase("NO")) {
                playAgain = false;
            }
            //Empty space for clarity
            System.out.println();


        }

        //Thank user, print total score
        System.out.println("-----\n");
        if (totalScore > 0) {
            System.out.println("You scored " + totalScore + " points!");
        } else if (totalScore == 0) {
            System.out.println("Better luck next time...");
        }
        System.out.println("Thanks for playing, " + fullName + "!");
    }
}
