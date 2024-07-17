package jogodavelha.jogador;

import java.util.Random;

import jogodavelha.Jogada;
import jogodavelha.Pair;
import jogodavelha.Tabuleiro;

public class JogadorIA implements Jogador {
    private String nome;
    private Random RNG;
    private Boolean player;

    public JogadorIA(Boolean player){
        this.player = player;

        RNG = new Random();

        String primeiroNome[] = {"Arthur", "John", "Jack", "Michael", "Ada", "Red", "Linus", "Alan"};
        String segundoNome[] = {"Morgan", "Marston", "Jackson", "Jordan", "Lovelace", "Harlow", "Torvalds", "Turing"};
       
        nome = primeiroNome[RNG.nextInt(primeiroNome.length)] + " " + segundoNome[RNG.nextInt(segundoNome.length)];

        System.out.println(nome);
    }

    public Jogada Jogar(Tabuleiro tabuleiro){
        System.out.println(nome + " está fazendo a jogada");

        // JogoDaVelha.Jogada Racional / Irracional
        if(RNG.nextInt(4) == 0){
            //JogoDaVelha.Jogada Irracional;
            Pair<Number, Number> pos = tabuleiro.getPosicoesRestantes().get(RNG.nextInt(tabuleiro.getPosicoesRestantes().size()));

            return new Jogada(player, pos.x.intValue(), pos.y.intValue());
        } else {
            Jogada jogada;

            // Tenta ganhar
            if((jogada = jogadaVitoriosa(player, tabuleiro.getTabuleiro())) != null){
                return jogada;
            }

            // Impede vitória
            if((jogada = jogadaVitoriosa(!player, tabuleiro.getTabuleiro())) != null){
                return jogada.inverse();
            }

            Pair<Number, Number> pos = tabuleiro.getPosicoesRestantes().get(RNG.nextInt(tabuleiro.getPosicoesRestantes().size()));

            return new Jogada(player, pos.x.intValue(), pos.y.intValue());
        }
    }

    public Jogada jogadaVitoriosa(Boolean jogador, Character[][] tabuleiro){
        Character player = jogador ? 'o':'x';
        int size = tabuleiro.length;

        // Verificar linhas horizontais;
        for(int i=0; i<size; i++){
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for(int j=0; j<size; j++){
                if(tabuleiro[i][j] == player){
                    contador++;
                }
                    
                if(tabuleiro[i][j] == ' '){
                    xVazio = i;
                    yVazio = j;
                }
            }

            if(contador == 2 && xVazio != -1){
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar linhas verticais;
        for(int i=0; i<size; i++){
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for(int j=0; j<size; j++){
                if(tabuleiro[j][i] == player){
                    contador++;
                }
                    
                if(tabuleiro[j][i] == ' '){
                    xVazio = j;
                    yVazio = i;
                }
            }

            if(contador == 2 && xVazio != -1){
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar a diagonal principal;
        {int contador = 0;
        int xVazio = -1, yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro[i][i] == player) {
                contador++;
            }
            if (tabuleiro[i][i] == ' ') {
                xVazio = i;
                yVazio = i;
            }
        }
        if (contador == 2 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        // Verificar a diagonal secundária
        {int contador = 0;
        int xVazio = -1;
        int yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro[i][size - 1 - i] == player) {
                contador++;
            }
            if (tabuleiro[i][size - 1 - i] == ' ') {
                xVazio = i;
                yVazio = size - 1 - i;
            }
        }
        if (contador == 2 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        return null;
    }


    public String getNome(){
        return nome;
    }
}
