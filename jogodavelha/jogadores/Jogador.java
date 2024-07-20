package jogodavelha.jogadores;

import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public interface Jogador {
    public Jogada Jogar(Tabuleiro tabuleiro);

    public String getNome();

    public boolean isAI();
}
