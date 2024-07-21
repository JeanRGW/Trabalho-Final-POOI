package jogodavelha.interfaces;

import jogodavelha.jogadores.Jogador;
import jogodavelha.Tabuleiro;
import jogodavelha.InvalidPositionException;

public interface InterfaceUsuario {
    public void mostrarTabuleiro(Tabuleiro x);

    public void notificarJogador(Jogador x);

    public void notificarPosicaoInvalida(Jogador x, InvalidPositionException e);

    public void mostrarResultado(int ganhador);
}
