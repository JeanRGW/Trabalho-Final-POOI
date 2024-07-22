package jogodavelha;

/**
 * Classe que representa uma jogada no jogo da velha.
 * Uma jogada é composta por coordenadas (x, y) e um indicador de qual jogador
 * fez a jogada.
 * 
 * @author GuilhermeKT e JeanRGW
 */
public class Jogada {
    private int x;
    private int y;
    private boolean player;

    /**
     * Construtor que inicializa uma nova jogada com as coordenadas e o jogador.
     * 
     * @param player um boolean indicando o jogador que fez a jogada (true para
     *               jogador 1, false para jogador 2)
     * @param x      a coordenada x da jogada
     * @param y      a coordenada y da jogada
     */
    public Jogada(Boolean player, int x, int y) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    /**
     * Retorna o jogador que fez a jogada.
     * 
     * @return um boolean indicando o jogador (true para jogador 1, false para
     *         jogador 2)
     */
    public Boolean getPlayer() {
        return player;
    }

    /**
     * Retorna a coordenada x (linha) da jogada.
     * 
     * @return a coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a coordenada y da jogada.
     * 
     * @return a coordenada y
     */
    public int getY() {
        return y;
    }
}
