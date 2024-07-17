package JogoDaVelha;

import java.util.Random;

public class JogadorIA implements Jogador {
    private String nome;
    private Random generator;
    private Boolean player;

    JogadorIA(Boolean player){
        this.player = player;

        generator = new Random();

        String primeiroNome[] = {"Arthur", "John", "Jack", "Michael", "Ada", "Red", "Linus", "Alan"};
        String segundoNome[] = {"Morgan", "Marston", "Jackson", "Jordan", "Lovelace", "Harlow", "Torvalds", "Turing"};
       
        nome = primeiroNome[generator.nextInt(primeiroNome.length)] + " " + segundoNome[generator.nextInt(segundoNome.length)];

        System.out.println(nome);
    }

    public Jogada Jogar(Tabuleiro tabuleiro){
        // JogoDaVelha.Jogada Racional / Irracional
        if(generator.nextInt(4) == 0){
            //JogoDaVelha.Jogada Irracional;
            return new Jogada(player, 0, 0);
        } else {
            Jogada jogada;

            // Tenta ganhar
            if((jogada = tabuleiro.jogadaVitoriosa(player)) != null){
                return jogada;
            }

            // Impede vitória
            if((jogada = tabuleiro.jogadaVitoriosa(!player)) != null){
                return jogada.inverse();
            }


            return tabuleiro.jogadaAleatoria(player);
        }
    }

    public String getNome(){
        return nome;
    }
}
