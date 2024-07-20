
import java.util.List;
import entradadados.Console;
import jogodavelha.Jogo;
import jogodavelha.jogadores.Jogador;

public class IniciaJogo {
    public static void main(String[] args) {
        Jogador player1, player2;

        boolean sair = false;
        boolean singlePlayer = false;

        player1 = escolherJogador();

        while(!sair){
            Console.cls();
            System.out.println("1.Iniciar.");
            System.out.println("2.Alternar Jogadores (" + (singlePlayer?"1":"2") + ")");
            System.out.println("3.Sair");

            switch(Console.nextInt()){
                case 1: {
                    Jogo jogo = new Jogo();
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

    public static Jogador escolherJogador(List<Jogador> jogadores){
        System.out.println("Escolha o jogador");
        for(int i=0; i < jogadores.size(); i++){
            System.out.println((i+1) + "." + jogadores.get(i).getNome());
        }
    }
}