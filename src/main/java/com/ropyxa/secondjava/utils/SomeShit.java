package main.java.com.ropyxa.secondjava.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.min;

public class SomeShit {
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalMoves = 0;
    private static String nextMove;
    private static String gameFinalMessage = "\nNobody won";
    private static int[] allMoves = {
            0, 1, 2,
            3, 4, 5,
            6, 7, 8
    };
    private static char[] field = {
            ' ', ' ', ' ',
            ' ', ' ', ' ',
            ' ', ' ', ' '
    };
    private static final int[][] wins = {
            { 0, 1, 2 },
            { 0, 3, 6 },
            { 0, 4, 8 },
            { 3, 4, 5 },
            { 1, 4, 7 },
            { 2, 5, 8 },
            { 2, 4, 6 },
            { 6, 7, 8 }
    };
    private static char tacticalMoves;
    private static char playerShape;
    private static char aiShape;

    private static int parseInput(int inputInt, int cycleInt) {
        if (inputInt < 1) {
            return 0;
        } else {
            int x = switch (cycleInt) {
                case 1 -> 4;
                case 2 -> 99;
                default -> 0;
            };
            inputInt = (inputInt > x) ? 2 : 1;
            return inputInt;
        }
    }

    public static void doShit() {
        int totalPoints = 0, outMessageInt, input;
        String[] questions = {"How many dicks has kiborg mome?", "How many dicks has kiborg father?", "How many dicks has kiborg?"};
        String[] answers = {"Kiborg go fuck", "Bro you're very good", "Bro this very small count, correct count was x > 4", "Bro this very small count, correct count was x > 99"};
        String globalMessage = "Write ONLY numbers";

        System.out.println(globalMessage);
        for (int i = 0; i < min(questions.length, answers.length); i++) {
            System.out.println("\n" + questions[i]);
            while (true) {
                try {
                    input = scanner.nextInt();
                    totalPoints = (parseInput(input, i) == 2) ? ++totalPoints : totalPoints;
                    outMessageInt = (parseInput(input, i) == 0) ? 0 : (parseInput(input, i) == 2) ? 1 : i+parseInput(input, i);
                    System.out.println(answers[outMessageInt]);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(globalMessage);
                    scanner.nextLine();
                }
            }
        }
        System.out.println("\nBro, your total results: " + totalPoints + "/3");
    }

    private static void analyzeFreeMoves() {
        for (int i = 0; i < field.length; i++) {
            allMoves[i] = (field[i] == ' ') ? i : -1;
        }
    }

    private static void analyzeShape(char yourShape) {
        System.out.println("Your shape is: " + yourShape);
        playerShape = yourShape;
        aiShape = (yourShape == 'x') ? 'o' : 'x';
    }

    private static boolean isPlayerNextMove() {
        return nextMove.equals("player");
    }

    private static boolean isAiNextMove() {
        return nextMove.equals("ai");
    }

    private static void playerNextMove() {
        nextMove = "player";
    }

    private static void aiNextMove() {
        nextMove = "ai";
    }

    private static boolean checkMove(int moveId) {
        return allMoves[moveId] != -1;
    }

    private static boolean checkMoves(int[] moveIds, char shape) {
        int localCount = 0;

        for (int i = 0; i < moveIds.length; i++) {
            if (allMoves[moveIds[i]] == -1 && field[moveIds[i]] == shape) {
                localCount++;
            }
        }
        return localCount == moveIds.length;
    }
    
    private static boolean allMovesIsFree() {
        int localCount = 0;

        for (int move : allMoves) {
            if (move != -1) {
                localCount++;
            }
        }
        
        return localCount == 8;
    }

    private static void doMove(int moveId, char shape) {
        allMoves[moveId] = -1;
        field[moveId] = shape;
    }
    
    private static int bestMove() {
        //UPGRADE
        int localOutput = 0;

        if (totalMoves == 0) {
            localOutput = 4;
        }

        else if (totalMoves == 2) {
            if (!checkMove(0)) {
                localOutput = 8;
            } else if (!checkMove(1)) {
                localOutput = 5;
            } else if (!checkMove(2)) {
                localOutput = 6;
            } else if (!checkMove(3)) {
                localOutput = 1;
            } else if (!checkMove(5)) {
                localOutput = 7;
            } else if (!checkMove(6)) {
                localOutput = 2;
            } else if (!checkMove(7)) {
                localOutput = 3;
            } else if (!checkMove(8)) {
                localOutput = 0;
            }

            if (localOutput % 2 == 0) {
                tacticalMoves = '▢';
            } else {
                tacticalMoves = 'x';
            }
        }

        else if (totalMoves == 4) {

        }

        else if (totalMoves == 6) {

        }

        else if (totalMoves == 8) {

        }

        else if (totalMoves == 1) {
            if (!checkMove(1)) {
                localOutput = 8;
            } else if (!checkMove(7)) {
                localOutput = 0;
            } else if (!checkMove(3)) {
                localOutput = 2;
            } else if (!checkMove(5)) {
                localOutput = 6;
            }
        }

        else if (totalMoves == 3) {

        }

        else if (totalMoves == 5) {

        }

        else if (totalMoves == 7) {

        }

        return localOutput;
    }

    private static void outField() {
        int[] localCount = { 0, 0 };

        System.out.print("| ");
        for (int i = 0; i < field.length; i++) {
            if (localCount[0] < 2) {
                if (field[i] == ' ') {
                    System.out.print("_ ");
                } else {
                    System.out.print(field[i] + " ");
                }
                localCount[0]++;
            } else {
                localCount[0] = 0;
                localCount[1]++;
                if (field[i] == ' ') {
                    System.out.println("_ |");
                } else {
                    System.out.println(field[i] + " |");
                }
                if (localCount[1] != 3) {
                    System.out.print("| ");
                }
            }
        }
    }

    private static boolean analyzeWin(char shape) {
        for (int[] win : wins) {
            if (checkMoves(win, shape)) {
                return true;
            }
        }

        return false;
    }

    private static void aiGoes() {
        System.out.println("\nAI do move:");
        playerNextMove();
        analyzeFreeMoves();
        doMove(bestMove(), aiShape);
        totalMoves++;
        outField();
    }

    private static void playerGoes() {
        int input;
        System.out.println("\nChoose your move");
        System.out.print("(free moves:");
        for (int move : allMoves) {
            if (move != -1) {
                System.out.print(" " + (move + 1));
            }
        }
        System.out.println(")");

        while (true) {
            try {
                input = scanner.nextInt();
                if (allMoves[input - 1] == -1) {
                    System.out.println("Only free moves!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Only numbers!");
            }
        }

        System.out.println("\nPlayer do move:");

        totalMoves++;
        aiNextMove();
        doMove(input - 1, playerShape);
        outField();
    }

    public static void shitTicTac(char yourShape) {
        analyzeShape(yourShape);
        System.out.println("Who goes first? Write \"ai\" or \"player\"");
        if (scanner.nextLine().equalsIgnoreCase("ai")) {
            aiGoes();
        } else {
            outField();
            playerGoes();
        }

        while (totalMoves < 9) {
            if (isAiNextMove()) {
                aiGoes();
                if (analyzeWin(aiShape)) {
                    gameFinalMessage = "\nAI win";
                    break;
                }
            } else {
                playerGoes();
                if (analyzeWin(playerShape)) {
                    gameFinalMessage = "\nPlayer win";
                    break;
                }
            }
        }

        System.out.println(gameFinalMessage);
    }
}