package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static String chooseFirstNumber = "";
    static String chooseSecondNumber = "";

    public static void thinkFirstNumber() {
        Random rand = new Random();
        int computerNum = (rand.nextInt(9999) + 1);
        chooseFirstNumber = Integer.toString(computerNum);
        while (checkForRepeatingNumbers(chooseFirstNumber)) {
            thinkFirstNumber();
        }
    }

    public static void thinkSecondNumber() {
        Random rand = new Random();
        int randomNumber = (rand.nextInt(9999) + 1);
        chooseSecondNumber = Integer.toString(randomNumber);
        while (checkForRepeatingNumbers(chooseSecondNumber)) {
            thinkSecondNumber();
        }
    }

    public static boolean checkForRepeatingNumbers(String num) {
        for (int i = 0; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if (num.charAt(i) == num.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void checkInputNumber(String input) {
        if (input.length() != 4) {
            return;
        }

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("invalid input");
        }
    }


    public static int countBulls(String firstNumber, String secondNumber) {
        int bullCounter = 0;

        for (int i = 0; i < firstNumber.length(); i++) {
            if (firstNumber.charAt(i) == secondNumber.charAt(i)) {
                bullCounter++;
            }
        }
        return bullCounter;
    }

    public static int countCows(String firstNumber, String secondNumber) {
        int cowsCounter = 0;
        for (int i = 0; i < firstNumber.length(); i++) {
            for (int j = 0; j < secondNumber.length(); j++) {
                if (i != j) {
                    if (firstNumber.charAt(i) == secondNumber.charAt(j)) {
                        cowsCounter++;
                    }
                }
            }
        }
        return cowsCounter;
    }

    public static void playWithOnePlayer() {
        Scanner sc = new Scanner(System.in);

        thinkFirstNumber();

        int bulls;
        int cows;
        int guess = 1;
        boolean isFound = true;

        while (isFound) {
            System.out.print("Enter guess number " + guess + ": ");
            String guessedNumber = sc.nextLine();
            bulls = countBulls(guessedNumber, chooseFirstNumber);
            cows = countCows(guessedNumber, chooseFirstNumber);
            if (guess >= 10) {
                System.out.println("Bulls = " + bulls + "  Cows = " + cows);
                System.out.println("Game over!");
                System.out.println("The correct number was: " + chooseFirstNumber);
                isFound = false;
            }
            if (checkForRepeatingNumbers(guessedNumber)) {
                System.out.println("Try again");
                continue;
            }
            if (guessedNumber.length() != 4) {
                checkInputNumber(guessedNumber);
                System.out.println("Try again");
                continue;
            }
            if (bulls == 4) {
                System.out.println("Bulls = " + bulls + "  Cows = " + cows);
                System.out.println("You win!");
                isFound = false;
            }
            if (!checkForRepeatingNumbers(guessedNumber)) {
                System.out.println("Bulls = " + bulls + "  Cows = " + cows);
                guess++;
            }
        }
    }

    public static void playWithTwoPlayers() {
        Scanner input = new Scanner(System.in);
        thinkFirstNumber();
        thinkSecondNumber();
        int bulls;
        int cows;
        int guess1 = 1;
        int guess2 = 1;
        boolean isFound = true;
        while (isFound) {
            System.out.print("player One : " + guess1 + ": ");
            String guessTheNumber1 = input.nextLine();

            bulls = countBulls(guessTheNumber1, chooseFirstNumber);
            cows = countCows(guessTheNumber1, chooseFirstNumber);
            checkInputNumber(guessTheNumber1);

            if (guess1 >= 10) {
                System.out.println("Bulls = " + bulls + "  Cows = " + cows);
                System.out.println("Game Over!");
                System.out.println("The correct number for player one was: " + chooseFirstNumber);
                isFound = false;
            }
            if (checkForRepeatingNumbers(guessTheNumber1)) {
                System.out.println("Try again");
                continue;
            }
            if (guessTheNumber1.length() != 4) {
                System.out.println("Try again");
                continue;
            }
            if (!checkForRepeatingNumbers(guessTheNumber1)) {
                System.out.println("Bulls = " + bulls + "  Cows = " + cows);
                guess1++;
            }
            if (bulls == 4) {

                System.out.println("Player one win! ");
                break;
            }
            int bulls2;
            int cows2;

            System.out.print("player Two " + guess2 + ": ");
            String guessTheNumber2 = input.nextLine();
            checkInputNumber(guessTheNumber2);
            bulls2 = countBulls(guessTheNumber2, chooseSecondNumber);
            cows2 = countCows(guessTheNumber2, chooseSecondNumber);
            if (guess2 >= 10) {
                System.out.println("Bulls = " + bulls2 + "  Cows = " + cows2);
                System.out.println("Game Over!");
                System.out.println("The correct number for player two was: " + chooseSecondNumber);
                isFound = false;
            }
            if (checkForRepeatingNumbers(guessTheNumber2)) {
                System.out.println("Try again");
                continue;
            }
            if (guessTheNumber2.length() != 4) {
                System.out.println("Try again");
                continue;
            }
            if (!checkForRepeatingNumbers(guessTheNumber2)) {
                System.out.println("Bulls = " + bulls2 + "  Cows = " + cows2);
                guess2++;
            }
            if (bulls2 == 4) {
                System.out.println("Player 2 win!");
                break;
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose an option: \n 1-for one player \n 2-for two players ");
        int option = input.nextInt();
        if (option == 1) {
            playWithOnePlayer();
        } else if (option == 2) {
            playWithTwoPlayers();
        } else {
            System.out.println("Invalid input");
        }
    }
}
