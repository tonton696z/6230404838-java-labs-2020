package harinsalai.ratchanon.lab4;

/**
 * This GuessNumberGameV4 program will work like GuessNumberGameV3
 * but add array to correct number while guessing
 * and add new 2 method
 * showGuesses() for pass 'a' to see all guesses that answered
 * showSpecific()for pass 'g' to see a guess on a specific play
 *
 * Author: Ratchanon Harinsalai
 * ID: 623040483-8
 * Sec: 1
 * Date:
 *
 **/

import java.util.Scanner;

public class GuessNumberGameV4 {
    static int correctNum;
    static int minNum = 1;
    static int maxNum = 10;
    static int maxTries = 3;
    static int[] guesses;
    static int numGuesses = 0;

    public static void main(String[] var0) {
        configGame();
        genAnswer();
        playGames();
    }

    //create scanner
    static Scanner scan = new Scanner(System.in);

    static void configGame() {
        //min and max possible answer
        System.out.print("Enter the min and the max value:");
        int num1 = scan.nextInt();
        int num2 = scan.nextInt();
        //check what is a higher number to make maxNum and minNum
        if (num1 > num2){
            minNum = num2;
            maxNum = num1;
        }

        else if (num2 > num1){
            minNum = num1;
            maxNum = num2;
        }

        else {
            minNum = num1;
            maxNum = num2;
        }
        //number of tries
        System.out.print("Enter the number of tries:");
        maxTries = scan.nextInt();
    }
    //create random number
    static void genAnswer() {
        correctNum = minNum + (int) (Math.random() * ((maxNum - minNum) + 1));
    }

    static void playGames() {
        //set size for array
        guesses = new int[maxTries];
        playGame();
    }

    static void playGame() {
        //create boolean for restartable
        boolean countinueplay = true;
        //start for guessing
        boolean wingame = false;

        //Loop
        while (countinueplay == true) {
            int tried = 0;

            while (wingame == false) {
                System.out.print("Please enter a guess (" + minNum + " - " + maxNum + "):");
                int guess = scan.nextInt();
                //out of answer range
                if (guess < minNum || guess > maxNum) {
                    System.out.println("The guess number must be in the range " + minNum + " and " + maxNum);
                }

                else if (guess > minNum || guess < maxNum) {
                    guesses[numGuesses] = guess;
                    numGuesses += 1;
                    //correct answer
                    if (guess == correctNum) {
                        System.out.println("Congratulations ! That's correct");
                        wingame = true;
                    }
                    //incorrect answer
                    else if (guess > correctNum) {
                        tried++;
                        System.out.println("Please type a lower number! Number of remaining tries:" + (maxTries - tried));
                    }

                    else if (guess < correctNum) {
                        tried++;
                        System.out.println("Please type a higher number! Number of remaining tries:" + (maxTries - tried));
                    }
                }
                //out of tries
                if (maxTries == tried) {
                    break;
                }

            }
            boolean exitgame = false;
            //loop for ask if want to continue
            while (exitgame == false) {
                System.out.println("If want to play again type 'y' to continue or 'q' to quit:" +
                        "\nType 'a' to see all your guesses or 'g' to see a guess on a specific play ");
                String restartgame = scan.next().toLowerCase();
                if (restartgame.equals("y")) {
                    wingame = false; //return to false for win a game statement
                    numGuesses = 0;
                    guesses = null; //make array empty
                    genAnswer();
                    playGames();
                }

                else if (restartgame.equals("q")) {
                    countinueplay = false;
                    break;
                }

                else if (restartgame.equals("a")) {
                    showGuesses();
                }

                else if (restartgame.equals("g")) {
                    showSpecific();
                }
            }
        }
    }
        static void showGuesses() {
            for (int i = 0; i < numGuesses; i++) {
                System.out.print(guesses[i] + " ");
            }
            System.out.println("");
        }
        static void showSpecific() {
            System.out.println("Enter which guess in the range (1-" + numGuesses + ")");
            int guessnumber = scan.nextInt();
            System.out.println("Guess number " + guessnumber + " is " + guesses[guessnumber-1]);
        }
    }


