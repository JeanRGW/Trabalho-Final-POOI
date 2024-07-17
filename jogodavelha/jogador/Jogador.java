package jogodavelha.jogador;

import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public interface Jogador {
    public Jogada Jogar(Tabuleiro tabuleiro);

    public String getNome();
}
