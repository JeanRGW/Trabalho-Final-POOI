package jogodavelha;

import entradadados.Console;
import jogodavelha.jogadores.*;

public class Jogo {
    Tabuleiro tabuleiro;
    Jogador jogadores[];

    public Jogo(){
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
    }

    public void Iniciar(Jogador jogador1, Jogador jogador2){
        Jogador jogadores[] = new Jogador[2];
        jogadores[0] = jogador1;
        jogadores[1] = jogador2;

        Console.cls();
        tabuleiro.print(); 
        int turno = 0;
        while(!tabuleiro.encerrado()){
            Jogador jogador = jogadores[turno % 2];
            System.out.println("Vez do jogador: " + jogador.getNome());

            try {
                tabuleiro.add(jogador.Jogar(tabuleiro));
            } catch (Exception x) {
                System.out.println(x);
            }

            Console.cls();
            tabuleiro.print();
            turno++;
        };
    }
}