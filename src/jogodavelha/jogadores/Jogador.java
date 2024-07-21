package jogodavelha.jogadores;

import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public interface Jogador {
    public Jogada jogar(Tabuleiro tabuleiro, boolean player);

    public String getNome();
}
