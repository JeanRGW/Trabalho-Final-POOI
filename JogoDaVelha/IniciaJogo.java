package jogodavelha;
import entradadados.Entrada;

public class IniciaJogo {
    public static void main(String[] args) {
        boolean qntJogadores = lerQntJogadores();

        Jogo jogo = new Jogo(qntJogadores);
        jogo.Iniciar();
    }

    public static boolean lerQntJogadores(){
        int escolha;

        System.out.println("Escolha a quantidade de jogadores\n1.Um Jogador\n2.Dois Jogadores");
        escolha = Entrada.nextInt();

        while(escolha != 1 && escolha != 2){
            System.out.println("Entrada inválida, insira novamente:");
            escolha = Entrada.nextInt();
        }

        return escolha == 1? true:false;
    }
}