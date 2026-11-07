package com.hardy.leavetracking;

import java.util.Scanner;

/**
 * Classe principal do Leave Tracking System.
 * Responsável por arrancar a aplicação e exibir o menu (esqueleto).
 */
public class LeaveTrackingApplication {

    /**
     * Ponto de entrada da aplicação.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   Welcome to Leave Tracking System   ");
        System.out.println("=========================================");

        while (running) {
            printMenu();
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("Add Employee");
                    break;
                case 2:
                    System.out.println("Submit Leave Request");
                    break;
                case 3:
                    System.out.println("Approve Leave Request");
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Add Employee");
        System.out.println("2. Submit Leave Request");
        System.out.println("3. Approve Leave Request");
        System.out.println("0. Exit");
    }
}
