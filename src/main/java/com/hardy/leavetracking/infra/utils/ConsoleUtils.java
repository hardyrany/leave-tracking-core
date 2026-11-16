package com.hardy.leavetracking.infra.utils;

import java.util.Scanner;

/**
 * Classe utilitária para centralizar as operações de entrada e saída na
 * consola.
 * Evita que a classe Main fique poluída com lógica de Scanner e validações.
 */
public class ConsoleUtils {

    /**
     * Instância do Scanner para ler input do utilizador (System.in).
     * É estática para ser partilhada e não criar múltiplos Scanners.
     */

    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma linha de texto introduzida pelo utilizador.
     * 
     * @param prompt A mensagem a mostrar antes de ler.
     * @return A string introduzida, sem espaços em branco nas pontas.
     */
    public static String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Lê um número inteiro introduzido pelo utilizador.
     * Inclui um loop de validação: se o utilizador escrever letras, pede novamente.
     * 
     * @param prompt A mensagem a mostrar.
     * @return O número inteiro lido.
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Lê um número decimal (double) introduzido pelo utilizador.
     * Substitui a vírgula por ponto para evitar erros em teclados portugueses.
     * 
     * @param prompt A mensagem a mostrar.
     * @return O número decimal lido.
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(input);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Imprime uma linha em branco para espaçamento visual.
     */
    public static void printBlankLine() {
        System.out.println();
    }
}
