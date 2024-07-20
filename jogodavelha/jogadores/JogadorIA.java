package jogodavelha.jogadores;

import java.util.Random;
import jogodavelha.Jogada;
import jogodavelha.Pair;
import jogodavelha.Tabuleiro;

public class JogadorIA implements Jogador {
    private String nome;
    private Random RNG;

    public JogadorIA(){
        this.RNG = new Random();

        String primeiroNome[] = {"Arthur", "John", "Jack", "Michael", "Ada", "Red", "Linus", "Alan"};
        String segundoNome[] = {"Morgan", "Marston", "Jackson", "Jordan", "Lovelace", "Harlow", "Torvalds", "Turing"};
       
        this.nome = primeiroNome[RNG.nextInt(primeiroNome.length)] + " " + segundoNome[RNG.nextInt(segundoNome.length)];

        System.out.println(nome);
    }

    public boolean isAI(){
        return true;
    }

    public Jogada Jogar(Tabuleiro tabuleiro, boolean player){
        System.out.println(nome + " está fazendo a jogada (" + player);

        // JogoDaVelha.Jogada Racional / Irracional
        if(RNG.nextInt(4) == 0){
            // Jogada irracional;
            return jogadaAleatoria(tabuleiro, player);
        } else {
            Jogada jogada;

            // Tenta ganhar
            if((jogada = jogadaVitoriosa(player, tabuleiro)) != null){
                return jogada;
            }

            // Impede vitória
            if((jogada = jogadaVitoriosa(!player, tabuleiro)) != null){
                return new Jogada(player, jogada.getX(), jogada.getY());
            }

            // Posição aleatória das restantes
            return jogadaAleatoria(tabuleiro, player);
        }
    }

    private Jogada jogadaAleatoria(Tabuleiro tabuleiro, boolean player) {
        int nPosRestantes = tabuleiro.getPosicoesRestantes().size();

        Pair<Number, Number> pos = tabuleiro.getPosicoesRestantes().get(RNG.nextInt(nPosRestantes));
        return new Jogada(player, pos.x.intValue(), pos.y.intValue());
    }

    public Jogada jogadaVitoriosa(Boolean jogador, Tabuleiro tabuleiro){
        Character player = jogador ? 'x':'o';
        int size = tabuleiro.getSize();

        // Verificar linhas horizontais;
        for(int i=0; i<size; i++){
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for(int j=0; j<size; j++){
                if(tabuleiro.getPos(i, j) == player){
                    contador++;
                }
                    
                if(tabuleiro.getPos(i, j) == ' '){
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
                if(tabuleiro.getPos(i, j) == player){
                    contador++;
                }
                    
                if(tabuleiro.getPos(i, j) == ' '){
                    xVazio = j;
                    yVazio = i;
                }
            }

            if(contador == size-1 && xVazio != -1){
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar a diagonal principal;
        {int contador = 0;
        int xVazio = -1, yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro.getPos(i, i) == player) {
                contador++;
            }
            if (tabuleiro.getPos(i, i) == ' ') {
                xVazio = i;
                yVazio = i;
            }
        }
        if (contador == size-1 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        // Verificar a diagonal secundária
        {int contador = 0;
        int xVazio = -1;
        int yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro.getPos(i, i) == player) {
                contador++;
            }
            if (tabuleiro.getPos(i, i) == ' ') {
                xVazio = i;
                yVazio = size - 1 - i;
            }
        }
        if (contador == size-1 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        return null;
    }


    public String getNome(){
        return nome;
    }
}
