package jogodavelha.jogadores;

import java.util.Map.Entry;
import java.util.Random;
import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

/**
 * Classe que representa um jogador de inteligência artificial (IA) no jogo da
 * velha.
 * A IA faz jogadas baseadas em uma combinação de estratégias racionais e
 * aleatórias.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class JogadorIA implements Jogador {
    private String nome;
    private Random RNG;

    /**
     * Construtor que inicializa um jogador de IA com um nome gerado aleatoriamente.
     */
    public JogadorIA() {
        this.RNG = new Random();

        String primeiroNome[] = { "Arthur", "John", "Jack", "Michael", "Ada", "Red", "Linus", "Alan" };
        String segundoNome[] = { "Morgan", "Marston", "Jackson", "Jordan", "Lovelace", "Harlow", "Torvalds", "Turing" };

        this.nome = primeiroNome[RNG.nextInt(primeiroNome.length)] + " " + segundoNome[RNG.nextInt(segundoNome.length)];

        System.out.println(nome);
    }

    /**
     * Realiza uma jogada no tabuleiro utilizando uma combinação de estratégias
     * racionais e aleatórias.
     * 
     * @param tabuleiro o tabuleiro atual do jogo
     * @param player    um boolean indicando qual jogador está jogando (true para
     *                  jogador 1, false para jogador 2)
     * @return a jogada realizada pela IA
     */
    public Jogada jogar(Tabuleiro tabuleiro, boolean player) {
        System.out.println(nome + " está fazendo a jogada");

        // JogoDaVelha.Jogada Racional / Irracional
        if (RNG.nextInt(4) == 0) {
            // Jogada irracional;
            return jogadaAleatoria(tabuleiro, player);
        } else {
            Jogada jogada;

            // Tenta ganhar
            if ((jogada = jogadaVitoriosa(player, tabuleiro)) != null) {
                return jogada;
            }

            // Impede vitória
            if ((jogada = jogadaVitoriosa(!player, tabuleiro)) != null) {
                return new Jogada(player, jogada.getX(), jogada.getY());
            }

            // Posição aleatória das restantes
            return jogadaAleatoria(tabuleiro, player);
        }
    }

    /**
     * Realiza uma jogada aleatória em uma das posições restantes no tabuleiro.
     * 
     * @param tabuleiro o tabuleiro atual do jogo
     * @param player    um boolean indicando qual jogador está jogando (true para
     *                  jogador 1, false para jogador 2)
     * @return uma jogada aleatória
     */
    private Jogada jogadaAleatoria(Tabuleiro tabuleiro, boolean player) {
        int nPosRestantes = tabuleiro.getPosicoesRestantes().size();

        Entry<Integer, Integer> pos = tabuleiro.getPosicoesRestantes().get(RNG.nextInt(nPosRestantes));
        return new Jogada(player, pos.getKey(), pos.getValue());
    }

    /**
     * Verifica se há uma jogada vitoriosa disponível para o jogador especificado.
     * 
     * @param jogador   um boolean indicando qual jogador está jogando (true para
     *                  jogador 1, false para jogador 2)
     * @param tabuleiro o tabuleiro atual do jogo
     * @return a jogada vitoriosa, se existir, ou null se não houver jogada
     *         vitoriosa
     */
    public Jogada jogadaVitoriosa(Boolean jogador, Tabuleiro tabuleiro) {
        Character player = jogador ? 'x' : 'o';
        int size = tabuleiro.getSize();

        // Verificar linhas horizontais;
        for (int i = 0; i < size; i++) {
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for (int j = 0; j < size; j++) {
                if (tabuleiro.getPos(i, j) == player) {
                    contador++;
                }

                if (tabuleiro.getPos(i, j) == ' ') {
                    xVazio = i;
                    yVazio = j;
                }
            }

            if (contador == 2 && xVazio != -1) {
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar linhas verticais;
        for (int i = 0; i < size; i++) {
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for (int j = 0; j < size; j++) {
                if (tabuleiro.getPos(i, j) == player) {
                    contador++;
                }

                if (tabuleiro.getPos(i, j) == ' ') {
                    xVazio = j;
                    yVazio = i;
                }
            }

            if (contador == size - 1 && xVazio != -1) {
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar a diagonal principal;
        {
            int contador = 0;
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
            if (contador == size - 1 && xVazio != -1) {
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar a diagonal secundária
        {
            int contador = 0;
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
            if (contador == size - 1 && xVazio != -1) {
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        return null;
    }

    /**
     * Retorna o nome do jogador de IA.
     * 
     * @return o nome do jogador
     */
    public String getNome() {
        return nome;
    }
}
