package com.alper;

import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions    = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {  {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},};
        printGameBoard(gameBoard);

        while (true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter ur placement *(1-9)* : ");
            int playerPosition = scan.nextInt();
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken, enter a correct position");
                playerPosition = scan.nextInt();
            }
            placePiece(gameBoard,playerPosition,"player");
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                cpuPosition = random.nextInt(9) + 1;
            }
            placePiece(gameBoard,cpuPosition,"cpu");

            printGameBoard(gameBoard);
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
        System.out.println("\n");
        printGameBoard(gameBoard);
    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        }
        else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1 :
                gameBoard[0][0] = symbol;
                break;
            case 2 :
                gameBoard[0][2] = symbol;
                break;
            case 3 :
                gameBoard[0][4] = symbol;
                break;
            case 4 :
                gameBoard[2][0] = symbol;
                break;
            case 5 :
                gameBoard[2][2] = symbol;
                break;
            case 6 :
                gameBoard[2][4] = symbol;
                break;
            case 7 :
                gameBoard[4][0] = symbol;
                break;
            case 8 :
                gameBoard[4][2] = symbol;
                break;
            case 9 :
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List leftCross = Arrays.asList(1,5,9);
        List rightCross = Arrays.asList(3,5,7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(leftCross);
        winningConditions.add(rightCross);

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }
            else if(cpuPositions.containsAll(l)){
                return "Cpu win, you bad!";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9 && !cpuPositions.containsAll(l) && !playerPositions.containsAll(l)){
                return "Draw";
            }
        }
        return "";
    }
}
