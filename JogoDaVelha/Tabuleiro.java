package jogodavelha;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * Representa o tabuleiro do jogo da velha.
 * Gerencia as jogadas e condições de vitória.
 * 
 * @author JeanRGW e GuilhermeKT
 * @version 1.0
 */
public class Tabuleiro {
    private Character tabuleiro[][];
    private int size;
    private List<Entry<Integer, Integer>> posicoesRestantes;

    private int[] contadorLinhas;
    private int[] contadorColunas;
    private int contadorDiagonalPrincipal;
    private int contadorDiagonalSecundaria;

    private int ganhador;

    /**
     * Construtor que inicializa o tabuleiro com o tamanho especificado.
     * 
     * @param size o tamanho do tabuleiro
     */
    public Tabuleiro(int size) {
        this.size = size;
        tabuleiro = new Character[size][size];
        posicoesRestantes = new ArrayList<>();
        contadorLinhas = new int[size];
        contadorColunas = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                posicoesRestantes.add(new SimpleEntry<Integer, Integer>(i, j));
                tabuleiro[i][j] = ' ';
            }

            contadorLinhas[i] = 0;
            contadorColunas[i] = 0;
        }

        contadorDiagonalPrincipal = 0;
        contadorDiagonalSecundaria = 0;

        ganhador = 0;
    }

    /**
     * Adiciona uma jogada ao tabuleiro.
     * 
     * @param x a jogada a ser adicionada
     * @throws InvalidPositionException se a posição já estiver ocupada ou for
     *                                  inválida
     */
    public void add(Jogada x) throws InvalidPositionException {
        Character player = x.getPlayer() ? 'x' : 'o';
        Entry<Integer, Integer> coordenada = new SimpleEntry<>(x.getX(), x.getY());

        // Verifica se a coordenada já foi utilizada ou não existe.
        if (!posicoesRestantes.contains(coordenada)) {
            throw new InvalidPositionException(
                    "A posição (" + x.getX() + ", " + x.getY() + ") já está ocupada ou é inválida.");
        }

        tabuleiro[x.getX()][x.getY()] = player;
        posicoesRestantes.remove(coordenada);

        incrementarContadores(x);
        verificaVitoria();
    }

    /**
     * Atualiza os contadores de vitória para linhas, colunas e diagonais após uma
     * jogada.
     * 
     * @param x a jogada que foi adicionada
     */
    private void incrementarContadores(Jogada x) {
        int alteraContador = x.getPlayer() == true ? 1 : -1;
        contadorLinhas[x.getX()] += alteraContador;
        contadorColunas[x.getY()] += alteraContador;

        if (x.getX() == x.getY())
            contadorDiagonalPrincipal += alteraContador;
        if ((x.getX() + x.getY()) == getSize() - 1)
            contadorDiagonalSecundaria += alteraContador;
    }

    /**
     * Verifica se há um vencedor com base nos contadores atualizados.
     */
    private void verificaVitoria() {
        for (int i = 0; i < size; i++) {
            if (contadorLinhas[i] == size || contadorColunas[i] == size) {
                ganhador = 1;
                return;
            }

            if (contadorLinhas[i] == -size || contadorColunas[i] == -size) {
                ganhador = 2;
                return;
            }
        }

        if (contadorDiagonalPrincipal == size || contadorDiagonalSecundaria == size) {
            ganhador = 1;
            return;
        }

        if (contadorDiagonalPrincipal == -size || contadorDiagonalSecundaria == -size) {
            ganhador = 2;
            return;
        }
    }

    /**
     * Verifica se o jogo está encerrado ou se há um ganhador.
     * 
     * @return true se o jogo estiver encerrado, false caso contrário
     */
    public boolean encerrado() {
        return posicoesRestantes.isEmpty() || ganhador != 0;
    }

    /**
     * Retorna o ganhador no estado atual do tabuleiro.
     * 
     * @return o número do player ganhador ou 0 para empate.
     */
    public int getGanhador() {
        return ganhador;
    }

    /**
     * Retorna o tamanho do tabuleiro.
     * 
     * @return o tamanho do tabuleiro
     */
    public int getSize() {
        return size;
    }

    /**
     * Retorna o valor na posição especificada do tabuleiro.
     * 
     * @param x a coordenada x
     * @param y a coordenada y
     * @return o valor na posição especificada ('x', 'o' ou ' ')
     * @throws IndexOutOfBoundsException se as coordenadas estiverem fora dos
     *                                   limites do tabuleiro
     */
    public Character getPos(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || y < 0 || x > size || y > size) {
            throw new IndexOutOfBoundsException("Coordenadas fora dos limites do tabuleiro");
        }
        return tabuleiro[x][y];
    }

    /**
     * Retorna a lista de posições restantes no tabuleiro.
     * 
     * @return a lista de posições restantes
     */
    public List<Entry<Integer, Integer>> getPosicoesRestantes() {
        return posicoesRestantes;
    }
}
