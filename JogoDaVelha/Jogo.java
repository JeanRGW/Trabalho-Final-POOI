package jogodavelha;

import entradadados.Console;
import jogodavelha.jogadores.*;

/**
 * Representa o jogo da velha, gerenciando a interação entre os jogadores e o tabuleiro.
 */
public class Jogo {
    Tabuleiro tabuleiro;
    Jogador jogadores[];

    /**
     * Construtor para iniciar o jogo com um jogador humano e um jogador de IA.
     * 
     * @param jogador1 o jogador humano
     */
    public Jogo(Jogador jogador1){
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
        
        jogadores[0] = jogador1;
        jogadores[1] = new JogadorIA();
    }

    /**
     * Construtor para iniciar o jogo com dois jogadores especificados.
     * 
     * @param jogador1 o primeiro jogador
     * @param jogador2 o segundo jogador
     */
    public Jogo(Jogador jogador1, Jogador jogador2){
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
        
        jogadores[0] = jogador1;
        jogadores[1] = jogador2;
    }

    
    /**
     * Inicia o jogo, alternando entre os jogadores até que o jogo termine.
     * 
     * @return 1 se o jogo terminou com vitória, 2 se terminou em empate, 0 se o jogo está em andamento
     */
    public int iniciar() {
        Console.cls();
        tabuleiro.print(); 

        int turno = 0;
        while (!tabuleiro.encerrado()) {
            Jogador jogadorAtual = jogadores[turno % 2];
            System.out.println("Vez do jogador: " + jogadorAtual.getNome());

            try {
                tabuleiro.add(jogadorAtual.jogar(tabuleiro, turno % 2 == 0));
            } catch (InvalidPositionException e) {
                System.out.println("InvalidPositionException: " + e.getMessage());
                // Reverte o turno em caso de erro para permitir a nova tentativa
                turno--;
                continue;
            }

            tabuleiro.print();
            turno++;
        }

        return tabuleiro.getGanhador();
    }

    public int getGanhador(){
        return tabuleiro.getGanhador();
    }
}