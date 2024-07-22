package jogodavelha.jogadores;

import entradadados.Console;
import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

/**
 * Classe que representa um jogador humano no jogo da velha.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class JogadorHumano implements Jogador {
    private String nome;
    private int pts;

    /**
     * Construtor que inicializa um jogador humano com um nome e pontos iniciais.
     * 
     * @param nome o nome do jogador
     * @param pts  os pontos iniciais do jogador
     */
    public JogadorHumano(String nome, int pts) {
        this.nome = nome;
        this.pts = pts;
    }

    /**
     * Realiza uma jogada no tabuleiro solicitando as coordenadas ao usuário.
     * 
     * @param tabuleiro o tabuleiro atual do jogo
     * @param player    um boolean indicando qual jogador está jogando (true para
     *                  jogador 1, false para jogador 2)
     * @return a jogada realizada pelo jogador
     */
    public Jogada jogar(Tabuleiro tabuleiro, boolean player) {
        System.out.println("Insira a linha: ");
        int x = (Console.nextInt() - 1);
        System.out.println("Insira a coluna: ");
        int y = (Console.nextInt() - 1);

        while (x < 0 || y < 0 || x >= tabuleiro.getSize() || y >= tabuleiro.getSize()
                || tabuleiro.getPos(x, y) != ' ') {
            System.out.println("Coordenada invadida, inserindo novamente.");
            System.out.println("Insira a linha: ");
            x = Console.nextInt() - 1;
            System.out.println("Insira a coluna: ");
            y = Console.nextInt() - 1;
        }

        return new Jogada(player, x, y);
    }

    /**
     * Retorna o nome do jogador.
     * 
     * @return o nome do jogador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna os pontos do jogador.
     * 
     * @return os pontos do jogador
     */
    public int getPontos() {
        return pts;
    }

    /**
     * Adiciona um ponto ao total de pontos do jogador.
     */
    public void addPonto() {
        pts++;
    }

    /**
     * Compara este jogador com outro objeto para verificar igualdade.
     * 
     * @param o o objeto a ser comparado
     * @return true se os jogadores tiverem o mesmo nome, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == getClass()) {
            Jogador obj = (Jogador) o;
            if (obj.getNome() == getNome()) {
                return true;
            }
        }

        return false;
    }
}
