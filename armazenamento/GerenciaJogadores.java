package armazenamento;

import java.util.List;
import jogodavelha.jogadores.Jogador;

public interface GerenciaJogadores {
    public void add(String nome, int pont);

    public void remove(String nome);

    public List<Jogador> getJogadores();
}
