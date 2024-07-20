
import java.util.List;

import armazenamento.GerenciaJogadoresArquivo;
import entradadados.Console;
import jogodavelha.Jogo;
import jogodavelha.jogadores.Jogador;
import jogodavelha.jogadores.JogadorHumano;
import jogodavelha.jogadores.JogadorIA;

public class IniciaJogo {
    private static Jogador player1 = null, player2 = null;
    private static boolean singlePlayer = true;

    private static List<JogadorHumano> jogadores = GerenciaJogadoresArquivo.read();

    public static void main(String[] args) {
        while(menuInicial()){
            GerenciaJogadoresArquivo.write(jogadores);
        };
    }

    public static boolean menuInicial(){
        Console.cls();
        System.out.println("1.Iniciar.");
        System.out.println("2.Alternar Jogadores (" + (singlePlayer?"1":"2") + ")");
        System.out.println("3.Escolher Jogadores");
        System.out.println("4.Sair");

        switch(Console.nextInt()){
            case 1: {
                while(player1 == null){
                    player1 = escolherJogador();
                }

                while((!singlePlayer && player2 == null) || player2 == player1){
                    player2 = escolherJogador();
                }

                Jogo jogo;

                if(singlePlayer){
                    jogo = new Jogo(player1, new JogadorIA());
                } else {
                    jogo = new Jogo(player1, player2);
                }

                jogo.Iniciar();
                
            } break;
            
            case 2: {
                singlePlayer = !singlePlayer;
            } break;

            case 3: {
                
            } break;

            case 4:
                return false;

            default: {

            } break;
        }

        return true;
    }

    public static Jogador escolherJogador(){
        System.out.println("Escolha o jogador, ou insira 'n' para novo jogador:");
        for(int i=0; i < jogadores.size(); i++){
            System.out.println((i+1) + "." + jogadores.get(i).getNome());
        }

        String x = Console.nextLine();

        if(x.equals("n")){
            JogadorHumano novoJogador = criarJogador();
            jogadores.add(novoJogador);

            return novoJogador;
        }

        try {
            int i = Integer.parseInt(x);
            return jogadores.get(i-1);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e);
            return escolherJogador();
        }
    }

    public static JogadorHumano criarJogador(){
        System.out.print("Insira o nome: ");
        
        return new JogadorHumano(Console.nextLine(), 0);
    }
}