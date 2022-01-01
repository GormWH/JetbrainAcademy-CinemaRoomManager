package cinema;

import java.util.Scanner;

public class Cinema {

    public static final Scanner scanner = new Scanner(System.in);
    private final int rows;
    private final int cols;
    private final char[][] seats;
    private final int[][] ticketPrice;

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int cols = scanner.nextInt();
        Cinema cinema = new Cinema(rows, cols);
        cinema.start();
    }

    public Cinema(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seats = new char[rows][cols];
        ticketPrice = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'S';
                if (rows * cols <= 60 || i + 1 <= rows / 2) {
                    ticketPrice[i][j] = 10;
                } else if (i + 1 > rows / 2) {
                    ticketPrice[i][j] = 8;
                }
            }
        }
    }

    public void start() {
        boolean exitSwitch = false;
        while (!exitSwitch) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            String action = scanner.next();
            switch (action) {
                case "1":
                    printSeats();
                    break;
                case "2":
                    buyTicket();
                    break;
                case "3":
                    printStatus();
                    break;
                case "0":
                    exitSwitch = true;
                    break;
                default:
                    break;
            }

        }
    }

    public void printSeats() {
        System.out.println("\nCinema:");
        for (int i = -1; i < rows; i++) {
            if (i == -1) {
                System.out.print("  ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < cols; j++) {
                if (i == -1) {
                    System.out.print(j + 1 + " ");
                } else {
                    System.out.print(seats[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void buyTicket() {
        System.out.println("\nEnter a row number:");
        final int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        final int col = scanner.nextInt();
        if (row < 1 || row > rows || col < 1 || col > rows) {
            System.out.println("Wrong input!");
            buyTicket();
        } else if (seats[row-1][col-1] == 'S') {
            seats[row-1][col-1] = 'B';
            System.out.println("\nTicket price: $" + ticketPrice[row-1][col-1]);
        } else {
            System.out.println("\nThat ticket has already been purchased!");
            buyTicket();
        }
    }

    public void printStatus() {
        int seatsSold = 0;
        int totalIncome = 0;
        int currentIncome = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 'B') {
                    seatsSold++;
                    currentIncome += ticketPrice[i][j];
                }
                totalIncome += ticketPrice[i][j];
            }
        }
        System.out.println();
        System.out.println("Number of purchased tickets: " + seatsSold);
        System.out.printf("Percentage: %.2f%%%n",  seatsSold * 100 / (double) (rows * cols));
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }
}