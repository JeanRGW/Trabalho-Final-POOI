package armazenamento;

import java.util.List;
import jogodavelha.jogadores.JogadorHumano;

/**
 * Interface para modelos de gerenciamento de jogadores.
 * Fornece métodos para adicionar, remover e obter jogadores armazenados.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public interface GerenciaJogadores {
    /**
     * Adiciona um jogador ao armazenamento. Se o jogador já existe, suas
     * informações devem atualizadas.
     *
     * @param jogador o jogador humano a ser adicionado ou atualizado
     */
    public void add(JogadorHumano jogador);

    /**
     * Remove um jogador do armazenamento pelo nome.
     *
     * @param nome o nome do jogador a ser removido
     */
    public void remove(String nome);

    /**
     * Retorna a lista de todos os jogadores armazenados.
     *
     * @return uma lista de objetos JogadorHumano representando todos os jogadores
     *         armazenados
     */
    public List<JogadorHumano> getJogadores();
}
