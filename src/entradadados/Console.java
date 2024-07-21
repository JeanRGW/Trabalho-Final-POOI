package entradadados;

import java.util.Scanner;

public class Console {
    private static Scanner scan;

    private static void init() {
        if (scan == null) {
            scan = new Scanner(System.in);
        }
    }

    public static String nextLine() {
        init();

        return scan.nextLine();
    }

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

    public static void close() {
        if (scan != null) {
            scan.close();
        }
    }

    public static void cls() {
        System.out.print("\033\143");
    }

    public static boolean isIntParseable(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
