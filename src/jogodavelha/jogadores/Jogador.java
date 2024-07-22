package jogodavelha.jogadores;

import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

/**
 * Interface que define as operações básicas para um jogador no jogo da velha.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public interface Jogador {
    /**
     * Realiza uma jogada no tabuleiro.
     * 
     * @param tabuleiro o tabuleiro do jogo
     * @param player    um boolean indicando qual jogador está jogando (true para
     *                  jogador 1, false para jogador 2)
     * @return a jogada realizada pelo jogador
     */
    public Jogada jogar(Tabuleiro tabuleiro, boolean player);

    /**
     * Retorna o nome do jogador.
     * 
     * @return o nome do jogador
     */
    public String getNome();
}
