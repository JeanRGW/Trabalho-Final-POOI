package jogodavelha;

import entradadados.Console;
import jogodavelha.jogadores.*;

public class Jogo {
    Tabuleiro tabuleiro;
    Jogador jogadores[];

    public Jogo(Jogador jogador1){
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
        
        jogadores[0] = jogador1;
        jogadores[1] = new JogadorIA();
    }

    public Jogo(Jogador jogador1, Jogador jogador2){
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
        
        jogadores[0] = jogador1;
        jogadores[1] = jogador2;
    }

    public int Iniciar(){
        Console.cls();
        tabuleiro.print(); 

        int turno = 0;
        while(!tabuleiro.encerrado()){
            Jogador jogador = jogadores[turno % 2];
            System.out.println("Vez do jogador: " + jogador.getNome());

            try {
                tabuleiro.add(jogador.Jogar(tabuleiro, turno%2==0));
            } catch (Exception x) {
                System.out.println(x);
            }

            tabuleiro.print();
            turno++;
        };

        return 1;
    }
}