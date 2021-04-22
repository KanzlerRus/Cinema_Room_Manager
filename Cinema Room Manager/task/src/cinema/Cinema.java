package cinema;

import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int cols;
    private static int row;
    private static int col;
    private static String[][] cinemaHall;
    private static int totalPrice;
    private static int maxPrice;
    private static int ticketCounter;

    public static void main(String[] args) {
        cinemaSize();
        initCinemaHall(rows, cols);
        maxPrice();
        dialog();
    }

    private static void dialog() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int answer = scanner.nextInt();

        switch (answer) {
            case 1:
                System.out.println();
                printCinemaHall();
                dialog();
                break;
            case 2:
                System.out.println();
                buyTicket();
                ticketPrice(row);
                dialog();
                break;
            case 3:
                System.out.println();
                statistics();
                dialog();
                break;
            default:
                break;
        }
    }

    private static void statistics() {
        System.out.printf("Number of purchased tickets: %d\n",ticketCounter);
        System.out.println(totalPrice);
        System.out.println(rows * cols);
        System.out.printf("Percentage: %.2f%%\n",1.0 * 100 * ticketCounter/(rows * cols));
        System.out.printf("Current income: $%d\n",totalPrice);
        System.out.printf("Total income: $%d\n",maxPrice);
    }


    private static void printCinemaHall() {
        System.out.println("Cinema:");
        for (String[] el : cinemaHall) {
            for (String ell : el) {
                System.out.print(ell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void buyTicket() {
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            col = scanner.nextInt();
            if (row > rows || row < 0 || col > cols || col < 0) {
                System.out.println("Wrong input");
                System.out.println();
            } else if (cinemaHall[row][col].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                cinemaHall[row][col] = "B";
                break;
            }
        }
    }


    private static void ticketPrice(int row) {
        int size = rows * cols;
        int price;
        if (size <= 60) {
            price = 10;
        } else if (rows / 2 >= row) {
            price = 10;
        } else {
            price = 8;
        }
        totalPrice += price;
        ticketCounter++;
        System.out.printf("Ticket price: $%d\n\n", price);
    }

    private static void cinemaSize() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();
        System.out.println();
    }

    private static void initCinemaHall(int rows, int cols) {
        cinemaHall = new String[rows + 1][cols + 1];

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if (i == 0) {
                    if (j == 0) {
                        cinemaHall[i][j] = " ";
                    } else {
                        cinemaHall[i][j] = String.valueOf(j);
                    }
                } else {
                    if (j == 0) {
                        cinemaHall[i][j] = String.valueOf(i);
                    } else {
                        cinemaHall[i][j] = "S";
                    }
                }
            }
        }
    }

    private static void maxPrice() {
        int size = rows * cols;
        if (size <= 60) {
            maxPrice = size * 10;
        } else {
            maxPrice = (rows/2 * 10 + (rows - rows/2) * 8 ) * cols;
        }
    }
}