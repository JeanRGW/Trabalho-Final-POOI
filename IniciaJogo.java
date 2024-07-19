
import entradadados.Console;
import jogodavelha.Jogo;

public class IniciaJogo {
    public static void main(String[] args) {
        boolean sair = false;
        boolean singlePlayer = false;

        while(!sair){
            Console.cls();
            System.out.println("1.Iniciar.");
            System.out.println("2.Alternar Jogadores (" + (singlePlayer?"1":"2") + ")");
            System.out.println("3.Sair");

            switch(Console.nextInt()){
                case 1: {
                    Jogo jogo = new Jogo(singlePlayer);
                    jogo.Iniciar();
                } break;
                
                case 2: {
                    singlePlayer = !singlePlayer;
                } break;

                case 3: {
                    sair = true;
                } break;

                default:
            }
        }
    }
}