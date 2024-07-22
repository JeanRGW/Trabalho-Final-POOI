
package jogodavelha.interfaces;

import jogodavelha.InvalidPositionException;
import jogodavelha.Tabuleiro;
import jogodavelha.jogadores.Jogador;

import entradadados.Console;

/**
 * Implementação da interface InterfaceUsuario que interage com o usuário via
 * console.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class InterfaceConsole implements InterfaceUsuario {
    /**
     * Mostra o estado atual do tabuleiro no console.
     * 
     * @param tabuleiro o tabuleiro atual do jogo
     */
    public void mostrarTabuleiro(Tabuleiro tabuleiro) {
        Console.cls();

        int size = tabuleiro.getSize();

        for (int i = 0; i < size; i++) {
            String linha = "";

            for (int j = 0; j < size; j++) {
                Character jogada = tabuleiro.getPos(i, j);
                linha += (jogada == 'o' ? ("\033[33m" + 'o' + "\033[39m")
                        : (jogada == 'x' ? ("\033[31m" + 'x' + "\033[39m") : jogada));
                if (j < size - 1)
                    linha += " | ";
            }

            System.out.println(linha);
            if (i < size - 1)
                System.out.println("- + - + -");
        }
    }

    /**
     * Notifica o jogador de que é a sua vez de jogar.
     * 
     * @param jogador o jogador que será notificado
     */
    public void notificarJogador(Jogador jogador) {
        System.out.println("Vez do jogador: " + jogador.getNome());
    }

    /**
     * Notifica o jogador de que a posição escolhida é inválida.
     * 
     * @param jogador o jogador que será notificado
     * @param e       a exceção lançada devido à posição inválida
     */
    public void notificarPosicaoInvalida(Jogador jogador, InvalidPositionException e) {
        System.out.println("Posição inválida escolhida pelo jogador: " + jogador.getNome());
    }

    /**
     * Mostra o resultado do jogo no console.
     * 
     * @param ganhador o identificador do jogador vencedor, ou 0 se houver empate
     */
    public void mostrarResultado(int ganhador) {
        if (ganhador == 0) {
            System.out.println("A partida terminou em empate");
        } else if (ganhador == 1 || ganhador == 2) {
            System.out.println("Vitória do jogador " + ganhador);
        }

        System.out.println("Insira qualquer coisa para continuar.");
        Console.nextLine();
    }
}
