package jogodavelha;

import jogodavelha.jogadores.*;
import jogodavelha.interfaces.InterfaceUsuario;

/**
 * Representa o jogo da velha, gerenciando a interação entre os jogadores e o
 * tabuleiro.
 * 
 * A classe é responsável por inicializar o jogo, alternar turnos entre
 * jogadores,
 * e interagir com a interface do usuário. Ela também determina o resultado do
 * jogo.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class Jogo {
    InterfaceUsuario uInterface;
    Tabuleiro tabuleiro;
    Jogador jogadores[];

    /**
     * Construtor para iniciar o jogo com um jogador e uma IA.
     * 
     * @param jogador1   o jogador humano que participará do jogo
     * @param uInterface a interface do usuário para interação com o jogo
     */
    public Jogo(Jogador jogador1, InterfaceUsuario uInterface) {
        this.uInterface = uInterface;
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];

        jogadores[0] = jogador1;
        jogadores[1] = new JogadorIA();
    }

    /**
     * Construtor para iniciar o jogo com dois jogadores especificados.
     * 
     * @param jogador1   o primeiro jogador
     * @param jogador2   o segundo jogador
     * @param uInterface a interface do usuário para interação com o jogo
     */
    public Jogo(Jogador jogador1, Jogador jogador2, InterfaceUsuario uInterface) {
        this.uInterface = uInterface;
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];

        jogadores[0] = jogador1;
        jogadores[1] = jogador2;
    }

    /**
     * Inicia o jogo, alternando entre os jogadores e chamando a interface até que o
     * jogo termine.
     * 
     * O método alterna os turnos entre os jogadores, atualiza o tabuleiro, e lida
     * com exceções relacionadas a posições inválidas. Ao final, exibe o resultado
     * do
     * jogo e retorna o ganhador.
     * 
     * @return 1 ou 2 para indicar o jogador vitorioso, 0 para empate
     */
    public int iniciar() {
        uInterface.mostrarTabuleiro(tabuleiro);

        int turno = 0;
        while (!tabuleiro.encerrado()) {
            Jogador jogadorAtual = jogadores[turno % 2];
            uInterface.notificarJogador(jogadorAtual);

            try {
                tabuleiro.add(jogadorAtual.jogar(tabuleiro, turno % 2 == 0));
            } catch (InvalidPositionException e) {
                uInterface.notificarPosicaoInvalida(jogadorAtual, e);

                // Reverte o turno em caso de erro para permitir a nova tentativa
                turno--;
                continue;
            }

            uInterface.mostrarTabuleiro(tabuleiro);
            turno++;
        }

        uInterface.mostrarResultado(getGanhador());
        return tabuleiro.getGanhador();
    }

    /**
     * Retorna o ganhador no estado atual do tabuleiro.
     * 
     * @return 1 ou 2 para indicar o jogador vitorioso, 0 para empate
     */
    public int getGanhador() {
        return tabuleiro.getGanhador();
    }
}