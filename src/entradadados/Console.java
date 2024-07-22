package entradadados;

import java.util.Scanner;

/**
 * Classe utilitária para operações de entrada de dados via console.
 * 
 * @author JeanRGW
 * @version 1.0
 */
public class Console {
    private static Scanner scan;

    /**
     * Inicializa o scanner se ele ainda não foi inicializado.
     */
    private static void init() {
        if (scan == null) {
            scan = new Scanner(System.in);
        }
    }

    /**
     * Lê uma linha de texto do console.
     * 
     * @return a linha de texto lida
     */
    public static String nextLine() {
        init();

        return scan.nextLine();
    }

    /**
     * Lê um inteiro do console, garantindo que a entrada seja válida.
     * Se a entrada não for um inteiro, solicita a entrada novamente.
     * 
     * @return o número inteiro lido
     */
    public static int nextInt() {
        init();

        while (!scan.hasNextInt()) {
            System.out.println("Entrada inválida, insira novamente");
            scan.next();
        }

        int x = scan.nextInt();
        scan.nextLine();

        return x;
    }

    /**
     * Fecha o scanner se ele foi inicializado.
     */
    public static void close() {
        if (scan != null) {
            scan.close();
        }
    }

    /**
     * Limpa a tela do console.
     * Funciona apenas em terminais que suportam o código ANSI.
     */
    public static void cls() {
        System.out.print("\033\143");
    }

    /**
     * Verifica se uma string pode ser convertida em um número inteiro.
     * 
     * @param x a string a ser verificada
     * @return true se a string pode ser convertida em um inteiro, false caso
     *         contrário
     */
    public static boolean isIntParseable(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
