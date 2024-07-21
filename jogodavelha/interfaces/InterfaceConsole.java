
package jogodavelha.interfaces;

import jogodavelha.InvalidPositionException;
import jogodavelha.Tabuleiro;
import jogodavelha.jogadores.Jogador;

import entradadados.Console;

public class InterfaceConsole implements InterfaceUsuario {
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

    public void notificarJogador(Jogador jogador) {
        System.out.println("Vez do jogador: " + jogador.getNome());
    }

    public void notificarPosicaoInvalida(Jogador jogador, InvalidPositionException e) {
        System.out.println("Posição inválida escolhida pelo jogador: " + jogador.getNome());
    }

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
