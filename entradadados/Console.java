package entradadados;
import java.util.Scanner;

public class Console {
    private static Scanner scan;

    private static void init(){
        if(scan == null){
            scan = new Scanner(System.in);
        }
    }

    public static String nextLine(){
        init();

        return scan.nextLine();
    }

    public static int nextInt(){
        init();

        try {
            return scan.nextInt();
        } catch (Exception e) {
            System.err.println("Entrada inválida, insira novamente:");
            scan.nextLine();
            return scan.nextInt();
        } finally {
            scan.nextLine();
        }
    }

    public static void close(){
        if(scan != null){
            scan.close();
        }
    }

    public static void cls(){
        System.out.print("\033\143");
    }
}
