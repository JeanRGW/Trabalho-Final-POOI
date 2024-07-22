package armazenamento;

import java.util.ArrayList;
import java.util.List;

import jogodavelha.jogadores.JogadorHumano;

/**
 * Implementação da interface GerenciaJogadores que utiliza um ArrayList para
 * armazenar e gerenciar os dados dos jogadores.
 * (Dados não persistentes entre sessões)
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class GerenciaJogadoresArrayList implements GerenciaJogadores {
    ArrayList<JogadorHumano> list;

    /**
     * Construtor que inicializa a lista de jogadores como um ArrayList vazio.
     */
    GerenciaJogadoresArrayList() {
        this.list = new ArrayList<>();
    }

    /**
     * Construtor que inicializa a lista de jogadores com uma lista fornecida.
     * 
     * @param list a lista de jogadores a ser utilizada para inicializar o
     *             armazenamento
     */
    GerenciaJogadoresArrayList(List<JogadorHumano> list) {
        this.list = new ArrayList<>(list);
    };

    /**
     * Remove um jogador do armazenamento pelo nome.
     * 
     * @param nome o nome do jogador a ser removido
     */
    public void remove(String nome) {
        for (JogadorHumano x : list) {
            if (x.getNome().equals(nome)) {
                list.remove(list.indexOf(x));
            }
        }
    }

    /**
     * Adiciona um jogador ao armazenamento ou atualiza as informações se o jogador
     * já existir.
     * 
     * @param x o jogador humano a ser adicionado ou atualizado
     */
    public void add(JogadorHumano x) {
        if (list.contains(x)) {
            list.set(list.indexOf(x), x);
        } else {
            list.add(x);
        }
    }

    /**
     * Retorna a lista de todos os jogadores armazenados.
     * 
     * @return uma lista de objetos JogadorHumano representando todos os jogadores
     *         armazenados
     */
    public List<JogadorHumano> getJogadores() {
        return list;
    }
}
