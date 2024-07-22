package jogodavelha.interfaces;

import jogodavelha.jogadores.Jogador;
import jogodavelha.Tabuleiro;
import jogodavelha.InvalidPositionException;

/**
 * Interface para a interação do jogo com o usuário.
 * Define métodos para exibir o tabuleiro, notificar jogadores e mostrar
 * resultados.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public interface InterfaceUsuario {

    /**
     * Mostra o estado atual do tabuleiro para o usuário.
     * 
     * @param x o tabuleiro do jogo
     */
    public void mostrarTabuleiro(Tabuleiro x);

    /**
     * Notifica o jogador de que é a sua vez de jogar.
     * 
     * @param x o jogador que será notificado
     */
    public void notificarJogador(Jogador x);

    /**
     * Notifica o jogador de que a posição escolhida é inválida.
     * 
     * @param x o jogador que será notificado
     * @param e a exceção lançada devido à posição inválida
     */
    public void notificarPosicaoInvalida(Jogador x, InvalidPositionException e);

    /**
     * Mostra o resultado do jogo.
     * 
     * @param ganhador o identificador do jogador vencedor, ou 0 se houver empate
     */
    public void mostrarResultado(int ganhador);
}
