package armazenamento;

import java.util.List;
import jogodavelha.jogadores.JogadorHumano;

public interface GerenciaJogadores {
    public void add(JogadorHumano jogador);

    public void remove(String nome);

    public List<JogadorHumano> getJogadores();
}
