package jogodavelha.jogadores;

import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public interface Jogador {
    public Jogada Jogar(Tabuleiro tabuleiro, boolean player);

    public String getNome();

    public boolean isAI();
}
